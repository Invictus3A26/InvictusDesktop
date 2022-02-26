/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionutilisateur1.entity;

/**
 *
 * @author 
 */
public class Article {
    
    private long idAticle;
    private String titre;
    private String contenu;
    private String description;
    private String imageURL ;
    private int nbrLike ;

    public Article() {
    }

    public Article(String titre, String contenu, String description, String imageURL, int nbrLike) {
        this.titre = titre;
        this.contenu = contenu;
        this.description = description;
        this.imageURL = imageURL;
        this.nbrLike = nbrLike;
    }

    public Article(long idAticle, String titre, String contenu, String description, String imageURL, int nbrLike) {
        this.idAticle = idAticle;
        this.titre = titre;
        this.contenu = contenu;
        this.description = description;
        this.imageURL = imageURL;
        this.nbrLike = nbrLike;
    }

    public long getIdAticle() {
        return idAticle;
    }

    public void setIdAticle(long idAticle) {
        this.idAticle = idAticle;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getNbrLike() {
        return nbrLike;
    }

    public void setNbrLike(int nbrLike) {
        this.nbrLike = nbrLike;
    }

    @Override
    public String toString() {
        return "Article{" + "idAticle=" + idAticle + ", titre=" + titre + ", contenu=" + contenu + ", description=" + description + ", imageURL=" + imageURL + ", nbrLike=" + nbrLike + '}'+"\n";
    }
    
    
}
