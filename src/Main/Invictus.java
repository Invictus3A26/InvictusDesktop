/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import entities.Aeroport;
import entities.Escale;
import entities.Vols;

import services.AeroportService;
import services.EscaleService;
import services.VolsService;
import tools.MaConnexion;

/**
 *
 * @author islem
 */
public class Invictus {
    
    
    
  

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MaConnexion mc = MaConnexion.getInstance();
      //  Aeroport a1 = new Aeroport(1,"tunis carthage","tunis");
      //  Aeroport a2 = new Aeroport(2,"paris airport","Paris");
        Escale e1 = new Escale(1,1,1,"11:30","12:30","60");
        Escale e2 = new Escale(1,1,1,"9;30","10:00","30");
        Vols v1 = new Vols(1,155,1,"12/03/2021","13/03/2021","23:00","00:30",1,"rfrffr","depart","hyyh",122,"0",0);
        Vols v2 = new Vols(3,155,1,"12/03/2021","13/03/2021","23:00","00:30",1,"rfrffr","depart","hyyh",140,"0",1);
        AeroportService ser = new AeroportService();
        EscaleService escal = new EscaleService();
        VolsService vo = new VolsService();
      //ser.ajouterAeroport(a2);
      // ser.ajouterAeroport(a1);
      // ser.deleteAeroport(2);
      //ser.updateAeroport(a1, 1);
      //escal.ajouterEscale(e1);
      //System.out.println(escal.afficherEscale());
      //escal.afficherEscale();
     // escal.deleteEscale(1);
      // escal.updateEscale(e2, 2);
    //vo.ajouterVols(v1);
   // vo.getVolById(18);
    // System.out.println(vo.rechercheAvecNumVol(155));
     // vo.deleteVols(3);
      //vo.updateVols(v2, 1);
      //vo.rechercheAvecNumVol(154);
      //vo.afficherVols();
       // System.out.println(vo.triNum_vol());  
       // System.out.println(ser.getAeroportById(6)); 
    }
    
}
