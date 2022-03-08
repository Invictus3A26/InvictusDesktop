/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Equipement;
import Services.EquipementService;
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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Aziz
 */
public class AjouterequipementFXController implements Initializable {

    @FXML
    private TextField tftypeEquipement;
    @FXML
    private TextField tfnomEquipement;
    @FXML
    private TextField tfdetailEquipement;
    @FXML
    private TextField tfzoneEquipement;
    @FXML
    private TextField tfetatEquipement;
    @FXML
    private TextField tfid_departement;
    int id_e;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void Addequipement(ActionEvent event) {
        String typeEquipement = tftypeEquipement.getText();
        String nomEquipement = tfnomEquipement.getText();
        String detailEquipement = tfdetailEquipement.getText();
        String zoneEquipement = tfzoneEquipement.getText();
        String etatEquipement = tfetatEquipement.getText();
        int id_departement = Integer.parseInt(tfid_departement.getText());

        Equipement p = new Equipement(typeEquipement, nomEquipement, detailEquipement, zoneEquipement, etatEquipement, id_departement);
        EquipementService ps = new EquipementService();
        //Alert alert = new Alert(Alert.AlertType.INFORMATION);

        //try {
            ps.ajouterEquipement(p);
            //alert.setTitle("Success");
            //alert.setHeaderText("Added");
            //alert.setContentText("Equipement added successfully !");

            //alert.setOnCloseRequest((evn) -> {
                //try {
                    //URL fxURL = getClass().getResource("AfficherequipementFX.fxml");
                    //FXMLLoader loader = new FXMLLoader(fxURL);
                    //Parent root = loader.load();
                    //AfficherequipementFXController sfc = loader.getController();
                    //sfc.setTypeEquipement("typeEquipement : " + tftypeEquipement.getText());
                    //sfc.setNomEquipement("nomEquipement : " + tfnomEquipement.getText());
                    //sfc.setDetailEquipement("detailEquipement : " + tfdetailEquipement.getText());
                    //sfc.setZoneEquipement("zoneEquipement : " + tfzoneEquipement.getText());
                    //sfc.setEtatEquipement("etatEquipement : " + tfetatEquipement.getText());
                    //sfc.setId_departement("tfid_departement : " + tfid_departement.getText());
                    //tftypeEquipement.getScene().setRoot(root);
                    //tfnomEquipement.getScene().setRoot(root);
                    //tfdetailEquipement.getScene().setRoot(root);
                    //tfzoneEquipement.getScene().setRoot(root);
                    //tfetatEquipement.getScene().setRoot(root);
                    //tfid_departement.getScene().setRoot(root);
                //} catch (IOException ex) {
                    //System.out.println(ex.getMessage());
                //}
            //});

        //} catch (SQLException ex) {
            //alert.setAlertType(Alert.AlertType.ERROR);
            //lert.setTitle("Error");
            //alert.setHeaderText("Adding error");
            //alert.setContentText(ex.getMessage());
        //} finally {
            //alert.showAndWait();
        //}

    }

    public void setTextField(int id, String typeEquipement, String nomEquipement, String detailEquipement,String zoneEquipement,String etatEquipement, String id_departement) {
        id_e = id;
        tftypeEquipement.setText(typeEquipement);
        tfnomEquipement.setText(nomEquipement);
        tfdetailEquipement.setText(detailEquipement);
        tfzoneEquipement.setText(zoneEquipement);
        tfetatEquipement.setText(etatEquipement);
        tfdetailEquipement.setText(id_departement);

    }

    @FXML
    private void ModifierEquipement(MouseEvent event) {
        String typeEquipement = tftypeEquipement.getText();
        String nomEquipement = tfnomEquipement.getText();
        String detailEquipement = tfdetailEquipement.getText();
        String zoneEquipement = tfzoneEquipement.getText();
        String etatEquipement = tfetatEquipement.getText();
        int id_departement = Integer.parseInt( tfid_departement.getText());
        Equipement d = new Equipement(typeEquipement, nomEquipement, detailEquipement,zoneEquipement,etatEquipement,id_departement);
        EquipementService ds = new EquipementService();
        ds.modifierEquipement(id_e, d);

    }
}
