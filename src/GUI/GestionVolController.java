/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Vols;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.VolsService;
import tools.MaConnexion;

/**
 * FXML Controller class
 *
 * @author islem
 */
public class GestionVolController implements Initializable {

    @FXML
    private TableColumn<Vols, String> num_vol;
    @FXML
    private TableColumn<Vols, String> date_depart_vol;
    @FXML
    private TableColumn<Vols, String> date_arrivé_vol;
    @FXML
    private TableColumn<Vols, String> heure_arrivé_vol;
    @FXML
    private TableColumn<Vols, String> id_aeroport;
    @FXML
    private TableColumn<Vols, String> type_vol;
    @FXML
    private TableColumn<Vols, String> id_escale;
    @FXML
    private TableColumn<Vols, String> durée_retard_vol;
    @FXML
    private TableColumn<Vols, String> annulation_vol;
    @FXML
    private TableView<Vols> volTable;
    
  
    
    
    
   ObservableList<Vols> VolsList = FXCollections.observableArrayList();
    
    
  
  
    
     
    
  

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
                        rs.getInt("id_vol"),
                        rs.getInt("num_vol"),
                        rs.getInt("id_escale"),
                        rs.getString("date_depart_vol"),
                        rs.getString("date_arrivé_vol"),
                        rs.getString("heure_depart_vol"),
                        rs.getString("heure_arrivé_vol"),
                        rs.getInt("id_aeroport"),
                        rs.getString("type_avion"),
                        rs.getString("type_vol"),
                        rs.getString("id_comp"),
                        rs.getInt("nombrePassager_vol"),
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
        date_depart_vol.setCellValueFactory(new PropertyValueFactory<>("date_depart_vol"));
       date_arrivé_vol.setCellValueFactory(new PropertyValueFactory<>("date_arrivé_vol"));
       heure_arrivé_vol.setCellValueFactory(new PropertyValueFactory<>("heure_arrivé_vol"));
       id_aeroport.setCellValueFactory(new PropertyValueFactory<>("id_aeroport"));
        type_vol.setCellValueFactory(new PropertyValueFactory<>("type_vol"));
        id_escale.setCellValueFactory(new PropertyValueFactory<>("id_escale"));
        durée_retard_vol.setCellValueFactory(new PropertyValueFactory<>("durée_retard_vol"));
        annulation_vol.setCellValueFactory(new PropertyValueFactory<>("annulation_vol"));
       
        
        
        
        
    }
}
