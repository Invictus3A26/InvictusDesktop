/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Departement;
import Services.DepartementService;
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
public class AfficherdepartementFXController implements Initializable {
    @FXML
    private Label lblnomDepartement;
    @FXML
    private Label lblzoneDepartement;
    @FXML
    private Label lbldetailDepartement;
    @FXML
    private Label lblList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    @FXML
    private void showDepartement(ActionEvent event) {
        DepartementService ds = new DepartementService();
        List<Departement> list = ds.afficherDepartement();
        String res = "";
        for (Departement d : list) {
            res += d.getNomDepartement() + " " + d.getZoneDepartement() + " " + d.getDetailDepartement() + "\n";
        }
        lblList.setText(res);
    }

    public void setNomDepartement(String nomDepartement) {
        lblnomDepartement.setText(nomDepartement);
    }

    public void setZoneDepartement(String zoneDepartement) {
        lblzoneDepartement.setText(zoneDepartement);
    }

    public void setDetailDepartement(String detailDepartement) {
        lbldetailDepartement.setText(detailDepartement);
    }

    
}
