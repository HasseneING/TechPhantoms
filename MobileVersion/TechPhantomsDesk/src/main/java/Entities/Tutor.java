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
public class Tutor extends User {

    private int nb_formation = 0;
    private String certificats;
    private int nb_student = 0;
    private double price_hour;
    private int schedule = 0;
    private String cv = null;
    private String video = null;
    private String subject;
    private String language;

    public Tutor(int id, String username, String email, String password, String first_name, String last_name, int cin, String status, Date date_birth, Timestamp date_add, String profile_pic, String state, int phone_number, int nb_formation, String certificats, int nb_student, double price_hour, int schedule, String cv, String video, String subject, String language) {
        super(id, username, email, password, first_name, last_name, cin, status, date_birth, date_add, profile_pic, state, phone_number);
        this.nb_formation = nb_formation;
        this.certificats = certificats;
        this.nb_student = nb_student;
        this.price_hour = price_hour;
        this.schedule = schedule;
        this.cv = cv;
        this.video = video;
        this.subject = subject;
        this.language = language;
    }

    public Tutor(int id, String username, String email, String password, String first_name, String last_name, int cin, String status, Date date_birth, String profile_pic, String state, int phone_number, int nb_formation, String certificats, int nb_student, double price_hour, int schedule, String cv, String video, String subject, String language) {
        super(id, username, email, password, first_name, last_name, cin, status, date_birth, profile_pic, state, phone_number);
        this.nb_formation = nb_formation;
        this.certificats = certificats;
        this.nb_student = nb_student;
        this.price_hour = price_hour;
        this.schedule = schedule;
        this.cv = cv;
        this.video = video;
        this.subject = subject;
        this.language = language;
    }

    public Tutor(int id, String username, String email, String password, String first_name, String last_name, int cin, String status, Date date_birth, String profile_pic, String state, int phone_number, String certificats, double price_hour, String cv, String video, String subject, String language) {
        super(id, username, email, password, first_name, last_name, cin, status, date_birth, profile_pic, state, phone_number);
        this.certificats = certificats;
        this.price_hour = price_hour;
        this.cv = cv;
        this.video = video;
        this.subject = subject;
        this.language = language;
    }

    public int getNb_formation() {
        return nb_formation;
    }

    public void setNb_formation(int nb_formation) {
        this.nb_formation = nb_formation;
    }

    public String getCertificats() {
        return certificats;
    }

    public void setCertificats(String certificats) {
        this.certificats = certificats;
    }

    public int getNb_student() {
        return nb_student;
    }

    public void setNb_student(int nb_student) {
        this.nb_student = nb_student;
    }

    public double getPrice_hour() {
        return price_hour;
    }

    public void setPrice_hour(double price_hour) {
        this.price_hour = price_hour;
    }

    public int getSchedule() {
        return schedule;
    }

    public void setSchedule(int schedule) {
        this.schedule = schedule;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
