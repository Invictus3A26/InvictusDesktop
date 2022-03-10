/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Anis
 */
public class TestController implements Initializable {

    @FXML
    private AnchorPane company;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
        @FXML
    private void goToComL(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CompListe.fxml"));

        Parent root = loader.load();
        int width = (int) Screen.getPrimary().getBounds().getWidth();
        int height = (int) Screen.getPrimary().getBounds().getHeight();

        Scene scene = new Scene(root, width, height);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setMaximized(true);
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.setScene(scene);
        stage.show();
    }
             @FXML
    private void goToLAv(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AvionListe.fxml"));

        Parent root = loader.load();
        int width = (int) Screen.getPrimary().getBounds().getWidth();
        int height = (int) Screen.getPrimary().getBounds().getHeight();

        Scene scene = new Scene(root, width, height);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setMaximized(true);
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.setScene(scene);
        stage.show();
    }
                 @FXML
    private void goDash(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DashboardCompagnie.fxml"));

        Parent root = loader.load();
        int width = (int) Screen.getPrimary().getBounds().getWidth();
        int height = (int) Screen.getPrimary().getBounds().getHeight();

        Scene scene = new Scene(root, width, height);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setMaximized(true);
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void LogOut(ActionEvent event) {

        try {
            Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stageclose.close();
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/Authentification.fxml"));
            Stage stage = new Stage();

            Scene scene = new Scene(root);

            stage.setTitle("Articles");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AuthentificationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Reclamation(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/Reclamation.fxml"));
        company.getChildren().setAll(pane);
    }

    @FXML
    private void companies(ActionEvent event) throws IOException {
        
        System.out.println("test");
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/Test.fxml"));
        company.getChildren().setAll(pane);

    }

    @FXML
    private void flights(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLFlights.fxml"));
        company.getChildren().setAll(pane);

    }

    @FXML
    private void luggage(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/Home.fxml"));
        company.getChildren().setAll(pane);
    }

    @FXML
    private void Infra(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/Homeaziz.fxml"));
        company.getChildren().setAll(pane);
    }

    @FXML
    private void profile(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLProfileEmploye.fxml"));
        company.getChildren().setAll(pane);
    }
}
////////////////////////////////////////////////////////////

