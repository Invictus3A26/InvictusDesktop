/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Entities.AvionModel;
import Entities.CompagnieModel;
import Services.AvionService;
import Services.CompagnieService;
import java.sql.SQLException;

/**
 *
 * @author Anis
 */
public class main{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
    //     CompagnieModel C = new CompagnieModel("DZ", "England" , "tunisair.tn" , "tunis" ,5,"Tunis","Tuniscartaghe",11,"bienvenue en tunisair");
      //  CompagnieService CS = new CompagnieService();
        // CS.ajouterCompagnie(C);
        //CS.modifierCompagnie("TN",C);
     //  CS.deleteCompagnie("DR");
        //System.out.println(CS.afficherCompagnie());
      AvionModel A = new AvionModel("engfez", "Small" , "bus" , 88,"EN");
      AvionService AS = new AvionService();
          AS.ajouterAvion(A);
        //AS.modifierAvion("CR7",A);
      System.out.println(AS.afficherAvion());
      //AS.deleteAvion("CR7");
        //System.out.println(AS.afficherAvion());
    CompagnieService AC = new CompagnieService();
     //System.out.println(AC.CalculAvionC());
    
    }
    
}
