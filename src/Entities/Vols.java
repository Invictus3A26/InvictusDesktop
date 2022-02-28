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
    
    private int id_vol,num_vol,id_escale,id_aeroport,nombrePassager_vol,annulation_vol;
    private String date_depart_vol,date_arrivé_vol,heure_depart_vol,heure_arrivé_vol,type_vol,durée_retard_vol , type_avion,id_comp ;
   



  

    public Vols() {
    }

    public Vols(int id, int num_vol, int id_escale, String date_depart_vol, String date_arrivé_vol, String heure_depart_vol, String heure_arrivé_vol, int nom_aeroport, String type_avion, String type_vol, String id_comp, int nombrePassager_vol,String durée_retard_vol, int annulation_vol ){
        this.id_vol = id;
        this.num_vol = num_vol;
        this.id_escale = id_escale;
        this.date_depart_vol = date_depart_vol;
        this.date_arrivé_vol = date_arrivé_vol;
        this.heure_depart_vol = heure_depart_vol;
        this.heure_arrivé_vol = heure_arrivé_vol;
        this.id_aeroport = nom_aeroport;
        this.type_avion = type_avion;
        this.type_vol = type_vol;
        this.id_comp = id_comp;
        this.nombrePassager_vol=nombrePassager_vol;
        this.durée_retard_vol=durée_retard_vol;
        this.annulation_vol=annulation_vol;
        
        
    }

    public int getId() {
        return id_vol;
    }

    public int getNum_vol() {
        return num_vol;
    }

    public int getEscale() {
        return id_escale;
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

    public int getNom_aeroport() {
        return id_aeroport;
    }

    public String getType_avion() {
        return type_avion;
    }

    public String getType_vol() {
        return type_vol;
    }



    public String getNomCom() {
        return id_comp;
    }
    
        public int getNombrePassager_vol() {
        return nombrePassager_vol;
    }

    public String getDurée_retard_vol() {
        return durée_retard_vol;
    }

    public int isAnnulation_vol() {
        return annulation_vol;
    }

    public void setId(int id) {
        this.id_vol = id;
    }

    public void setNum_vol(int num_vol) {
        this.num_vol = num_vol;
    }

    public void setEscale(int escales) {
        this.id_escale = escales;
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

    public void setNom_aeroport(int nom_aeroport) {
        this.id_aeroport = nom_aeroport;
    }

    public void setType_avion(String type_avion) {
        this.type_avion = type_avion;
    }

    public void setNombrePassager_vol(int nombrePassager_vol) {
        this.nombrePassager_vol = nombrePassager_vol;
    }

    public void setDurée_retard_vol(String durée_retard_vol) {
        this.durée_retard_vol = durée_retard_vol;
    }

    public void setAnnulation_vol(int annulation_vol) {
        this.annulation_vol = annulation_vol;
    }

    public void setType_vol(String type_vol) {
        this.type_vol = type_vol;
    }

  

    public void setNomCom(String id_comp) {
        this.id_comp = id_comp;
    }

    @Override
    public String toString() {
        return "Vols{" + "id_vol=" + id_vol + ", num_vol=" + num_vol + ", id_escale=" + id_escale + ", id_aeroport=" + id_aeroport + ", nombrePassager_vol=" + nombrePassager_vol + ", annulation_vol=" + annulation_vol + ", date_depart_vol=" + date_depart_vol + ", date_arriv\u00e9_vol=" + date_arrivé_vol + ", heure_depart_vol=" + heure_depart_vol + ", heure_arriv\u00e9_vol=" + heure_arrivé_vol + ", type_vol=" + type_vol + ", dur\u00e9e_retard_vol=" + durée_retard_vol + ", type_avion=" + type_avion + ", id_comp=" + id_comp + '}';
    }

 

  
    
    
}
