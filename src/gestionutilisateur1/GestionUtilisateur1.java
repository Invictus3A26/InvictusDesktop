/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionutilisateur1;

import gestionutilisateur1.entity.Role;
import gestionutilisateur1.entity.User;
import gestionutilisateur1.service.UserService;
import gestionutilisateur1.utils.MyConnexion;
import java.time.LocalDate;
import java.sql.Date;
import java.time.Month;



/**
 *
 * @author MSI
 */
public class GestionUtilisateur1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         UserService us=new UserService();
         Date d =Date.valueOf(LocalDate.of(2000, Month.MARCH, 14));
       User u=new User("wajih tlili",d,"wajih.tlili@gmail.com","wajih","test",55820931,"tunis",Role.EMPLOYE);
        
      us.ajouter(u);
       //us.supprimer(7);
       // System.out.println(us.afficher());
       // us.modifier(10, u);
    }
    
}
