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
public class Bagages {
    int Id ;
    String poidsS;
    String poidsM;
    int num_valise;

    public Bagages() {
    }

    public Bagages(int Id, String poidsS, String poidsM, int num_valise) {
        this.Id = Id;
        this.poidsS = poidsS;
        this.poidsM = poidsM;
        this.num_valise = num_valise;
    }

    public Bagages(String poidsS, String poidsM, int num_valise) {
        this.poidsS = poidsS;
        this.poidsM = poidsM;
        this.num_valise = num_valise;
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
        final Bagages other = (Bagages) obj;
        if (this.Id != other.Id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Bagages{" + "Id=" + Id + ", poidsS=" + poidsS + ", poidsM=" + poidsM + ", num_valise=" + num_valise + '}';
    }
    

    
    
  
    
    
}
