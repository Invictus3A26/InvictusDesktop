/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;



import entities.Escale;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tools.MaConnexion;

/**
 *
 * @author islem
 */
public class EscaleService {
     Connection cnx ;
    
      public EscaleService(){
          cnx=MaConnexion.getInstance().getCnx();
    }
      
       public void ajouterEscale(Escale e){
        String sql ="INSERT INTO `escale`( `heureArriveEscale`, `heureDepartEscale`,`durée`) VALUES ('"+e.getHeureArriveEscale()+"','"+e.getHeureDepartEscale()+"','"+e.getDurée()+"')";
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Escale Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        public List<Escale> afficherEscale(){
        List<Escale> escales = new ArrayList<>();
        String query="SELECT * FROM escale";
         try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs= ste.executeQuery();
            while(rs.next()){
                Escale a = new Escale();
                a.setId(rs.getInt("id"));
                a.setHeureArriveEscale(rs.getString("heureArriveEscale"));
                a.setHeureDepartEscale(rs.getString("heureDepartEscale"));
                a.setDurée(rs.getString("durée"));
                escales.add(a);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         return escales ;
    }
        
        public void deleteEscale(int id){
        String sql ="DELETE FROM `escale` WHERE id = ? ";
         try {
            PreparedStatement ste = cnx.prepareStatement(sql);
              ste.setInt(1, id);
              ste.executeUpdate();
            System.out.println("Escale Supprimer");
         }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            
    }
        
         public void updateEscale(Escale escale,int id){
        
         String sql ="UPDATE escale SET heureArriveEscale= ?,heureDepartEscale=?,durée=? WHERE id = ? ";
         try {
            PreparedStatement ste = cnx.prepareStatement(sql);
             ste.setString(1,escale.getHeureArriveEscale());
             ste.setString(2,escale.getHeureDepartEscale());
             ste.setString(3,escale.getDurée());
             ste.setInt(4, id);
              ste.executeUpdate();
            System.out.println("Escale modifier");
         }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
