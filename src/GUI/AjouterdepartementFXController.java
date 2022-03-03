/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Departement;
import Services.DepartementService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Aziz
 */
public class AjouterdepartementFXController implements Initializable {

    @FXML
    private TextField tfnomDepartement;
    @FXML
    private TextField tfzoneDepartement;
    @FXML
    private TextField tfdetailDepartement;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void AddDepartement(MouseEvent event) {
        String nomDepartement = tfnomDepartement.getText();
        String zoneDepartement = tfzoneDepartement.getText();
        String detailDepartement = tfdetailDepartement.getText();

        Departement d = new Departement(nomDepartement, zoneDepartement, detailDepartement);
        DepartementService ds = new DepartementService();
        //Alert alert = new Alert(Alert.AlertType.INFORMATION);

        //try {
        ds.ajouterDepartement(d);
        //alert.setTitle("Success");
        //alert.setHeaderText("Added");
        //alert.setContentText("Departement added successfully !");

        //alert.setOnCloseRequest((evn) -> {
        //try {
        //URL fxURL = getClass().getResource("AfficherdepartementFX.fxml");
        //FXMLLoader loader = new FXMLLoader(fxURL);
        //Parent root = loader.load();
        //AfficherdepartementFXController sfc = loader.getController();
        //sfc.setNomDepartement("nomDepartement : " + tfnomDepartement.getText());
        //sfc.setZoneDepartement("zoneDepartement : " + tfzoneDepartement.getText());
        //sfc.setDetailDepartement("detailDepartement : " + tfdetailDepartement.getText());
        //tfnomDepartement.getScene().setRoot(root);
        //} catch (IOException ex) {
        //System.out.println(ex.getMessage());
        //}
        //});
        //} catch (SQLException ex) {
        //alert.setAlertType(Alert.AlertType.ERROR);
        //alert.setTitle("Error");
        //alert.setHeaderText("Adding error");
        //alert.setContentText(ex.getMessage());
        //} finally {
        //alert.showAndWait();
        //}
    }

}
