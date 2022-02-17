/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Anis
 */
public class DBConnexion {
    public String url="jdbc:mysql://localhost:3306/pidev";
    public String user="root";
    public String pwd="";
    
    private Connection cnx;
    public static DBConnexion ct;

    private DBConnexion() {
        try {
            cnx=DriverManager.getConnection(url, user, pwd);
            System.out.println("Connexion etablie");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static DBConnexion getInstance(){
        if(ct==null)
            ct = new DBConnexion();
        return ct;
    }

    public Connection getCnx() {
        return cnx;
    }
}
