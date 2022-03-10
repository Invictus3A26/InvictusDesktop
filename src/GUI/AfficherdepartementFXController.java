/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Departement;
import Services.DepartementService;
import Tools.MaConnexion;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.time.Instant;
//import java.time.LocalDateTime;
//import java.time.ZoneOffset;
//import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
//import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
//import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import jxl.*;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

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
    @FXML
    private ComboBox<String> combotri;
    @FXML
    private TextField recherchet;
    ObservableList<Departement> data = FXCollections.observableArrayList();
    DepartementService ds = new DepartementService();

    Departement departement = null;
    private TableColumn<Departement, String> id;
    Connection cnx;
    ObservableList<String> ss = FXCollections.observableArrayList();

    public AfficherdepartementFXController() {

        cnx = MaConnexion.getInstance().getCnx();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            ResultSet rs = cnx.createStatement().executeQuery("select * from departement");

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
        //loadDate();
        departementTable.setItems(departementList);
        ss.add("Par nom");
        ss.add("Par zone");
        
        
        combotri.setItems(ss);
        refreshlist();
        //recherche_avance();

    }

    private void loadDate() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblnomDepartement.setCellValueFactory(new PropertyValueFactory<>("nomDepartement"));
        tblzoneDepartement.setCellValueFactory(new PropertyValueFactory<>("zoneDepartement"));
        tbldetailDepartement.setCellValueFactory(new PropertyValueFactory<>("detailDepartement"));

    }

    @FXML
    public void refreshlist() {
        data.clear();
        data = FXCollections.observableArrayList(ds.afficherDepartement());
        tblnomDepartement.setCellValueFactory(new PropertyValueFactory<>("nomDepartement"));
        tblzoneDepartement.setCellValueFactory(new PropertyValueFactory<>("zoneDepartement"));

        tbldetailDepartement.setCellValueFactory(new PropertyValueFactory<>("detailDepartement"));

        departementTable.setItems(data);
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
        refreshlist();
    }

    @FXML
    private void deleteDepartement(MouseEvent event) {
        departement = departementTable.getSelectionModel().getSelectedItem();
        DepartementService ds = new DepartementService();
        int input = JOptionPane.showConfirmDialog(null, "Voulez vous supprimer !?",
                "Choisir une option...",
                JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
        if (input == 0) {
            ds.supprimerDepartement(departement.getId());
            
        } else if (input == 1) {
            
        }

    }

    @FXML
    private void updateDepartement(MouseEvent event) {
        departement = departementTable.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../GUI/AjouterdepartementFX.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(AfficherdepartementFXController.class.getName()).log(Level.SEVERE, null, ex);
        }
        AjouterdepartementFXController AjouterdepartementFXController = loader.getController();

        AjouterdepartementFXController.setTextField(
                departement.getId(),
                departement.getNomDepartement(),
                departement.getZoneDepartement(),
                departement.getDetailDepartement());
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
            wworkbook = Workbook.createWorkbook(new File("C:\\Users\\Aziz\\Desktop\\departements.xls"));

            String query = "select nomDepartement,zoneDepartement,detailDepartement from departement";
            PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs = ste.executeQuery();
            WritableSheet wsheet = wworkbook.createSheet("First Sheet", 0);
            Label label = new Label(0, 2, "A label record");
            wsheet.addCell(label);
            int i = 0;

            int j = 1;
            while (rs.next()) {

                i = 0;

                label = new Label(i++, j, j + "");
                wsheet.addCell(label);
                label = new Label(i++, j, rs.getString("nomDepartement"));
                wsheet.addCell(label);
                label = new Label(i++, j, rs.getString("zoneDepartement"));
                wsheet.addCell(label);
                label = new Label(i++, j, rs.getString("detailDepartement"));
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
        System.out.println("*******************");

        //System.out.println(id.departement);
        FilteredList<Departement> filtereddata = new FilteredList<>(data, b -> true);
        System.out.println(recherchet.getText());
        recherchet.textProperty().addListener((observable, oldvalue, newValue) -> {
            filtereddata.setPredicate(departement -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowercasefilter = newValue.toLowerCase();
                if (departement.getNomDepartement().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (departement.getZoneDepartement().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (departement.getDetailDepartement().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });

        });
        System.out.println(filtereddata);
        SortedList<Departement> sorteddata = new SortedList<>(filtereddata);
        sorteddata.comparatorProperty().bind(departementTable.comparatorProperty());
        departementTable.setItems(filtereddata);
    }
    @FXML
    private void trilist(ActionEvent event) {
        if (combotri.getValue().equals("Par nom")) {
            ObservableList<Departement> tri1 = FXCollections.observableArrayList();
            tri1 = FXCollections.observableArrayList(ds.sortByNom());
            departementTable.setItems(tri1);

        } else if (combotri.getValue().equals("Par zone")) {
            ObservableList<Departement> tri2 = FXCollections.observableArrayList();
            tri2 = FXCollections.observableArrayList(ds.sortByZone());
            departementTable.setItems(tri2);
        }
    }
    

}
