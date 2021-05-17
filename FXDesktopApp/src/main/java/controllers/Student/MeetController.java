package controllers.Student;

import java.awt.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import DataBase.DataSource;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.json.JSONException;
import org.json.JSONObject;


public class MeetController implements Initializable {


    @FXML
    private Connection conn = DataBase.DataSource.getInstance().getCnx();
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    @Override
    public void initialize(URL url, ResourceBundle rb) {


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
        String req = "select access_token from token WHERE id=28 ";
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
    private void ButtonZoom(ActionEvent event) throws IOException {
        Runtime.getRuntime().exec("C:\\Users\\MJ-INFO\\AppData\\Roaming\\Zoom\\bin\\Zoom.exe");

    }



}