/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeroport;

import Services.BagagesCrud;
import Services.FraisBagageCrud;
import Tools.DataSource;

import entities.Bagages;
import entities.FraisBagage;

/**
 *
 * @author dell
 */
public class Aeroport {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    DataSource ds1=DataSource.getInstance();
     System.out.println(ds1);     
        
       Bagages b1 = new Bagages("77","2",8);
       Bagages b2 = new Bagages("30","2",1);
       Bagages b3 = new Bagages("30","2",1);


       BagagesCrud bc = new BagagesCrud();
       System.out.println(b1);
      // bc.ajouterBagagesPst(b1);
        //bc.delete(2);
      bc.modifierBagages(4,b1);
      // System.out.println(bc.modifierBagages(3,b3));

     //System.out.println(bc.afficherBagages());;
    
       
      // FraisBagage f1 = new FraisBagage ("22","55",3,4,1);
       //FraisBagage f2 = new FraisBagage ("22","55",50,3,8);
       //FraisBagageCrud fb = new FraisBagageCrud();
       //System.out.println(f2);
      // fb.ajouterFraisBagagesPst(f2);
       //fb.delete(2);
    }
    
}
