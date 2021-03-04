/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entité;

//import java.sql.Date;
import java.sql.Time;
import java.util.Date;



/**
 *
 * @author USER
 */
public class chat {
    private int id_E;
    private String mdp;
    private String message;
    private Date date;
    private Time heure;

    public chat() {
    }

    public chat(int id_E, String mdp, String message, Date date, Time heure) {
        this.id_E = id_E;
        this.mdp = mdp;
        this.message = message;
        this.date = date;
        this.heure = heure;
    }
    
    

    public int getId_E() {
        return id_E;
    }

    public void setId_E(int id_E) {
        this.id_E = id_E;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getHeure() {
        return heure;
    }

    public void setHeure(Time heure) {
        this.heure = heure;
    }

    @Override
    public String toString() {
        return "chat{" + "id_E=" + id_E + ", mdp=" + mdp + ", message=" + message 
                + ", date=" + date + ", heure=" + heure + '}';
    }
    
    
    
}