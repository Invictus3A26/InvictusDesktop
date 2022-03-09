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
public class GestionVolsController implements Initializable {

    @FXML
    private TableView<?> volTable;
    @FXML
    private TableColumn<?, ?> num_vol;
    @FXML
    private TableColumn<?, ?> nombre;
    @FXML
    private TableColumn<?, ?> dateDepart;
    @FXML
    private TableColumn<?, ?> dateArrive;
    @FXML
    private TableColumn<?, ?> type;
    @FXML
    private TableColumn<?, ?> retard;
    @FXML
    private TableColumn<?, ?> annulé;
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
    private void AjouteVol(MouseEvent event) {
    }

    @FXML
    private void deleteVol(MouseEvent event) {
    }

    @FXML
    private void updateVol(MouseEvent event) {
    }

    @FXML
    private void chercher(ActionEvent event) {
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
