/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Taha;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author t4h4
 */
public class ShowReviewsController implements Initializable {

    @FXML
    private ListView<String> lstVV;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      ObservableList<String> List = FXCollections.observableArrayList("Show students reviews","Show tutors reviews");
      
       lstVV.setItems(List);
    }    
    
    
    static class Cell extends ListCell<String> {
        Button  btnn = new Button("ookii")  ;       
        
        
        public Cell() {
            
            super() ; 
            
        }
        
        
    }
}
