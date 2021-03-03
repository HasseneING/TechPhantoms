/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.niveau;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            Logger.getLogger(matiere_service.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    
    
    }
    }

