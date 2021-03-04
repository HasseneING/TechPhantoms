/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

/**
 *
 * @author USER
 */
public class rec {
    
    private int id;
    private String nom_E;
    private String prenom_E;
    private String motif;
    private String description;

    public rec() {
    }

    public rec(int id, String nom_E, String prenom_E, String motif, String description) {
        this.id = id;
        this.nom_E = nom_E;
        this.prenom_E = prenom_E;
        this.motif = motif;
        this.description = description;
    }
    
    //pour l'insertion, car id c'est auto increment
    public rec(String nom_E, String prenom_E, String motif, String description) {
        this.nom_E = nom_E;
        this.prenom_E = prenom_E;
        this.motif = motif;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_E() {
        return nom_E;
    }

    public void setNom_E(String nom_E) {
        this.nom_E = nom_E;
    }

    public String getPrenom_E() {
        return prenom_E;
    }

    public void setPrenom_E(String prenom_E) {
        this.prenom_E = prenom_E;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final rec other = (rec) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rec{" + "id=" + id + ", nom_E=" + nom_E + ", prenom_E=" 
                + prenom_E + ", motif=" + motif + ", description=" + description + '}';
    }
    
    
    
}
