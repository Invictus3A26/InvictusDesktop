/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionutilisateur1;

import gestionutilisateur1.entity.Article;
import gestionutilisateur1.entity.Role;
import gestionutilisateur1.entity.User;
import gestionutilisateur1.service.ArticleService;
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
        UserService us = new UserService();
        Date d = Date.valueOf(LocalDate.of(2000, Month.MARCH, 17));
        User u = new User("wajih", "tlili", d, "wajih.tlili@gmail.com", "wej", "admiinnslm1", 58760718, "kef", Role.ADMIN);

        Article a = new Article("test", "test", "test", "dd", 9);
        ArticleService as = new ArticleService();

        us.ajouter(u);
        //us.supprimer(15);
        //System.out.println(us.afficher());
        //us.modifier(12, u);
        //System.out.println(us.findById(8));
        //System.out.println(us.findByName("aaaa"));
        //System.out.println(us.findByPrenom("wa"));
        //System.out.println(us.findByRole(Role.FORMATEUR));
       // System.out.println(us.findByDate(Date.valueOf(LocalDate.of(1920, Month.MARCH, 21))));
        //System.out.println(us.findByEmail("aaaaaaaaaa@gmail.com"));
        //System.out.println(us.findByUsername("dah"));
        //System.out.println(us.findByNum(58760718));
        //System.out.println(us.findByAdresse("kef"));
        //System.out.println(us.sortByDate());
        //System.out.println(us.sortById());
        //System.out.println(us.sortByNom());
        //System.out.println(us.checklogin("dahmoun", "19223a7bbd7325516f069df18b50"));
        //as.ajouter(a);
        //as.modifier(0, a);
        //as.supprimer(1);
        //System.out.println(as.afficher());
        //System.out.println(as.findById(3));
        //System.out.println(as.findByTitre("foot"));
        //System.out.println(as.findByNbrLike(5));
        //System.out.println(as.sortByIdArticle());
       // System.out.println(as.sortByNbrLike());
    }

}
