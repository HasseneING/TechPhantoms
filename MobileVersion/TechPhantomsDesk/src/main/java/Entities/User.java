/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author SAFA
 */
public class User {

    private int id;
    private String username;
    private String email;
    private String password;
    private String first_name;
    private String last_name;
    private int cin;
    private String status;
    private Date date_birth;
    private Timestamp date_add = Timestamp.valueOf(LocalDateTime.now());;
    private String profile_pic;
    private String state;
    private int phone_number;

    public User(int id, String username, String email, String password, String first_name, String last_name, int cin, String status, Date date_birth, Timestamp date_add, String profile_pic, String state, int phone_number) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.cin = cin;
        this.status = status;
        this.date_birth = date_birth;
        this.date_add = date_add;
        this.profile_pic = profile_pic;
        this.state = state;
        this.phone_number = phone_number;
    }
        public User(int id, String username, String email, String password, String first_name, String last_name, int cin, String status, Date date_birth, String profile_pic, String state, int phone_number) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.cin = cin;
        this.status = status;
        this.date_birth = date_birth;
        this.profile_pic = profile_pic;
        this.state = state;
        this.phone_number = phone_number;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate_birth() {
        return date_birth;
    }

    public void setDate_birth(Date date_birth) {
        this.date_birth = date_birth;
    }

    public Timestamp getDate_add() {
        return date_add;
    }

    public void setDate_add(Timestamp date_add) {
        this.date_add = date_add;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }
}
