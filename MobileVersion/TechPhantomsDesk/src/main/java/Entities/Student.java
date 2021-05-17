/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author SAFA
 */
public class Student extends User {

    private int nb_formation_enrg = 0;
    private String active_formation = "None";
    private String finished_formation = "None";
    private int schedule = 0;

    public Student(int id, String username, String email, String password, String first_name, String last_name, int cin, String status, Date date_birth,
            Timestamp date_add, String profile_pic, String state, int phone_number, int nb_formation_enrg, String active_formation, String finished_formation, int schedule) {
        super(id, username, email, password, first_name, last_name, cin, status, date_birth, date_add, profile_pic, state, phone_number);
        this.nb_formation_enrg = nb_formation_enrg;
        this.active_formation = active_formation;
        this.finished_formation = finished_formation;
        this.schedule = schedule;
    }

    public Student(int id, String username, String email, String password, String first_name, String last_name, int cin, String status, Date date_birth,
            String profile_pic, String state, int phone_number, int nb_formation_enrg, String active_formation, String finished_formation, int schedule) {
        super(id, username, email, password, first_name, last_name, cin, status, date_birth, profile_pic, state, phone_number);
        this.nb_formation_enrg = nb_formation_enrg;
        this.active_formation = active_formation;
        this.finished_formation = finished_formation;
        this.schedule = schedule;
    }

    public Student(int id, String username, String email, String password, String first_name, String last_name, int cin, String status, Date date_birth,
            String profile_pic, String state, int phone_number) {
        super(id, username, email, password, first_name, last_name, cin, status, date_birth, profile_pic, state, phone_number);
    }

    public int getNb_formation_enrg() {
        return nb_formation_enrg;
    }

    public void setNb_formation_enrg(int nb_formation_enrg) {
        this.nb_formation_enrg = nb_formation_enrg;
    }

    public String getActive_formation() {
        return active_formation;
    }

    public void setActive_formation(String active_formation) {
        this.active_formation = active_formation;
    }

    public String getFinished_formation() {
        return finished_formation;
    }

    public void setFinished_formation(String finished_formation) {
        this.finished_formation = finished_formation;
    }

    public int getSchedule() {
        return schedule;
    }

    public void setSchedule(int schedule) {
        this.schedule = schedule;
    }
    
    
}
