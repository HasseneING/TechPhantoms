package controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Meet;
import utils.DataSource;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

public class UpdateMeet implements Initializable {
    private Connection conn = DataSource.getInstance().getCnx();
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    @FXML
    private JFXTextField meetName;
    @FXML
   private  JFXTextField meetPass;
    @FXML
    private JFXDatePicker meetDate;
    @FXML
    private JFXTimePicker meetTime;
    @FXML
    private JFXComboBox<String> student;
    int meetID;
    private boolean update;
    ObservableList<String> r = FXCollections.observableArrayList();
    @FXML
    Button submit;


    public void  setTextField(int id) throws SQLException {
        meetID = id;
        System.out.println(meetID);
        ObservableList<Meet> meetList = FXCollections.observableArrayList();
        String req = "select * from meet where meet_id=" + id + "";
        ste = conn.createStatement();
        rs = ste.executeQuery(req);
        String name = null;
        String pass=null;
        Timestamp date=null;
        while (rs.next()) {
                    name=rs.getString("meet_name");
                    pass=rs.getString("meet_pass");
                    date=rs.getTimestamp("meet_date");
        }
        meetName.setText(name);
        meetPass.setText(pass);
        meetDate.setValue(date.toLocalDateTime().toLocalDate());
        meetTime.setValue(date.toLocalDateTime().toLocalTime());
    }


    public void setUpdate(boolean b) {
        this.update = b;

    }

    @FXML
    public void meetUpdate(ActionEvent actionEvent) throws SQLException, IOException {
        String student_value=student.getValue();
        String req = "update meet set meet_name=? ,meet_pass=?, meet_date=? ,studentid_id=? where meet_id=?";
        Map<String,String> list = new HashMap<String,String>();
        list=getStudent();
        Optional<Map.Entry<String, String>> found =
                list.entrySet()
                        .stream()
                        .filter(entry -> entry.getValue().contains(student.getSelectionModel().getSelectedItem()))
                        .max(Map.Entry.comparingByKey());

        LocalDateTime date=LocalDateTime.of(meetDate.getValue(),meetTime.getValue());
        Timestamp meet_date=Timestamp.valueOf(date);
        pst = conn.prepareStatement(req);
        pst.setString(1,meetName.getText());
        pst.setString(2,meetPass.getText());
        pst.setTimestamp(3,meet_date);
        pst.setInt(4, Integer.parseInt(found.get().getKey()));
        pst.setInt(5,meetID);
        pst.executeUpdate();
        Stage previous = (Stage) submit.getScene().getWindow();
        previous.close();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setContentText("Meeting  "+meetID+" has been updated");
        alert.showAndWait();


    }

    public Map<String,String> getStudent() throws SQLException {
        Map<String,String> tutorset = new HashMap<String,String>();
        String req = "select id,username from student";
        ste = conn.createStatement();
        rs = ste.executeQuery(req);
        while (rs.next()) {
            tutorset.put(rs.getString("id"),rs.getString("username"));

        }
        return tutorset;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Map<String,String> list = new HashMap<String,String>();
        try {
            list=getStudent();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        for (Map.Entry<String, String> c : list.entrySet()) {
            r.add(c.getValue());
        }
        student.setItems(r);

    }
}
