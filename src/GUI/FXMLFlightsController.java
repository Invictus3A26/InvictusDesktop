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
public class FXMLFlightsController implements Initializable {

    @FXML
    private AnchorPane flights;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

     @FXML
    private void Reclamation(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/Reclamation.fxml"));
        flights.getChildren().setAll(pane);
    }

    @FXML
    private void LogOut(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/Authentification.fxml"));
        flights.getChildren().setAll(pane);
    }

    @FXML
    private void companies(ActionEvent event) throws IOException {
        
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLcompanies.fxml"));
        flights.getChildren().setAll(pane);
    }

    @FXML
    private void flights(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLFlights.fxml"));
        flights.getChildren().setAll(pane);
    }

    @FXML
    private void luggage(ActionEvent event) throws IOException {
        
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/Home.fxml"));
        flights.getChildren().setAll(pane);
    }

    @FXML
    private void Infra(ActionEvent event) throws IOException {
        
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/Homeaziz.fxml"));
        flights.getChildren().setAll(pane);
    }

    @FXML
    private void profile(ActionEvent event) throws IOException {
        
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLProfileEmploye.fxml"));
        flights.getChildren().setAll(pane);
    }

    @FXML
    private void vols(ActionEvent event) throws IOException {
        
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/GestionVols.fxml"));
        flights.getChildren().setAll(pane);
    }

    @FXML
    private void escale(ActionEvent event) throws IOException {
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/GestionEscale.fxml"));
        flights.getChildren().setAll(pane);
    }

    @FXML
    private void airport(ActionEvent event) throws IOException {
        
        
         
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/GestionAeroport.fxml"));
        flights.getChildren().setAll(pane);
    }

}
