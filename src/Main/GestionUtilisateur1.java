/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Entities.Article;
import Entities.Role;
import Entities.User;
import Services.ArticleService;
import Services.CryptWithMD5;
import Services.UserService;
import Tools.Mailapi;
import Tools.MyConnexion;
import Tools.Smsapi;
import java.io.IOException;
import java.time.LocalDate;
import java.sql.Date;
import java.time.Month;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintException;




/**
 *
 * @author LENOVO
 */
public class GestionUtilisateur1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         UserService us=new UserService();
         Date d =Date.valueOf(LocalDate.of(2000, Month.MARCH, 17));
         User u=new User("ahmed","temani",d,"mohameddehmani@gmail.com","dah","admiinnslm1",58760718,"kef",Role.ADMIN);
       
         Article a= new Article("tenis","tenisbravo","dd");
         ArticleService as=new ArticleService();
         //System.out.println(us.afficher());
         //System.out.println(us.findByEmail("fdgfd").isEmpty());
         
         
         
        //System.out.println(as.afficher());
       //us.ajouter(u);
       //us.supprimer(15);
       // System.out.println(us.afficher());
        //us.modifier(12, u);
       // System.out.println(us.findById(120));
       // System.out.println(us.findByName("dehmani"));
       // System.out.println(us.findByPrenom("dahmoun"));
        //System.out.println(us.findByRole(Role.FORMATEUR));
        //System.out.println(us.findByDate(Date.valueOf(LocalDate.of(2000, Month.MARCH, 14))));
        //System.out.println(us.findByEmail("aaaaaaaaaa@gmail.com"));
        //System.out.println(us.findByUsername("dah"));
       // System.out.println(us.findByNum(58760718));
        //System.out.println(us.findByAdresse("kef"));
       // System.out.println(us.sortByDate());
        //System.out.println(us.sortById());
       //System.out.println(us.sortByNom());
      //System.out.println(us.checklogin("dahmoun", "19223a7bbd7325516f069df18b50"));
   //  Mailapi.send("temanimohameddahmani@gmail.com", "meddahmani123456789", "moatez.oueslati@esprit.tn", "test java", "test");
       
    //  Smsapi.sendSMS("", "");
            
          
            
            //as.ajouter(a);
            //as.modifier(0, a);
            //as.supprimer(1);
            //System.out.println(as.afficher());
            //System.out.println(as.findById(3));
            // System.out.println(as.findByTitre("foot"));
            // System.out.println(as.findByNbrLike(5));
            //System.out.println(as.sortByIdArticle());
            //System.out.println(as.sortByNbrLike());
         
         
    }
    
}
