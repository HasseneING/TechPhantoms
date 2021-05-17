/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entities.Student;
import Entities.Tutor;
import services.StudentCrud;
import services.TutorCrud;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class LoginController implements Initializable {

    @FXML
    private JFXTextField inpUsername;
    @FXML
    private JFXPasswordField inpPassword;
    @FXML
    private JFXButton btnSignIn;
    @FXML
    private JFXButton btnSignInFB;
    @FXML
    private JFXButton btnSignInGoogle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void SignIn(ActionEvent event) {
        if (verifIssets()) {
            String WUsername = inpUsername.getText();
            String WPassword = null;
            try {
                WPassword = encrypt(inpPassword.getText());
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Student s = StudentCrud.GetStudent(WUsername, WPassword);
            if (s != null) {
                showAlertInfo("Student", "Log in successful");
                ////////////////////////////////////////////////Hot FXML path student interface
                try {
                    FXMLLoader loader = new FXMLLoader((getClass().getClassLoader().getResource("fxml/student/Home.fxml")));
                    Parent root = loader.load();
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                Tutor t = TutorCrud.GetTutor(WUsername, WPassword);
                if (t != null) {
                    showAlertInfo("Tutor", "Log in successful");
                    try {
                        FXMLLoader loader = new FXMLLoader((getClass().getClassLoader().getResource("fxml/tutor/Home.fxml")));
                       SignUpController suc = loader.getController();
                       Parent root = loader.load();
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.show();
                    } catch (IOException ex) {
                       Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    showAlertError("ERROR!", "Information Invalid");
                }
            }
        } else {
            showAlertError("Empty Field(s)", "Please insert information(s)");
        }
    }

    public boolean verifIssets() {
        if (inpUsername.getText().equals("") || inpPassword.getText().equals("")) {
            return false;
        }
        return true;
    }

    public String encrypt(String password) throws NoSuchAlgorithmException {
        MessageDigest md;

        md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte byteData[] = md.digest();
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            String hex = Integer.toHexString(0xff & byteData[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        String crypte = new String(hexString);
        return crypte;
    }

    @FXML
    private void SignInFB(ActionEvent event) {
    }

    @FXML
    private void SignInGoogle(ActionEvent event) {
    }

    @FXML
    private void SignUp(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader((getClass().getClassLoader().getResource("fxml/SignUp.fxml")));
            SignUpController suc = loader.getController();
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void showAlertInfo(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void showAlertError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
