/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;



/**
 *
 * @author Anis
 */
public class CompagnieModel {
    private String Code_IATA;
    private String NomCom;
    private String Link;
    private String Pays;
    private int  Number;
    private String Siege;
    private String AeBase ;
    private float PassagerNum ;
    private String Description ;
    private int id_aeroport;
    public CompagnieModel(){};

    public CompagnieModel(String Code_IATA, String NomCom, String Link, String Pays, int Number, String Siege, String AeBase, float PassagerNum, String Description, int id_aeroport) {
        this.Code_IATA = Code_IATA;
        this.NomCom = NomCom;
        this.Link = Link;
        this.Pays = Pays;
        this.Number = Number;
        this.Siege = Siege;
        this.AeBase = AeBase;
        this.PassagerNum = PassagerNum;
        this.Description = Description;
        this.id_aeroport = id_aeroport;
    }

    public String getCode_IATA() {
        return Code_IATA;
    }

    public void setCode_IATA(String Code_IATA) {
        this.Code_IATA = Code_IATA;
    }

    public String getNomCom() {
        return NomCom;
    }

    public void setNomCom(String NomCom) {
        this.NomCom = NomCom;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String Link) {
        this.Link = Link;
    }

    public String getPays() {
        return Pays;
    }

    public void setPays(String Pays) {
        this.Pays = Pays;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int Number) {
        this.Number = Number;
    }

    public String getSiege() {
        return Siege;
    }

    public void setSiege(String Siege) {
        this.Siege = Siege;
    }

    public String getAeBase() {
        return AeBase;
    }

    public void setAeBase(String AeBase) {
        this.AeBase = AeBase;
    }

    public float getPassagerNum() {
        return PassagerNum;
    }

    public void setPassagerNum(float PassagerNum) {
        this.PassagerNum = PassagerNum;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public int getId_aeroport() {
        return id_aeroport;
    }

    public void setId_aeroport(int id_aeroport) {
        this.id_aeroport = id_aeroport;
    }

    @Override
    public String toString() {
        return "CompagnieModel{" + "Code_IATA=" + Code_IATA + ", NomCom=" + NomCom + ", Link=" + Link + ", Pays=" + Pays + ", Number=" + Number + ", Siege=" + Siege + ", AeBase=" + AeBase + ", PassagerNum=" + PassagerNum + ", Description=" + Description + ", id_aeroport=" + id_aeroport + '}';
    }

   

}