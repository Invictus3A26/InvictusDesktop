/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Vols;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import tools.MaConnexion;

/**
 *
 * @author islem
 */
public class VolsService {
    Connection cnx ;
    
      public VolsService(){
          cnx=MaConnexion.getInstance().getCnx();
    }
      public void ajouterVols(Vols v){
          String sql="INSERT INTO vols( num_vol, date_depart_vol, date_arrivé_vol, heure_depart_vol, heure_arrivé_vol, id_aeroport, type_avion, type_vol, id_escale, id_comp,nombrePassager_vol,durée_retard_vol,annulation_vol) VALUES ('"+v.getNum_vol()+"','"+v.getDate_depart_vol()+"','"+v.getDate_arrivé_vol()+"','"+v.getHeure_depart_vol()+"','"+v.getHeure_arrivé_vol()+"','"+v.getNom_aeroport()+"','"+v.getType_avion()+"','"+v.getType_vol()+"','"+v.getEscale()+"','"+v.getNomCom()+"','"+v.getNombrePassager_vol()+"','"+v.getDurée_retard_vol()+"','"+v.isAnnulation_vol()+"')";
           try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Vols Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      }
      
      
      
      
       public List<Vols> afficherVols(){
        List<Vols> vol = new ArrayList<>();
        String query="SELECT * FROM vols";
         try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs= ste.executeQuery();
            while(rs.next()){
                Vols a = new Vols();
                a.setId(rs.getInt("id_vol"));
                a.setNum_vol(rs.getInt("num_vol"));
                a.setEscale(rs.getInt("id_escale"));             
                a.setDate_depart_vol(rs.getString("date_depart_vol"));
                a.setDate_arrivé_vol(rs.getString("date_arrivé_vol"));
                a.setHeure_depart_vol(rs.getString("heure_depart_vol"));
                a.setHeure_arrivé_vol(rs.getString("heure_arrivé_vol"));
                a.setNom_aeroport(rs.getInt("id_aeroport"));
                a.setType_avion(rs.getString("type_avion"));
                a.setType_vol(rs.getString("type_vol"));
                a.setNomCom(rs.getString("id_comp"));
                a.setNombrePassager_vol(rs.getInt("nombrePassager_vol"));
                a.setDurée_retard_vol(rs.getString("durée_retard_vol"));
                a.setAnnulation_vol(rs.getInt("annulation_vol"));
                    
                vol.add(a);
               
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return vol;
    }
       
        public void deleteVols(int id){
        String sql ="DELETE FROM vols WHERE id_vol = ? ";
         try {
            PreparedStatement ste = cnx.prepareStatement(sql);
              ste.setInt(1, id);
              ste.executeUpdate();
            System.out.println("Vols Supprimer");
         }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            
    }
        
        public void updateVols(Vols vol,int id_vol){
        
         String sql ="UPDATE vols SET num_vol=?,date_depart_vol=?,date_arrivé_vol=?,heure_depart_vol=?,heure_arrivé_vol=?,id_aeroport=?,type_avion=?,type_vol=?,escales=?,id_comp=?,nombrePassager_vol=?,durée_retard_vol=?,annulation_vol=?  WHERE id_vol = ? ";
         try {
            PreparedStatement ste = cnx.prepareStatement(sql);
             ste.setInt(1,vol.getNum_vol());
             ste.setString(2,vol.getDate_depart_vol());
             ste.setString(3,vol.getDate_arrivé_vol());
             ste.setString(4,vol.getHeure_depart_vol());
             ste.setString(5,vol.getHeure_arrivé_vol());
             ste.setInt(6,vol.getNom_aeroport());
             ste.setString(7,vol.getType_avion());
             ste.setString(8,vol.getType_vol());
             ste.setInt(9,vol.getEscale());
             ste.setString(10,vol.getNomCom());
             ste.setInt(11,vol.getNombrePassager_vol());
             ste.setString(12,vol.getDurée_retard_vol() );
             ste.setInt(13,vol.isAnnulation_vol());
             ste.setInt(11, id_vol);
             ste.executeUpdate();
            System.out.println("Vol modifier");
         }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        
        
         public Vols getVolById(int id) {
        Vols vol = new Vols();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from vols where id_vol=" + id;
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {

                vol.setId(rs.getInt("id_vol"));
                vol.setNum_vol(rs.getInt("num_vol"));
                vol.setDate_depart_vol(rs.getString("date_depart_vol"));
                vol.setDate_arrivé_vol(rs.getString("date_arrivé_vol"));
                vol.setHeure_depart_vol(rs.getString("heure_depart_vol"));
                vol.setHeure_arrivé_vol(rs.getString("heure_arrivé_vol"));
                vol.setNom_aeroport(rs.getInt("id_aeroport"));
                vol.setType_avion(rs.getString("type_avion"));
                vol.setType_vol(rs.getString("type_vol"));
                vol.setEscale(rs.getInt("id_escale"));
                vol.setNomCom(rs.getString("id_comp"));
                vol.setNombrePassager_vol(rs.getInt("nombrePassager_vol"));
                vol.setDurée_retard_vol(rs.getString("durée_retard_vol"));
                vol.setAnnulation_vol(rs.getInt("annulation_vol"));
               
            } else {
                System.out.println("vol n'esxiste pas");

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       return vol ;

    }
         
         public List<Vols> rechercheAvecNumVol(int numVol) {

        List<Vols> vol = afficherVols();
        List<Vols> resultat = vol.stream().filter(vols -> numVol== vols.getNum_vol()).collect(Collectors.toList());
        if (resultat.isEmpty()) {
            System.out.println("vol n'existe pas");
            return null;

        } else {
            System.out.println("vol existe");
            return resultat;

        }


}



  public List<Vols> triNum_vol() {
        List<Vols> vol = afficherVols();
        List<Vols> resultat = vol.stream().sorted(Comparator.comparing(Vols::getNum_vol)).collect(Collectors.toList());
       if (resultat.isEmpty()) {
            System.out.println("vol n'existe pas");
            return null;

        } else {
            System.out.println("vol exisite");
            return resultat;

        }
    }


}
