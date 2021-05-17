/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Connection.MyConnection;
import Entities.SendingMail;
import Entities.Student;
import services.StudentCrud;
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
public class VerifCodeStudentController implements Initializable {

    @FXML
    private JFXTextField inpCode;
    @FXML
    private JFXButton btnConfirm;

    private Student s;
    private int StudentReadID;
    @FXML
    private Label CodeInc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void ConfirmCode(ActionEvent event) {
        String CodeInserted = inpCode.getText();
        if (GetCode(StudentReadID).equals(CodeInserted)) {
            StudentCrud.ChangeStatus2Active(StudentReadID);
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
        String code = GetCode(StudentReadID);
        SendingMail mailing = new SendingMail();
        SendingMail.sendEmail(s, StudentReadID, code, "Student");
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
            pst.setString(2, "Student");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                code = rs.getString("code");
            }
            System.out.println("Code generated!");
        } catch (SQLException ex) {
            Logger.getLogger(StudentCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return code;
    }

    private String CodeVerified() {
        String code = GetCode(StudentReadID);
        String requete = "UPDATE verifcode SET DateActiv = ? where code = ? and role = 'Student'";

        try {
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            pst.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            pst.setString(2, code);
            pst.executeUpdate();
            System.out.println("Code Verified!");
        } catch (SQLException ex) {
            Logger.getLogger(StudentCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return code;
    }

    @FXML
    private void SetLabelVis(KeyEvent event) {
        CodeInc.setVisible(false);
    }

    @FXML
    private void ref(ActionEvent event) {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s = (Student) window.getUserData();
        StudentReadID = StudentCrud.GetStudentID(s);
        System.out.println(s.getFirst_name());
        String code = GenerateCode();
        SendingMail.sendEmail(s, StudentReadID, code, "Student");
    }

}
