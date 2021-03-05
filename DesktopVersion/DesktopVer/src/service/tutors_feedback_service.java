/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import entite.students_feedback;
import entite.tutors_feedback;
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
 * @author t4h4
 */
public class tutors_feedback_service {
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    private Connection conn;

    public tutors_feedback_service() {
        conn = DataSource.getInstance().getCnx();
    }
     public void ajoutertutorReview(tutors_feedback stud_rev) {
        String req = "insert into tutors_feedback (comment,note,recommandation,rating,id_tutor,id_student) values (?,?,?,?,?,?)";

        try {
           
            pst = conn.prepareStatement(req);
            pst.setString(1, stud_rev.getComment());
            pst.setInt(2, stud_rev.getNote());
            pst.setBoolean(3, stud_rev.getRecommandation());
          // pst.setDate(4, (java.sql.Date) stud_rev.getTodayD());
            pst.setDouble(4, stud_rev.getNbstars());
            pst.setInt(5, stud_rev.getTutorID());
            pst.setInt(6, stud_rev.getStudentID());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(matiere_service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
