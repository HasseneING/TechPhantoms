/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

/**
 *
 * @author PC
 */
public class matiere {
   private int id ;
   private String nom ;
   private String type ;
   private String disponibilité ;
   private int id_teacher ;

    public matiere(String nom, String type, String disponibilité, int id_teacher) {
        this.nom = nom;
        this.type = type;
        this.disponibilité = disponibilité;
        this.id_teacher = id_teacher;
    }
   

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the disponibilté
     */
    public String getDisponibilité() {
        return disponibilité;
    }

    /**
     * @param disponibilté the disponibilté to set
     */
    public void setDisponibilté(String disponibilité) {
        this.disponibilité = disponibilité;
    }

    /**
     * @return the id_teacher
     */
    public int getId_teacher() {
        return id_teacher;
    }

    /**
     * @param id_teacher the id_teacher to set
     */
    public void setId_teacher(int id_teacher) {
        this.id_teacher = id_teacher;
    }
    
   
    
}
