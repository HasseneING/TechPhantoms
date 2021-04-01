/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sidenavigation;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class recService implements Initializable {

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
    @FXML
    private Button Submit;
    
    ObservableList<String> r = FXCollections.observableArrayList();
    private final Connection conn= DataSource.getInstance().getCnx();
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

    }
    
    public void btnSubmit(ActionEvent action){
        
        Notifications.create().title("Done").text("Your complaint has been received").showInformation();
    }
    
    
}
