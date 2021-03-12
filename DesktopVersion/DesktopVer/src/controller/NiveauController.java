/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entite.niveau;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import service.niveau_service;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class NiveauController  {

   
    public ObservableList<niveau> data = FXCollections.observableArrayList() ; 
    @FXML
    private TextField Idn_tf;
    @FXML
    private TextField nomniveau_tf;
    @FXML
    private TextField id_teacher_tf;
    @FXML
    private Button Ajout1;
    @FXML
    private Button Supp1;
    @FXML
    private Button modif1;
    @FXML
    private Label tnom1;
    @FXML
    private ListView<niveau> listniveau;
    @FXML
    private TextField recherchetf;
    @FXML
    private Button rechercher;
    @FXML
    private Button sort_btn;


     public void initialize(URL url, ResourceBundle rb) {
         niveau n =new niveau();
            niveau_service ser = new niveau_service();
      
        try {
            data = ser.readAll();
            System.out.println(data.size());
            listniveau.setItems((ObservableList<niveau>) data);
        } catch (SQLException ex) {
            System.out.println(ex);        }
        
        
        

    }    
    
    
    
    
    
    
    
    
    
    @FXML
    public void btnAjout1Clicked() throws SQLException  {
         niveau n = new niveau(nomniveau_tf.getText(), Integer.valueOf(id_teacher_tf.getText()));
         
    niveau_service ns =new niveau_service();
    ns.ajouterNiveau(n);
       data.clear();
      data = ns.readAll();
         listniveau.setItems((ObservableList<niveau>) data); 
        
    }    
    @FXML
    public void btnSupp1Click() throws SQLException{
         
          niveau n = new niveau(Integer.valueOf(Idn_tf.getId()));
          niveau_service ns =new niveau_service();
          ns.supprimerNiveau(n);
          data.clear();
      data = ns.readAll();
         listniveau.setItems((ObservableList<niveau>) data);
          
    }
    public void btnmodif1Click() throws SQLException{
         niveau n = new niveau(Integer.valueOf(Idn_tf.getText()),nomniveau_tf.getText(),  Integer.valueOf(id_teacher_tf.getText()));
        niveau_service ns=new niveau_service();
       ns.modifierNiveau(n) ;
        data.clear();
      data = ns.readAll();
         listniveau.setItems((ObservableList<niveau>) data);
    
    }

    public void setTnom1(Label tnom) {
        this.tnom1=tnom; //To change body of generated methods, choose Tools | Templates.
    }
    
    @FXML
    private void rechercher(ActionEvent event) {
        
        try {
            niveau n = new niveau();
            n.setNom_niv(recherchetf.getText());
            System.out.println(n);
            niveau_service ser = new niveau_service();
            if (!(ser.rechercher(n).isEmpty())) {
                data.clear();
               data.addAll(ser.rechercher(n));
            listniveau.setItems((ObservableList<niveau>) data);
            } else if (ser.rechercher(n).isEmpty()) {
               // System.out.println("vide");
                //JOptionPane.showMessageDialog(null, "Veuillez sélectionner le nom du niveau à chercher");
                data = ser.readAll();
                listniveau.setItems((ObservableList<niveau>) data);
            }
            System.out.println(ser.rechercher(n));
        } catch (SQLException ex) {
            System.out.println(ex);        }
    
    
    } 
    
    
    @FXML
     public void btnSortClicked() throws SQLException  {
        niveau n =new niveau();
            niveau_service ser = new niveau_service();
         data = ser.readAllTrie();
        data.clear();
      data = ser.readAll();
         
            
            listniveau.setItems((ObservableList<niveau>) data);
     
     
     
     }
}
