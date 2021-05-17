package controllers.Admin;

import DataBase.DataSource;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import model.Meet;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MeetDashboard implements Initializable {

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

        loadChart();
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
        String req = "select * from meet ";
        ste = conn.createStatement();
        rs = ste.executeQuery(req);
        while (rs.next()) {
            Meet meets = new Meet(
                    rs.getInt("meet_id"),
                    rs.getString("meet_name"),
                    rs.getString("meet_pass"),
                    rs.getString("meet_link"),
                    rs.getInt("tutorid_id"),
                    rs.getInt("studentid_id"),
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

        MeetId.setCellValueFactory(new PropertyValueFactory("meet_id"));
        MeetDate.setCellValueFactory(new PropertyValueFactory("meet_date"));
        Student.setCellValueFactory(new PropertyValueFactory("student_id"));
        Tutor.setCellValueFactory(new PropertyValueFactory("tutor_id"));
        tbData.setItems(list);
        Callback<TableColumn<Meet, String>, TableCell<Meet, String>> cellFoctory = (TableColumn<Meet, String> param) -> {
            // make cell containing buttons
            final TableCell<Meet, String> cell = new TableCell<Meet, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                            try {
                        String  req = "DELETE FROM meet WHERE meet_id  ="+tbData.getSelectionModel().getSelectedItem().getMeet_id();
                                pst = conn.prepareStatement(req);
                                pst.execute();

                            } catch (SQLException ex) {
                                Logger.getLogger(MeetDashboard.class.getName()).log(Level.SEVERE, null, ex);
                            }





                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {

                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(this.getClass().getClassLoader().getResource("addMeet.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(MeetDashboard.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            AddMeetController addmeet = loader.getController();
                            addmeet.setUpdate(true);
                          /*  addmeet.setTextField(
                                    tbData.getSelectionModel().getSelectedItem().getMeet_id(),
                                    tbData.getSelectionModel().getSelectedItem().getMeet_name()
                            );*/
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();




                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        Actions.setCellFactory(cellFoctory);

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