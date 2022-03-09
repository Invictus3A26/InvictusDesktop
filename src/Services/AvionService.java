/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.AvionModel;
import Tools.MyConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Anis
 */
public class AvionService {
    Connection cnx;

    public AvionService() {
        cnx=MyConnexion.getInstance().getCnx();
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
                A.setPassagerN(rs.getInt("PassagerN"));
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
public List<AvionModel> findByCA(String type){
        List<AvionModel> avion=afficherAvion();
        List<AvionModel> resultat=avion.stream().filter(a->type.equals(a.getCodeAvion())).collect(Collectors.toList());
        if(resultat.isEmpty()){
            System.out.println("l'avion n existe pas");
        }else{
            System.out.println("l avion existe");
        }
        return resultat;
    }
    
    public List<AvionModel> findByType(String type){
        List<AvionModel> avion=afficherAvion();
        List<AvionModel> resultat=avion.stream().filter(a->type.equals(a.getTypeA())).collect(Collectors.toList());
        if(resultat.isEmpty()){
            System.out.println("l'avion n existe pas");
        }else{
            System.out.println("l avion existe");
        }
        return resultat;
    }
    
     public List<AvionModel> findbyModel(String Model){
        List< AvionModel> avion=afficherAvion();
        List<AvionModel> resultat=avion.stream().filter(a->Model.equals(a.getModel())).collect(Collectors.toList());
        if(resultat.isEmpty()){
            System.out.println("l avion n existe pas");
        }else{
            System.out.println("l avion existe");
        }
        return resultat;
    }
         public List<AvionModel> findbyNP(int Np){
        List< AvionModel> avion=afficherAvion();
        List<AvionModel> resultat=avion.stream().filter(a->Np==a.getPassagerN()).collect(Collectors.toList());
        if(resultat.isEmpty()){
            System.out.println("l avion n existe pas");
        }else{
            System.out.println("l avion existe");
        }
        return resultat;
    }
            public List<AvionModel> sortByTypeA(){
        List<AvionModel> avion=afficherAvion();
        List<AvionModel> resultat=avion.stream().sorted(Comparator.comparing(AvionModel::getTypeA)).collect(Collectors.toList());
        return resultat;
    }
            public List<AvionModel> sortByModel(){
        List<AvionModel> avion=afficherAvion();
        List<AvionModel> resultat=avion.stream().sorted(Comparator.comparing(AvionModel::getModel)).collect(Collectors.toList());
        return resultat;
    }
            public List<AvionModel> sortByPN(){
        List<AvionModel> avion=afficherAvion();
        List<AvionModel> resultat=avion.stream().sorted(Comparator.comparing(AvionModel::getPassagerN)).collect(Collectors.toList());
        return resultat;
    }
}
