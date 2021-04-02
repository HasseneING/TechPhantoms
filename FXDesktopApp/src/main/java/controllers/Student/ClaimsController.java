package controllers.Student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ClaimsController implements Initializable {
    @FXML
    private TextField name;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;
    @FXML
    private TextField TutorID;
    @FXML
    private ComboBox<String> reasons;
    @FXML
    private TextArea other_reasons;


    ObservableList<String> r = FXCollections.observableArrayList();
    private final Connection conn= DataBase.DataSource.getInstance().getCnx();
    private ResultSet result;
    private PreparedStatement pst;
    private Statement st;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        r.addAll("Incorrect Information Provided", "Lack of Information", "Lack of Respect");
        reasons.setItems(r);
    }


    @FXML
    public void btnSubmit() throws SQLException {
        System.out.println("ok");
        String reason_value=reasons.getValue();
        String req = "insert into rec (name, lastName,email,TutorID,reasons,other_reasons) values (?,?,?,?,?,?)";

        pst = conn.prepareStatement(req);

        pst.setString(1,name.getText());
        pst.setString(2,lastName.getText());
        pst.setString(3,email.getText());
        pst.setString(4, TutorID.getText());
        pst.setString(5,reason_value);
        pst.setString(6,other_reasons.getText());
        pst.executeUpdate();
        Notifications.create().title("Done").text("Your complaint has been received").showInformation();


    }

}
