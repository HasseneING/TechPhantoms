/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entite.matiere;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
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
    private TableColumn<matiere, Integer > id_v;
    @FXML
    private TableColumn<matiere, String> nom_v;
    @FXML
    private TableColumn<matiere, String> type_v;
    @FXML
    private TableColumn<matiere, String> dispo_v;
    @FXML
    private TableColumn<matiere, Integer> id_techer_v;
    @FXML
    private Button modif_btn;

  
    @FXML
    public void btnAjoutClicked()  {
         matiere m = new matiere(nom_tf.getText(), type.getText(), dispo_tf.getText(), Integer.valueOf(id_teach_tf.getText()));

    matiere_service ms =new matiere_service();
    ms.ajouterMatiere(m);
        
        
    }    
    @FXML
            
    public void btnSuppClick(){
         matiere m = new matiere(Integer.valueOf(id_tf.getText()));
       matiere_service ms=new matiere_service();
       ms.supprimerMatiere(m) ;
    } 
    @FXML
    public void btnmodifClick(){
         matiere m = new matiere(Integer.valueOf(id_tf.getText()),nom_tf.getText(), type.getText(), dispo_tf.getText(), Integer.valueOf(id_teach_tf.getText()));
        matiere_service ms=new matiere_service();
       ms.modifierMatiere(m) ;
    }
}
