/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author wajih
 */
public class GestionEscaleController implements Initializable {

    @FXML
    private TableView<?> EscaleTable;
    @FXML
    private TableColumn<?, ?> aeroport_depart;
    @FXML
    private TableColumn<?, ?> aeroport_destination;
    @FXML
    private TableColumn<?, ?> heure_arrivé;
    @FXML
    private TableColumn<?, ?> heure_depart;
    @FXML
    private TableColumn<?, ?> durée;
    @FXML
    private TextField rechercheTxt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouteEscale(MouseEvent event) {
    }

    @FXML
    private void deleteEscale(MouseEvent event) {
    }

    @FXML
    private void updateEscale(MouseEvent event) {
    }

    @FXML
    private void chercher(MouseEvent event) {
    }

    @FXML
    private void refreshTable(MouseEvent event) {
    }

    @FXML
    private void Reclamation(ActionEvent event) {
    }

    @FXML
    private void LogOut(ActionEvent event) {
    }
    
}
