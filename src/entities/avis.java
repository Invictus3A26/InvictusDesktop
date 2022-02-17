/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author AMEN
 */
public class avis {
    private int id;
    private int rating;
    private Date datecreation;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(Date datecreation) {
        this.datecreation = datecreation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public avis() {
    }

    public avis(int id, int rating, Date datecreation, String description) {
        this.id = id;
        this.rating = rating;
        this.datecreation = datecreation;
        this.description = description;
    }
    
    

}

