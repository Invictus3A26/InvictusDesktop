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

    Departement departement = null;
    @FXML
    private TableColumn<Departement, String> id;
    Connection cnx;

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
            wworkbook = Workbook.createWorkbook(new File("C:\\Users\\Aziz\\Desktop\\absent_details_JAN.xls"));

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

}
