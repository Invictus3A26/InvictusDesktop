/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Departement;
import Services.DepartementService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
    int id_d;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void AddDepartement(MouseEvent event) {
        StringBuilder errors=new StringBuilder();
        if(tfnomDepartement.getText().trim().isEmpty()){
            errors.append("Ajouter un nom de departement\n");
        }
        if(tfzoneDepartement.getText().trim().isEmpty()){
            errors.append("Ajouter une zone de departement\n");
        }
        if(tfdetailDepartement.getText().trim().isEmpty()){
            errors.append("Ajouter un detail de departement\n");
        }
      
        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errors");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        }
        else {
        String nomDepartement = tfnomDepartement.getText();
        String zoneDepartement = tfzoneDepartement.getText();
        String detailDepartement = tfdetailDepartement.getText();

        Departement d = new Departement(nomDepartement, zoneDepartement, detailDepartement);
        DepartementService ds = new DepartementService();
        
        ds.ajouterDepartement(d);
        }
        
        
    }

    public void setTextField(int id, String nomDepartement, String zoneDepartement, String detailDepartement) {
        id_d = id;
        tfnomDepartement.setText(nomDepartement);
        tfzoneDepartement.setText(zoneDepartement);
        tfdetailDepartement.setText(detailDepartement);

    }

    @FXML
    private void ModifierDepartement(MouseEvent event) {
        StringBuilder errors=new StringBuilder();
        if(tfnomDepartement.getText().trim().isEmpty()){
            errors.append("Ajouter un nom de departement\n");
        }
        if(tfzoneDepartement.getText().trim().isEmpty()){
            errors.append("Ajouter une zone de departement\n");
        }
        if(tfdetailDepartement.getText().trim().isEmpty()){
            errors.append("Ajouter un detail de departement\n");
        }
      
        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errors");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        }
        else {
        String nomDepartement = tfnomDepartement.getText();
        String zoneDepartement = tfzoneDepartement.getText();
        String detailDepartement = tfdetailDepartement.getText();
        Departement d = new Departement(nomDepartement, zoneDepartement, detailDepartement);
        DepartementService ds = new DepartementService();
        ds.modifierDepartement(id_d, d);

    }
    }
    
    

}
