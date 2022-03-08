/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Role;
import Entities.User;
import Tools.MyConnexion;
import static Services.CryptWithMD5.cryptWithMD5;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author LENOVO
 */
public class UserService implements IService<User> {

    Connection cnx;

    public UserService() {
        cnx = MyConnexion.getInstance().getCnx();

    }

    @Override
    public void ajouter(User t) {
        try {
            Statement st;
            st = cnx.createStatement();
            String query = "INSERT INTO `user`( `adresse`, `date_naissance`, `email`, `nom`, `num_tel`, `password`, `prenom`, `role`, `username`) "
                    + "VALUES ('" + t.getAdresse() + "','" + t.getDate_naissance() + "','" + t.getEmail() + "','" + t.getNom() + "','" + t.getNumTel() + "','" + cryptWithMD5(t.getPassword()) + "','" + t.getPrenom() + "','" + t.getRole() + "','" + t.getUsername() + "')";
            st.executeUpdate(query);
            System.out.println("user ajouter avec success");
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifier(long id_amodifier, User t) {
        try {
            System.out.println("1");

            PreparedStatement st;
            st = cnx.prepareStatement("UPDATE `user` SET `adresse`=?,`date_naissance`=?, `email`=?,`nom`=?,`num_tel`=?,`prenom`=?,"
                    + "`role`=?,`username`=? WHERE id=?");
            System.out.println("2");

            st.setString(1, t.getAdresse());
            st.setDate(2, new java.sql.Date(t.getDate_naissance().getTime()));
            st.setString(3, t.getEmail());
            st.setString(4, t.getNom());
            st.setInt(5, t.getNumTel());
            // st.setString(6, cryptWithMD5(t.getPassword()));
            st.setString(6, t.getPrenom());
            st.setString(7, t.getRole().toString());
            st.setString(8, t.getUsername());
            st.setLong(9, id_amodifier);
            if (st.executeUpdate() == 1) {
                System.out.println("user modifier avec success");
            } else {
                System.out.println("user n'existe pas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void supprimer(long id) {
        try {
            Statement st = cnx.createStatement();
            String query = "delete from user where id=" + id;
            if (st.executeUpdate(query) == 1) {
                System.out.println("suppression avec success");
            } else {
                System.out.println("user n'existe pas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<User> afficher() {
        List<User> lu = new ArrayList<>();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from user";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                User u = new User();
                u.setAdresse(rs.getString("adresse"));
                u.setDate_naissance(rs.getDate("date_naissance"));
                u.setEmail(rs.getString("email"));
                u.setId(rs.getLong("id"));
                u.setNom(rs.getString("nom"));
                u.setNumTel(rs.getInt("num_tel"));
                u.setPassword(cryptWithMD5(rs.getString("password")));
                u.setPrenom(rs.getString("prenom"));
                u.setUsername(rs.getString("username"));
                u.setRole(Role.valueOf(rs.getString("role")));
                lu.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lu;
    }

    @Override
    public List<User> afficheremployes() {
        List<User> lu = new ArrayList<>();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from user where role='EMPLOYE'";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                User u = new User();
                u.setAdresse(rs.getString("adresse"));
                u.setDate_naissance(rs.getDate("date_naissance"));
                u.setEmail(rs.getString("email"));
                u.setId(rs.getLong("id"));
                u.setNom(rs.getString("nom"));
                u.setNumTel(rs.getInt("num_tel"));
                u.setPassword(cryptWithMD5(rs.getString("password")));
                u.setPrenom(rs.getString("prenom"));
                u.setUsername(rs.getString("username"));
                u.setRole(Role.valueOf(rs.getString("role")));
                lu.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lu;
    }

    @Override
    public List<User> afficherpassager() {
        List<User> lu = new ArrayList<>();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from user where role='PASSAGER'";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                User u = new User();
                u.setAdresse(rs.getString("adresse"));
                u.setDate_naissance(rs.getDate("date_naissance"));
                u.setEmail(rs.getString("email"));
                u.setId(rs.getLong("id"));
                u.setNom(rs.getString("nom"));
                u.setNumTel(rs.getInt("num_tel"));
                u.setPassword(cryptWithMD5(rs.getString("password")));
                u.setPrenom(rs.getString("prenom"));
                u.setUsername(rs.getString("username"));
                u.setRole(Role.valueOf(rs.getString("role")));
                lu.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lu;
    }

    @Override
    public int affichernumber(String role) {
        if (role.equals("PASSAGER")) {
            try {
                Statement st = cnx.createStatement();
                String query = "select count(*) from user where role='PASSAGER'";
                ResultSet rs = st.executeQuery(query);
                if (rs.next()) {
                    int theCount = rs.getInt(1);
                    System.out.println(theCount);
                    return theCount;

                }
            } catch (SQLException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (role.equals("EMPLOYE")) {
            try {
                Statement st = cnx.createStatement();
                String query = "select count(*) from user where role='EMPLOYE'";
                ResultSet rs = st.executeQuery(query);
                if (rs.next()) {
                    int theCount = rs.getInt(1);
                    System.out.println(theCount);
                    return theCount;

                }
            } catch (SQLException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return 0;
    }

    @Override
    public int affichercount(String table) {
        if (table.equals("reclamation")) {
            try {
                Statement st = cnx.createStatement();
                String query = "select count(*) from reclamation";
                System.out.println("**********************");
                ResultSet rs = st.executeQuery(query);

                if (rs.next()) {
                    int theCount = rs.getInt(1);
                    System.out.println(theCount);
                    return theCount;

                }
            } catch (SQLException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return 0;
    }

    public User findById(long id) {
        User u = new User();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from user where id=" + id;
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {

                u.setAdresse(rs.getString("adresse"));
                u.setDate_naissance(rs.getDate("date_naissance"));
                u.setEmail(rs.getString("email"));
                u.setId(rs.getLong("id"));
                u.setNom(rs.getString("nom"));
                u.setNumTel(rs.getInt("num_tel"));
                u.setPassword(cryptWithMD5(rs.getString("password")));
                u.setPrenom(rs.getString("prenom"));
                u.setUsername(rs.getString("username"));
                u.setRole(Role.valueOf(rs.getString("role")));
            } else {
                System.out.println("user n existe pas");

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;

    }

    public List<User> findByName(String name) {
        List<User> users = afficher();
        List<User> resultat = users.stream().filter(user -> name.equals(user.getNom())).collect(Collectors.toList());
        if (resultat.isEmpty()) {
            System.out.println("l utilisateur n existe pas");
        } else {
            System.out.println("l utilisateur existe");
        }
        //resultat.forEach(System.out::println);
        return resultat;
    }

    public List<User> findByPrenom(String prenom) {
        List<User> users = afficher();
        List<User> resultat = users.stream().filter(user -> prenom.equals(user.getPrenom())).collect(Collectors.toList());
        if (resultat.isEmpty()) {
            System.out.println("l utilisateur n existe pas");
        } else {
            System.out.println("l utilisateur existe");
        }
        return resultat;
    }

    public List<User> findByRole(Role role) {
        List<User> users = afficher();
        List<User> resultat = users.stream().filter(user -> role.equals(user.getRole())).collect(Collectors.toList());

        if (resultat.isEmpty()) {
            System.out.println("l utilisateur n existe pas");
        } else {
            System.out.println("l utilisateur existe");
        }
        return resultat;
    }

    public List<User> findByDate(Date date) {
        List<User> users = afficher();
        List<User> resultat = users.stream().filter(user -> date.toString().equals(user.getDate_naissance().toString())).collect(Collectors.toList());
        if (resultat.isEmpty()) {
            System.out.println("l utilisateur n existe pas");
        } else {
            System.out.println("l utilisateur existe");
        }
        return resultat;
    }

    public List<User> findByEmail(String email) {
        List<User> users = afficher();
        List<User> resultat = users.stream().filter(user -> email.equals(user.getEmail())).collect(Collectors.toList());
        if (resultat.isEmpty()) {
            System.out.println("l utilisateur n existe pas");
        } else {
            System.out.println("l utilisateur existe");
        }
        return resultat;
    }

    public boolean usernameExist(String username) {

        try {
            Statement st = cnx.createStatement();
            String query = "select * from user where username='" + username + "'";
            ResultSet rs = st.executeQuery(query);
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public User findByUsername(String username) {
        User u = new User();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from user where username='" + username + "'";
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                u.setAdresse(rs.getString("adresse"));
                u.setDate_naissance(rs.getDate("date_naissance"));
                u.setEmail(rs.getString("email"));
                u.setId(rs.getLong("id"));
                u.setNom(rs.getString("nom"));
                u.setNumTel(rs.getInt("num_tel"));
                u.setPassword(cryptWithMD5(rs.getString("password")));
                u.setPrenom(rs.getString("prenom"));
                u.setUsername(rs.getString("username"));
                u.setRole(Role.valueOf(rs.getString("role")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

    public List<User> findByNum(int numtel) {
        List<User> users = afficher();
        List<User> resultat = users.stream().filter(user -> numtel == user.getNumTel()).collect(Collectors.toList());
        if (resultat.isEmpty()) {
            System.out.println("l utilisateur n existe pas");
        } else {
            System.out.println("l utilisateur existe");
        }
        return resultat;
    }

    public List<User> findByAdresse(String adresse) {
        List<User> users = afficher();
        List<User> resultat = users.stream().filter(user -> adresse.equals(user.getAdresse())).collect(Collectors.toList());
        if (resultat.isEmpty()) {
            System.out.println("l utilisateur n existe pas");
        } else {
            System.out.println("l utilisateur existe");
        }
        return resultat;
    }

    public List<User> sortByDate() {
        List<User> users = afficher();
        List<User> resultat = users.stream().sorted(Comparator.comparing(User::getDate_naissance)).collect(Collectors.toList());
        return resultat;

    }

    public List<User> sortById() {
        List<User> users = afficher();
        List<User> resultat = users.stream().sorted(Comparator.comparing(User::getId)).collect(Collectors.toList());
        return resultat;
    }

    public List<User> sortByNom() {
        List<User> users = afficher();
        List<User> resultat = users.stream().sorted(Comparator.comparing(User::getNom)).collect(Collectors.toList());
        return resultat;
    }

    public boolean checklogin(String username, String password) {
        try {
            Statement st = cnx.createStatement();
            String query = "SELECT * FROM `user` WHERE `username`='" + username + "' AND `password`='" + password + "'";
            ResultSet rs = st.executeQuery(query);
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

}
