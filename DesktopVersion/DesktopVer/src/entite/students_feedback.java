/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.sql.Timestamp;
import java.util.Date;
import org.controlsfx.control.Rating;

/**
 *
 * @author t4h4
 */
public class students_feedback {
    
    public String comment ; 
    public int reviewid ; 
    public boolean recommandation;
    public int note ; 
    public double nbstars ;
    public Date todayD ;
    public int  studentID,tutorID ; 
    public Rating starRate;

    public Rating getStarRate() {
      
        starRate.setRating(nbstars);
        return starRate;
    }

    public int getReviewid() {
        return reviewid;
    }

    public students_feedback( int studentID, int tutorID, String comment , Date todayD, double nbstars, int note,boolean recommandation ) {
        this.studentID = studentID;
        this.tutorID = tutorID;
        this.comment = comment;
        this.todayD = todayD;
        this.nbstars = nbstars;
        this.note = note;
        this.recommandation = recommandation;
        this.starRate= new Rating(); 
     
    }
 public students_feedback( int reviewid , int studentID, int tutorID, String comment , Date todayD, double nbstars, int note,boolean recommandation ) {
        this.studentID = studentID;
        this.tutorID = tutorID;
        this.comment = comment;
        this.todayD = todayD;
        this.nbstars = nbstars;
        this.note = note;
        this.recommandation = recommandation;
        this.starRate= new Rating(); 
        this.reviewid=reviewid ; 
     
    }
  public students_feedback () {this.studentID = 55;
//        this.tutorID = 55;
//        this.comment = "ee";
//          Date da = java.util.Calendar.getInstance().getTime();
//      
//java.sql.Date d = new java.sql.Date(da.getTime());
//        this.todayD = todayD;
//        this.nbstars = 5;
//        this.note = 2;
//        this.recommandation = false;
    }
    

    public int getStudentID() {
        return studentID;
    }
    public void setstudentID(int studentID) {
        this.studentID = studentID;
    }

    
public int getTutorID() {
        return tutorID;
    }
 public void settutorID(int tutorID) {
        this.tutorID = tutorID;
    }
public boolean getRecommandation() {
        return recommandation;
    }
public void setrecommandation(boolean recommandation) {
        this.recommandation = recommandation;
    }
public double getNbstars() {
        return nbstars;
    }
public void setnbstars(double nbstars) {
        this.nbstars = nbstars;
    }
public int getNote() {
        return note;
    }
public void setnote(int note) {
        this.note = note;
    }
public Date getTodayD() {
        return todayD;
    }
public void setDate(Date todayD) {
        this.todayD = todayD;
    }
public String getComment() {
        return comment;
    }
public void setComment(String comment) {
        this.comment = comment;
    }
}
