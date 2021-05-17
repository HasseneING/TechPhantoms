/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Connection.MyConnection;
import Entities.SendingMail;
import Entities.Tutor;
import services.TutorCrud;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class VerifCodeTutorController implements Initializable {

    @FXML
    private Label CodeInc;
    @FXML
    private JFXTextField inpCode;
    @FXML
    private JFXButton btnConfirm;

    private Tutor t;
    private int TutorReadID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void SetLabelVis(KeyEvent event) {
        CodeInc.setVisible(false);
    }

    @FXML
    private void ConfirmCode(ActionEvent event) {
        String CodeInserted = inpCode.getText();
        if (GetCode(TutorReadID).equals(CodeInserted)) {
            TutorCrud.ChangeStatus2Active(TutorReadID);
            CodeVerified();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/GUI/login.fxml"));
                Scene scene = new Scene(root);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                VerifCodeStudentController vicsc = new VerifCodeStudentController();
                window.setScene(scene);
                window.show();
            } catch (IOException ex) {
                Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            CodeInc.setVisible(true);
        }
    }

    @FXML
    private void ResendCode(ActionEvent event) {
        String code = GetCode(TutorReadID);
        SendingMail mailing = new SendingMail();
        SendingMail.sendEmail(t, TutorReadID, code, "Tutor");
    }

    @FXML
    private void ref(ActionEvent event) {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        t = (Tutor) window.getUserData();
        TutorReadID = TutorCrud.GetTutorID(t);
        System.out.println(t.getFirst_name());
        String code = GenerateCode();
        SendingMail mailing = new SendingMail();
        SendingMail.sendEmail(t, TutorReadID, code, "Tutor");
    }

    public String GenerateCode() {
        Random rand = new Random(System.currentTimeMillis());

        int code = rand.nextInt(999999);
        return String.format("%06d", code);
    }

    private String GetCode(int id) {
        String code = null;
        String requete = "SELECT code FROM verifcode where id_user = ? and role = ?";

        try {
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            pst.setInt(1, id);
            pst.setString(2, "Tutor");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                code = rs.getString("code");
            }
            System.out.println("Code generated!");
        } catch (SQLException ex) {
            Logger.getLogger(VerifCodeTutorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return code;
    }

    private String CodeVerified() {
        String code = GetCode(TutorReadID);
        String requete = "UPDATE verifcode SET DateActiv = ? where code = ? and role = 'Tutor'";

        try {
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            pst.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            pst.setString(2, code);
            pst.executeUpdate();
            System.out.println("Code Verified!");
        } catch (SQLException ex) {
            Logger.getLogger(VerifCodeTutorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return code;
    }
}
