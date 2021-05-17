package controllers.student;

import model.Student;
import utils.DataSource;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utils.EmailUtils;
import utils.UserSession;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import static utils.CredentialsProvider.getCredentials;

public class BookAppointement implements Initializable {

    @FXML
    private JFXButton cancel;
    @FXML
    private JFXDatePicker start_date;
    @FXML
    private JFXTimePicker start_time;

    @FXML
    private JFXTimePicker end_time;
    @FXML
    private CheckBox agree_box;


    private static final String APPLICATION_NAME = "Google Calendar API Java Quickstart";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        DeleteAppointement();
    }

    public void DeleteAppointement() {
        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT;
        // Initialize Calendar service with valid OAuth credentials
        Calendar service = null;
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                    .setApplicationName(APPLICATION_NAME)
                    .build();
        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
        }
        DateTime noww = new DateTime(System.currentTimeMillis());
        Events e = null;
        try {
            e = service.events().list("primary")
                    .setMaxResults(20)
                    .setTimeMax(noww)
                    .setOrderBy("startTime")
                    .setSingleEvents(true)
                    .execute();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        List<Event> expired_items = e.getItems();
        if (expired_items.isEmpty()) {
            System.out.println("No Expired Appointements.");
        } else {
            System.out.println("Expired Appointements");
            for (Event even : expired_items) {
                DateTime expired_start = even.getEnd().getDateTime();
                if (expired_start == null) {
                    expired_start = even.getEnd().getDate();
                }
                try {
                    service.events().delete("primary", even.getId()).execute();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }

    }

    public void CreateMeetLink(Event event){
        ConferenceSolutionKey conferenceSKey = new ConferenceSolutionKey();
        conferenceSKey.setType("hangoutsMeet"); // Non-G suite user
        CreateConferenceRequest createConferenceReq = new CreateConferenceRequest();
        createConferenceReq.setRequestId("cG5vZWRrb2dhc3ZjdWRkZWVzNm9ib3JtaW9fMjAyMTAzMjRUMTYwMDAwWiBsYWFqaWxpLmFobGVtMEBt"); // ID generated by you
        createConferenceReq.setConferenceSolutionKey(conferenceSKey);
        ConferenceData conferenceData = new ConferenceData();
        conferenceData.setCreateRequest(createConferenceReq);
        event.setConferenceData(conferenceData);

    }

    public void CreateAppointement(DateTime start_reservation, DateTime end_reservation, Timestamp start_date) throws IOException, GeneralSecurityException, SQLException {
        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

        // Initialize Calendar service with valid OAuth credentials
        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();

        //Read all the list of events from google calendar
        DateTime now = new DateTime(0);
        Events events = service.events().list("primary")
                .setMaxResults(20)
                .setTimeMin(now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();

        List<Event> items = events.getItems();
        if (items.isEmpty()) {
            System.out.println("No upcoming events found.");
        } else {
            System.out.println("Upcoming events");
            for (Event event : items) {
                DateTime start = event.getStart().getDateTime();
                if (start == null) {
                    start = event.getStart().getDate();
                }
                System.out.printf("%s (%s)\n", event.getSummary(), start);
            }
        }

        //CRUD CREATE
        Event event_1 = new Event()
                .setSummary("Appointement with tutor")
                .setDescription("Appointement with tutor");


        // starts on
        EventDateTime start = new EventDateTime()
                .setDateTime(start_reservation);
        event_1.setStart(start);

        //ends on
        EventDateTime end = new EventDateTime()
                .setDateTime(end_reservation);
        event_1.setEnd(end);


        String s1 = "laajili.ahlem0@gmail.com";
        String s2 = "ahlem.laajili@esprit.tn";
        EventAttendee[] attendees = new EventAttendee[]{
                new EventAttendee().setEmail(s1),
                new EventAttendee().setEmail(s2),
        };

        event_1.setAttendees(Arrays.asList(attendees));


        EventReminder[] reminderOverrides = new EventReminder[]{
                new EventReminder().setMethod("email").setMinutes(24 * 60),
                new EventReminder().setMethod("popup").setMinutes(10),
        };


        Event.Reminders reminders = new Event.Reminders()
                .setUseDefault(false)
                .setOverrides(Arrays.asList(reminderOverrides));
        event_1.setReminders(reminders);

        CreateMeetLink(event_1);



        String calendarId = "primary";
        //Convert startDateTime and endDateTime to ISO 8601 date
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        String startdate = df.format(start_reservation.getValue());

        String enddate = df.format(end_reservation.getValue());

        boolean existe = false;
        for (Event e : items) {
            if (e.getStart().getDateTime().toString().equals(startdate)) {
                existe = true;
            }
        }

        System.out.println(existe);
        if (existe == false) {
            event_1 = service.events().insert(calendarId, event_1).setConferenceDataVersion(1).execute();
            System.out.printf("Event created: %s\n", event_1.getHtmlLink());
            event_1 = service.events().patch(calendarId, event_1.getId(), event_1).setConferenceDataVersion(1).execute();
            System.out.println("meet link" + event_1.getHangoutLink());
            String url="fxml/student/addCustomer.fxml";
            showAlert(url);
            Connection conn = DataSource.getInstance().getCnx();
            PreparedStatement pst;
         //   Date a=new Date("","","","","","")
           // Date meet_dateutil =new Date(start_date.,start_date.getValue().getMonth().getValue(),start_date.getValue().getDayOfWeek().getValue(),start_time.getValue().getHour(),start_time.getValue().getMinute(),start_time.getValue().getSecond());
            String req = "insert into googlemeet (meet_link,meet_date,tutorid_id,studentid_id) values (?,?,?,?)";
            pst = conn.prepareStatement(req);
            pst.setString(1, event_1.getHangoutLink());
            java.sql.Date meet_date=new java.sql.Date(start_date.getTime());
          //  java.sql.Date meeting=new java.sql.Date(start_date.getValue().getYear(),start_date.getValue().getMonth().getValue(),start_date.getValue().getDayOfWeek().getValue());
            pst.setTimestamp(2,start_date);
            pst.setInt(3,1);
            pst.setInt(4,1);
            pst.executeUpdate();
            Student s =new Student(1,"ahlem","ahlem.laajili@esprit.tn");
            Student session= UserSession.getInstance(s).getU();
            EmailUtils.sendMail(session.getEmail(),event_1.getHangoutLink());


            String reqres = "insert into reservation (tutorid_id,studentid_id,reservation_date) values (?,?,?)";
            pst = conn.prepareStatement(reqres);
            pst.setInt(1,1);
            pst.setInt(2,1);
            pst.setTimestamp(3,start_date);
            pst.executeUpdate();
        } else {
           String url="fxml/InvalidAlert.fxml";
            showAlert(url);
        }



    }


    public void btnCancel() {
        cancel.setOnAction(e -> {
            Platform.exit();
        });
    }

    public void showAlert(String url) throws IOException {
        Parent pane = FXMLLoader.load(this.getClass().getClassLoader().getResource(url.toString()));
        Stage stage = new Stage();
        stage.setScene(new Scene(pane));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
    public void btnBook() throws GeneralSecurityException, IOException, SQLException {
        LocalDate d1 = LocalDate.now();
        LocalTime t1 = LocalTime.now();
        if (start_time.getValue() == null) {
            alertBox("Please select a start time!");
        }
        else if (start_date.getValue().isBefore(d1)) {
            alertBox("Please select a valid date");
        } else if (end_time.getValue() == null) {
            alertBox("Please select an end time!");
        } else if (!agree_box.isSelected()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Cannot proceed Booking!");
            alert.setContentText("You must agree on terms!");
            alert.showAndWait();

        }else {
            //Get and Convert value form datepicker and timepicker START
            LocalDate cs = start_date.getValue();
            System.out.println(cs.getMonth().getValue());
            System.out.println(cs.getDayOfWeek().getValue());
            System.out.println(cs.getYear());
            LocalTime ts = start_time.getValue();
            LocalDateTime check_startDate = LocalDateTime.of(cs.getYear(), cs.getMonth(), cs.getDayOfMonth(), ts.getHour(), ts.getMinute(), ts.getSecond());
            Timestamp reservation_startDate = Timestamp.valueOf(check_startDate);
            Date start_date = reservation_startDate;
             System.out.println(start_date);
            //Variable startdate
            DateTime start_reservation = new DateTime(start_date);

            //Get and Convert value form datepicker and timepicker END
            LocalTime te = end_time.getValue();
            LocalDateTime check_endDate = LocalDateTime.of(cs.getYear(), cs.getMonth(), cs.getDayOfMonth(), te.getHour(), te.getMinute(), te.getSecond());
            Timestamp reservation_endDate = Timestamp.valueOf(check_endDate);
            Date end_date = reservation_endDate;

            //Variable starttime
            DateTime end_reservation = new DateTime(end_date);
            CreateAppointement(start_reservation,end_reservation,reservation_startDate);
        }

    }
    public void alertBox(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Cannot proceed Booking!");
        alert.setContentText(text);
        alert.showAndWait();

    }
}