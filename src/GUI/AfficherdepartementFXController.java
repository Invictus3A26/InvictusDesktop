/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Departement;
import Services.DepartementService;
import Tools.MaConnexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Aziz
 */
public class AfficherdepartementFXController implements Initializable {

    @FXML
    private TableView<Departement> departementTable;

    ObservableList<Departement> departementList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Departement, String> tblnomDepartement;
    @FXML
    private TableColumn<Departement, String> tblzoneDepartement;
    @FXML
    private TableColumn<Departement, String> tbldetailDepartement;
    
    Departement departement = null;
    @FXML
    private TableColumn<Departement, String> id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Connection con = MaConnexion.getInstance().getCnx();
            ResultSet rs = con.createStatement().executeQuery("select * from departement");

            while (rs.next()) {
                departementList.add(new Departement(
                        rs.getInt("id"),
                        rs.getString("nomDepartement"),
                        rs.getString("zoneDepartement"),
                        rs.getString("detailDepartement")
                        
                ));

            }

        } catch (SQLException ex) {
            Logger.getLogger(AfficherdepartementFXController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadDate();
        departementTable.setItems(departementList);

    }

    private void loadDate() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblnomDepartement.setCellValueFactory(new PropertyValueFactory<>("nomDepartement"));
        tblzoneDepartement.setCellValueFactory(new PropertyValueFactory<>("zoneDepartement"));
        tbldetailDepartement.setCellValueFactory(new PropertyValueFactory<>("detailDepartement"));

    }

    @FXML
    private void AjouteDepartement(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("../GUI/AjouterdepartementFX.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AfficherdepartementFXController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void deleteDepartement(MouseEvent event) {
         departement = departementTable.getSelectionModel().getSelectedItem();
                                DepartementService ds = new DepartementService();
                                ds.supprimerDepartement(departement.getId());
    }

}
