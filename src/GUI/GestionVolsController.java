/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Vols;
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
import services.VolsService;
import tools.MaConnexion;

/**
 * FXML Controller class
 *
 * @author islem
 */
public class GestionVolsController implements Initializable {

    @FXML
    private TableView<Vols> volTable;
    @FXML
    private TableColumn<Vols, String> num_vol;
    @FXML
    private TableColumn<Vols, String> nombre;
    
    ObservableList<Vols> VolsList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Vols, String> dateDepart;
    @FXML
    private TableColumn<Vols, String> dateArrive;
    @FXML
    private TableColumn<Vols, String> type;
    @FXML
    private TableColumn<Vols, String> retard;
    @FXML
    private TableColumn<Vols, String> annulé;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     try {
           Connection con =MaConnexion.getInstance().getCnx();
           ResultSet rs = con.createStatement().executeQuery("select * from vols");
            
           
            
            while (rs.next()){
                VolsList.add(new  Vols(
                      
                        rs.getInt("num_vol"),
                        rs.getInt("nombrePassager_vol"),
                        rs.getString("date_depart_vol"),
                        rs.getString("date_arrivé_vol"),
                        rs.getString("type_vol"),
                        rs.getString("durée_retard_vol"),
                        rs.getInt("annulation_vol")
                        
                    
                        
                ));
               
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(GestionVolController.class.getName()).log(Level.SEVERE, null, ex);
        }
         loadDate();
        volTable.setItems(VolsList);
                
    }    
    private void loadDate(){
        num_vol.setCellValueFactory(new PropertyValueFactory<>("num_vol"));
         nombre.setCellValueFactory(new PropertyValueFactory<>("nombrePassager_vol"));
         dateDepart.setCellValueFactory(new PropertyValueFactory<>("date_depart_vol"));
        dateArrive.setCellValueFactory(new PropertyValueFactory<>("date_arrivé_vol"));
         type.setCellValueFactory(new PropertyValueFactory<>("type_vol"));
         retard.setCellValueFactory(new PropertyValueFactory<>("durée_retard_vol"));
         annulé.setCellValueFactory(new PropertyValueFactory<>("annulation_vol"));
     
       
        
        
        
        
    }

    @FXML
    private void AjouteVol(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("../gui/AjouteVols.fxml"));
            Scene scene =new Scene (parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(GestionVolsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void deleteVol(MouseEvent event) {
        VolsService vs = new VolsService();
       // vs.deleteVols(0);
        
    }
}
