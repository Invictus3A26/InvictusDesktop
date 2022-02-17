/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Tools.DataSource;
import entities.FraisBagage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        connection=DataSource.getInstance().getCnx();        
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
            Logger.getLogger(BagagesCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void delete(int id){
        String requete="delete from Fraisbagage where id ="+id;
        
        try {
            ste=connection.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(BagagesCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        String req = "SELECT * FROM FraisBagage ";
        ste = connection.createStatement();
        rs = ste.executeQuery(req);
        while (rs.next()) {
            list.add(new FraisBagage(rs.getInt("id"), rs.getString("poids"), rs.getString("dimension"), rs.getInt("tarifs_base"), rs.getInt("tarifs_confort"), rs.getInt("montant")));
        }
        return list;
    }

    @Override
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    @Override
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
