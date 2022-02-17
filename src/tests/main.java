/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import Entities.AvionModel;
import Entities.CompagnieModel;
import Service.AvionService;
import Service.CompagnieService;

/**
 *
 * @author Anis
 */
public class main{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // CompagnieModel C = new CompagnieModel("DR", "Abfuibiebfze" , "tunisair.tn" , "tunis" ,5,"Tunis","Tuniscartaghe",11,25,10,"bienvenue en tunisair");
        // CompagnieService CS = new CompagnieService();
         // CS.ajouterCompagnie(C);
        //CS.modifierCompagnie("TN",C);
        //CS.deleteCompagnie("TN");
       // System.out.println(CS.afficherCompagnie());
       AvionModel A = new AvionModel("CR7", "Small" , "bus" ,78);
         AvionService AS = new AvionService();
         // AS.ajouterAvion(A);
        //AS.modifierAvion("CR7",A);
      //
      AS.deleteAvion("CR7");
        //System.out.println(AS.afficherAvion());
    }
    
}
