/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.matiere;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.DataSource;

/**
 *
 * @author PC
 */
public class matiere_service {
    public ObservableList<matiere> data = FXCollections.observableArrayList() ; 

    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    private Connection conn;

    public matiere_service() {
        conn = DataSource.getInstance().getCnx();
    }

    public void ajouterMatiere(matiere m) {
        String req = "insert into matiere (nom,type,disponibilité,id_teacher) values (?,?,?,?)";

        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, m.getNom());
            pst.setString(2, m.getType());
            pst.setString(3, m.getDisponibilité());
            pst.setInt(4, m.getId_teacher());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(matiere_service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modifierMatiere(matiere m) {

        String req = "update matiere set nom=? , type=?, disponibilité=? , id_teacher=? where id=?";

        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, m.getNom());
            pst.setString(2, m.getType());
            pst.setString(3, m.getDisponibilité());
            pst.setInt(4, m.getId_teacher());
            pst.setInt(5, m.getId());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(matiere_service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void supprimerMatiere(matiere m) {
        String req = "delete from matiere  where id=?";

        try {
            pst = conn.prepareStatement(req);

            pst.setInt(1, m.getId());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(matiere_service.class.getName()).log(Level.SEVERE, null, ex);

        }

    }
   /* public ObservableList<matiere> afficherMatiere (){
        
       

    String sql= "select * from matiere";
    try {
    ste=conn.prepareStatement(sql);
    rs=pst.executeQuery();
    while(rs.next()){
        data.add (new matiere (rs.getString("nom"),rs.getString("type"),rs.getString("disponibilité"),rs.getInt("id_teacher")) );
    }
    } catch (SQLException e){ System.out.print(e); 
    
    } return data ; 
    
   
}*/
 
public ObservableList<matiere> readAll() throws SQLException {
ObservableList<matiere> arr =FXCollections.observableArrayList();
ste=conn.createStatement();
    ResultSet rs=ste.executeQuery("select * from matiere ");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String nom=rs.getString(2);
               String type=rs.getString(3);
               String disponibilite=rs.getString(4);
               int idt=rs.getInt(5);
               matiere m=new matiere(id, nom, type, disponibilite,idt);
     arr.add(m);
     }
    return arr;
    }
 

    public List<matiere> rechercher(matiere m) throws SQLException {
        List<matiere> list=new ArrayList<>();
        String req = "select * from `matiere` where `nom` = '"+m.getNom()+"';";
        try {
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
               list.add(new matiere (rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4), rs.getInt(5)));
            }
        } 
        catch (SQLException ex) {
            System.out.println(ex);        
        }
        return list;
}
     public ObservableList<matiere> readAllTrie() throws SQLException {
        ObservableList<matiere> arr =FXCollections.observableArrayList();
        String req = "select * from matiere order by type DESC";
       
        try
        {
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next())
                arr.add(new matiere (rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getInt(5)));
        }
        catch(SQLException ex)
        {
            System.out.println(ex);
        }
        return arr;
    }
   


}
