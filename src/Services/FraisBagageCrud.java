/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.FraisBagage;
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
 * @author dell
 */
public class FraisBagageCrud implements Iservice<FraisBagage>  {
    
    
     private Connection connection;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    private List<FraisBagage> list = new ArrayList<>();

    public FraisBagageCrud () {
        connection=MyConnexion.getInstance().getCnx();        
    }
    public void ajouterFraisBagage(FraisBagage f){
        String requete="insert into FraisBagage (poids,dimension,tarifs_base,tarifs_confort,montant) values ('"+f.getPoids()+"','"+f.getDimension()+"','"+f.getTarifs_base()+"','"+f.getTarifs_confort()+"','"+f.getMontant()+"')";
        
        try {
            ste=connection.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(FraisBagageCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     public void ajouterFraisBagagesPst(FraisBagage f ){
       String requete="insert into FraisBagage (poids,dimension,tarifs_base,tarifs_confort,montant) values (?,?,?,?,?)";
       
        try {
            pst=connection.prepareStatement(requete);
            
            pst.setString(1, f.getPoids());
            pst.setString(2, f.getDimension());
            pst.setInt(3, f.getTarifs_base());
            pst.setInt(4, f.getTarifs_confort());
            pst.setInt(5, f.getMontant());

            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(FraisBagageCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public int supprimer(int id){
       int i = 0;
        try {
            Statement ste = connection.createStatement();
            String sql = "DELETE FROM fraisbagage WHERE id=" + id;
            i = ste.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(FraisBagageCrud.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return i;
    }
     
     public void modifierBagages(long id , FraisBagage f) {

        try {
            PreparedStatement ste;
            ste=connection.prepareStatement("UPDATE `bagages` SET `poids`=?,"
                    + "`dimension`=?,`tarifs_base`=?,`tarifs_confort`=?,`Montant`=? WHERE `id`=?"  );
            ste.setString(1, f.getPoids());
            ste.setString(2,f.getDimension());
            ste.setInt(3, f.getTarifs_base());
            ste.setInt(4, f.getTarifs_confort());
            ste.setInt(5, f.getMontant());
            ste.setLong(6, id);


            ste.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }}
     
        public List<FraisBagage> getAllFraisBagage() throws SQLException {
       List<FraisBagage> lu=new ArrayList<>();
        try {
            
            Statement ste=connection.createStatement();
            String query="select * from FraisBagage";
            ResultSet rs=ste.executeQuery(query);
            while(rs.next()){
                FraisBagage a =new FraisBagage();
                a.setId(rs.getInt("id"));
 
                a.setPoids(rs.getString("poids"));
                a.setDimension(rs.getString("dimension"));
                a.setTarifs_base(rs.getInt("tarifs_base"));
                a.setTarifs_confort(rs.getInt("tarifs_confort"));
                 a.setMontant(rs.getInt("montant"));


                

                lu.add(a);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FraisBagageCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lu;
    }

        
        public List<FraisBagage> sortByMontant() throws SQLException{
         List<FraisBagage> FraisBagages=getAllFraisBagage();
         List<FraisBagage> resultat=FraisBagages.stream().sorted(Comparator.comparing(FraisBagage::getMontant)).collect(Collectors.toList());
         return resultat;
     }
     
     public List<FraisBagage> sortByTarifs_base() throws SQLException{
         List<FraisBagage> FraisBagages=getAllFraisBagage();
         List<FraisBagage> resultat=FraisBagages.stream().sorted(Comparator.comparing(FraisBagage::getTarifs_base)).collect(Collectors.toList());
         return resultat;
     }

    public void ajouter(FraisBagage t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<FraisBagage> readAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public FraisBagage readById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(FraisBagage t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(FraisBagage t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }





    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

   
    

