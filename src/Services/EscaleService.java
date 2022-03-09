/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;



import Entities.Escale;
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
public class EscaleService {
     Connection cnx ;
    
      public EscaleService(){
          cnx=MyConnexion.getInstance().getCnx();
    }
      
       public void ajouterEscale(Escale e){
        String sql ="INSERT INTO `escale`( `aeroport_depart`, `aeroport_destination`, `heureArriveEscale`, `heureDepartEscale`,`durée`) VALUES ('"+e.getAeroport_depart()+"','"+e.getAeroport_destination()+"','"+e.getHeureArriveEscale()+"','"+e.getHeureDepartEscale()+"','"+e.getDurée()+"')";
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Escale Ajoutée");
             Notifications notifications=Notifications.create();

        notifications.text("Escale est ajoutée");
        notifications.title("Success Message");
        notifications.show();
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
                a.setId_escale(rs.getInt("id_escale"));
                a.setAeroport_depart(rs.getInt("aeroport_depart"));
                a.setAeroport_destination(rs.getInt("aeroport_destination"));
                
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
        String sql ="DELETE FROM `escale` WHERE id_escale = ? ";
         try {
            PreparedStatement ste = cnx.prepareStatement(sql);
              ste.setInt(1, id);
              ste.executeUpdate();
            System.out.println("Escale Supprimer");
              Notifications notifications=Notifications.create();

        notifications.text("Escale est supprimer");
        notifications.title("Success Message");
      
        /*notifications.darkStyle();*/
     /*   notifications.position(Pos.BOTTOM_CENTER);*/
        notifications.show();
         }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            
    }
        
        
     
        
         public void updateEscale(Escale escale,int id){
        
         String sql ="UPDATE escale SET aeroport_depart=?,aeroport_destination=?, heureArriveEscale= ?,heureDepartEscale=?,durée=? WHERE id_escale = ? ";
         try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, escale.getAeroport_depart());
            ste.setInt(2, escale.getAeroport_destination());
            ste.setString(3,escale.getHeureArriveEscale());
            ste.setString(4,escale.getHeureDepartEscale());
            ste.setString(5,escale.getDurée());
            ste.setInt(6, id);
            ste.executeUpdate();
            System.out.println("Escale modifier");
              Notifications notifications=Notifications.create();

        notifications.text("Escale est modifier");
        notifications.title("Success Message");
        notifications.show();
         }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
         
          public Escale getEscaleById(int id) {
        Escale escale = new Escale();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from escale where id_escale=" + id;
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {

                escale.setId_escale(rs.getInt("id_escale"));
                escale.setAeroport_depart(rs.getInt("aeroport_depart"));
                escale.setAeroport_destination(rs.getInt("aeroport_destination"));
                escale.setHeureArriveEscale(rs.getString("heureArriveEscale"));
                escale.setHeureDepartEscale(rs.getString("heureDepartEscale"));
                escale.setDurée(rs.getString("durée"));
                
          
               
            } else {
                System.out.println("escale n'esxiste pas");

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       return escale ;

    }

}
