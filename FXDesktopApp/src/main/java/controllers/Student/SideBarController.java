/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.Student;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class SideBarController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void home(MouseEvent event) {
        bp.setCenter(ap);
    }

    @FXML
    private void page1(MouseEvent event) throws MalformedURLException {
        URL root_url = new File("src/main/java/fxml/Student/page1.fxml").toURI().toURL();
        loadPage(root_url);
    }

    @FXML
    private void page2(MouseEvent event) throws MalformedURLException {
        URL root_url = new File("src/main/java/fxml/Student/page2.fxml").toURI().toURL();
        loadPage(root_url);
    }

    
    
   private void loadPage(URL url){
        Parent root = null;
        
        try {
            root = FXMLLoader.load(url);
        } catch (IOException ex) {
            Logger.getLogger(SideBarController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        bp.setCenter(root);
    }


    
}
