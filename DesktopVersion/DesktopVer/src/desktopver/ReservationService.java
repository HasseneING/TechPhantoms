package desktopver;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import com.sun.javafx.scene.control.skin.DatePickerSkin;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utils.DataSource;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class ReservationService implements Initializable {
    @FXML
    private JFXDatePicker calendar;
    @FXML
    private JFXTimePicker timee;
    @FXML
    private JFXButton submit;

    private Connection conn= DataSource.getInstance().getCnx();
    private ResultSet result;
    private PreparedStatement pst;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void btnSubmit() throws SQLException {
        LocalDate c=calendar.getValue();
        LocalTime t=timee.getValue();
        LocalDateTime reservation_DT = LocalDateTime.of(c.getYear(), c.getMonth(), c.getDayOfMonth(),t.getHour(), t.getMinute(),t.getSecond());
      //  System.out.println(reservation_DT);
        Timestamp reservationDate= Timestamp.valueOf(reservation_DT);

       String req = "insert into reservation (reservationDate) values (?)";
        pst = conn.prepareStatement(req);
        pst.setTimestamp(1,reservationDate);
        pst.executeUpdate();

    }


}

