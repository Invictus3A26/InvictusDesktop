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
public class AvionModel {
    private String CodeAvion;
    private String TypeA;
    private String Model;
    private int PassagerN;
    private String CodeC;

    public AvionModel() {
    }

    public AvionModel(String CodeAvion, String TypeA, String Model, int PassagerN, String CodeC) {
        this.CodeAvion = CodeAvion;
        this.TypeA = TypeA;
        this.Model = Model;
        this.PassagerN = PassagerN;
        this.CodeC = CodeC;
    }

    public AvionModel(String TypeA, String Model, int PassagerN) {
        this.TypeA = TypeA;
        this.Model = Model;
        this.PassagerN = PassagerN;
    }

   

    public String getCodeAvion() {
        return CodeAvion;
    }

    public void setCodeAvion(String CodeAvion) {
        this.CodeAvion = CodeAvion;
    }

    public String getTypeA() {
        return TypeA;
    }

    public void setTypeA(String TypeA) {
        this.TypeA = TypeA;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String Model) {
        this.Model = Model;
    }

    public int getPassagerN() {
        return PassagerN;
    }

    public void setPassagerN(int PassagerN) {
        this.PassagerN = PassagerN;
    }

    public String getCodeC() {
        return CodeC;
    }

    public void setCodeC(String CodeC) {
        this.CodeC = CodeC;
    }

    @Override
    public String toString() {
        return "AvionModel{" + "CodeAvion=" + CodeAvion + ", TypeA=" + TypeA + ", Model=" + Model + ", PassagerN=" + PassagerN + ", CodeC=" + CodeC + '}';
    }


  
}
