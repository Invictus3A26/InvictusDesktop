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
public class Escale {
    private int id_escale ;
      private int aeroport_depart;
    private int aeroport_destination;
    private String heureArriveEscale;
    private String heureDepartEscale;
    private String Durée;
  
    public Escale() {
    }

    public Escale(int id_escale, int aeroport_depart, int aeroport_destination, String heureArriveEscale, String heureDepartEscale, String Durée) {
        this.id_escale = id_escale;
           this.aeroport_depart = aeroport_depart;
        this.aeroport_destination = aeroport_destination;
        this.heureArriveEscale = heureArriveEscale;
        this.heureDepartEscale = heureDepartEscale;
        this.Durée = Durée;
     
    }
      public Escale( int aeroport_depart, int aeroport_destination, String heureArriveEscale, String heureDepartEscale, String Durée) {
       
           this.aeroport_depart = aeroport_depart;
        this.aeroport_destination = aeroport_destination;
        this.heureArriveEscale = heureArriveEscale;
        this.heureDepartEscale = heureDepartEscale;
        this.Durée = Durée;
     
    }
            public Escale( int aeroport_depart, int aeroport_destination, String heureArriveEscale, String heureDepartEscale, String Durée,int id_escale) {
       
           this.aeroport_depart = aeroport_depart;
        this.aeroport_destination = aeroport_destination;
        this.heureArriveEscale = heureArriveEscale;
        this.heureDepartEscale = heureDepartEscale;
        this.Durée = Durée;
         this.id_escale = id_escale;
     
    }
      
      

    public int getId_escale() {
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

    public int getAeroport_depart() {
        return aeroport_depart;
    }

    public int getAeroport_destination() {
        return aeroport_destination;
    }

    public void setId_escale(int id_escale) {
        this.id_escale = id_escale;
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

    public void setAeroport_depart(int aeroport_depart) {
        this.aeroport_depart = aeroport_depart;
    }

    public void setAeroport_destination(int aeroport_destination) {
        this.aeroport_destination = aeroport_destination;
    }
    

}