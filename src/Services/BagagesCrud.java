/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Tools.DataSource;
import entities.Bagages;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



public class BagagesCrud implements Iservice <Bagages> {
    
    private Connection connection;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    private List<Bagages> list = new ArrayList<>();


    public BagagesCrud() {
        connection=DataSource.getInstance().getCnx();        
    }
    
     public void ajouterBagage(Bagages b){
        String requete="insert into bagages (poidsS,poidsM,num_valise) values ('"+b.getPoidsS()+"','"+b.getPoidsM()+"','"+b.getNum_valise()+"')";
        
        try {
            ste=connection.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(BagagesCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     public void ajouterBagagesPst(Bagages b){
       String requete="insert into bagages (poidsS,poidsM,num_valise) values (?,?,?)";
       
        try {
            pst=connection.prepareStatement(requete);
            
            pst.setString(1, b.getPoidsS());
            pst.setString(2, b.getPoidsM());
            pst.setInt(3, b.getNum_valise());

            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(BagagesCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void delete(int id){
        String requete="delete from bagages where id ="+id;
        
        try {
            ste=connection.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(BagagesCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     
     
    public void modifierBagages(long id , Bagages b) {

        try {
            PreparedStatement ste;
            ste=connection.prepareStatement("UPDATE `bagages` SET `poidsS`=?,"
                    + "`poidsM`=?,`num_valise`=? WHERE `id`=?"  );
            ste.setString(1, b.getPoidsS());
            ste.setString(2, b.getPoidsM());
            ste.setInt(3, b.getNum_valise());
            ste.setLong(4, id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }





    }
     public List<Bagages> getAllBagages() throws SQLException {
        String req = "SELECT * FROM Bagages";
        ste = connection.createStatement();
        rs = ste.executeQuery(req);
        while (rs.next()) {
            list.add(new Bagages(rs.getInt("id"), rs.getString("poidsS"), rs.getString("poidsM"), rs.getInt("Num_valise")));
        }
        return list;
    }
    
     /* public  void modifierBagages(long id , Bagages b) {
       
            
            String requete = "UPDATE `bagages` SET `poidsS`=?,"
                    + "`poidsM`=?,`num_valise`=? WHERE `id`=?";
             try {
            pst=connection.prepareStatement(requete);
            pst.setString(1, b.getPoidsS());
            pst.setString(2, b.getPoidsM());
            pst.setInt(3, b.getNum_valise());
            pst.setLong(4, id);


              
          pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
          

    }*/
    /*public Bagages BagageParId(int id_b) throws SQLException {
    List<Bagages> arr=new ArrayList<>();
    //JSONArray roles = new JSONArray();
        
    ste = connection.createStatement();
    ResultSet rs=ste.executeQuery("select * from personnel where id='"+id_b+"'");
    Bagages b = new Bagages();
     while (rs.next()) {                
              int id=rs.getInt("id");
              
                
                b= new Bagages(id, poidsS , poidsM , num_valise);
  
               
     }
     return b;
           
    }*/
    

    /*public List<Bagages> readAll(){
        List<Bagages> list=new ArrayList<>();
        
        String requete="select * from bagages";
        try {
            ste=connection.createStatement();
            rs=ste.executeQuery(requete);
            while(rs.next()){
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(BagagesCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return list;
    }
    */
    
    

    @Override
    public void ajouter(Bagages t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public Bagages readById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Bagages t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(Bagages t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Bagages> readAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    
    
 
    
    
    
    
    
    
    
    
    
}
        
        
    

