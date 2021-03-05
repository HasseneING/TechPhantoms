/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class niveau {
     private int id_n;
   private String nom_niv ;
   private int id_teacher ;

    public niveau(String nom_niv, int id_teacher) {
        this.nom_niv = nom_niv;
        this.id_teacher = id_teacher;
    }

    public niveau(int id) {
        this.id_n=id;

    }
     public niveau(int id_n, String nom_niv,int id_teacher) {
        this.id_n = id_n;
        this.nom_niv = nom_niv;
        this.id_teacher = id_teacher;
    }

   


    public int getId_n() {
        return id_n;
    }

    public void setId_n(int id_n) {
        this.id_n = id_n;
    }

    public String getNom_niv() {
        return nom_niv;
    }

    public void setNom_niv(String nom_niv) {
        this.nom_niv = nom_niv;
    }

    public int getId_teacher() {
        return id_teacher;
    }

    public void setId_teacher(int id_teacher) {
        this.id_teacher = id_teacher;
    }
   
    
    
   
   
   
    
}
