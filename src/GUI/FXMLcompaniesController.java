/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author wajih
 */
public class FXMLcompaniesController implements Initializable {

    @FXML
    private AnchorPane comapnies;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void companies(ActionEvent event) throws IOException {
        
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLcompanies.fxml"));
        comapnies.getChildren().setAll(pane);
    }

    @FXML
    private void flights(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLFlights.fxml"));
        comapnies.getChildren().setAll(pane);

    }

    @FXML
    private void luggage(ActionEvent event) throws IOException {
         
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/Home.fxml"));
        comapnies.getChildren().setAll(pane);
    }

    @FXML
    private void Reclamation(ActionEvent event) throws IOException {
        
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/Reclamation.fxml"));
        comapnies.getChildren().setAll(pane);
    }

    @FXML
    private void Infra(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/Homeaziz.fxml"));
        comapnies.getChildren().setAll(pane);
    }

    @FXML
    private void LogOut(ActionEvent event) throws IOException {
        
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/Authentification.fxml"));
        comapnies.getChildren().setAll(pane);
    }

    @FXML
    private void profile(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLProfileEmploye.fxml"));
        comapnies.getChildren().setAll(pane);
    }

    @FXML
    private void to_avion(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/Avion.fxml"));
        comapnies.getChildren().setAll(pane);
    }

    @FXML
    private void to_comapgnie(ActionEvent event) throws IOException {
        
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/Compagnie.fxml"));
        comapnies.getChildren().setAll(pane);
    }
    

    @FXML
    private void to_avion_compagnie(ActionEvent event) throws IOException {
        
          AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/AvionCompagnie.fxml"));
        comapnies.getChildren().setAll(pane);
        
    }
    
}
