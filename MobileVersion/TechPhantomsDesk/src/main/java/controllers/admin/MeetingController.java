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
import model.Meet;
import utils.DataSource;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class MeetingController implements Initializable {

    @FXML
    private TableView<Meet> tbData;
    @FXML
    public TableColumn<Meet, String> MeetName;

    @FXML
    public TableColumn<Meet, String> MeetLink;

    @FXML
    public TableColumn<Meet, String> MeetPass;

    @FXML
    public TableColumn<Meet, Date> MeetDate;
    @FXML
    public TableColumn<Meet, String> MeetId;
    @FXML
    public TableColumn<Meet, String> Student;
    @FXML
    public TableColumn<Meet, String> Tutor;
    @FXML
    private TableColumn<Meet, String> Actions;
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

    public ObservableList<Meet> readAll() throws SQLException {
        ObservableList<Meet> meetList = FXCollections.observableArrayList();
        String req = "select username,meet_id,tutorid_id,studentid_id,meet_link,meet_date,meet_pass,meet_name from meet INNER JOIN tutor ON tutor.id = meet.tutorid_id where studentid_id=1";
        ste = conn.createStatement();
        rs = ste.executeQuery(req);
        while (rs.next()) {
            Meet meets = new Meet(
                    rs.getInt("meet_id"),
                    rs.getString("meet_name"),
                    rs.getString("meet_pass"),
                    rs.getString("meet_link"),
                    rs.getInt("tutorid_id"),
                    rs.getString("username"),
                    rs.getDate("meet_date"));
            meetList.add(meets);
        }
        return meetList;
    }

    @FXML
    public void showMeets() throws SQLException {
        ObservableList<Meet> list = readAll();
        MeetName.setCellValueFactory(new PropertyValueFactory("meet_name"));
        MeetLink.setCellValueFactory(new PropertyValueFactory("meet_link"));
        MeetPass.setCellValueFactory(new PropertyValueFactory("meet_pass"));
        MeetDate.setCellValueFactory(new PropertyValueFactory("meet_date"));
        Tutor.setCellValueFactory(new PropertyValueFactory("student_id"));
        tbData.setItems(list);

        //Search
        FilteredList<Meet> filteredData = new FilteredList<>(list, b -> true);
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(meet -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) return true;

                // Compare every table columns fields with lowercase filter text
                String lowerCaseFilter = newValue.toLowerCase();

                // Filter with all table columns
                if (meet.getMeet_name().toLowerCase().indexOf(lowerCaseFilter) != -1) return true;
                else if (meet.getMeet_link().toLowerCase().indexOf(lowerCaseFilter) != -1) return true;
                else if (meet.getMeet_pass().toLowerCase().indexOf(lowerCaseFilter) != -1) return true;
                else if (meet.getStudent_id().toLowerCase().indexOf(lowerCaseFilter) != -1) return true;
                else return false; // Does not match
            });
            SortedList<Meet> sortedData = new SortedList<>(filteredData);

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
        pst.setInt(1, tbData.getSelectionModel().getSelectedItem().getMeet_id());
        pst.executeUpdate();
        System.out.println("Room Deleted");
        showMeets();
    }


}