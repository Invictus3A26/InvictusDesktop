/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Aziz
 */
public class Equipement {
    private int id;
    private String typeEquipement,nomEquipement,detailEquipement,zoneEquipement,etatEquipement;
    private int id_departement ;

    public Equipement() {
    }

    public Equipement(int id, String typeEquipement, String nomEquipement, String detailEquipement, String zoneEquipement, String etatEquipement, int id_departement ) {
        this.id = id;
        this.typeEquipement = typeEquipement;
        this.nomEquipement = nomEquipement;
        this.detailEquipement = detailEquipement;
        this.zoneEquipement = zoneEquipement;
        this.etatEquipement = etatEquipement;
        this.id_departement = id_departement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeEquipement() {
        return typeEquipement;
    }

    public void setTypeEquipement(String typeEquipement) {
        this.typeEquipement = typeEquipement;
    }

    public String getNomEquipement() {
        return nomEquipement;
    }

    public void setNomEquipement(String nomEquipement) {
        this.nomEquipement = nomEquipement;
    }

    public String getDetailEquipement() {
        return detailEquipement;
    }

    public void setDetailEquipement(String detailEquipement) {
        this.detailEquipement = detailEquipement;
    }

    public String getZoneEquipement() {
        return zoneEquipement;
    }

    public void setZoneEquipement(String zoneEquipement) {
        this.zoneEquipement = zoneEquipement;
    }

    public String getEtatEquipement() {
        return etatEquipement;
    }

    public void setEtatEquipement(String etatEquipement) {
        this.etatEquipement = etatEquipement;
    }

    public int getId_departement() {
        return id_departement;
    }

    public void setId_departement(int id_departement) {
        this.id_departement = id_departement;
    }

    @Override
    public String toString() {
        return "Equipement{" + "id=" + id + ", typeEquipement=" + typeEquipement + ", nomEquipement=" + nomEquipement + ", detailEquipement=" + detailEquipement + ", zoneEquipement=" + zoneEquipement + ", etatEquipement=" + etatEquipement + ", id_departement=" + id_departement + '}';
    }

    

    
    
}
