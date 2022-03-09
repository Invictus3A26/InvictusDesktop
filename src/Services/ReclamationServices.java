/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

//import static com.sun.org.omg.SendingContext.CodeBasePackage.ValueDescSeqHelper.type;
import Entities.Reclamation;
import GUI.Id;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import static org.omg.CORBA.ServiceInformationHelper.type;
import Tools.MyConnexion;

public class ReclamationServices {

    Connection cnx;

    public ReclamationServices() {
        cnx = MyConnexion.getInstance().getCnx();
    }

    public void ajouterReclamation(Reclamation r) {
        String sql = "insert into reclamation(nom,prenom,email,tel,type,nbr,etat,description,date_reclamation,id_user) values(?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, r.getNom());
            ste.setString(2, r.getPrenom());
            ste.setString(3, r.getEmail());
            ste.setInt(4, r.getTel());
            ste.setString(5, r.getType());
            ste.setInt(6, r.getNbr());
            ste.setString(7, r.getEtat());
            ste.setString(8, r.getDescription());
            ste.setDate(9, new java.sql.Date(r.getDate_reclamation().getTime()));
            ste.setInt(10, (int) Id.user);

            ste.executeUpdate();
            System.out.println("Reclamation Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int supprimerreclamation(int id) throws SQLException {

        int i = 0;
        try {
            Statement ste = cnx.createStatement();
            String sql = "DELETE FROM `reclamation` WHERE id=" + id;
            i = ste.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }

    public void modifierReclamation(long id, Reclamation r) {

        try {
            PreparedStatement ste;
            ste = cnx.prepareStatement(
                    "UPDATE `reclamation` SET `nom`=?,`prenom`=?,"
                    + "`email`=?,`tel`=?,`type`=?,"
                    + "`nbr`=?,`etat`=?,"
                    + "`description`=?,`date_reclamation`=? WHERE id=?");
            ste.setString(1, r.getNom());
            ste.setString(2, r.getPrenom());
            ste.setString(3, r.getEmail());
            ste.setInt(4, r.getTel());
            ste.setString(5, r.getType());
            ste.setInt(6, r.getNbr());
            ste.setString(7, r.getEtat());
            ste.setString(8, r.getDescription());
            ste.setDate(9, new java.sql.Date(r.getDate_reclamation().getTime()));
            ste.setLong(10, id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public List<Reclamation> afficherReclamation() {
        List<Reclamation> reclamations = new ArrayList<>();
        String query = "select * from reclamation";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
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

    public List<Reclamation> ReclamationParId(String id) throws SQLException {
        List<Reclamation> reclamations = new ArrayList<>();
        //JSONArray roles = new JSONArray();

        Statement ste = cnx.createStatement();
        ResultSet rs = ste.executeQuery("select * from reclamation where (sn LIKE '%" + id + "%')");
        Reclamation r = new Reclamation();
        while (rs.next()) {

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
        return reclamations;

    }

    public Reclamation findById(long id) {
        Reclamation r = new Reclamation();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from reclamation where id=" + id;
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {

                r.setNom(rs.getString("nom"));
                r.setPrenom(rs.getString("prenom"));
                r.setEmail(rs.getString("email"));
                r.setTel(rs.getInt("tel"));
                r.setType(rs.getString("type"));
                r.setNbr(rs.getInt("nbr"));
                r.setEtat(rs.getString("etat"));
                r.setDescription(rs.getString("description"));
                r.setDate_reclamation(rs.getDate("date_reclamation"));
            } else {
                System.out.println("Reclamation n existe pas");

            }
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;

    }

    public Reclamation findByNom(String nom) {
        Reclamation r = new Reclamation();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from reclamation where nom='" + nom + "'";
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                r.setNom(rs.getString("nom"));
                r.setPrenom(rs.getString("prenom"));
                r.setEmail(rs.getString("email"));
                r.setTel(rs.getInt("tel"));
                r.setType(rs.getString("type"));
                r.setNbr(rs.getInt("nbr"));
                r.setEtat(rs.getString("etat"));
                r.setDescription(rs.getString("description"));
                r.setDate_reclamation(rs.getDate("date_reclamation"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public List<Reclamation> sortByDate() {
        List<Reclamation> reclamations = afficherReclamation();
        List<Reclamation> resultat = reclamations.stream().sorted(Comparator.comparing(Reclamation::getDate_reclamation)).collect(Collectors.toList());
        return resultat;

    }

    public List<Reclamation> sortById() {
        List<Reclamation> reclamations = afficherReclamation();
        List<Reclamation> resultat = reclamations.stream().sorted(Comparator.comparing(Reclamation::getId)).collect(Collectors.toList());
        return resultat;
    }

    public List<Reclamation> sortByNom() {
        List<Reclamation> reclamations = afficherReclamation();
        List<Reclamation> resultat = reclamations.stream().sorted(Comparator.comparing(Reclamation::getNom)).collect(Collectors.toList());
        return resultat;
    }

    public List<Reclamation> sortByNbr() {
        List<Reclamation> reclamations = afficherReclamation();
        List<Reclamation> resultat = reclamations.stream().sorted(Comparator.comparing(Reclamation::getNbr)).collect(Collectors.toList());
        return resultat;
    }

    public int getNbrReclamation() {
        String sql = "SELECT COUNT(*) FROM reclamation";
        ResultSet rs;
        int countIdRec = 0;
        try {
            PreparedStatement st = cnx.prepareStatement(sql);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                countIdRec = res.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countIdRec;
    }

}
