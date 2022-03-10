/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Midou
 */
public class MenuController implements Initializable {

    @FXML
    private AnchorPane recpane;
    @FXML
    private Button r;
    @FXML
    private Button a;
    @FXML
    private Label Passagername;

   @FXML
    private void reclamation(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/Reclamation.fxml"));
           recpane.getChildren().setAll(pane);
    }
    @FXML
    private void avis(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/rating.fxml"));
           recpane.getChildren().setAll(pane);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
     
    @FXML
    private void LogOut(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/Authentification.fxml"));
        recpane.getChildren().setAll(pane);   
    }


    @FXML
    private void flights(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/GestionVolsUser.fxml"));
        recpane.getChildren().setAll(pane);
    }

    @FXML
    private void Feedbacks(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/menu.fxml"));
        recpane.getChildren().setAll(pane);
    }

    @FXML
    private void Profile(ActionEvent event) throws IOException {
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLProfilePassager.fxml"));
        recpane.getChildren().setAll(pane);
    }
    
}
