/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Aeroport;
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
public class AeroportService {
    Connection cnx ;
    public AeroportService(){
          cnx=MaConnexion.getInstance().getCnx();
    }
    public void ajouterAeroport(Aeroport a){
        String sql ="INSERT INTO `airport`( `nom_aeroport`, `ville_aeroport`) VALUES ('"+a.getNom_aeroport()+"','"+a.getVille_aeroport()+"')";
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Aeroprt Ajoutée");
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
                a.setId(rs.getInt("id_aeroport"));
                a.setNom_aeroport(rs.getString("nom_aeroport"));
                a.setVille_aeroport(rs.getString("ville_aeroport"));
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
         }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            
    }
    
    
    public void updateAeroport(Aeroport aeroport,int id){
        
         String sql ="UPDATE airport SET nom_aeroport= ?,ville_aeroport=? WHERE id_aeroport = ? ";
         try {
            PreparedStatement ste = cnx.prepareStatement(sql);
             ste.setString(1, aeroport.getNom_aeroport());
             ste.setString(2, aeroport.getVille_aeroport());
             ste.setInt(3, id);
              ste.executeUpdate();
            System.out.println("Aeroprt modifier");
         }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        
    }
    
