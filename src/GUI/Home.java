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
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class Home implements Initializable {

    @FXML
    private Button xbtn;
    @FXML
    private Button add;
    @FXML
    private Button dised;
    @FXML
    private Button mi;
    @FXML
    private AnchorPane bagages;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void AddCom(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GestBagage.fxml"));
        Parent root = loader.load();
        add.getScene().setRoot(root);
    }

    @FXML
    private void DisplayEditCom(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FraisBagage.fxml"));
        Parent root = loader.load();
        dised.getScene().setRoot(root);
    }

    @FXML
    private void Exit(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
        JOptionPane.showMessageDialog(null, "Are you sure ? :(");
    }
    
    @FXML
    private void mpb(ActionEvent event) throws IOException {
        System.out.println("test");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MailRec.fxml"));
        Parent root = loader.load();
        dised.getScene().setRoot(root);
    }

    @FXML
    private void Reclamation(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/Reclamation.fxml"));
        bagages.getChildren().setAll(pane);
    }

    @FXML
    private void LogOut(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/Authentification.fxml"));
        bagages.getChildren().setAll(pane);
    }

    @FXML
    private void companies(ActionEvent event) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/Test.fxml"));
        bagages.getChildren().setAll(pane);
    }

    @FXML
    private void flights(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLFlights.fxml"));
        bagages.getChildren().setAll(pane);
    }

    @FXML
    private void luggage(ActionEvent event) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/Home.fxml"));
        bagages.getChildren().setAll(pane);
    }

    @FXML
    private void Infra(ActionEvent event) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/Homeaziz.fxml"));
        bagages.getChildren().setAll(pane);
    }

    @FXML
    private void profile(ActionEvent event) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLProfileEmploye.fxml"));
        bagages.getChildren().setAll(pane);
    }
}
