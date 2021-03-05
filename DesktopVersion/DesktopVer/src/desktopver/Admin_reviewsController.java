/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package desktopver;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import desktopver.chart_stats;
import entite.students_feedback;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.controlsfx.control.Rating;
import service.matiere_service;
import service.scheduleService;
import service.students_feedback_service;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author t4h4
 */
public class Admin_reviewsController implements Initializable {
 private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    private Connection conn;

    @FXML
    private Button lirebt;
           

    @FXML
    private TableView<students_feedback> tablerevieww;
    @FXML
    private TableColumn<students_feedback, ?> clstudentID;
    @FXML
    private TableColumn<students_feedback,?>  cltutorid;
    @FXML
    private TableColumn<students_feedback, ?>  clcomment;

    @FXML
    private TableColumn<students_feedback, Date> cltodayD;
   
    @FXML
    private TableColumn<students_feedback, ?> clnote;
    @FXML
    private TableColumn<students_feedback, ?> clrecomm;
    @FXML
    private TableColumn<students_feedback, Rating> starsrat;
    @FXML
    private TableColumn<students_feedback, ?> reviewID;
    @FXML
    private Button deletbtn;
    @FXML
    private ChoiceBox  selectshow;
    @FXML
    private Button stats;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       lire() ; 
       
       ObservableList<String> selectshowList = FXCollections.observableArrayList("Show students reviews","Show tutors reviews");
       selectshow.setItems(selectshowList);
       selectshow.setValue("Show students reviews") ; 
       
    }   
     @FXML
    public void lire(){
    students_feedback_service ok = new students_feedback_service();
      
       
   
         clstudentID.setCellValueFactory(new PropertyValueFactory("studentID"));
          cltutorid.setCellValueFactory(new PropertyValueFactory("tutorID"));
           clcomment.setCellValueFactory(new PropertyValueFactory("comment"));
            cltodayD.setCellValueFactory(new PropertyValueFactory("todayD"));
              //clnbstars.setCellValueFactory(new PropertyValueFactory("nbstars"));
                    clnote.setCellValueFactory(new PropertyValueFactory("note"));
                 clrecomm.setCellValueFactory(new PropertyValueFactory("recommandation")); 
                 starsrat.setCellValueFactory(new PropertyValueFactory("starRate"));  
                   reviewID.setCellValueFactory(new PropertyValueFactory("reviewid"));
                 
                 
               
                 
                 
                 
                 
        tablerevieww.setItems(ok.showReviews());
        tablerevieww.refresh();
}

    @FXML
    public  void deletselected() {

    ObservableList<students_feedback> itemlist ;  
itemlist = tablerevieww.getSelectionModel().getSelectedItems() ;  
//System.out.print(itemlist.get(0).getReviewid()) ; 
 students_feedback_service ok = new students_feedback_service();
   ok.deletedrow(itemlist.get(0).getReviewid()) ;
    lire() ; 

 
  
}

    @FXML
    private void statss()  {
        
    }











}
