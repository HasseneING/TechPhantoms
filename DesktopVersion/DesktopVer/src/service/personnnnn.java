/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.students_feedback;
import java.sql.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author t4h4
 */

public class personnnnn extends students_feedback{
    String  name ;
    String lastname ;
    String email ; 
        public personnnnn (String name, String lastname , String email ) {
           this.lastname = lastname ; 
            this.name=name;
            this.email=email ;
        }
    

//    public personnnnn(String s ,String d , int e ) {
//        name = s ; 
//        lastname =  d; 
//        email = e ; 
//        
//        
//    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }
 
}