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
import utils.DataSource;

/**
 *
 * @author PC
 */
public class matiere_service {

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

}
