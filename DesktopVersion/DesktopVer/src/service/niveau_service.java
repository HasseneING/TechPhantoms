/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.matiere;
import entite.niveau;
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
import utils.DataSource;

/**
 *
 * @author PC
 */
public class niveau_service {

    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    private Connection conn;

    public niveau_service() {
        conn = DataSource.getInstance().getCnx();
    }

    public void ajouterNiveau(niveau n) {
        String req = "insert into niveau (nom_niv,id_teacher) values (?,?)";

        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, n.getNom_niv());
            pst.setInt(2, n.getId_teacher());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(niveau_service.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void modifierNiveau(niveau n) {

        String req = "update niveau set nom_niv=? , id_teacher=? where id_n=?";

        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, n.getNom_niv());

            pst.setInt(2, n.getId_teacher());
            pst.setInt(3, n.getId_n());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(niveau_service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void supprimerNiveau(niveau n) {
        String req = "delete from niveau  where id_n=?";

        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, n.getId_n());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(niveau_service.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    public ObservableList<niveau> readAll() throws SQLException {
ObservableList<niveau> arr =FXCollections.observableArrayList();
ste=conn.createStatement();
    ResultSet rs=ste.executeQuery("select * from niveau");
     while (rs.next()) {                
               
               String nom=rs.getString(2);
               int idt=rs.getInt(3);
               niveau n=new niveau( nom,idt);
     arr.add(n);
     }
    return arr;
    }
    
      public List<niveau> rechercher(niveau n) throws SQLException {
        List<niveau> list=new ArrayList<>();
        String req = "select * from niveau  where nom_niv = '"+n.getNom_niv()+"';";
        try {
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
               list.add(new niveau (rs.getInt(1), rs.getString(2), rs.getInt(3)));
            }
        } 
        catch (SQLException ex) {
            System.out.println(ex);        
        }
        return list;
}

      
       public ObservableList<niveau> readAllTrie() throws SQLException {
        ObservableList<niveau> arr =FXCollections.observableArrayList();
        String req = "select * from niveau order by nom_niv DESC";
       
        try
        {
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next())
                arr.add(new niveau (rs.getInt(1), rs.getString(2), rs.getInt(3)));
        }
        catch(SQLException ex)
        {
            System.out.println(ex);
        }
        return arr;
    }
    
    
    
    
    
}
