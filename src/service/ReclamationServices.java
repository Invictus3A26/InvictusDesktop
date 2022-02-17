/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entities.Reclamation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tools.MaConnexion;


public class ReclamationServices {
  
     Connection cnx;

    public ReclamationServices() {
        cnx=MaConnexion.getInstance().getCnx();
    }
    
        
        
    
   
    
     public void ajouterReclamation(Reclamation r){
        String sql="insert into reclamation(nom,prenom,email,tel,type,nbr,etat,description,date_reclamation) values(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ste= cnx.prepareStatement(sql);
            ste.setString(1,r.getNom());
            ste.setString(2,r.getPrenom());
            ste.setString(3,r.getEmail());
            ste.setInt(4, r.getTel());
            ste.setString(5,r.getType());
            ste.setInt(6, r.getNbr());
            ste.setString(7,r.getEtat());
            ste.setString(8,r.getDescription()); 
            ste.setDate(9,new java.sql.Date(r.getDate_reclamation().getTime()));
            ste.executeUpdate();
            System.out.println("Reclamation Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        
    public List<Reclamation> afficherReclamation(){
        List<Reclamation> reclamations = new ArrayList<>();
        String query="select * from reclamation";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs= ste.executeQuery();
            while(rs.next()){
                Reclamation r = new Reclamation();
                r.setId(rs.getInt("id"));
                r.setNom(rs.getString("nom"));
                r.setPrenom(rs.getString("prenom"));
                r.setEmail(rs.getString("email"));
                r.setTel(rs.getInt("tel"));
                r.setType(rs.getString("type"));
                r.setNbr(rs.getInt("nbr"));
                r.setEtat(rs.getString("etat"));
                r.setDescription(rs.getString("description"));
                r.setDate_reclamation(rs.getDate("date_reclamation"));
                reclamations.add(r);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return reclamations;
        
    }

    

public void supprimerReclamation(int id) {
 try {
            String sql = "DELETE FROM reclamation WHERE id="+id+"";
            PreparedStatement ste  = cnx.prepareStatement(sql);
           
            ste.executeUpdate();
            System.out.println("Reclamation Supprimée ");
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }   
    }



    
   
    public void modifierReclamation(long id, Reclamation r) {
        
        try {
            PreparedStatement ste;
            ste=cnx.prepareStatement(
                    "UPDATE `reclamation` SET `nom`=?,`prenom`=?,"
                            + "`email`=?,`tel`=?,`type`=?,"
                            + "`nbr`=?,`etat`=?,"
                            + "`description`=?,`date_reclamation`=? WHERE id=?");
            ste.setString(1,r.getNom());
            ste.setString(2,r.getPrenom());
            ste.setString(3,r.getEmail());
            ste.setInt(4, r.getTel());
            ste.setString(5,r.getType());
            ste.setInt(6, r.getNbr());
            ste.setString(7,r.getEtat());
            ste.setString(8,r.getDescription());
            ste.setDate(9,new java.sql.Date(r.getDate_reclamation().getTime()));
            ste.setLong(10, id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        
        
        

    }

    
    
    
    
    
     
   
}









    


