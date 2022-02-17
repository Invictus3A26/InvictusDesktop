/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.util.Date;


public class Reclamation {
    private int id;
    private int nbr;
    private int tel;
    private String nom;
    private String prenom;
    private String email;
    private String description;
    private String type;
    private String etat;
   private Date date_reclamation; 
    public Reclamation() {
    }

    public Reclamation(int id, String nom, String prenom, String email, int tel, String type, int nbr, String etat, String description, Date date_reclamation) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email=email;
        this.tel = tel;
        this.type = type;
        this.nbr = nbr;
        this.etat = etat;
        this.description = description;
        this.date_reclamation = date_reclamation;
    }

    
      public Reclamation(String nom, String prenom, String email, int tel, String type, int nbr, String etat, String description,Date date_reclamation) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email; 
        this.tel = tel;
        this.type = type;
        this.nbr = nbr;
        this.etat = etat;
        this.description = description;
        this.date_reclamation = date_reclamation;
    }
   
 

    public int getId() {
        return id;
    }
    
     public String getNom() {
        return nom;
    }
     
      public String getPrenom() {
        return prenom;
    }
      
       public String getEmail() {
        return email;
    }
       
       public int getTel() {
        return tel;
    }
       
        public String getType() {
        return type;
    }
    
      public int getNbr() {
        return nbr;
    }
      
       public String getEtat() {
        return etat;
    }
       
        public String getDescription() {
        return description;
    }
        
         public Date getDate_reclamation() {
        return date_reclamation;
    }
    

   

  

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
      public void setTel(int tel) {
        this.tel = tel;
    }
      
       public void setType(String type) {
        this.type = type;
    }
       
     public void setNbr(int nbr) {
        this.nbr = nbr;
    }
     
     public void setEtat(String etat) {
        this.etat = etat;
    }
    
     public void setDescription(String description) {
        this.description = description;
    }
     
     public void setDate_reclamation(Date date_reclamation) {
        this.date_reclamation = date_reclamation;
    }
    
    
    @Override
    public String toString() {
        return "reclamation{" + "id=" + id + ", nom=" + nom + ", prenom="+ prenom +", email=" + email +", tel="+ tel +", type="+ type +", nbr="+ nbr +", etat="+ etat +", description="+ description +", date_reclamation="+ date_reclamation+ '}';
    }

}
