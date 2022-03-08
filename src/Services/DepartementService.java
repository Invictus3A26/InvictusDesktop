/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Departement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import Tools.MaConnexion;
import java.util.stream.Collectors;

/**
 *
 * @author Aziz
 */
public class DepartementService {

    Connection cnx;

    public DepartementService() {
        cnx = MaConnexion.getInstance().getCnx();
    }

    public void ajouterDepartement(Departement d) {
        String sql = "INSERT INTO `departement`(`id`, `nomDepartement`, `zoneDepartement` , `detailDepartement`  ) VALUES ('" + d.getId() + "','" + d.getNomDepartement() + "','" + d.getZoneDepartement() + "','" + d.getDetailDepartement() + "')";
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Departement Ajoutee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public List<Departement> afficherDepartement() {
        List<Departement> departement = new ArrayList<>();
        String query = "select * from departement";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                Departement d = new Departement();
                d.setId(rs.getInt("id"));
                d.setNomDepartement(rs.getString("nomDepartement"));
                d.setZoneDepartement(rs.getString("zoneDepartement"));
                d.setDetailDepartement(rs.getString("detailDepartement"));
                departement.add(d);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return departement;
    }

    public void supprimerDepartement(int id) {

        try {
            String sql = "DELETE FROM departement WHERE id=" + id + "";
            PreparedStatement ste = cnx.prepareStatement(sql);

            ste.executeUpdate();
            System.out.println("Departement Supprimée ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifierDepartement(int id, Departement d) {

        try {
            PreparedStatement ste;
            ste = cnx.prepareStatement(
                    "UPDATE `departement` SET `nomDepartement`=?,`zoneDepartement`=?,"
                    + "`detailDepartement`=? WHERE id=?");
            ste.setString(1, d.getNomDepartement());
            ste.setString(2, d.getZoneDepartement());
            ste.setString(3, d.getDetailDepartement());
            ste.setInt(4, id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void ChercherDepartement() throws SQLException {
        
        List<Departement> departement = new ArrayList<>();
        try {

            String sql = "Select * from departement WHERE `id` =" ;
            ResultSet rs;
            PreparedStatement ste;
            ste = cnx.prepareStatement(sql);
            rs = ste.executeQuery();

            while (rs.next()) {
                Departement u = new Departement();
                u.setId(rs.getInt(1));
                u.setNomDepartement(rs.getString(2));
                u.setZoneDepartement(rs.getString(3));
                u.setDetailDepartement(rs.getString(4));

                departement.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepartementService.class.getName()).log(Level.SEVERE, null, ex);

        }
        System.out.println(departement);
    }

    public List<Departement> findByName(String NomDepartement) {

        List<Departement> Departement = afficherDepartement();
        List<Departement> resultat = Departement.stream().filter(departement -> NomDepartement.equals(departement.getNomDepartement())).collect(Collectors.toList());
        if (resultat.isEmpty()) {
            System.out.println("Aucun departement par ce nom");
            return null;

        } else {
            System.out.println("Departement : ");
            return resultat;

        }


    }
    public List<Departement> findByZone(String ZoneDepartement) {

        List<Departement> Departement = afficherDepartement();
        List<Departement> resultat = Departement.stream().filter(departement -> ZoneDepartement.equals(departement.getZoneDepartement())).collect(Collectors.toList());
        if (resultat.isEmpty()) {
            System.out.println("Aucun departement par cette zone");
            return null;

        } else {
            System.out.println("Departement : ");
            return resultat;

        }


    }
    public List<Departement> findByDetail(String DetailDepartement) {

        List<Departement> Departement = afficherDepartement();
        List<Departement> resultat = Departement.stream().filter(departement -> DetailDepartement.equals(departement.getDetailDepartement())).collect(Collectors.toList());
        if (resultat.isEmpty()) {
            System.out.println("Aucun departement par ce detail");
            return null;

        } else {
            System.out.println("Departement : ");
            return resultat;

        }


    }

}
