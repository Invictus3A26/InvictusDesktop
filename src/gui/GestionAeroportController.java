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
public class GestionAeroportController implements Initializable {


    @FXML
    private TableView<?> AeroportTable;
    @FXML
    private TableColumn<?, ?> nom_aeroport;
    @FXML
    private TableColumn<?, ?> pays_aeroport;
    @FXML
    private TableColumn<?, ?> ville_aeroport;
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
    private void AjouteAeroport(MouseEvent event) {
    }

    @FXML
    private void deleteAeroport(MouseEvent event) {
    }

    @FXML
    private void updateAeroport(MouseEvent event) {
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
