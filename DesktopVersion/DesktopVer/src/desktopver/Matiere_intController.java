/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopver;

import entite.matiere;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import service.matiere_service;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class Matiere_intController {

    @FXML
    private TextField type;
    @FXML
    private Button ajout_btn;
    @FXML
    private Button suppression_btn;
    @FXML
    private TextField id_tf;
    @FXML
    private TextField nom_tf;
    @FXML
    private TextField dispo_tf;
    @FXML
    private TextField id_teach_tf;

  
    @FXML
    public void btnAjoutClicked()  {
         matiere m = new matiere(nom_tf.getText(), type.getText(), dispo_tf.getText(), Integer.valueOf(id_teach_tf.getText()));

    matiere_service ms =new matiere_service();
    ms.ajouterMatiere(m);
        
        
    }    
    
}
