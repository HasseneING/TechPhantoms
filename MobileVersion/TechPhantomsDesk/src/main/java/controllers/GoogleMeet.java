package controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.Student;
import utils.DataSource;
import utils.UserSession;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class GoogleMeet implements Initializable {

    @FXML
    JFXComboBox<String> tutor;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    Connection conn= DataSource.getInstance().getCnx();
    ObservableList<String> meet = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        java.util.List<String> list = new ArrayList<String>();

        try {
            list =getMeets();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        for (int i=0;i<list.size();i++) {
            meet.add(list.get(i));
        }

        tutor.setItems(meet);



    }

    @FXML
    public void handleButtonAction(ActionEvent actionEvent) throws IOException {
        Desktop desktop = Desktop.getDesktop();
        desktop.browse(URI.create(tutor.getSelectionModel().getSelectedItem()));
    }

    public java.util.List<String> getMeets() throws SQLException {
        Student s =new Student(1,"","");
        java.util.List<String> studentset = new ArrayList<String>();
        String req = "select meet_link from googlemeet where studentid_id=1";
        //pst.setInt(1,s.getId());
        System.out.println(s.getId());
        pst = conn.prepareStatement(req);
        rs = pst.executeQuery(req);
        while (rs.next()) {
            studentset.add(rs.getString("meet_link"));
            System.out.println(rs.getString("meet_link"));
        }
        return studentset;
    }

}
