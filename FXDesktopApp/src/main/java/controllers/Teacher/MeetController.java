package controllers.Teacher;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.awt.*;
import java.io.IOException;
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





}