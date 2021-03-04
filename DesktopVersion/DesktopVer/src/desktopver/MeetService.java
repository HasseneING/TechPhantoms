package desktopver;

import entite.Meet;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.DataSource;


public class MeetService implements Initializable {
    @FXML
    private TextField roomNAMEField;
    @FXML
    private Button insertButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TableView<Meet> TableView;
    @FXML
    private TableColumn<Meet, Integer> roomIDColumn;
    @FXML
    private TableColumn<Meet, Integer> teacherIDColumn;
    @FXML
    private TableColumn<Meet, String> roomNAMEColumn;

    private Connection conn = DataSource.getInstance().getCnx();
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    public MeetService() {
    }

    @FXML
    private void insertButton() throws SQLException {
        String req = "insert into meet (room_name) values (?)";
        pst = conn.prepareStatement(req);
        pst.setString(1, roomNAMEField.getText());
        pst.executeUpdate();
        showMeets();
    }

    @FXML
    public ObservableList<Meet> readAll() throws SQLException {
        ObservableList<Meet> meetList = FXCollections.observableArrayList();
        String req = "select * from meet ";
        ste = conn.createStatement();
        rs = ste.executeQuery(req);
        while (rs.next()) {
            Meet meets = new Meet(rs.getInt("room_id"),rs.getInt("teacherID"),rs.getString("room_name"));
            meetList.add(meets);
        }
        return meetList;
    }

    @FXML
    private void updateButton() throws SQLException {
        String req = " UPDATE meet SET room_name=? WHERE room_id=? ";
        pst = conn.prepareStatement(req);
        pst.setString(1, roomNAMEField.getText());
        pst.setInt(2, TableView.getSelectionModel().getSelectedItem().getRoom_id());
        pst.executeUpdate();
        showMeets();
    }

    @FXML
    private void deleteButton() throws SQLException {
        String req = "DELETE FROM meet WHERE room_id=?";
        pst = conn.prepareStatement(req);
        pst.setInt(1, TableView.getSelectionModel().getSelectedItem().getRoom_id());
        pst.executeUpdate();
        System.out.println("Room Deleted");
        showMeets();
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        try {
            showMeets();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        }



    @FXML
    public void showMeets() throws SQLException {
        ObservableList<Meet> list = readAll();
        roomIDColumn.setCellValueFactory(new PropertyValueFactory("room_id"));
        teacherIDColumn.setCellValueFactory(new PropertyValueFactory("teacherID"));
        roomNAMEColumn.setCellValueFactory(new PropertyValueFactory("room_name"));
        TableView.setItems(list);
    }
}
