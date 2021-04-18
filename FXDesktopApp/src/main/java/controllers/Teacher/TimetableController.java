package controllers.Teacher;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import controllers.Student.CredentialsProvider;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import static controllers.Student.CredentialsProvider.getCredentials;

public class TimetableController implements Initializable {


    private CalendarView calendar;

    @FXML
    private GridPane pnlHost;

    private static final String APPLICATION_NAME = "TeachMeApp";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    CredentialsProvider cred = new CredentialsProvider();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadCalendar();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            loadGoogleCalendar();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<Event> loadGoogleCalendar() throws GeneralSecurityException, IOException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

        // Initialize Calendar service with valid OAuth credentials
        com.google.api.services.calendar.Calendar service = new com.google.api.services.calendar.Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
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

    return items;
    }

    private void loadCalendar() throws GeneralSecurityException, IOException {

        calendar = new CalendarView();
        Calendar classes = new Calendar("Classes");
        Calendar meetings = new Calendar("Meetings");
        classes.setStyle(Calendar.Style.STYLE7);
        meetings.setStyle(Calendar.Style.STYLE2);
        CalendarSource myCalendarSource = new CalendarSource("Timetable");
        myCalendarSource.getCalendars().addAll(classes, meetings);
        calendar.getCalendarSources().addAll(myCalendarSource);
        calendar.setRequestedTime(LocalTime.now());
        //Gets list from google calendar with all the booked Appointements
        List <Event> items=loadGoogleCalendar();
        for (int i=0; i< items.size();i++){
            Date date = new Date(items.get(i).getStart().getDateTime().getValue());
            Timestamp ts=new Timestamp(date.getTime());
            LocalDateTime calend= ts.toLocalDateTime();
            Entry<String> dentistAppointment = new Entry<>("Dentist");
            dentistAppointment.setInterval(calend);
            dentistAppointment.setTitle("Appointement with tutor");
            classes.addEntry(dentistAppointment);
        }



        Thread updateTimeThread = new Thread("Calendar: Update Time Thread") {
            @Override
            public void run() {
                while (true) {
                    Platform.runLater(() -> {
                        calendar.setToday(LocalDate.now());
                        calendar.setTime(LocalTime.now());
                        Entry<String> dentistAppointment = new Entry<>("Dentist");
                      //  calendar.setEntry
                    });

                    try {
                        // update every 10 seconds
                        sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };




        updateTimeThread.setPriority(Thread.MIN_PRIORITY);
        updateTimeThread.setDaemon(true);
        updateTimeThread.start();

        calendar.showMonthPage();
        pnlHost.getChildren().add(calendar);
    }


}
