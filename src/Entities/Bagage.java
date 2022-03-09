/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author dell
 */
public class Bagage {
    int Id ;
    String poidsS;
    String poidsM;
    int num_valise;
    String poids ;
    String dimension ;

    public Bagage() {
    }

    public Bagage(int Id, String poidsS, String poidsM, int num_valise, String poids, String dimension) {
        this.Id = Id;
        this.poidsS = poidsS;
        this.poidsM = poidsM;
        this.num_valise = num_valise;
        this.poids = poids;
        this.dimension = dimension;
    }

    public Bagage(String poidsS, String poidsM, int num_valise, String poids, String dimension) {
        this.poidsS = poidsS;
        this.poidsM = poidsM;
        this.num_valise = num_valise;
        this.poids = poids;
        this.dimension = dimension;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getPoidsS() {
        return poidsS;
    }

    public void setPoidsS(String poidsS) {
        this.poidsS = poidsS;
    }

    public String getPoidsM() {
        return poidsM;
    }

    public void setPoidsM(String poidsM) {
        this.poidsM = poidsM;
    }

    public int getNum_valise() {
        return num_valise;
    }

    public void setNum_valise(int num_valise) {
        this.num_valise = num_valise;
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

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Bagage other = (Bagage) obj;
        if (this.Id != other.Id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Bagage{" + "Id=" + Id + ", poidsS=" + poidsS + ", poidsM=" + poidsM + ", num_valise=" + num_valise + ", poids=" + poids + ", dimension=" + dimension + '}';
    }

    

   

    
    
  
    
    
}
