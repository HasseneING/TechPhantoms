/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entities.Student;
import services.StudentCrud;
import com.jfoenix.controls.*;
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
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
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
public class SignUpFurtherInfoController implements Initializable {

    @FXML
    private ImageView inpPhoto;
    @FXML
    private JFXTextField inpFirstName;
    @FXML
    private JFXTextField inpLastName;
    @FXML
    private JFXComboBox<String> inpState;
    @FXML
    private JFXDatePicker inpDate;
    @FXML
    private JFXTextField inpNumber;
    @FXML
    private JFXTextField inpCin;
    @FXML
    private JFXButton btnSignIn;
    @FXML
    private JFXRadioButton rbStudent;
    @FXML
    private ToggleGroup role;
    @FXML
    private JFXRadioButton rbTutor;

    private String username, email, password;
    private String ImageString;
    private List<String> ItemsToSend;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setStateComboBox();
    }

    public void setStateComboBox() {
        ObservableList<String> State = FXCollections.observableArrayList();
        State.addAll("Ariana", "Béja", "Ben Arous", "Bizerte", "Gabès", "Gafsa", "Jendouba", "Kairouan", "Gasserine", "Gebili", "Kef", "Mahdia", "Manouba", "Medenine", "Monastir", "Nabeul", "Sfax", "Sidi Bouzid", "Siliana", "Sousse", "Tataouine", "Tozeur", "Tunis", "Zaghouan");
        inpState.setItems(State);
    }

    public void VerifyAndSet(List<String> list) throws NoSuchAlgorithmException {
        list.add(inpFirstName.getText());
        list.add(inpLastName.getText());
        list.add(ImageString);
        list.add(inpState.getValue());
        list.add(inpDate.getValue().toString());
        list.add(inpNumber.getText());
        list.add(inpCin.getText());
    }

    @FXML
    private void Submit(ActionEvent event) {
//        if (!isNumberValid(inpCin.getText())) {
//            if (!isNumberValid(inpNumber.getText())) {
                if (verifIssets()) {

                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    ItemsToSend = (List<String>) window.getUserData();
                    username = ItemsToSend.get(0);
                    email = ItemsToSend.get(1);
                    password = ItemsToSend.get(2);
                    if (getCBValue() == 0) {
                        Student s = addStudent();
                        window.setUserData(s);
                        try {
                            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/VerifCodeStudent.fxml"));
                            Scene scene = new Scene(root);
                            VerifCodeStudentController vicsc = new VerifCodeStudentController();
                            window.setScene(scene);
                            window.show();
                        } catch (IOException ex) {
                            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        try {
                            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/TutorSignUpDetails.fxml"));
                            //Parent root = loader.load();
                            Scene scene = new Scene(root);
                            // SignUpFurtherInfoController sufic = loader.getController();
                            TutorSignUpDetailsController tsudc = new TutorSignUpDetailsController();
                            VerifyAndSet(ItemsToSend);
                            window.setScene(scene);
                            window.show();
                        } catch (NoSuchAlgorithmException ex) {
                            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {
                    showAlertError("Empty/Wrong Field(s)", "Please insert correct information(s)");
                }
//            } else {
//                showAlertError("Phone Number invalid input", "Please insert an 8 digit number : CIN");
//            }
//        } else {
//            showAlertError("Phone Number invalid input", "Please insert an 8 digit number : Phone number");
//        }
    }

    private boolean isNumberValid(String phoneNumber) {
        String pattern = "^[0-9]{8}$";
        return phoneNumber.matches(pattern);
    }

    public boolean verifIssets() {
        if (inpFirstName.getText().equals("") || inpLastName.getText().equals("") || inpState.getValue().equals("") || inpDate.getValue().equals("") || !isNumberValid(inpNumber.getText()) || !isNumberValid(inpCin.getText())) {
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

    public Student addStudent() {
        String username = this.username;
        String email = this.email;
        String password = this.password;
        String firstname = inpFirstName.getText();
        String lastname = inpLastName.getText();
        int cin = Integer.valueOf(inpCin.getText());
        Date datebirth = Date.valueOf(inpDate.getValue());
        System.out.println(datebirth);
        String State = inpState.getValue();
        int number = Integer.valueOf(inpNumber.getText());
        Student s = new Student(5, username, email, password, firstname, lastname, cin, "ReqAct", datebirth, ImageString, State, number);
        StudentCrud S = new StudentCrud();
        S.addStudent(s);
        return s;
    }

    public int getCBValue() {
        if (rbStudent.isSelected()) {
            return 0;
        }
        return 1;
    }

    @FXML
    private void OnlyNumbers(KeyEvent event) {
        char c = event.getCharacter().charAt(0);
        if (!Character.isDigit(c)) {
            event.consume();
        }
        if (inpNumber.getText().length() != 8) {
            inpNumber.setFocusColor(Color.CRIMSON);
        } else {
            inpNumber.setFocusColor(Color.MEDIUMSPRINGGREEN);
        }
    }

    public void SetData(String user, String email, String password) {
        this.username = user;
        this.email = email;
        this.password = password;
    }

    @FXML
    private void Add_ProfilePic(MouseEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        chooser.setInitialDirectory(new File(System.getProperty("user.home")));
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.bmp", "*.png", "*.jpg", "*.gif"));
        File file = chooser.showOpenDialog(new Stage());
        if (file != null) {
            String imagepath = file.toURI().toString();
            Image image = new Image(imagepath);
            inpPhoto.setImage(image);
            ImageString = imagepath;

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Please Select a File");
            alert.setHeaderText(null);
            alert.setContentText("You didn't select a file!");
            alert.showAndWait();
        }
    }
}
