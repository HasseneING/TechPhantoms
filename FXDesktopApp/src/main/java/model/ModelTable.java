/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author USER
 */
public class ModelTable {
    
    int id;
    public int TutorID;
    String name,lastName,email,reasons,other_reasons;

    public ModelTable(int id, String name, String lastName, String email, int TutorID, String reasons, String other_reasons) {
        this.id = id;
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

    public int getTutorID() {
        return TutorID;
    }

    public void setTutorID(int TutorID) {
        this.TutorID = TutorID;
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
    
    
    
}
