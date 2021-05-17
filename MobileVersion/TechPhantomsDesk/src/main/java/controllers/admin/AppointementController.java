package controllers.admin;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Reservation;
import utils.DataSource;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AppointementController implements Initializable {

    @FXML
    private TableView<Reservation> tbData;
    @FXML
    public TableColumn<Reservation, String> reservationId;

    @FXML
    public TableColumn<Reservation, String> reservationDate;

    @FXML
    public TableColumn<Reservation, Date> student;
    @FXML
    private TableColumn<Reservation, String> Actions;
    @FXML
    public TextField search;
    @FXML
    private PieChart pieChart;
    private Connection conn = DataSource.getInstance().getCnx();
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            showMeets();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void loadChart()
    {

        PieChart.Data slice1 = new PieChart.Data("Total Meetings", 213);
        PieChart.Data slice2 = new PieChart.Data("Previous Meetings"  , 67);
        PieChart.Data slice3 = new PieChart.Data("Upcoming Meetings" , 36);

        pieChart.getData().add(slice1);
        pieChart.getData().add(slice2);
        pieChart.getData().add(slice3);

    }




    @FXML

    public ObservableList<Reservation> readAll() throws SQLException {
        ObservableList<Reservation> meetList = FXCollections.observableArrayList();
        String req = "select username,reservation_id,reservation_date,tutorid_id,studentid_id from reservation INNER JOIN tutor ON tutor.id = reservation.studentid_id where studentid_id=1";
        ste = conn.createStatement();
        rs = ste.executeQuery(req);
        while (rs.next()) {
            Reservation reservations = new Reservation(
                    rs.getInt("reservation_id"),
                    rs.getDate("reservation_date"),
                    rs.getString("tutorid_id"),
                    rs.getString("username")
            );
            meetList.add(reservations);
        }
        return meetList;
    }

    @FXML
    public void showMeets() throws SQLException {
        ObservableList<Reservation> list = readAll();
        reservationDate.setCellValueFactory(new PropertyValueFactory("reservation_date"));
        student.setCellValueFactory(new PropertyValueFactory("student"));
        tbData.setItems(list);

        //Search
        FilteredList<Reservation> filteredData = new FilteredList<>(list, b -> true);
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(reservation -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) return true;
                String lowerCaseFilter = newValue.toLowerCase();
                if (reservation.getUser_student().toLowerCase().indexOf(lowerCaseFilter) != -1) return true;
                else if (reservation.getReservation_date().toString().toLowerCase().indexOf(lowerCaseFilter) != -1) return true;
                 return false; // Does not match
            });
            SortedList<Reservation> sortedData = new SortedList<>(filteredData);

            // 4. Bind the SortedList comparator to the TableView comparator.
            // Otherwise, sorting the TableView would have no effect.
            sortedData.comparatorProperty().bind(tbData.comparatorProperty());

            // 5. Add sorted (and filtered) data to the table.
            tbData.setItems(sortedData);
        });
    }
    @FXML
    private void deleteButton() throws SQLException {
        String req = "DELETE FROM meet WHERE meet_id=?";
        pst = conn.prepareStatement(req);
        pst.setInt(1, tbData.getSelectionModel().getSelectedItem().getId());
        pst.executeUpdate();
        System.out.println("Room Deleted");
        showMeets();
    }


}