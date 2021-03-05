/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.util.Date;
import org.controlsfx.control.Rating;

/**
 *
 * @author t4h4
 */
public class tutors_feedback {
     public String comment ; 
    public int reviewid ; 
    public boolean recommandation;
    public int note ; 
    public double nbstars ;
    public Date todayD ;
    public int  studentID,tutorID ; 
    public Rating starRate;
public tutors_feedback (int studentID, int tutorID, String comment , Date todayD, double nbstars, int note,boolean recommandation ) {
        this.studentID = studentID;
        this.tutorID = tutorID;
        this.comment = comment;
        this.todayD = todayD;
        this.nbstars = nbstars;
        this.note = note;
        this.recommandation = recommandation;
        this.starRate= new Rating(); }
public tutors_feedback ( int reviewid , int studentID, int tutorID, String comment , Date todayD, double nbstars, int note,boolean recommandation ) {
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

    public String getComment() {
        return comment;
    }

    public int getReviewid() {
        return reviewid;
    }

    public boolean getRecommandation() {
        return recommandation;
    }

    public int getNote() {
        return note;
    }

    public double getNbstars() {
        return nbstars;
    }

    public Date getTodayD() {
        return todayD;
    }

    public int getStudentID() {
        return studentID;
    }

    public int getTutorID() {
        return tutorID;
    }

    public Rating getStarRate() {
        return starRate;
    }
    
}
