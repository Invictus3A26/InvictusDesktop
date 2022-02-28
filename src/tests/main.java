/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tests;


import entities.Reclamation;
import entities.avis;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import service.AvisServices;
import service.ReclamationServices;
import tools.MaConnexion;

/**
 *
 * @author amen
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MaConnexion mc = MaConnexion.getInstance();
        
        
        Reclamation r1;
        Date d =new Date(2021,4,18);
        r1 = new Reclamation("amennn","ben khalifa","aymen.benkhalifa@gmail.com", 50031324,"bagage",1,"en cours","aaaa",d);
       ReclamationServices ps = new ReclamationServices();
       
       avis a1;
       Date d1 =new Date(2021,4,18);
<<<<<<< HEAD
       a1 = new avis(3,200,d1,"aaaa");
=======
       a1 = new avis(1,200,d1,"amen");
>>>>>>> 870b1ec42e8da68ac25c50f7ca8133a76b4c2aed
       AvisServices pa = new AvisServices();
     
 //ps.ajouterReclamation(r1);
  // ps.supprimerReclamation(47); 
        //ps.modifierReclamation(48,r1);
        System.out.println(ps.afficherReclamation());
       
       
       
<<<<<<< HEAD
      // pa.ajouterAvis(a1);
      //pa.modifierAvis(1,a1);
      //pa.supprimerAvis(2);
      System.out.println(pa.afficher());
=======
       //pa.ajouterAvis(a1);
      //pa.modifierAvis(1,a1);
      //pa.supprimerAvis(2);
     // System.out.println(pa.afficher());
>>>>>>> 870b1ec42e8da68ac25c50f7ca8133a76b4c2aed
   
      
       
        
        
    
   
    }

   
}





