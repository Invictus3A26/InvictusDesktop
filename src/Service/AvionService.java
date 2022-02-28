/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.AvionModel;
import Tools.DBConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Anis
 */
public class AvionService {
    Connection cnx;

    public AvionService() {
        cnx=DBConnexion.getInstance().getCnx();
    }
 public void ajouterAvion(AvionModel A){
          String sql="INSERT INTO avion(CodeAvion,TypeA,Model,PassagerN,CodeC) VALUES ('"+A.getCodeAvion()+"','"+A.getTypeA()+"','"+A.getModel()+"','"+A.getPassagerN()+"','"+A.getCodeC()+"')";
           try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Avion Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      }
        
        
    
       public List<AvionModel> afficherAvion(){
        List<AvionModel> avion = new ArrayList<>();
        String query="select * from avion";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs= ste.executeQuery();
            while(rs.next()){
                AvionModel A = new AvionModel();
                A.setCodeAvion(rs.getString("CodeAvion"));
                A.setTypeA(rs.getString("TypeA"));
                A.setModel(rs.getString("Model"));
                A.setPassagerN(rs.getFloat("PassagerN"));
                  A.setCodeC(rs.getString("CodeC"));
                avion.add(A);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         return avion;
        }
       
 public void deleteAvion(String CodeAvion){
        String sql ="DELETE FROM avion WHERE CodeAvion=? ";
         try {
            PreparedStatement ste = cnx.prepareStatement(sql);
              ste.setString(1, CodeAvion);
              ste.executeUpdate();
            System.out.println("Compagnie Supprimer");
         }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            
    }
public void modifierAvion(String CodeAvion, AvionModel A) {

        try {
            PreparedStatement ste;
            ste=cnx.prepareStatement(
                    "UPDATE `avion` SET `CodeAvion`=?,`TypeA`=?,`Model`=?,`PassagerN`=? ,`CodeC`=? WHERE CodeAvion=?");
            ste.setString(1,A.getCodeAvion());
            ste.setString(2,A.getTypeA());
            ste.setString(3,A.getModel());
            ste.setFloat(4,A.getPassagerN());
              ste.setString(5,A.getCodeC());
            ste.setString(6, CodeAvion);
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

}
