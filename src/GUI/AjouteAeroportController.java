/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Aeroport;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Notifications;
import Services.AeroportService;

/**
 * FXML Controller class
 *
 * @author islem
 */
public class AjouteAeroportController implements Initializable {

    @FXML
    private TextField nom_aeroport;
    @FXML
    private ChoiceBox<String> pays;
    @FXML
    private TextField ville;
    
     private int id_aeroport;
     private String[] payss ={"Tunisia","Algeria","Morraco","France","Germany","Italy","Spain","USA","Brazil","Argentina","KSA","England"};

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pays.getItems().addAll(payss);
    }    

    @FXML
    private void ajouterAeroport(MouseEvent event) {
          String nomAeroport = nom_aeroport.getText();
         String villeAeroport = ville.getText();
         String Pays = pays.getValue();
         
         Aeroport A =new Aeroport(nomAeroport,villeAeroport,Pays);
         AeroportService as = new AeroportService();
         as.ajouterAeroport(A);

    }

    @FXML
    private void ModifierAeroport(MouseEvent event) {
        
                 String Nom = nom_aeroport.getText();
         String Ville = ville.getText();
         String Pays = pays.getValue();
         Aeroport e = new Aeroport(Nom,Ville,Pays);
         AeroportService es = new AeroportService();
         es.updateAeroport(e, id_aeroport);
          
        
    }
    
    
    
    
     public void setTextField(int Id_aeroport,String nom, String Ville, String Pays) {
     id_aeroport=Id_aeroport;
     nom_aeroport.setText(nom);
     ville.setText(Ville);
     pays.setValue(Pays);}
    
}
