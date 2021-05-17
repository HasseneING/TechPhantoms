package controllers;


import model.AppointementCard;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import model.Reservation;
import utils.DataSource;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 *
 * @author SUWIMA
 */
public class AppointementController implements Initializable {
    @FXML
    private GridPane cardHolder;
    ObservableList<AppointementCard> list = FXCollections.observableArrayList();
    private Connection conn = DataSource.getInstance().getCnx();
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            show();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        onSearch();

    }

    @FXML
    public void onSearch() {
        int count = 0;
        for (int i = 0; i <list.size()/2; i++) {
            for (int j = 0; j < 2; j++) {
                cardHolder.add(list.get(count), j, i);
                count++;
                //System.out.println(i + " " + j);
            }
        }

    }

   /* @FXML
    public void onSearch() {
        int count = 0;
        for (int i = 0; i <list.size(); i++) {
            //  for (int j = 0; j < 2; j++) {
            //  cardHolder.add(list.get(count), j, i);
            cardHolder.addRow(i,list.get(count));
            count++;
            //System.out.println(i + " " + j);
            // }
        }

    }
    */

    public List<Reservation> readAll() throws SQLException {
        List<Reservation> reservationList = new ArrayList<>();
        String req = "select username,reservation_id,tutorid_id,studentid_id,reservation_date from reservation INNER JOIN student ON student.id = reservation.studentid_id where tutorid_id=1";
        ste = conn.createStatement();
        rs = ste.executeQuery(req);
        while (rs.next()) {
            Reservation reservations = new Reservation(
                    rs.getInt("reservation_id"),
                    rs.getDate("reservation_date"),
                    rs.getString("username")
            );
            reservationList.add(reservations);
        }
        return reservationList;
    }

    public void show() throws SQLException {
        List<Reservation> reservationList = new ArrayList<Reservation>();
        reservationList=readAll();
        if (reservationList.size()==0){
            // Action you want to do
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setContentText("You don't have upcoming meetings");
            alert.showAndWait();
        }


        for(int i=0;i<reservationList.size();i++){
            String date =reservationList.get(i).getReservation_date().toString();

            list.add(new AppointementCard(reservationList.get(i).getId(),date,reservationList.get(i).getUser_student()));
        }
        cardHolder.setAlignment(Pos.CENTER);
        cardHolder.setVgap(20.00);
        cardHolder.setHgap(80.00);
        cardHolder.setStyle("-fx-padding:10px;-fx-border-color:transparent");
    }
}
