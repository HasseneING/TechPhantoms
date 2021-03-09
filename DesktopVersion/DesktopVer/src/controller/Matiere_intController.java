/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entite.matiere;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import service.matiere_service;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class Matiere_intController implements Initializable {
public ObservableList<matiere> data = FXCollections.observableArrayList() ; 

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
    private TableColumn<matiere, Integer > id_v;
    private TableColumn<matiere, String> nom_v;
    private TableColumn<matiere, String> type_v;
    private TableColumn<matiere, String> dispo_v;
    @FXML
    private Button modif_btn;
    private TableView<matiere> matiereview;
    private TableColumn<matiere, Integer> id_teacher_v;
    @FXML
    private Label tnom1;
    @FXML
    private ListView<matiere> listmatiere;
    @FXML
    private Button telecharger_;
    @FXML
    private TextField rechercher;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         matiere m =new matiere();
            matiere_service ser = new matiere_service();
        /*id_v.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_v.setCellValueFactory(new PropertyValueFactory<>("nom"));
            type_v.setCellValueFactory(new PropertyValueFactory<>("type"));
            dispo_v.setCellValueFactory(new PropertyValueFactory<>("disponibilité"));*/
            
        try {
            data = ser.readAll();
            System.out.println(data.size());
        } catch (SQLException ex) {
            System.out.println(ex);        }
        
        listmatiere.setItems((ObservableList<matiere>) data);
        //matiereview.setEditable(true);

    }    

  
    @FXML
    public void btnAjoutClicked() throws SQLException  {
         matiere m = new matiere(nom_tf.getText(), type.getText(), dispo_tf.getText(), Integer.valueOf(id_teach_tf.getText()));

    matiere_service ms =new matiere_service();
    ms.ajouterMatiere(m);
        data.clear();
      data = ms.readAll();
         listmatiere.setItems((ObservableList<matiere>) data);
    }    
    @FXML
            
    public void btnSuppClick() throws SQLException{
         matiere m = new matiere(Integer.valueOf(id_tf.getText()));
       matiere_service ms=new matiere_service();
       ms.supprimerMatiere(m) ;
        data.clear();
      data = ms.readAll();
         listmatiere.setItems((ObservableList<matiere>) data);
    } 
    @FXML
    public void btnmodifClick() throws SQLException{
         matiere m = new matiere(Integer.valueOf(id_tf.getText()),nom_tf.getText(), type.getText(), dispo_tf.getText(), Integer.valueOf(id_teach_tf.getText()));
        matiere_service ms=new matiere_service();
        ms.modifierMatiere(m) ;
         data.clear();
      data = ms.readAll();
         listmatiere.setItems((ObservableList<matiere>) data);
    }

    private void afficherM() throws SQLException {
          matiere_service ms=new matiere_service();
         System.out.print("ee");
         
       id_v.setCellValueFactory (new PropertyValueFactory <matiere,Integer>("Id"));
    
    nom_v.setCellValueFactory (new PropertyValueFactory <matiere,String>("nom"));
    
    type_v.setCellValueFactory (new PropertyValueFactory <matiere,String>("type"));
    
    dispo_v.setCellValueFactory (new PropertyValueFactory <matiere,String>("diponibilité"));

    id_teacher_v.setCellValueFactory (new PropertyValueFactory <matiere,Integer>("Id"));
        matiereview.setItems((ObservableList<matiere>) ms.readAll());
    }

    public void setTnom1(Label tnom) {
        this.tnom1=tnom; 
    }
 
    
    
    
    @FXML
    private void telecharger(ActionEvent event) throws IOException,FileNotFoundException, DocumentException, SQLException  {
        
            String file_name="C:\\Users\\PC\\Desktop\\PiDev\\TechPhantoms.pdf";
            Document document = new Document();
            
            PdfWriter.getInstance(document, new FileOutputStream(file_name));
            
            document.open();
            DataSource db = new DataSource();
            Connection cnx = db.getCnx();
            PreparedStatement ps =null;
            ResultSet rs =null;
            String req = "Select * from matiere ";
            ps = cnx.prepareCall(req);
            rs=ps.executeQuery();
            PdfPTable t = new PdfPTable(5);
            PdfPCell c1 = new PdfPCell(new Phrase("Id"));
            t.addCell(c1);
            PdfPCell c2 = new PdfPCell(new Phrase("Nom"));
            t.addCell(c2);
            PdfPCell c3 = new PdfPCell(new Phrase("Type"));
            t.addCell(c3);
            PdfPCell c4 = new PdfPCell(new Phrase("Disponibilty"));
            t.addCell(c4);
             PdfPCell c5 = new PdfPCell(new Phrase("Id_teacher"));
            t.addCell(c5);
            t.setHeaderRows(1);
          try {
            while(rs.next()){
                t.addCell(rs.getString(1));
                t.addCell(rs.getString(2));
                t.addCell(rs.getString(3));
                t.addCell(rs.getString(4));
                t.addCell(rs.getString(5));
                document.add(t);
            }
            document.close();
            System.out.println("finished");
        } catch (DocumentException ex) {
            System.out.println(ex); 
        }
  
    }
@FXML
    private void rechercher(ActionEvent event) {
        
        try {
            matiere m = new matiere();
            m.setNom(rechercher.getText());
            System.out.println(m);
            matiere_service ser = new matiere_service();
            if (!(ser.rechercher(m).isEmpty())) {
                data.clear();
               data.addAll(ser.rechercher(m));
            listmatiere.setItems((ObservableList<matiere>) data);
            } else if (ser.rechercher(m).isEmpty()) {
                System.out.println("vide");
                JOptionPane.showMessageDialog(null, "Veuillez sélectionner le nom du centre à chercher");
                data = ser.readAll();
                listmatiere.setItems((ObservableList<matiere>) data);
            }
            System.out.println(ser.rechercher(m));
        } catch (SQLException ex) {
            System.out.println(ex);        }

    }







}













