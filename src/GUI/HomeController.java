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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Aziz
 */
public class HomeController implements Initializable {

    @FXML
    private AnchorPane Infra;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void AccesDepartement(MouseEvent event) throws IOException {
        /* try {
            System.out.println("test");
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/AfficherdepartementFX.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } */
       /* AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/AfficherdepartementFX.fxml"));
        Infra.getChildren().setAll(pane); */
    }

    @FXML
    private void AccesEquipement(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/AfficherequipementFX.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void Reclamation(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/Reclamation.fxml"));
        Infra.getChildren().setAll(pane);
    }

    @FXML
    private void LogOut(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/Authentification.fxml"));
        Infra.getChildren().setAll(pane);
    }

    @FXML
    private void companies(ActionEvent event) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLcompanies.fxml"));
        Infra.getChildren().setAll(pane);
    }

    @FXML
    private void flights(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLFlights.fxml"));
        Infra.getChildren().setAll(pane);
    }

    @FXML
    private void luggage(ActionEvent event) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/Home.fxml"));
        Infra.getChildren().setAll(pane);
    }

    @FXML
    private void Infra(ActionEvent event) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/Homeaziz.fxml"));
        Infra.getChildren().setAll(pane);
    }

    @FXML
    private void profile(ActionEvent event) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLProfileEmploye.fxml"));
        Infra.getChildren().setAll(pane);
    }

    @FXML
    private void AccesDepartement(ActionEvent event) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/AfficherdepartementFX.fxml"));
        Infra.getChildren().setAll(pane);
    }

}
