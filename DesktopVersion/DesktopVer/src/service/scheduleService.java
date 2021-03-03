/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.dateEmploi;
import entite.matiere;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DataSource;

/**
 *
 * @author Administrateur
 */
public class scheduleService {

    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    private Connection conn;

    public scheduleService() {
        conn = DataSource.getInstance().getCnx();
    }

    public void ajouterDate(dateEmploi DateEmp) {
        String req = "insert into date_emploi (teacherID,disponibility,startTime,endTime) values (?,?,?,?)";

        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, 1);
            pst.setBoolean(2, true);
            pst.setTimestamp(3, DateEmp.getStartTime());
            pst.setTimestamp(4, DateEmp.getEndTime());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(matiere_service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<dateEmploi> getDatesForSchedule(int teacherID) { //From Session Variable ? 
        String req = "SELECT * from date_emploi where teacherID=?";
        ObservableList<dateEmploi> dateList = FXCollections.observableArrayList();

        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, 1);
            rs = pst.executeQuery();

            while (rs.next()) {
                int dateID = rs.getInt("dateID");
                int teachID = rs.getInt("dateID");
                boolean dispo = rs.getBoolean("disponibility");
                Timestamp startTime = rs.getTimestamp("startTime");
                Timestamp endTime = rs.getTimestamp("endTime");
                dateList.add(new dateEmploi(dateID,dispo,startTime,endTime));
                System.out.println(dateID);
            }
        } catch (SQLException ex) {
            Logger.getLogger(matiere_service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dateList;
    }

}
