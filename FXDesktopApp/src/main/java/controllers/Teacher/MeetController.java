package controllers.Teacher;

import DataBase.DataSource;
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

import java.awt.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class MeetController implements Initializable {


    @FXML
    private Connection conn = DataBase.DataSource.getInstance().getCnx();
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
    JFXComboBox studentid;
    @FXML
    JFXComboBox tutorid;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            settutorvalues() ;
            setstudentvalues();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void settutorvalues() throws SQLException {
       // String sql = "SELECT username FROM tutor INNER JOIN meet ON tutor.id = meet.tutorid_id WHERE tutor.id = 1 ";
        String sql = "SELECT username FROM tutor ";
        ste = conn.createStatement();
        rs = ste.executeQuery(sql);
        while (rs.next()) {
            ObservableList<String> r = FXCollections.observableArrayList();
r.addAll(rs.getString(1));
tutorid.setItems(r);
            //   System.out.println();
        }
    }

    public void setstudentvalues() throws SQLException {
        // String sql = "SELECT username FROM tutor INNER JOIN meet ON tutor.id = meet.tutorid_id WHERE tutor.id = 1 ";
        String sql = "SELECT username FROM student ";
        ste = conn.createStatement();
        rs = ste.executeQuery(sql);
        while (rs.next()) {
            ObservableList<String> r = FXCollections.observableArrayList();
            r.addAll(rs.getString(1));
            studentid.setItems(r);
            //   System.out.println();
        }
    }
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException, SQLException {
        String req = "select meet_link from meet where meet_id=15";
        ste = conn.createStatement();
        rs = ste.executeQuery(req);
        while (rs.next()) {
            rs.getString(1);
         //   System.out.println();
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(URI.create(rs.getString(1)));
        }

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
        tutorid.getSelectionModel().getSelectedItem();
        studentid.getSelectionModel().getSelectedItem();
        String date=start_date.getValue().toString();
String pass = meet_pass.getText();
CreateMeeting(token(),meet_name.getText(),"2",date,"60","Tunisia/Tunis",pass);
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

    public void creatMeet(String meet_name,String meet_link,String meet_pass, Date meet_date,int tutor,int student) throws SQLException {
        String req = "insert into meet (meet_name,meet_link,meet_pass,meet_date,tutorid_id,studentid_id) values (?,?,?,?,?,?)";

        pst = conn.prepareStatement(req);
        pst.setString(1,meet_name);
        pst.setString(2,meet_link);
        pst.setString(3,meet_pass);
        pst.setDate(4, meet_date);
        pst.setInt(5,tutor);
        pst.setInt(6,student);
        pst.executeUpdate();
    }
}