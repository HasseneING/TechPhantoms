/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopver;

import entite.students_feedback;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.scheduleService;
import service.students_feedback_service;
import service.personnnnn;

/**
 * FXML Controller class
 *
 * @author t4h4
 */
public class Admin_reviewsController implements Initializable {

    @FXML
    private Button lirebt;
    @FXML
    private TableView<personnnnn> tablerevieww;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }   
     @FXML
    public void lire(){
    students_feedback_service sS = new students_feedback_service();
       // clid.setCellValueFactory(new PropertyValueFactory<>("id"));
       
//        clnote.setCellValueFactory(new PropertyValueFactory<>("name"));
//        clrec.setCellValueFactory(new PropertyValueFactory<>("lastname"));
//        clrating.setCellValueFactory(new PropertyValueFactory<>("email"));
//         clcomment.setCellValueFactory(new PropertyValueFactory<>("comment"));
//        cl_tutor.setCellValueFactory(new PropertyValueFactory<>("tutor id"));
//        cl_student.setCellValueFactory(new PropertyValueFactory<>("student if"));
        
        tablerevieww.setItems(sS.showReviews());
        tablerevieww.refresh();
}}
 //public students_feedback( int studentID, int tutorID, String comment , Date todayD ,  double nbstars, int note,boolean recommandation ) 