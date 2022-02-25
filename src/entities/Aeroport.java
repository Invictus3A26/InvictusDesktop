/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author islem
 */
public class Aeroport {
    private int id;
    private String nom_aeroport ;
    private String ville_aeroport;

    public Aeroport() {
    }

    public Aeroport(int id, String nom_aeroport, String ville_aeroport) {
        this.id = id;
        this.nom_aeroport = nom_aeroport;
        this.ville_aeroport = ville_aeroport;
    }

    public int getId() {
        return id;
    }

    public String getNom_aeroport() {
        return nom_aeroport;
    }

    public String getVille_aeroport() {
        return ville_aeroport;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom_aeroport(String nom_aeroport) {
        this.nom_aeroport = nom_aeroport;
    }

    public void setVille_aeroport(String ville_aeroport) {
        this.ville_aeroport = ville_aeroport;
    }

    @Override
    public String toString() {
        return "aeroport{" + "id=" + id + ", nom_aeroport=" + nom_aeroport + ", ville_aeroport=" + ville_aeroport + '}';
    }
    
    
    
}
