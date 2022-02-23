/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Aziz
 */
public class Departement {
    private int id ;
    private String nomDepartement,zoneDepartement,detailDepartement;

    public Departement() {
    }

    public Departement(int id, String nomDepartement, String zoneDepartement, String detailDepartement) {
        this.id = id;
        this.nomDepartement = nomDepartement;
        this.zoneDepartement = zoneDepartement;
        this.detailDepartement = detailDepartement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomDepartement() {
        return nomDepartement;
    }

    public void setNomDepartement(String nomDepartement) {
        this.nomDepartement = nomDepartement;
    }

    public String getZoneDepartement() {
        return zoneDepartement;
    }

    public void setZoneDepartement(String zoneDepartement) {
        this.zoneDepartement = zoneDepartement;
    }

    public String getDetailDepartement() {
        return detailDepartement;
    }

    public void setDetailDepartement(String detailDepartement) {
        this.detailDepartement = detailDepartement;
    }

    @Override
    public String toString() {
        return "Departement{" + "id=" + id + ", nomDepartement=" + nomDepartement + ", zoneDepartement=" + zoneDepartement + ", detailDepartement=" + detailDepartement + '}';
    }
    
}
