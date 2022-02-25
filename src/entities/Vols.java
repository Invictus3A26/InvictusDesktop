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
public class Vols {
    
    private int id,num_vol,escales ;
    private String date_depart_vol,date_arrivé_vol,heure_depart_vol,heure_arrivé_vol,nom_aeroport,type_avion,type_vol,nomCom;

    public Vols() {
    }

    public Vols(int id, int num_vol, int escales, String date_depart_vol, String date_arrivé_vol, String heure_depart_vol, String heure_arrivé_vol, String nom_aeroport, String type_avion, String type_vol, String nomCom) {
        this.id = id;
        this.num_vol = num_vol;
        this.escales = escales;
        this.date_depart_vol = date_depart_vol;
        this.date_arrivé_vol = date_arrivé_vol;
        this.heure_depart_vol = heure_depart_vol;
        this.heure_arrivé_vol = heure_arrivé_vol;
        this.nom_aeroport = nom_aeroport;
        this.type_avion = type_avion;
        this.type_vol = type_vol;
       
        this.nomCom = nomCom;
    }

    public int getId() {
        return id;
    }

    public int getNum_vol() {
        return num_vol;
    }

    public int getEscale() {
        return escales;
    }

    public String getDate_depart_vol() {
        return date_depart_vol;
    }

    public String getDate_arrivé_vol() {
        return date_arrivé_vol;
    }

    public String getHeure_depart_vol() {
        return heure_depart_vol;
    }

    public String getHeure_arrivé_vol() {
        return heure_arrivé_vol;
    }

    public String getNom_aeroport() {
        return nom_aeroport;
    }

    public String getType_avion() {
        return type_avion;
    }

    public String getType_vol() {
        return type_vol;
    }



    public String getNomCom() {
        return nomCom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNum_vol(int num_vol) {
        this.num_vol = num_vol;
    }

    public void setEscale(int escales) {
        this.escales = escales;
    }

    public void setDate_depart_vol(String date_depart_vol) {
        this.date_depart_vol = date_depart_vol;
    }

    public void setDate_arrivé_vol(String date_arrivé_vol) {
        this.date_arrivé_vol = date_arrivé_vol;
    }

    public void setHeure_depart_vol(String heure_depart_vol) {
        this.heure_depart_vol = heure_depart_vol;
    }

    public void setHeure_arrivé_vol(String heure_arrivé_vol) {
        this.heure_arrivé_vol = heure_arrivé_vol;
    }

    public void setNom_aeroport(String nom_aeroport) {
        this.nom_aeroport = nom_aeroport;
    }

    public void setType_avion(String type_avion) {
        this.type_avion = type_avion;
    }

    public void setType_vol(String type_vol) {
        this.type_vol = type_vol;
    }

  

    public void setNomCom(String nomCom) {
        this.nomCom = nomCom;
    }

    @Override
    public String toString() {
        return "Vols{" + "id=" + id + ", num_vol=" + num_vol + ", escale=" + escales + ", date_depart_vol=" + date_depart_vol + ", date_arriv\u00e9_vol=" + date_arrivé_vol + ", heure_depart_vol=" + heure_depart_vol + ", heure_arriv\u00e9_vol=" + heure_arrivé_vol + ", nom_aeroport=" + nom_aeroport + ", type_avion=" + type_avion + ", type_vol=" + type_vol + ", nomCom=" + nomCom + '}';
    }
    
    
}
