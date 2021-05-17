package controllers.student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;

import java.net.URL;
import java.sql.*;
import java.util.*;

public class ClaimsController implements Initializable {
    @FXML
    private TextField name;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;
    @FXML
    private ComboBox<String> reasons;
    @FXML
    private TextArea other_reasons;
    @FXML
    private ComboBox<String> tutor;


    ObservableList<String> r = FXCollections.observableArrayList();
    ObservableList<String> tut = FXCollections.observableArrayList();

    private final Connection conn= utils.DataSource.getInstance().getCnx();
    private ResultSet result;
    private PreparedStatement pst;
    private Statement st;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Map<String,String> list = new HashMap<String,String>();
        try {
            list=getTutor();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        r.addAll("Incorrect Information Provided", "Lack of Information", "Lack of Respect");
        for (Map.Entry<String, String> c : list.entrySet()) {
            tut.add(c.getValue());
        }
        tutor.setItems(tut);
        reasons.setItems(r);
    }


    @FXML
    public void btnSubmit() throws SQLException {
        System.out.println("ok");
        String reason_value=reasons.getValue();
        String req = "insert into rec (name, lastName,email,TutorID,reasons,other_reasons) values (?,?,?,?,?,?)";
        Map<String,String> list = new HashMap<String,String>();
        list=getTutor();
        Optional<Map.Entry<String, String>> found =
                list.entrySet()
                .stream()
                .filter(entry -> entry.getValue().contains(tutor.getSelectionModel().getSelectedItem()))
                .max(Map.Entry.comparingByKey());

        pst = conn.prepareStatement(req);
        pst.setString(1,name.getText());
        pst.setString(2,lastName.getText());
        pst.setString(3,email.getText());
        pst.setString(4, found.get().getKey());
        pst.setString(5,reason_value);
        pst.setString(6,other_reasons.getText());
        pst.executeUpdate();
        Notifications.create().title("Done").text("Your complaint has been received").showInformation();


    }

    public Map<String,String> getTutor() throws SQLException {
        Map<String,String> tutorset = new HashMap<String,String>();
        String req = "select id,username from tutor";
        st = conn.createStatement();
        result = st.executeQuery(req);
        while (result.next()) {
            tutorset.put(result.getString("id"),result.getString("username"));

        }
        return tutorset;
    }

}
