/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Entities.Departement;
import Services.DepartementService;
import Entities.Equipement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Services.EquipementService;
import Tools.MaConnexion;

/**
 *
 * @author Aziz
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       MaConnexion mc = MaConnexion.getInstance();

       // Equipement p1 = new Equipement(111, "afaaf", "aaaaafaaa" , "3a26" , "dddddd" , "djfjdfj",1446);
        //EquipementService ps = new EquipementService();
        // ps.ajouterEquipement(p1);
        // ps.modifierEquipement(111,p1);
        // ps.supprimerEquipement(111);
        // System.out.println(ps.afficherEquipement());
        
        
        
        
        //Departement d1 = new Departement(1446 , "aaaa", "hjgdfdfds" , "sqqsqdff"  );
        DepartementService ds = new DepartementService();
        //ds.ajouterDepartement(d1);
        // ds.modifierDepartement(1444,d1);
        // ds.supprimerDepartement(1446);
         System.out.println(ds.findByName("hkhjk"));
         System.out.println(ds.findByZone(""));
         System.out.println(ds.findByDetail(""));
        //try {
            //System.out.println(ds.afficherDepartement());
           // ds.ChercherDepartement();
       // } catch (SQLException ex) {
           // Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        //}
   }
   
}
