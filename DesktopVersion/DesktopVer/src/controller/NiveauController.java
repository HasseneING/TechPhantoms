/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopver;

import entite.matiere;
import entite.niveau;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import service.niveau_service;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class NiveauController  {

    @FXML
    private TextField Idn_tf;
    @FXML
    private TextField nomniveau_tf;
    @FXML
    private TextField id_teacher_tf;

     @FXML
    public void btnAjout1Clicked()  {
         niveau n = new niveau(nomniveau_tf.getText(), Integer.valueOf(id_teacher_tf.getText()));
         
    niveau_service ns =new niveau_service();
    ns.ajouterNiveau(n);
        
        
    }    
    @FXML
    public void btnSupp1Click(){
        System.out.println("Supprimer");
    }
    
}
