/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Vols;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import services.VolsService;

/**
 * FXML Controller class
 *
 * @author islem
 */
public class AjouteVolsController implements Initializable {

    @FXML
    private TextField num_vol;
    @FXML
    private TextField date_depart_vol;
    @FXML
    private TextField date_arrivé_vol;
    @FXML
    private TextField heure_depart_vol;
    @FXML
    private TextField heure_arrivé_vol;
    @FXML
    private TextField id_aeroport;
    @FXML
    private TextField type_avion;
    @FXML
    private TextField type_vol;
    @FXML
    private TextField escale;
    @FXML
    private TextField compagnie;
    @FXML
    private TextField nombre_passager;
    @FXML
    private TextField durée_retard;
    @FXML
    private TextField annulé;
    int id_vol;
     

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterVol(MouseEvent event) {
         int numVol = Integer.parseInt( num_vol.getText());
         String dateDepartVol = date_depart_vol.getText();
         String dateArrivéVol = date_arrivé_vol.getText();
         String heureArrivéVol = heure_arrivé_vol.getText();
         String heureDepartVol = heure_depart_vol.getText();
         int idAeroport = Integer.parseInt(id_aeroport.getText());
         String typeAvion = type_avion.getText();
         String typeVol = type_vol.getText();
         int idEscale = Integer.parseInt(escale.getText());
         String Compagnie = compagnie.getText();
         int nombrePass = Integer.parseInt(nombre_passager.getText());
         String duréRetard = durée_retard.getText();
         int Annulé = Integer.parseInt(annulé.getText());
         
         Vols v = new Vols(numVol, idAeroport, nombrePass, Annulé, idEscale, dateDepartVol, dateArrivéVol, heureDepartVol, heureArrivéVol, typeVol, duréRetard, typeAvion, Compagnie);
        VolsService sv = new VolsService();
       
      
         sv.ajouterVols(v);
       
        
         
        
        
         
         
         
    }
    
    public void setTextField(int Id_vol,String Num_vol, String Nombre_passager, String Date_depart_vol, String Date_arrivé_vol, String Type_vol,String duré_arrivé_vol,String annulation) {
     id_vol=Id_vol;
     num_vol.setText(Num_vol);
     nombre_passager.setText(Nombre_passager);
     date_depart_vol.setText(Date_depart_vol);
     date_arrivé_vol.setText(Date_arrivé_vol);
     type_vol.setText(Type_vol);
     durée_retard.setText(duré_arrivé_vol);
     annulé.setText(annulation);
     
     
        


    }

    @FXML
    private void ModifierVol(MouseEvent event) {
         int numVol = Integer.parseInt( num_vol.getText());
         String dateDepartVol = date_depart_vol.getText();
         String dateArrivéVol = date_arrivé_vol.getText();
         String heureArrivéVol = heure_arrivé_vol.getText();
         String heureDepartVol = heure_depart_vol.getText();
         int idAeroport = Integer.parseInt(id_aeroport.getText());
         String typeAvion = type_avion.getText();
         String typeVol = type_vol.getText();
         int idEscale = Integer.parseInt(escale.getText());
         String Compagnie = compagnie.getText();
         int nombrePass = Integer.parseInt(nombre_passager.getText());
         String duréRetard = durée_retard.getText();
         int Annulé = Integer.parseInt(annulé.getText());
         Vols v = new Vols(numVol, idAeroport, nombrePass, Annulé, idEscale, dateDepartVol, dateArrivéVol, heureDepartVol, heureArrivéVol, typeVol, duréRetard, typeAvion, Compagnie);
        VolsService sv = new VolsService();
        VolsService vs = new VolsService();
        vs.updateVols(v, id_vol);
        
        
    }
 
}
