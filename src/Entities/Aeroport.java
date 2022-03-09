/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author islem
 */
public class Aeroport {
    private int id_aeroport;
    private String nom_aeroport ;
    private String ville_aeroport;
    private String pays;

    public Aeroport() {
    }

    public Aeroport(int id_aeroport, String nom_aeroport, String ville_aeroport, String pays) {
        this.id_aeroport = id_aeroport;
        this.nom_aeroport = nom_aeroport;
        this.ville_aeroport = ville_aeroport;
        this.pays = pays;
    }

    public Aeroport(String nom_aeroport, String ville_aeroport, String pays) {
        this.nom_aeroport = nom_aeroport;
        this.ville_aeroport = ville_aeroport;
        this.pays = pays;
    }

    public int getId_aeroport() {
        return id_aeroport;
    }

    public String getNom_aeroport() {
        return nom_aeroport;
    }

    public String getVille_aeroport() {
        return ville_aeroport;
    }

    public String getPays() {
        return pays;
    }

    public void setId_aeroport(int id_aeroport) {
        this.id_aeroport = id_aeroport;
    }

    public void setNom_aeroport(String nom_aeroport) {
        this.nom_aeroport = nom_aeroport;
    }

    public void setVille_aeroport(String ville_aeroport) {
        this.ville_aeroport = ville_aeroport;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    @Override
    public String toString() {
        return "Aeroport{" + "id_aeroport=" + id_aeroport + ", nom_aeroport=" + nom_aeroport + ", ville_aeroport=" + ville_aeroport + ", pays=" + pays + '}';
    }
    

}