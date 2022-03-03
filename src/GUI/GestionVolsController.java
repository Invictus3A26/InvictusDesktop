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

    Vols vol = null;
    @FXML
    private TableColumn<Vols, String> id;
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
                        rs.getInt("annulation_vol"),
                        rs.getInt("id_vol")
                        
                    
                        
                ));
               
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(GestionVolsController.class.getName()).log(Level.SEVERE, null, ex);
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
         id.setCellValueFactory(new PropertyValueFactory<>("id_vol"));
     
       
        
        
        
        
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
         vol = volTable.getSelectionModel().getSelectedItem();
                                VolsService vs = new VolsService();
                                vs.deleteVols(vol.getId());
    }

    @FXML
    private void updateVol(MouseEvent event) {
         vol = volTable.getSelectionModel().getSelectedItem();
          FXMLLoader loader = new FXMLLoader ();
          loader.setLocation(getClass().getResource("../GUI/AjouteVols.fxml"));
             try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(GestionVolsController.class.getName()).log(Level.SEVERE, null, ex);
                            }
          AjouteVolsController ajouteVolsController= loader.getController();
                          
                            ajouteVolsController.setTextField(
                                    vol.getId(),
                                    String.valueOf(vol.getNum_vol()),
                                    
                                    String.valueOf(vol.getNombrePassager_vol()) ,
                                    vol.getDate_depart_vol(),
                                    vol.getDate_arrivé_vol(),
                                    vol.getType_vol(),
                                    vol.getDurée_retard_vol(),
                                    String.valueOf(vol.isAnnulation_vol()));
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
           
          
         
        
        
    }
}
