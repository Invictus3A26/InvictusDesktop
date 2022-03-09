/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.util.Date;
import javafx.scene.image.ImageView;


public class Reclamation {
    private int id;
    private long  id_user;
    private int nbr;
    private int tel;
    private String nom;
    private String prenom;
    private String email;
    private String description;
    private String type;
    private String etat;
   private Date date_reclamation; 

    public Reclamation() {
    }

    public Reclamation(int id, long id_user, int nbr, int tel, String nom, String prenom, String email, String description, String type, String etat, Date date_reclamation) {
        this.id = id;
        this.id_user = id_user;
        this.nbr = nbr;
        this.tel = tel;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.description = description;
        this.type = type;
        this.etat = etat;
        this.date_reclamation = date_reclamation;
    }

    public Reclamation(int nbr, int tel, String nom, String prenom, String email, String description, String type, String etat, Date date_reclamation) {
        this.nbr = nbr;
        this.tel = tel;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.description = description;
        this.type = type;
        this.etat = etat;
        this.date_reclamation = date_reclamation;
    }

    public Reclamation(int i, String bagage, String mon_Bagage_est_perdu) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    public int getNbr() {
        return nbr;
    }

    public void setNbr(int nbr) {
        this.nbr = nbr;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Date getDate_reclamation() {
        return date_reclamation;
    }

    public void setDate_reclamation(Date date_reclamation) {
        this.date_reclamation = date_reclamation;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", id_user=" + id_user + ", nbr=" + nbr + ", tel=" + tel + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", description=" + description + ", type=" + type + ", etat=" + etat + ", date_reclamation=" + date_reclamation + '}';
    }
    
   
   
}
