/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class ProfileTutorController implements Initializable {

    @FXML
    private ImageView inpPhoto;
    @FXML
    private JFXTextField inpFirstName;
    @FXML
    private JFXTextField inpLastName;
    @FXML
    private JFXComboBox<?> inpState;
    @FXML
    private JFXDatePicker inpDate;
    @FXML
    private JFXTextField inpNumber;
    @FXML
    private JFXTextField inpCin;
    @FXML
    private JFXTextField inpUsername;
    @FXML
    private JFXTextField inpEmail;
    @FXML
    private JFXTextField inpPassword;
    @FXML
    private JFXButton btnModify;
    @FXML
    private JFXButton btnCancel;
    @FXML
    private JFXComboBox<?> inpLang;
    @FXML
    private JFXComboBox<?> inpSubject;
    @FXML
    private JFXTextField inpPrice;
    @FXML
    private JFXButton inpCV;
    @FXML
    private JFXButton inpVideo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ModifyStuPRof(ActionEvent event) {
    }

    @FXML
    private void Cancel(ActionEvent event) {
    }

    @FXML
    private void GetCV(ActionEvent event) {
    }

    @FXML
    private void GetVideo(ActionEvent event) {
    }

    
}
