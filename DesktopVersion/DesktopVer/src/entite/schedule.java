/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

/**
 *
 * @author Administrateur
 */
public class schedule {
    private int scheduleID,teacherID,dateID;

    public schedule(int scheduleID, int teacherID, int dateID) {
        this.scheduleID = scheduleID;
        this.teacherID = teacherID;
        this.dateID = dateID;
    }

    public schedule() {
    }

    public int getScheduleID() {
        return scheduleID;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public int getDateID() {
        return dateID;
    }
    
    
}
