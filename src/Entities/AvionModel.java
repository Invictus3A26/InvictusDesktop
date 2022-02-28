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
    private float PassagerN;
    private String CodeC;

    public AvionModel() {
    }

    public AvionModel(String CodeAvion, String TypeA, String Model, float PassagerN, String CodeC) {
        this.CodeAvion = CodeAvion;
        this.TypeA = TypeA;
        this.Model = Model;
        this.PassagerN = PassagerN;
        this.CodeC = CodeC;
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

    public float getPassagerN() {
        return PassagerN;
    }

    public void setPassagerN(float PassagerN) {
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
