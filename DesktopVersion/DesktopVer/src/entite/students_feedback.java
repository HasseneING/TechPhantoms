/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author t4h4
 */
public class students_feedback {
    
    public String comment;
    public boolean recommandation;
    public int note ; 
    public double nbstars ;
    public Date todayD ;
    public int studentID, tutorID ; 

    public students_feedback( int studentID, int tutorID, String comment , Date todayD, double nbstars, int note,boolean recommandation ) {
        this.studentID = studentID;
        this.tutorID = tutorID;
        this.comment = comment;
        this.todayD = todayD;
        this.nbstars = nbstars;
        this.note = note;
        this.recommandation = recommandation;
    }

    public students_feedback () {
    }
    

    public int getstudentID() {
        return studentID;
    }
    public void setstudentID(int studentID) {
        this.studentID = studentID;
    }

    
public int gettutorID() {
        return tutorID;
    }
 public void settutorID(int tutorID) {
        this.tutorID = tutorID;
    }
public boolean getrecommandation() {
        return recommandation;
    }
public void setrecommandation(boolean recommandation) {
        this.recommandation = recommandation;
    }
public double getnbstars() {
        return nbstars;
    }
public void setnbstars(double nbstars) {
        this.nbstars = nbstars;
    }
public int getnote() {
        return note;
    }
public void setnote(int note) {
        this.note = note;
    }
public Date getDate() {
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
