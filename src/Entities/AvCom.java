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
public class AvCom {
    private int id;
    private String CodeCom;
    private String NomC;
    private int numbAvC;

    public AvCom() {
    }

    public AvCom(int id, String CodeCom, String CodeAv, String NomC, int numbAvC) {
        this.id = id;
        this.CodeCom = CodeCom;
        this.NomC = NomC;
        this.numbAvC = numbAvC;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodeCom() {
        return CodeCom;
    }

    public void setCodeCom(String CodeCom) {
        this.CodeCom = CodeCom;
    }


    public String getNomC() {
        return NomC;
    }

    public void setNomC(String NomC) {
        this.NomC = NomC;
    }

    public int getNumbAvC() {
        return numbAvC;
    }

    public void setNumbAvC(int numbAvC) {
        this.numbAvC = numbAvC;
    }

    @Override
    public String toString() {
        return "AvCom{" + "id=" + id + ", CodeCom=" + CodeCom + ", NomC=" + NomC + ", numbAvC=" + numbAvC + '}';
    }

   }
