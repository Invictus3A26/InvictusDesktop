/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Equipement;
import Services.EquipementService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Aziz
 */
public class AfficherequipementFXController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label lbltypeEquipement;
    @FXML
    private Label lblnomEquipement;
    @FXML
    private Label lbldetailEquipement;
    @FXML
    private Label lblzoneEquipement;
    @FXML
    private Label lbletatEquipement;
    @FXML
    private Label lblid_departement;
    @FXML
    private Label lblList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void showEquipement(ActionEvent event) {
        EquipementService sp = new EquipementService();
        List<Equipement> list = sp.afficherEquipement();
        String res = "";
        for (Equipement p : list) {
            res += p.getTypeEquipement() + " " + p.getNomEquipement() + " " + p.getDetailEquipement() + " " + p.getZoneEquipement() + " " + p.getEtatEquipement() + " " + p.getId_departement() + "\n";
        }
        lblList.setText(res);
    }

    public void setTypeEquipement(String typeEquipement) {
        lbltypeEquipement.setText(typeEquipement);
    }

    public void setNomEquipement(String nomEquipement) {
        lblnomEquipement.setText(nomEquipement);
    }

    public void setDetailEquipement(String detailEquipement) {
        lbldetailEquipement.setText(detailEquipement);
    }

    public void setZoneEquipement(String zoneEquipement) {
        lblzoneEquipement.setText(zoneEquipement);
    }

    public void setEtatEquipement(String etatEquipement) {
        lbletatEquipement.setText(etatEquipement);
    }

    public void setId_departement(String id_departement) {
        lblid_departement.setText(id_departement);
    }

}
