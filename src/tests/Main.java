/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;


import entities.Departement;
import services.DepartementService;
import entities.Equipement;
import services.EquipementService;
import tools.MaConnexion;

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
        
       // Equipement p1 = new Equipement(111, "afaaf", "aaaaafaaa" , "3a26" , "dddddd" , "djfjdfj");
       // EquipementService ps = new EquipementService();
        //ps.ajouterEquipement(p1);
        //ps.modifierEquipement(123,p1);
       // ps.supprimerEquipement(111);
        //System.out.println(ps.afficherEquipement());
        
        
        
        Departement d1 = new Departement(1445 , "hkhjk", "hjgfds" , "sdff" );
        DepartementService ds = new DepartementService();
        //ds.ajouterDepartement(d1);
       // ds.modifierDepartement(1444,d1);
        ds.supprimerDepartement(1445);
        System.out.println(ds.afficherDepartement());
    }
    
}
