/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.rec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;

/**
 *
 * @author USER
 */
public class recService {
    
    private PreparedStatement pst;
    private Statement st;
    
    private ResultSet rs;
    
    private Connection connection;

    public recService() {
        connection=DataSource.getInstance().getCnx();
    }
    
    public void ajouterRec(rec r){
        String req=" insert into rec (nom_E, prenom_E, motif, description) values (?,?,?,?)";
        
        try {
            pst=connection.prepareStatement(req);
            pst.setString(1, r.getNom_E());
            pst.setString(2, r.getPrenom_E());
            pst.setString(3, r.getMotif());
            pst.setString(4, r.getDescription());

            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(recService.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    public List<rec> readAll(){
    
        String req= "select * from rec";
        List<rec> list=new ArrayList<>();
        
        try {
            st=connection.createStatement();
            rs=st.executeQuery(req);
            
            while(rs.next()){
                list.add(new rec(rs.getInt("id"), rs.getString(2), rs.getString("prenom_E"), rs.getString(4),
                        rs.getString(5) ));
                
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(recService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
        
    }
    
    
    
    public void modifierRec(rec r) {

        String req = "update rec set nom_E=?  where id=?";

        try {
            pst = connection.prepareStatement(req);
            
            pst.setString(1, r.getNom_E());


            pst.setInt(2, r.getId());
              

            
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(recService.class.getName()).log(Level.SEVERE, null, ex);
        }
     }


    
    public void deleteRec(rec r){
        
        String req = "DELETE FROM rec WHERE id=? ";
        
        try {
            pst=connection.prepareStatement(req);
            
            pst.setInt(1, r.getId());

            pst.executeUpdate();

            
        } catch (SQLException ex) {
            Logger.getLogger(recService.class.getName()).log(Level.SEVERE, null, ex);
        }

    
    }
    
    
}
