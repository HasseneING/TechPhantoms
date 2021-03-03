/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.sql.Timestamp;

/**
 *
 * @author Administrateur
 */
public class dateEmploi {
    private int dateID;
    private boolean disponibility;
    private Timestamp startTime,endTime;

    public dateEmploi( Timestamp startTime, Timestamp endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public dateEmploi(int dateID, boolean disponibility, Timestamp startTime, Timestamp endTime) {
        this.dateID = dateID;
        this.disponibility = disponibility;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    

    public int getDateID() {
        return dateID;
    }

    public boolean isDisponibility() {
        return disponibility;
    }

    public void setDisponibility(boolean disponibility) {
        this.disponibility = disponibility;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }
    
    
    
    
}
