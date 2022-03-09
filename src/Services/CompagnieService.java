/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.AvCom;
import Entities.CompagnieModel;
import Tools.MyConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Anis
 */
public class CompagnieService {

    Connection cnx;

    public CompagnieService() {
        cnx = MyConnexion.getInstance().getCnx();
    }

    public void ajouterCompagnie(CompagnieModel C) {
        String sql = "INSERT INTO compagnie(Code_IATA, NomCom, Link, Pays, Number, Siege, AeBase, PassagerNum,  Description) VALUES ('" + C.getCode_IATA() + "','" + C.getNomCom() + "','" + C.getLink() + "','" + C.getPays() + "','" + C.getNumber() + "','" + C.getSiege() + "','" + C.getAeBase() + "','" + C.getPassagerNum() + "','" + C.getDescription() + "')";
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Compagnie Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<CompagnieModel> afficherCompagnie() {
        List<CompagnieModel> compagnie = new ArrayList<>();
        String query = "select * from compagnie";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                CompagnieModel C = new CompagnieModel();
                C.setCode_IATA(rs.getString("Code_IATA"));
                C.setNomCom(rs.getString("NomCom"));
                C.setLink(rs.getString("Link"));
                C.setPays(rs.getString("Pays"));
                C.setNumber(rs.getInt("Number"));
                C.setSiege(rs.getString("Siege"));
                C.setAeBase(rs.getString("AeBase"));
                C.setPassagerNum(rs.getInt("PassagerNum"));
                C.setDescription(rs.getString("Description"));
                compagnie.add(C);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return compagnie;
    }

    public void deleteCompagnie(String Code_IATA) {
        String sql = "DELETE FROM compagnie WHERE Code_IATA=? ";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, Code_IATA);
            ste.executeUpdate();
            System.out.println("Compagnie Supprimer");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void modifierCompagnie(String Code_IATA, CompagnieModel c) {

        try {
            PreparedStatement ste;
            ste = cnx.prepareStatement(
                    "UPDATE compagnie SET NomCom=?,Link=?,"
                    + "Pays=?,Number=?,Siege=?,"
                    + "AeBase=?,PassagerNum=?,Description=? WHERE Code_IATA=?");
            ste.setString(1, c.getNomCom());
            ste.setString(2, c.getLink());
            ste.setString(3, c.getPays());
            ste.setInt(4, c.getNumber());
            ste.setString(5, c.getSiege());
            ste.setString(6, c.getAeBase());
            ste.setInt(7, c.getPassagerNum());
            ste.setString(8, c.getDescription());
            ste.setString(9, Code_IATA);
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<AvCom> CalculAvionC() {
        List<AvCom> avcom = new ArrayList<>();
        String query = "SELECT CodeC, C.NomCom, COUNT(*) 'nombreAv'\n"
                + "FROM compagnie C , avion A \n"
                + "WHERE CodeC=C.Code_IATA\n"
                + "GROUP BY C.Code_IATA;";
        String sql = "INSERT INTO `avcom`(`CodeCom`, `NomC`, `numbAvC`) Values (?,?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            PreparedStatement st = cnx.prepareStatement(sql);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
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

    public List<CompagnieModel> findbyCode(String CodeC) {
        List< CompagnieModel> comp = afficherCompagnie();
        List<CompagnieModel> resultat = comp.stream().filter(a -> CodeC.equals(a.getCode_IATA())).collect(Collectors.toList());
        if (resultat.isEmpty()) {
            System.out.println("l avion n existe pas");
        } else {
            System.out.println("l avion existe");
        }
        return resultat;
    }

    public List<CompagnieModel> findbyNom(String NomCom) {
        List< CompagnieModel> comp = afficherCompagnie();
        List<CompagnieModel> resultat = comp.stream().filter(a -> NomCom.equals(a.getCode_IATA())).collect(Collectors.toList());
        if (resultat.isEmpty()) {
            System.out.println("l avion n existe pas");
        } else {
            System.out.println("l avion existe");
        }
        return resultat;
    }

    public List<CompagnieModel> findbyLink(String Link) {
        List< CompagnieModel> comp = afficherCompagnie();
        List<CompagnieModel> resultat = comp.stream().filter(a -> Link.equals(a.getLink())).collect(Collectors.toList());
        if (resultat.isEmpty()) {
            System.out.println("l avion n existe pas");
        } else {
            System.out.println("l avion existe");
        }
        return resultat;
    }

    public List<CompagnieModel> findbyCountry(String Pays) {
        List< CompagnieModel> comp = afficherCompagnie();
        List<CompagnieModel> resultat = comp.stream().filter(a -> Pays.equals(a.getPays())).collect(Collectors.toList());
        if (resultat.isEmpty()) {
            System.out.println("l avion n existe pas");
        } else {
            System.out.println("l avion existe");
        }
        return resultat;
    }

    public List<CompagnieModel> findbynumb(int Np) {
        List< CompagnieModel> comp = afficherCompagnie();
        List<CompagnieModel> resultat = comp.stream().filter(a -> Np == a.getNumber()).collect(Collectors.toList());
        if (resultat.isEmpty()) {
            System.out.println("l avion n existe pas");
        } else {
            System.out.println("l avion existe");
        }
        return resultat;
    }

    public List<CompagnieModel> findbySiege(String Siege) {
        List< CompagnieModel> comp = afficherCompagnie();
        List<CompagnieModel> resultat = comp.stream().filter(a -> Siege.equals(a.getSiege())).collect(Collectors.toList());
        if (resultat.isEmpty()) {
            System.out.println("l avion n existe pas");
        } else {
            System.out.println("l avion existe");
        }
        return resultat;
    }

    public List<CompagnieModel> findbyAB(String AEB) {
        List< CompagnieModel> comp = afficherCompagnie();
        List<CompagnieModel> resultat = comp.stream().filter(a -> AEB.equals(a.getAeBase())).collect(Collectors.toList());
        if (resultat.isEmpty()) {
            System.out.println("l avion n existe pas");
        } else {
            System.out.println("l avion existe");
        }
        return resultat;
    }

    public List<CompagnieModel> findbyPassnum(int Np) {
        List< CompagnieModel> comp = afficherCompagnie();
        List<CompagnieModel> resultat = comp.stream().filter(a -> Np == a.getPassagerNum()).collect(Collectors.toList());
        if (resultat.isEmpty()) {
            System.out.println("l avion n existe pas");
        } else {
            System.out.println("l avion existe");
        }
        return resultat;
    }

    public List<CompagnieModel> findbyDescription(String description) {
        List< CompagnieModel> comp = afficherCompagnie();
        List<CompagnieModel> resultat = comp.stream().filter(a -> description.equals(a.getDescription())).collect(Collectors.toList());
        if (resultat.isEmpty()) {
            System.out.println("l avion n existe pas");
        } else {
            System.out.println("l avion existe");
        }
        return resultat;
    }

    public List<CompagnieModel> sortBySiege() {
        List<CompagnieModel> comp = afficherCompagnie();
        List<CompagnieModel> resultat = comp.stream().sorted(Comparator.comparing(CompagnieModel::getSiege)).collect(Collectors.toList());
        return resultat;
    }

    public List<CompagnieModel> sortByAe() {
        List<CompagnieModel> comp = afficherCompagnie();
        List<CompagnieModel> resultat = comp.stream().sorted(Comparator.comparing(CompagnieModel::getAeBase)).collect(Collectors.toList());
        return resultat;
    }

    public List<CompagnieModel> sortByPassnum() {
        List<CompagnieModel> comp = afficherCompagnie();
        List<CompagnieModel> resultat = comp.stream().sorted(Comparator.comparing(CompagnieModel::getPassagerNum)).collect(Collectors.toList());
        return resultat;
    }

    public List<CompagnieModel> sortByNom() {
        List<CompagnieModel> comp = afficherCompagnie();
        List<CompagnieModel> resultat = comp.stream().sorted(Comparator.comparing(CompagnieModel::getNomCom)).collect(Collectors.toList());
        return resultat;
    }

    public List<CompagnieModel> sortBydescription() {
        List<CompagnieModel> comp = afficherCompagnie();
        List<CompagnieModel> resultat = comp.stream().sorted(Comparator.comparing(CompagnieModel::getDescription)).collect(Collectors.toList());
        return resultat;
    }
}
