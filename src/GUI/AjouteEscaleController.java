/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Escale;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Notifications;
import services.EscaleService;
import tools.MaConnexion;

/**
 * FXML Controller class
 *
 * @author islem
 */
public class AjouteEscaleController implements Initializable {

    @FXML
    private ComboBox<String> aeroport_depart;
    @FXML
    private ComboBox<String> aeroport_retour;
    @FXML
    private TextField heure_arrivé;
    @FXML
    private TextField heure_depart;
    @FXML
    private TextField durée;
    
    private int id_escale;
    ObservableList options = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         comboAeroport();
         aeroport_depart.getItems().addAll(options);
            aeroport_retour.getItems().addAll(options);
           
    }    

    @FXML
    private void ajouterEscale(MouseEvent event) {
         int aeroportDepart = Integer.parseInt( aeroport_depart.getValue());
         int aeroportRetour = Integer.parseInt( aeroport_retour.getValue());
         String heureArrivé = heure_arrivé.getText();
         String heureDepart = heure_depart.getText();
         String Durée = durée.getText();
         
         Escale es =new Escale(aeroportDepart,aeroportRetour,heureArrivé,heureDepart,Durée);
         EscaleService eS = new EscaleService();
         eS.ajouterEscale(es);
        
         
         
    }
    
     public void comboAeroport(){
          try {
           Connection con =MaConnexion.getInstance().getCnx();
           ResultSet rs = con.createStatement().executeQuery("SELECT id_aeroport FROM `airport`");
            
           
            
            while (rs.next()){
                options.add(
                      
                        rs.getString("id_aeroport")
                    
                        
                        
                       
                      
                        
                    
                        
                );
               
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(GestionVolsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
     public void setTextField(int Id_escale,String aeroportDepart, String aeroportDestination, String heureArrivé, String heureDepart, String Durée) {
     id_escale=Id_escale;
     aeroport_depart.setValue(aeroportDepart);
     aeroport_retour.setValue(aeroportDestination);
     heure_depart.setText(heureDepart);
     heure_arrivé.setText(heureArrivé);
     durée.setText(Durée);
     
     
     
      
     
        


    }
    
    

    @FXML
    private void ModifierEscale(MouseEvent event) {
        
          int aeroportDepart = Integer.parseInt( aeroport_depart.getValue());
          int aeroportDestination = Integer.parseInt( aeroport_retour.getValue());
         String heureArrivé = heure_arrivé.getText();
         String heureDepart = heure_depart.getText();
         String Durée = durée.getText();
         Escale e = new Escale(aeroportDepart,aeroportDestination,heureArrivé,heureDepart,Durée);
         EscaleService es = new EscaleService();
         es.updateEscale(e, id_escale);
        
    }
    
}
