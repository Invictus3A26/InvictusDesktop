/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Tools.MyConnexion;
import Entities.Bagage;
import GUI.Id;
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

public class BagagesCrud implements Iservice<Bagage> {

    private Connection connection;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    private List<Bagage> list = new ArrayList<>();

    public BagagesCrud() {
        connection = MyConnexion.getInstance().getCnx();
    }

    public void ajouterBagage(Bagage b) {
        String requete = "insert into bagage (poidsS,poidsM,num_valise,poids,dimension,id_userr) values ('" + b.getPoidsS() + "','" + b.getPoidsM() + "','" + b.getNum_valise() + "','" + b.getPoids() + "','" + b.getDimension() + "','" + Id.user + "')";
        System.out.println("***********************");

        System.out.println(Id.user);
        System.out.println("***********************");

        try {
            ste = connection.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(BagagesCrud.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ajouterBagagesPst(Bagage b) {
        String requete = "insert into bagage (poidsS,poidsM,num_valise,poids,dimension) values (?,?,?,?,?)";

        try {
            pst = connection.prepareStatement(requete);

            pst.setString(1, b.getPoidsS());
            pst.setString(2, b.getPoidsM());
            pst.setInt(3, b.getNum_valise());
            pst.setString(4, b.getPoids());
            pst.setString(5, b.getDimension());

            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(BagagesCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int supprimerbagage(int id) throws SQLException {

        int i = 0;
        try {
            Statement ste = connection.createStatement();
            String sql = "DELETE FROM bagage WHERE id=" + id;
            i = ste.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(BagagesCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }

    public void modifierBagages(Integer id, Bagage b) {

        try {
            PreparedStatement ste;
            ste = connection.prepareStatement("UPDATE `bagage` SET `poids`=?,`poidsM`=?, `poidsS`=?,"
                    + "`dimension`=?,`num_valise`=? WHERE `id`=?");
            ste.setString(1, b.getPoids());
            ste.setString(2, b.getPoidsM());
            ste.setString(3, b.getPoidsS());
            ste.setString(4, b.getDimension());
            ste.setInt(5, b.getNum_valise());
            ste.setLong(6, id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public List<Bagage> getAllBagages() throws SQLException {
        String req = "SELECT * FROM bagage";
        ste = connection.createStatement();
        rs = ste.executeQuery(req);
        while (rs.next()) {
            list.add(new Bagage(rs.getInt("id"), rs.getString("poidsS"), rs.getString("poidsM"), rs.getInt("Num_valise"), rs.getString("poids"), rs.getString("dimension")));
        }
        return list;
    }

    public Bagage findById(long id) {
        Bagage u = new Bagage();
        try {
            ste = connection.createStatement();
            String query = "select * from user where id=" + id;
            ResultSet rs = ste.executeQuery(query);
            if (rs.next()) {

                u.setPoidsM(rs.getString("PoidsM"));
                u.setPoidsS(rs.getString("poidsS"));
                u.setDimension(rs.getString("dimension"));
                u.setPoids(rs.getString("Poids"));
                u.setNum_valise(rs.getInt("Num_valise"));

            } else {
                System.out.println("bagage n existe pas");

            }
        } catch (SQLException ex) {
            Logger.getLogger(BagagesCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;

    }

    public List<Bagage> sortByNum_valise() throws SQLException {
        List<Bagage> bagages = getAllBagages();
        List<Bagage> resultat = bagages.stream().sorted(Comparator.comparing(Bagage::getNum_valise)).collect(Collectors.toList());
        return resultat;
    }

    public List<Bagage> chercherBagage(Object o) {
        String query = "";
        String ch = "";
        int i = 0;
        List<Bagage> bagages = new ArrayList<>();
        if (o instanceof Integer) {
            i = (Integer) o;
            query = "SELECT * FROM bagage WHERE id = " + i + "";
        }
        try {
            System.out.println(query);
            PreparedStatement ste = connection.prepareStatement(query);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                Bagage u = new Bagage();

                u.setPoidsM(rs.getString("PoidsM"));
                u.setPoidsS(rs.getString("poidsS"));
                u.setDimension(rs.getString("dimension"));
                u.setPoids(rs.getString("Poids"));
                u.setNum_valise(rs.getInt("Num_valise"));
                bagages.add(u);

                System.out.println(bagages);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return bagages;
    }

    /* public  void modifierBagages(long id , Bagage b) {
       
            
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
 /*public Bagage BagageParId(int id_b) throws SQLException {
    List<Bagages> arr=new ArrayList<>();
    //JSONArray roles = new JSONArray();
        
    ste = connection.createStatement();
    ResultSet rs=ste.executeQuery("select * from personnel where id='"+id_b+"'");
    Bagage b = new Bagage();
     while (rs.next()) {                
              int id=rs.getInt("id");
              
                
                b= new Bagage(id, poidsS , poidsM , num_valise);
  
               
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
    public void ajouter(Bagage t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Bagage readById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Bagage t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(Bagage t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Bagage> readAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
