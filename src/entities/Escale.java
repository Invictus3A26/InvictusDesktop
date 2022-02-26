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
public class Escale {
    private int id_escale ;
    private String heureArriveEscale;
    private String heureDepartEscale;
    private String Durée;

    public Escale() {
    }

    public Escale(int id, String heureArriveEscale, String heureDepartEscale, String Durée) {
        this.id_escale = id;
        this.heureArriveEscale = heureArriveEscale;
        this.heureDepartEscale = heureDepartEscale;
        this.Durée = Durée;
    }

    @Override
    public String toString() {
        return "Escale{" + "id=" + id_escale + ", heureArriveEscale=" + heureArriveEscale + ", heureDepartEscale=" + heureDepartEscale + ", Dur\u00e9e=" + Durée + '}';
    }

    public void setId(int id) {
        this.id_escale = id;
    }

    public void setHeureArriveEscale(String heureArriveEscale) {
        this.heureArriveEscale = heureArriveEscale;
    }

    public void setHeureDepartEscale(String heureDepartEscale) {
        this.heureDepartEscale = heureDepartEscale;
    }

    public void setDurée(String Durée) {
        this.Durée = Durée;
    }

    public int getId() {
        return id_escale;
    }

    public String getHeureArriveEscale() {
        return heureArriveEscale;
    }

    public String getHeureDepartEscale() {
        return heureDepartEscale;
    }

    public String getDurée() {
        return Durée;
    }

   

 
    
}
