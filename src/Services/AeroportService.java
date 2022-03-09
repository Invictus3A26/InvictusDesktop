/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Aeroport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.controlsfx.control.Notifications;
import Tools.MyConnexion;

/**
 *
 * @author islem
 */
public class AeroportService {
    Connection cnx ;
    public AeroportService(){
          cnx=MyConnexion.getInstance().getCnx();
    }
    public void ajouterAeroport(Aeroport a){
        String sql ="INSERT INTO `airport`( `nom_aeroport`, `ville_aeroport`,`pays`) VALUES ('"+a.getNom_aeroport()+"','"+a.getVille_aeroport()+"','"+a.getPays()+"')";
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Aeroprt Ajoutée");
                     Notifications notifications=Notifications.create();

        notifications.text("Aeroport est ajoutée");
        notifications.title("Success Message");
        notifications.show();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public List<Aeroport> afficherAeroport(){
        List<Aeroport> aeroports = new ArrayList<>();
        String query="SELECT * FROM airport";
         try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs= ste.executeQuery();
            while(rs.next()){
                Aeroport a = new Aeroport();
                a.setId_aeroport(rs.getInt("id_aeroport"));
                a.setNom_aeroport(rs.getString("nom_aeroport"));
                a.setVille_aeroport(rs.getString("ville_aeroport"));
                a.setPays(rs.getString("pays"));
                aeroports.add(a);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         return aeroports ;
    }
    
    
    public void deleteAeroport(int id){
        String sql ="DELETE FROM `airport` WHERE id_aeroport = ? ";
         try {
            PreparedStatement ste = cnx.prepareStatement(sql);
              ste.setInt(1, id);
              ste.executeUpdate();
            System.out.println("Aeroprt Supprimer");
             Notifications notifications=Notifications.create();

        notifications.text("Aeroport est supprimer");
        notifications.title("Success Message");
        notifications.show();
         }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            
    }
    
    
    public void updateAeroport(Aeroport aeroport,int id){
        
         String sql ="UPDATE airport SET nom_aeroport= ?,ville_aeroport=?,pays=? WHERE id_aeroport = ? ";
         try {
            PreparedStatement ste = cnx.prepareStatement(sql);
             ste.setString(1, aeroport.getNom_aeroport());
             ste.setString(2, aeroport.getVille_aeroport());
             ste.setString(3, aeroport.getPays());
             ste.setInt(4, id);
              ste.executeUpdate();
            System.out.println("Aeroprt modifier");
            Notifications notifications=Notifications.create();

        notifications.text("Aeroport est modifier");
        notifications.title("Success Message");
        notifications.show();
         }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        
     public Aeroport getAeroportById(int id) {
        Aeroport escale = new Aeroport();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from airport where id_aeroport=" + id;
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {

                escale.setId_aeroport(rs.getInt("id_aeroport"));
                escale.setNom_aeroport(rs.getString("nom_aeroport"));
              
                escale.setVille_aeroport(rs.getString("ville_aeroport"));
                escale.setPays(rs.getString("pays"));
               
                
          
               
            } else {
                System.out.println("aeroport n'esxiste pas");

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       return escale ;

    }
    
    }
    
