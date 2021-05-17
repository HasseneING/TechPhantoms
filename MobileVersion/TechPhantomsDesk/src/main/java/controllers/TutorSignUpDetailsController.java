/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entities.Tutor;
import services.TutorCrud;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class TutorSignUpDetailsController implements Initializable {

    @FXML
    private JFXButton btnSignIn;
    @FXML
    private JFXTextField inpPrice;
    @FXML
    private JFXComboBox<String> inpLang;
    @FXML
    private JFXComboBox<String> inpSubject;
    @FXML
    private JFXButton inpCV;
    @FXML
    private JFXButton inpVideo;

    private String username, email, password, firstname, lastname, ImageString, state;
    private Date date;
    private int phone, cin;
    private String cvString;
    private String videoString;
    private List<String> Items;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setSubjectComboBox();
        setLaguageComboBox();
    }

    public void setSubjectComboBox() {
        ObservableList<String> sub = FXCollections.observableArrayList();
        sub.addAll("Maths", "Physics", "Computer Science", "Science", "Arabic", "French", "English", "Spanish", "Italian", "Sports", "Music", "Art", "Chinese", "Economic", "Management", "History", "Geographique");
        inpSubject.setItems(sub);
    }

    public void setLaguageComboBox() {
        ObservableList<String> lang = FXCollections.observableArrayList();
        lang.addAll("French", "English", "Arabic", "Spanish", "Italian");
        inpLang.setItems(lang);
    }

    @FXML
    private void GetCV(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        chooser.setInitialDirectory(new File(System.getProperty("user.home")));
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        File file = chooser.showOpenDialog(new Stage());
        if (file != null) {
            String cvpath = file.toURI().toString();
            cvString = cvpath;
            inpCV.setStyle("-fx-background-color : MediumSpringGreen; -fx-background-radius : 1em;");

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Please Select a File");
            /*alert.setContentText("You didn't select a file!");*/
            alert.showAndWait();
        }
    }

    @FXML
    private void GetVideo(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        chooser.setInitialDirectory(new File(System.getProperty("user.home")));
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Video Files", "*.mp4"));
        File file = chooser.showOpenDialog(new Stage());
        if (file != null) {
            String vidpath = file.toURI().toString();
            videoString = vidpath;
            inpVideo.setStyle("-fx-background-color : MediumSpringGreen; -fx-background-radius : 1em;");
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Please Select a File");
            /*alert.setContentText("You didn't select a file!");*/
            alert.showAndWait();
        }
    }
    
        private boolean isNumberValid(String phoneNumber) {
            String pattern = "^[0-9]{8}$";
        return phoneNumber.matches(pattern);
    }
        
        
    public boolean verifIssets() {
        if (inpLang.getValue().equals("") || inpSubject.getValue().equals("") || !isNumberValid(inpPrice.getText())){
            return false;
        }
        return true;
    }

    public static void showAlertError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void SignUpTutorDetails(ActionEvent event) {
        if(verifIssets()){
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Items = (List<String>) window.getUserData();
        username = Items.get(0);
        email = Items.get(1);
        password = Items.get(2);
        firstname = Items.get(3);
        lastname = Items.get(4);
        ImageString = Items.get(5);
        state = Items.get(6);
        date = Date.valueOf(Items.get(7));
        phone = Integer.valueOf(Items.get(8));
        cin = Integer.valueOf(Items.get(9));
        Tutor t = new Tutor(52, username, email, password, firstname, lastname, cin, "ReqAct", date, ImageString, state, phone, "None", Double.valueOf(inpPrice.getText()), cvString, videoString, inpSubject.getValue(), inpLang.getValue());
        TutorCrud T = new TutorCrud();
        T.addTutor(t);
        window.setUserData(t);
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/VerifCodeTutor.fxml"));
            Scene scene = new Scene(root);
            VerifCodeStudentController vicsc = new VerifCodeStudentController();
            window.setScene(scene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else{
            showAlertError("Empty/Wrong field(s)", "Please insert correct information");
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    @FXML
    private void OnlyNumbers(KeyEvent event) {
                char c = event.getCharacter().charAt(0);
        if (!Character.isDigit(c)) {
            event.consume();
        }
        if (isNumberValid(inpPrice.getText())) {
            inpPrice.setFocusColor(Color.CRIMSON);
        } else {
            inpPrice.setFocusColor(Color.MEDIUMSPRINGGREEN);
        }
    }

}
