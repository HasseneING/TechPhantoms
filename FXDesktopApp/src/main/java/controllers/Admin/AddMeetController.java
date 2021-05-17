package controllers.Admin;

import DataBase.DataSource;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import model.Meet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddMeetController {

    @FXML
    private JFXTextField meetName;
    @FXML
    private JFXDatePicker meetDate;
    @FXML
    private JFXTextField meetPass;
    @FXML
    private JFXComboBox studentid;
    @FXML
    private JFXComboBox tutorid;


    String query = null;
    Connection connection = DataSource.getInstance().getCnx();
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Meet student = null;
    private boolean update;
    int studentId;
    void setTextField(int id, String name, String pass,String tutor,String student) {

        int meetID = id;
        meetName.setText(name);
        meetPass.setText(pass);
      //  tutorid.setSelectionModel(student);
    //    tutorid.setSelectionModel(tutor);
    }

    void setUpdate(boolean b) {
        this.update = b;

    }

    @FXML
    private void save(MouseEvent event) {

        Connection connection = DataSource.getInstance().getCnx();
        String name = meetName.getText();
        String date = String.valueOf(meetDate.getValue());
        String pass = meetPass.getText();

        if (name.isEmpty() || date.isEmpty() || pass.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        } else {
            getQuery();
          //  insert();
            clean();

        }

    }

    @FXML
    private void clean() {
        meetName.setText(null);
        meetPass.setText(null);
        meetDate.setValue(null);
tutorid.setValue(null);
studentid.setValue(null);
    }

    private void getQuery() {

        if (update == false) {

            query = "INSERT INTO ( meet_name, meet_pass, meet_date, tutorid_id,studentid_id) VALUES (?,?,?,?,?)";

        }else{
            query = "UPDATE `meet` SET "
                    + "`meet_name`=?,"
                    + "`meet_pass`=?,"
                    + "`meet_date`=?,"
                    + "`tutorid_id`= ? "
                    + "`studentid_id`= ? "
                    + "WHERE id = '"+studentId+"'";
        }

    }

    /*private void insert() {

        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nameFld.getText());
            preparedStatement.setString(2, String.valueOf(birthFld.getValue()));
            preparedStatement.setString(3, adressFld.getText());
            preparedStatement.setString(4, emailFld.getText());
            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(AddMeetController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }*/
}

