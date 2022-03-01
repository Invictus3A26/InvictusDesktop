/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entitie.Departement;
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
 * @author Aziz
 */
public class DepartementService {
     Connection cnx;

    public DepartementService() {
        cnx=MaConnexion.getInstance().getCnx();
    }
       public void ajouterDepartement(Departement d){
        String sql="INSERT INTO `departement`(`id`, `nomDepartement`, `zoneDepartement` , `detailDepartement`  ) VALUES ('"+d.getId()+"','"+d.getNomDepartement()+"','"+d.getZoneDepartement()+"','"+d.getDetailDepartement()+"')";
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Departement Ajoutee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }
       public List<Departement> afficherDepartement(){
        List<Departement> departement = new ArrayList<>();
        String query="select * from departement";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs= ste.executeQuery();
            while(rs.next()){
                Departement d = new Departement();
                d.setId(rs.getInt("id"));
                d.setNomDepartement(rs.getString("nomDepartement"));
                d.setZoneDepartement(rs.getString("zoneDepartement"));
                d.setDetailDepartement(rs.getString("detailDepartement"));
                departement.add(d);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         return departement;
        }
       
    public void supprimerDepartement(int id) {
        
 try {
            String sql = "DELETE FROM departement WHERE id="+id+"";
            PreparedStatement ste  = cnx.prepareStatement(sql);
           
            ste.executeUpdate();
            System.out.println("Departement Supprimée ");
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }   
    }



    
   
    public void modifierDepartement(long id, Departement d) {
        
        try {
            PreparedStatement ste;
            ste=cnx.prepareStatement(
                    "UPDATE `departement` SET `nomDepartement`=?,`zoneDepartement`=?,"
                            + "`detailDepartement`=? WHERE id=?");
            ste.setString(1,d.getNomDepartement());
            ste.setString(2,d.getZoneDepartement());
            ste.setString(3,d.getDetailDepartement());
            ste.setLong(4, id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        
        
        

    }
}