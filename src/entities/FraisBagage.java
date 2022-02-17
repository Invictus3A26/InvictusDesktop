/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author dell
 */
public class FraisBagage {
    private int id ;
    private String poids;
    private String dimension ;
    private int tarifs_base;
    private int tarifs_confort;
    private int montant;

    public FraisBagage() {
    }

    public FraisBagage(int id, String poids, String dimension, int tarifs_base, int tarifs_confort, int montant) {
        this.id = id;
        this.poids = poids;
        this.dimension = dimension;
        this.tarifs_base = tarifs_base;
        this.tarifs_confort = tarifs_confort;
        this.montant = montant;
    }

    public FraisBagage(String poids, String dimension, int tarifs_base, int tarifs_confort, int montant) {
        this.poids = poids;
        this.dimension = dimension;
        this.tarifs_base = tarifs_base;
        this.tarifs_confort = tarifs_confort;
        this.montant = montant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPoids() {
        return poids;
    }

    public void setPoids(String poids) {
        this.poids = poids;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String demension) {
        this.dimension = demension;
    }

    public int getTarifs_base() {
        return tarifs_base;
    }

    public void setTarifs_base(int tarifs_base) {
        this.tarifs_base = tarifs_base;
    }

    public int getTarifs_confort() {
        return tarifs_confort;
    }

    public void setTarifs_confort(int tarifs_confort) {
        this.tarifs_confort = tarifs_confort;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FraisBagage other = (FraisBagage) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FraisBagage{" + "id=" + id + ", poids=" + poids + ", dimension=" + dimension + ", tarifs_base=" + tarifs_base + ", tarifs_confort=" + tarifs_confort + ", montant=" + montant + '}';
    }
            
            
}
