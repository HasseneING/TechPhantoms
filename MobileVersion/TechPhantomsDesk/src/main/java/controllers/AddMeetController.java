package controllers;

import com.google.api.client.util.DateTime;
import com.jfoenix.controls.JFXTimePicker;
import utils.DataSource;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.json.JSONException;
import org.json.JSONObject;
import utils.EmailUtils;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.awt.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class AddMeetController implements Initializable {



    @FXML
    private Connection conn = DataSource.getInstance().getCnx();
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    @FXML
    JFXDatePicker start_date;
    @FXML
    JFXTextField meet_name;
    @FXML
    JFXTextField meet_pass;
    @FXML
    JFXComboBox<String> student;
    @FXML
    JFXComboBox<String> tutor;
    @FXML
    JFXTimePicker start_time;

    ObservableList<String> stu = FXCollections.observableArrayList();
    ObservableList<String> tut = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Map<String,String> listtut = new HashMap<String,String>();
        Map<String,String> liststu = new HashMap<String,String>();

        try {
            listtut =getTutor();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        for (Map.Entry<String, String> c : listtut.entrySet()) {
            tut.add(c.getValue());
        }

        try {
            liststu=getStudent();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        for (Map.Entry<String, String> c : liststu.entrySet()) {
            stu.add(c.getValue());
        }


        student.setItems(stu);
        tutor.setItems(tut);
        try {
            utils.ExportPDF.ExportMeetPDF();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Map<String,String> getTutor() throws SQLException {
        Map<String,String> tutorset = new HashMap<String,String>();
        String req = "select id,username from tutor";
        ste = conn.createStatement();
        rs = ste.executeQuery(req);
        while (rs.next()) {
            tutorset.put(rs.getString("id"),rs.getString("username"));

        }
        return tutorset;
    }

    public Map<String,String> getStudent() throws SQLException {
        Map<String,String> studentset = new HashMap<String,String>();
        String req = "select id,username from student";
        ste = conn.createStatement();
        rs = ste.executeQuery(req);
        while (rs.next()) {
            studentset.put(rs.getString("id"),rs.getString("username"));
        }
        return studentset;
    }

    public Map<String,String> getStudentMail() throws SQLException {
        Map<String,String> studentset = new HashMap<String,String>();
        String req = "select id,username,email from student";
        ste = conn.createStatement();
        rs = ste.executeQuery(req);
        while (rs.next()) {
            studentset.put(rs.getString("id"),rs.getString("email"));

        }
        return studentset;
    }




    private static String token() throws SQLException, JSONException {
        Statement ste;
        ResultSet rs;
        Connection conn = DataSource.getInstance().getCnx();
        String token="";
        String req = "select access_token from token WHERE id=31 ";
        ste = conn.createStatement();
        rs = ste.executeQuery(req);
        while (rs.next()) {
            token=rs.getString("access_token");
            JSONObject obj = new JSONObject(token);
            String pageName = obj.getString("access_token");
            token=pageName;
        }
        return token;
    }

    @FXML
    private void CreateZoomMeeting(ActionEvent event) throws Exception {
        Map<String,String> listemail = new HashMap<String,String>();
        listemail=getStudentMail();
        Optional<Map.Entry<String, String>> foundemail =
                listemail.entrySet()
                        .stream()
                        .filter(entry -> entry.getValue().contains(student.getSelectionModel().getSelectedItem()))
                        .max(Map.Entry.comparingByKey());

        Map<String,String> listtutor = new HashMap<String,String>();
        listtutor=getTutor();
        Optional<Map.Entry<String, String>> foundtutor =
                listtutor.entrySet()
                        .stream()
                        .filter(entry -> entry.getValue().contains(tutor.getSelectionModel().getSelectedItem()))
                        .max(Map.Entry.comparingByKey());

        Map<String,String> liststudent = new HashMap<String,String>();
        liststudent=getStudent();
        Optional<Map.Entry<String, String>> foundstudent =
                liststudent.entrySet()
                        .stream()
                        .filter(entry -> entry.getValue().contains(student.getSelectionModel().getSelectedItem()))
                        .max(Map.Entry.comparingByKey());
        int tutor =Integer.parseInt(foundtutor.get().getKey());
        int student=Integer.parseInt(foundstudent.get().getKey());
        LocalDate cs =start_date.getValue();
        LocalTime ts= start_time.getValue();
        LocalDateTime meet_date = LocalDateTime.of(cs.getYear(), cs.getMonth(), cs.getDayOfMonth(), ts.getHour(), ts.getMinute(), ts.getSecond());
        Timestamp meetDate = Timestamp.valueOf(meet_date);

        String pass = meet_pass.getText();
        creatMeet("ok","ok","ok",meetDate,tutor,student);
        sendMail(foundemail.get().getValue());
    }
    public static String CreateMeeting(String token, String topic, String type, String start_time, String duration, String timezone, String password) throws Exception {
        String meet_code="";
        String url = "https://api.zoom.us/v2/users/me/meetings";
        URL serverUrl = new URL(url);
        HttpURLConnection urlConnection = (HttpURLConnection) serverUrl.openConnection();
        urlConnection.setDoOutput(true);
        urlConnection.setRequestMethod("POST");
        urlConnection.setRequestProperty("Content-Type", "application/json");
        urlConnection.setRequestProperty("Accept", "application/json");
        urlConnection.setRequestProperty("Authorization", "Bearer " + token);
        String d = "{\"topic\": \"" + topic + "\",\"type\": \"" + type + "\",\"start_time\": \"" + start_time + "\",\"duration\": \"" + duration + "\",\"timezone\": \"" + timezone + "\",\"password\": \"" + password + "\"}";
        BufferedWriter httpRequestBodyWriter = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream()));
        httpRequestBodyWriter.write(d);
        httpRequestBodyWriter.close();
        BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        // int responseCode = urlConnection.getResponseCode();
        StringBuilder results = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            JSONObject obj = new JSONObject(line);
            meet_code = obj.optString("id");
        }
        urlConnection.disconnect();
        return meet_code;
    }

    public void creatMeet(String meet_name,String meet_link,String meet_pass, Timestamp meet_date,int tutor,int student) throws SQLException {
        String req = "insert into meet (meet_name,meet_link,meet_pass,meet_date,tutorid_id,studentid_id) values (?,?,?,?,?,?)";

        pst = conn.prepareStatement(req);
        pst.setString(1,meet_name);
        pst.setString(2,meet_link);
        pst.setString(3,meet_pass);
        pst.setTimestamp(4, meet_date);
        pst.setInt(5,tutor);
        pst.setInt(6,student);
        pst.executeUpdate();
    }
    public void sendMail(String toEmail){
        final String fromEmail = "laajili.ahlempidev@gmail.com"; //requires valid gmail id
        final String password = "maarch2244.."; // correct password for gmail id
        System.out.println("TLSEmail Start");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(props, auth);

        EmailUtils.sendEmail(session, toEmail,"Appointement with tutor", "MeetLink = \n"+"Meet Pass="+meet_pass.getText());


    }

}