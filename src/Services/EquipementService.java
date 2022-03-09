
package Services;

import Entities.Equipement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Tools.MaConnexion;

/**
 *
 * @author Aziz
 */
public class EquipementService {
    Connection cnx;

    public EquipementService() {
        cnx=MaConnexion.getInstance().getCnx();
    }
    public void ajouterEquipement(Equipement p){
        String sql="INSERT INTO `equipements`(`id`, `typeEquipement`, `nomEquipement` , `detailEquipement` , `zoneEquipement` , `etatEquipement`,`id_departement` ) VALUES ('"+p.getId()+"','"+p.getTypeEquipement()+"','"+p.getNomEquipement()+"','"+p.getDetailEquipement()+"','"+p.getZoneEquipement()+"','"+p.getEtatEquipement()+"','"+p.getId_departement()+"')";
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Equipement Ajoutee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }
  
    
    public List<Equipement> afficherEquipement(){
        List<Equipement> equipement = new ArrayList<>();
        String query="select * from equipements";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs= ste.executeQuery();
            while(rs.next()){
                Equipement p = new Equipement();
                p.setId(rs.getInt("id"));
                p.setTypeEquipement(rs.getString("typeEquipement"));
                p.setNomEquipement(rs.getString("nomEquipement"));
                p.setDetailEquipement(rs.getString("detailEquipement"));
                p.setZoneEquipement(rs.getString("zoneEquipement"));
                p.setEtatEquipement(rs.getString("etatEquipement"));
                p.setId_departement(rs.getInt("id_departement"));
                equipement.add(p);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return equipement;
        
    }
    
 
    public void supprimerEquipement(int id) {
 try {
            String sql = "DELETE FROM equipements WHERE id="+id+"";
            PreparedStatement ste  = cnx.prepareStatement(sql);
           
            ste.executeUpdate();
            System.out.println("Equipement Supprimée ");
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }   
    }



    
   
    public void modifierEquipement(long id, Equipement p) {
        
        try {
            PreparedStatement ste;
            ste=cnx.prepareStatement(
                    "UPDATE `equipements` SET `typeEquipement`=?,`nomEquipement`=?,"
                            + "`detailEquipement`=?,`zoneEquipement`=?,"
                            + "`etatEquipement`=?,`id_departement`=? WHERE id=?");
            ste.setString(1,p.getTypeEquipement());
            ste.setString(2,p.getNomEquipement());
            ste.setString(3,p.getDetailEquipement());
            ste.setString(4, p.getZoneEquipement());
            ste.setString(5,p.getEtatEquipement());
            ste.setInt(6,p.getId_departement());
            ste.setLong(7, id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        
        
        

    }
}
