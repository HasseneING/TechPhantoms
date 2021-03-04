/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi;

import entite.rec;
import service.recService;
import utils.DataSource;

/**
 *
 * @author USER
 */
public class Pi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
    //Singleton: pour unifier l'instanciation de la classe DataSource (public-->private)
//        DataSource ds1=DataSource.getInstance();
//        DataSource ds2=DataSource.getInstance();
       
        //DataSource ds2=new DataSource();
        
        rec r1=new rec("Zahras","Syrine","motif","descrip");
        rec r2=new rec("foulen","ben foulen","motif","descrip");
        rec r3=new rec("Syrine","ben foulen","motif","descrip");
        rec r4=new rec("rania","ahlem","sysy","descrip");

        
        recService pst=new recService();
        
//        pst.ajouterRec(r1);
//        pst.ajouterRec(r2);
//        pst.ajouterRec(r3);
        
        
       // pst.ajouterRec(r4);
     
        //pst.updateRec(r2);
        
        //pst.readAll().forEach(e->System.out.println(e));
        
       //pst.deleteRec(r4);
//        
//        pst.modifierRec(r4);
 
          
        //pst.modifierRec(r4);

        
        pst.deleteRec(r4);
    }
    
}
