/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopver;

import controller.Matiere_intController;
import controller.NiveauController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class HomeController implements Initializable {

    @FXML
    private Button mat_btn;
    @FXML
    private Button niv_btn;
    @FXML
    private Label tnom;
    private Label tnom1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
      public void setTnom1(Label tnom) {
        this.tnom1=tnom; 
    }

    @FXML
      private void matiere (ActionEvent event) {
        try {
            //   try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("matiere_int.fxml"));
            
            
            Parent root = (Parent) fxmlLoader.load();
            Matiere_intController apc=fxmlLoader.getController();
           apc.setTnom1( tnom);
           tnom.getScene().setRoot(root);
           // stage.setTitle("hello");
            //       stage.setScene(new Scene(root));
          //  stage.show();
              } catch (IOException e) {
            System.out.println("can't load new window");
        }
        
    }
      @FXML
      private void niveau (ActionEvent event) {
        try {
            //   try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Niveau.fxml"));
            
            
            Parent root = (Parent) fxmlLoader.load();
            NiveauController apc=fxmlLoader.getController();
           apc.setTnom1( tnom);
           tnom.getScene().setRoot(root);
           // stage.setTitle("hello");
            //       stage.setScene(new Scene(root));
          //  stage.show();
              } catch (IOException e) {
            System.out.println("can't load new window");
        }
        
    }
      
}
