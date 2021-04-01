/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.util.Objects;

/**
 *
 * @author USER
 */
public class rec {
    
    private int id;
    private String name;
    private String lastName;
    private String email;
    private int TutorID;
    private String reasons;
    private String other_reasons;

    public rec() {
    }

    public rec(int id, String name, String lastName, String email, int TutorID, String reasons, String other_reasons) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.TutorID = TutorID;
        this.reasons = reasons;
        this.other_reasons = other_reasons;
    }

    public rec(String name, String lastName, String email, int TutorID, String reasons, String other_reasons) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.TutorID = TutorID;
        this.reasons = reasons;
        this.other_reasons = other_reasons;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTutorID() {
        return TutorID;
    }

    public void setTutorID(int TutorID) {
        this.TutorID = TutorID;
    }

    public String getReasons() {
        return reasons;
    }

    public void setReasons(String reasons) {
        this.reasons = reasons;
    }

    public String getOther_reasons() {
        return other_reasons;
    }

    public void setOther_reasons(String other_reasons) {
        this.other_reasons = other_reasons;
    }

    @Override
    public String toString() {
        return "rec{" + "id=" + id + ", name=" + name + ", lastName=" + lastName + ", email=" + email + ", TutorID=" + TutorID + ", reasons=" + reasons + ", other_reasons=" + other_reasons + '}';
    }


   

    
    
}
