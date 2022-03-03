/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import Entities.Equipement;
import Services.EquipementService;
import Tools.MaConnexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
public class AfficherequipementFXController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Equipement> equipementTable;

    ObservableList<Equipement> equipementList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Equipement, String> tbltypeEquipement;
    @FXML
    private TableColumn<Equipement, String> tblnomEquipement;
    @FXML
    private TableColumn<Equipement, String> tbldetailEquipement;
    @FXML
    private TableColumn<Equipement, String> tblzoneEquipement;
    @FXML
    private TableColumn<Equipement, String> tbletatEquipement;
    @FXML
    private TableColumn<Equipement, Integer> tblid_departement;
     Equipement equipement = null;
    @FXML
    private TableColumn<Equipement, String> id;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Connection con = MaConnexion.getInstance().getCnx();
            ResultSet rs = con.createStatement().executeQuery("select * from equipements");

            while (rs.next()) {
                equipementList.add(new Equipement(
                        rs.getInt("id"),
                        rs.getString("typeEquipement"),
                        rs.getString("nomEquipement"),
                        rs.getString("detailEquipement"),
                        rs.getString("zoneEquipement"),
                        rs.getString("etatEquipement"),
                        rs.getInt("id_departement")
                ));

            }

        } catch (SQLException ex) {
            Logger.getLogger(AfficherdepartementFXController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadDate();
        equipementTable.setItems(equipementList);

    }

    private void loadDate() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tbltypeEquipement.setCellValueFactory(new PropertyValueFactory<>("typeEquipement"));
        tblnomEquipement.setCellValueFactory(new PropertyValueFactory<>("nomEquipement"));
        tbldetailEquipement.setCellValueFactory(new PropertyValueFactory<>("detailEquipement"));
        tblzoneEquipement.setCellValueFactory(new PropertyValueFactory<>("zoneEquipement"));
        tbletatEquipement.setCellValueFactory(new PropertyValueFactory<>("etatEquipement"));
        tblid_departement.setCellValueFactory(new PropertyValueFactory<>("id_departement"));

    }

    @FXML
    private void AjouterEquipement(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("../GUI/AjouterequipementFX.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AfficherequipementFXController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void deleteEquipement(MouseEvent event) {
         equipement = equipementTable.getSelectionModel().getSelectedItem();
                                EquipementService ds = new EquipementService();
                                ds.supprimerEquipement(equipement.getId());
    }
}
