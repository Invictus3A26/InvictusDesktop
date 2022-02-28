/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.AvCom;
import Entities.CompagnieModel;
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
public class CompagnieService {
    Connection cnx;

    public CompagnieService() {
        cnx=DBConnexion.getInstance().getCnx();
    }
 public void ajouterCompagnie(CompagnieModel C){
          String sql="INSERT INTO compagnie(Code_IATA, NomCom, Link, Pays, Number, Siege, AeBase, PassagerNum,  Description,id_aeroport) VALUES ('"+C.getCode_IATA()+"','"+C.getNomCom()+"','"+C.getLink()+"','"+C.getPays()+"','"+C.getNumber()+"','"+C.getSiege()+"','"+C.getAeBase()+"','"+C.getPassagerNum()+"','"+C.getDescription()+"','"+C.getId_aeroport()+"')";
           try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Compagnie Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      }
        
        
    
       public List<CompagnieModel> afficherCompagnie(){
        List<CompagnieModel> compagnie = new ArrayList<>();
        String query="select * from compagnie";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs= ste.executeQuery();
            while(rs.next()){
                CompagnieModel C = new CompagnieModel();
                C.setCode_IATA(rs.getString("Code_IATA"));
                C.setNomCom(rs.getString("NomCom"));
                C.setLink(rs.getString("Link"));
                C.setPays(rs.getString("Pays"));
                C.setNumber(rs.getInt("Number"));
                C.setSiege(rs.getString("Siege"));
                C.setAeBase(rs.getString("AeBase"));
                C.setPassagerNum(rs.getFloat("PassagerNum"));
                C.setDescription(rs.getString("Description"));
                C.setId_aeroport(rs.getInt("id_aeroport"));
                compagnie.add(C);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         return compagnie;
        }
       
 public void deleteCompagnie(String Code_IATA){
        String sql ="DELETE FROM compagnie WHERE Code_IATA=? ";
         try {
            PreparedStatement ste = cnx.prepareStatement(sql);
              ste.setString(1, Code_IATA);
              ste.executeUpdate();
            System.out.println("Compagnie Supprimer");
         }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            
    }
public void modifierCompagnie(String Code_IATA, CompagnieModel c) {

        try {
            PreparedStatement ste;
            ste=cnx.prepareStatement(
                    "UPDATE compagnie SET NomCom=?,Link=?,"
                            + "Pays=?,Number=?,Siege=?,"
                            + "AeBase=?,PassagerNum=?,Description=?,id_aeroport=? WHERE Code_IATA=?");
            ste.setString(1,c.getNomCom());
            ste.setString(2,c.getLink());
            ste.setString(3,c.getPays());
            ste.setInt(4, c.getNumber());
            ste.setString(5,c.getSiege());
            ste.setString(6, c.getAeBase());
            ste.setFloat(7,c.getPassagerNum());
            ste.setString(8,c.getDescription());
            ste.setInt(9,c.getId_aeroport());
            ste.setString(10, Code_IATA);
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
        public List<AvCom> CalculAvionC(){
        List<AvCom> avcom = new ArrayList<>();
        String query="SELECT CodeC, C.NomCom, COUNT(*) 'nombreAv'\n" +
"FROM compagnie C , avion A \n" +
"WHERE CodeC=C.Code_IATA\n" +
"GROUP BY C.Code_IATA;";
       String sql = "INSERT INTO `avcom`(`CodeCom`, `NomC`, `numbAvC`) Values (?,?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            PreparedStatement st = cnx.prepareStatement(sql);
            ResultSet rs= ste.executeQuery();
            while(rs.next()){
                AvCom AC = new AvCom();
                AC.setCodeCom(rs.getString("CodeC"));
                AC.setNomC(rs.getString("NomCom"));
                AC.setNumbAvC(rs.getInt("nombreAv"));
                st.setString(1, AC.getCodeCom());
                st.setString(2, AC.getNomC());
                st.setInt(3, AC.getNumbAvC());
                avcom.add(AC);
                st.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         return avcom;
        }

    
   
}
