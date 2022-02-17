/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionutilisateur1.service;

import gestionutilisateur1.entity.Role;
import gestionutilisateur1.entity.User;
import gestionutilisateur1.utils.MyConnexion;
import gestionutilisateur1.service.CryptWithMD5;
import static gestionutilisateur1.service.CryptWithMD5.cryptWithMD5;
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
 * @author MSI
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
            String query = "INSERT INTO `user`( `adresse`, `date_naissance`, `email`, `name`, `num_tel`, `password`, `role`, `username`) "
                    + "VALUES ('" + t.getAdresse() + "','" + t.getDate_naissance() + "','" + t.getEmail() + "','" + t.getName() + "','" + t.getNumTel() + "','" + cryptWithMD5(t.getPassword()) + "','" + t.getRole() + "','" + t.getUsername() + "')";
            st.executeUpdate(query);
            System.out.println("user ajouté avec success");
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifier(long id_amodifier, User t) {
        try {
            PreparedStatement st;
            st = cnx.prepareStatement("UPDATE `user` SET `adresse`=?,`date_naissance`=?,`email`=?,`name`=?,`num_tel`=?,`password`=?,`role`=?,`username`=? WHERE id=?");
            st.setString(1, t.getAdresse());
            st.setDate(2, new java.sql.Date(t.getDate_naissance().getTime()));
            st.setString(3, t.getEmail());
            st.setString(4, t.getName());
            st.setInt(5, t.getNumTel());
            st.setString(6, cryptWithMD5(t.getPassword()));
            st.setString(7, t.getRole().toString());
            st.setString(8, t.getUsername());
            st.setLong(9, id_amodifier);

            if (st.executeUpdate() == 1) {
                System.out.println("user modifié avec success");
            } else {
                System.out.println("user does not exist");
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
                System.out.println("user does not exist");
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
                u.setName(rs.getString("name"));
                u.setNumTel(rs.getInt("num_tel"));
                u.setPassword(rs.getString("password"));
                u.setUsername(rs.getString("username"));
                u.setRole(Role.valueOf(rs.getString("role")));
                lu.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lu;
    }

}
