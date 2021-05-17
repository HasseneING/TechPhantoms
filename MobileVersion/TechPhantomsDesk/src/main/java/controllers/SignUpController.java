/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

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
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class SignUpController implements Initializable {

    @FXML
    private JFXTextField inpUsername;
    @FXML
    private JFXTextField inpEmail;
    @FXML
    private JFXPasswordField inpPassword;
    @FXML
    private JFXButton btnSignIn;
    @FXML
    private JFXPasswordField inpConfPassword;
    @FXML
    private JFXButton btnSignupFB;
    @FXML
    private JFXButton btnSignUpGoogle;

    private static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    @FXML
    private Label passwordStrength;
    @FXML
    private Line passwordStrengthLine;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private boolean EmailInput(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            inpEmail.setFocusColor(Color.MEDIUMSPRINGGREEN);
            return true;
        } else {
            inpEmail.setFocusColor(Color.CRIMSON);
            return false;
        }
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

    public boolean verifIssets() {
        if (inpUsername.getText().equals("") || inpEmail.getText().equals("") || inpPassword.getText().equals("") || inpConfPassword.getText().equals("")) {
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
    private void handleSignIn(ActionEvent event) throws IOException, NoSuchAlgorithmException {
        if (verifIssets()) {
            int Strength = calculatePasswordStrength(inpPassword.getText());
            if (Strength >= 5) {
                if (inpPassword.getText().equals(inpConfPassword.getText())) {
                    FXMLLoader loader = new FXMLLoader((getClass().getClassLoader().getResource("fxml/SignUpFurtherInfo.fxml")));

                    System.out.println(inpUsername.getText() + "  " + inpEmail.getText() + "  " + inpPassword.getText());
                    SignUpFurtherInfoController sufic = loader.getController();
                    List<String> l = new ArrayList<String>();
                    l.add(inpUsername.getText());
                    l.add(inpEmail.getText());
                    l.add(encrypt(inpPassword.getText()));
                    Parent root = loader.load();

                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setUserData(l);
                    stage.setScene(new Scene(root));
                    stage.show();
                } else {
                    showAlertError("Password", "Password does not match");
                }
            } else {
                showAlertError("Password", "Password is too weak, try another one");
            }
        } else {
            showAlertError("Empty Field(s)", "Please insert information(s)");
        }
    }

    public void loadGui(ActionEvent event, String gui) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/" + gui + ".fxml"));
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (Exception e) {
        }
    }

    @FXML
    private void SignUpFB(ActionEvent event) {
    }

    @FXML
    private void SignUpGoogle(ActionEvent event) {
    }

    @FXML
    private void EmailInput(KeyEvent event) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(inpEmail.getText());
        if (matcher.matches()) {
            inpEmail.setFocusColor(Color.MEDIUMSPRINGGREEN);
            //return true;
        } else {
            inpEmail.setFocusColor(Color.CRIMSON);
            //return false;
        }
    }

    //mdp strong weak average
    private int calculatePasswordStrength(String password) {

        //total score of password
        int iPasswordScore = 0;

        if (password.length() < 8) {
            return 0;
        } else if (password.length() >= 10) {
            iPasswordScore += 2;
        } else {
            iPasswordScore += 1;
        }

        //if it contains one digit, add 2 to total score
        if (password.matches("(?=.*[0-9]).*")) {
            iPasswordScore += 2;
        }

        //if it contains one lower case letter, add 2 to total score
        if (password.matches("(?=.*[a-z]).*")) {
            iPasswordScore += 2;
        }

        //if it contains one upper case letter, add 2 to total score
        if (password.matches("(?=.*[A-Z]).*")) {
            iPasswordScore += 2;
        }

        //if it contains one special character, add 2 to total score
        if (password.matches("(?=.*[~!@#$%^&*()_-]).*")) {
            iPasswordScore += 2;
        }

        return iPasswordScore;
    }

    private void updatePasswordStrength(String value) {
        if (calculatePasswordStrength(value) < 5) {
            passwordStrength.setText("weak");
            passwordStrength.setTextFill(Color.CRIMSON);
            passwordStrengthLine.setEndX(0);
            passwordStrengthLine.setStroke(Color.CRIMSON);
            inpPassword.setFocusColor(Color.CRIMSON);
        } else if (calculatePasswordStrength(value) == 5) {
            passwordStrength.setText("average");
            passwordStrength.setTextFill(Color.ORANGE);
            passwordStrengthLine.setEndX(50);
            passwordStrengthLine.setStroke(Color.ORANGE);
            inpPassword.setFocusColor(Color.ORANGE);
        } else if (calculatePasswordStrength(value) >= 8) {
            passwordStrength.setText("strong");
            passwordStrength.setTextFill(Color.web("#14e11a"));
            passwordStrengthLine.setEndX(100);
            passwordStrengthLine.setStroke(Color.web("#14e11a"));
            inpPassword.setFocusColor(Color.web("#14e11a"));
        }
    }

    @FXML
    private void SetPasswordStrength(KeyEvent event) {
        if (inpPassword.getText().equals("")) {
            passwordStrength.setVisible(false);
            passwordStrengthLine.setVisible(false);
        } else {
            passwordStrength.setVisible(true);
            passwordStrengthLine.setVisible(true);
            updatePasswordStrength(inpPassword.getText());
        }
    }
}
