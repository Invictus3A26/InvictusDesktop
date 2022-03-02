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
         Alert alert = new Alert(AlertType.INFORMATION);
      
         sv.ajouterVols(v);
         alert.setTitle("Success");
         alert.setHeaderText("Added");
         alert.setContentText("Vol added successfully !");
        
         
        
        
         
         
         
    }
    
}
