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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.File;
import java.sql.PreparedStatement;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

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
    @FXML
    private TextField recherchet;
    ObservableList<Equipement> data = FXCollections.observableArrayList();
    EquipementService es = new EquipementService();
    Equipement equipement = null;
    @FXML
    private TableColumn<Equipement, String> id;
    Connection cnx;

    public AfficherequipementFXController() {

        cnx = MaConnexion.getInstance().getCnx();
    }

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

    public void refreshlist() {
        data.clear();
        data = FXCollections.observableArrayList(es.afficherEquipement());
        tbltypeEquipement.setCellValueFactory(new PropertyValueFactory<>("typeEquipement"));
        tblnomEquipement.setCellValueFactory(new PropertyValueFactory<>("nomEquipement"));
        tbldetailEquipement.setCellValueFactory(new PropertyValueFactory<>("detailEquipement"));
        tblzoneEquipement.setCellValueFactory(new PropertyValueFactory<>("zoneEquipement"));
        tbletatEquipement.setCellValueFactory(new PropertyValueFactory<>("etatEquipement"));
        tblid_departement.setCellValueFactory(new PropertyValueFactory<>("id_departement"));

        equipementTable.setItems(data);
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
        
        int input = JOptionPane.showConfirmDialog(null, "Voulez vous supprimer !?",
                "Choisir une option...",
                JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
        if (input == 0) {
            es.supprimerEquipement(equipement.getId());
            
        } else if (input == 1) {
            
        }
        
    }

    @FXML
    private void updateDepartement(MouseEvent event) {
        equipement = equipementTable.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../GUI/AjouterequipementFX.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(AfficherdepartementFXController.class.getName()).log(Level.SEVERE, null, ex);
        }
        AjouterequipementFXController AjouterequipementFXController = loader.getController();

        AjouterequipementFXController.setTextField(
                equipement.getId(),
                equipement.getTypeEquipement(),
                equipement.getNomEquipement(),
                equipement.getDetailEquipement(),
                equipement.getZoneEquipement(),
                equipement.getEtatEquipement(),
                String.valueOf(equipement.getId_departement()));
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();

    }

    @FXML
    private void exel(MouseEvent event) {

        WritableWorkbook wworkbook;
        try {
            wworkbook = Workbook.createWorkbook(new File("C:\\Users\\Aziz\\Desktop\\equipements.xls"));

            String query = "select typeEquipement,nomEquipement,detailEquipement,zoneEquipement,etatEquipement from equipements";
            PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs = ste.executeQuery();
            WritableSheet wsheet = wworkbook.createSheet("First Sheet", 0);
            jxl.write.Label label = new jxl.write.Label(0, 2, "A label record");
            wsheet.addCell(label);
            int i = 0;

            int j = 1;
            while (rs.next()) {

                i = 0;

                label = new jxl.write.Label(i++, j, j + "");
                wsheet.addCell(label);
                label = new jxl.write.Label(i++, j, rs.getString("typeEquipement"));
                wsheet.addCell(label);
                label = new jxl.write.Label(i++, j, rs.getString("nomEquipement"));
                wsheet.addCell(label);
                label = new jxl.write.Label(i++, j, rs.getString("detailEquipement"));
                wsheet.addCell(label);
                label = new jxl.write.Label(i++, j, rs.getString("zoneEquipement"));
                wsheet.addCell(label);
                label = new jxl.write.Label(i++, j, rs.getString("etatEquipement"));
                wsheet.addCell(label);

                j++;
            }

            wworkbook.write();
            wworkbook.close();
            System.out.println("fineshed");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    public void recherche_avance() {

        FilteredList<Equipement> filtereddata = new FilteredList<>(data, b -> true);
        System.out.println(recherchet.getText());
        recherchet.textProperty().addListener((observable, oldvalue, newValue) -> {
            filtereddata.setPredicate(equipements -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowercasefilter = newValue.toLowerCase();
                if (equipements.getTypeEquipement().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (equipements.getNomEquipement().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (equipements.getDetailEquipement().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (equipements.getZoneEquipement().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (equipements.getEtatEquipement().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });

        });
        System.out.println(filtereddata);
        SortedList<Equipement> sorteddata = new SortedList<>(filtereddata);
        sorteddata.comparatorProperty().bind(equipementTable.comparatorProperty());
        equipementTable.setItems(filtereddata);
    }
}
