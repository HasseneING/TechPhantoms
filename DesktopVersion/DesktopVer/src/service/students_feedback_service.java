/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.students_feedback;
import entite.matiere;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DataSource;
/**
 *
 * @author t4h4
 */
public class students_feedback_service {
     private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    private Connection conn;

    public students_feedback_service() {
        conn = DataSource.getInstance().getCnx();
    }

    public void ajouterStudentReview(students_feedback stud_rev) {
//        String req = "insert into students_feedback (comment,note,recommandation,rating,id_tutor,id_sutdent) values (?,?,?,?,?,?)";
//
//        try {
//           
//            pst = conn.prepareStatement(req);
//            pst.setString(1, stud_rev.getComment());
//            pst.setInt(2, stud_rev.getnote());
//            pst.setBoolean(3, stud_rev.getrecommandation());
//         //  pst.setDate(4, (java.sql.Date) stud_rev.getDate());
//            pst.setDouble(4, stud_rev.getnbstars());
//            pst.setInt(5, stud_rev.gettutorID());
//            pst.setInt(6, stud_rev.getstudentID());
//            pst.executeUpdate();
//
//        } catch (SQLException ex) {
//            Logger.getLogger(matiere_service.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
      public ObservableList<personnnnn> showReviews() { //From Session Variable ? 
        String req = "SELECT * from students_feedback where rating=?";
        Date da = java.util.Calendar.getInstance().getTime();
     
java.sql.Date d = new java.sql.Date(da.getTime());
        ObservableList<personnnnn> reviewList = FXCollections.observableArrayList();

    //    try {
      //      pst = conn.prepareStatement(req);
        //    pst.setInt(1, 1);
          //  rs = pst.executeQuery();

          //  while (rs.next()) {
              //  int dateID = rs.getInt("dateID");
             //   int teachID = rs.getInt("dateID");
              //  boolean dispo = rs.getBoolean("disponibility");
         //       Timestamp startTime = rs.getTimestamp("startTime" );
              //  Timestamp endTime = rs.getTimestamp("endTime");     public students_feedback( int studentID, int tutorID, String comment , Date todayD, double nbstars, int note,boolean recommandation ) {

                reviewList.add(new personnnnn("10","22","dddd")); 
                //public students_feedback( int studentID, int tutorID, String comment , Date todayD ,  double nbstars, int note,boolean recommandation ) 
               System.out.println("ffff");
        //    }
    //    } catch (SQLException ex) {
      ///      Logger.getLogger(matiere_service.class.getName()).log(Level.SEVERE, null, ex);
      //  }
        return reviewList;
    }
}

    
