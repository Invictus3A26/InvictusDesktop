/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Escale;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import services.EscaleService;
import services.VolsService;
import tools.MaConnexion;

/**
 * FXML Controller class
 *
 * @author islem
 */
public class GestionEscaleController implements Initializable {

    @FXML
    private TableView<Escale> EscaleTable;
    @FXML
    private TableColumn<Escale, String> aeroport_depart;
    @FXML
    private TableColumn<Escale, String> aeroport_destination;
    @FXML
    private TableColumn<Escale, String> heure_arrivé;
    @FXML
    private TableColumn<Escale, String> heure_depart;
    @FXML
    private TableColumn<Escale, String> durée;
    @FXML
    private TextField rechercheTxt;
    
     Escale escale = null;
         ObservableList<Escale> EscaleList = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
           Connection con =MaConnexion.getInstance().getCnx();
           ResultSet rs = con.createStatement().executeQuery("select * from escale");
            
           
            
            while (rs.next()){
                EscaleList.add(new  Escale(
                      
                        rs.getInt("aeroport_depart"),
                        rs.getInt("aeroport_destination"),
                        rs.getString("heureArriveEscale"),
                        rs.getString("heureDepartEscale"),
                        rs.getString("durée"),
                        rs.getInt("id_escale") 
                       
                      
                        
                    
                        
                ));
               
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(GestionVolsController.class.getName()).log(Level.SEVERE, null, ex);
        }
         loadDate();
        EscaleTable.setItems(EscaleList);
                
    }    
    
    private void loadDate(){
     aeroport_depart.setCellValueFactory(new PropertyValueFactory<>("aeroport_depart"));
         aeroport_destination.setCellValueFactory(new PropertyValueFactory<>("aeroport_destination"));
         heure_depart.setCellValueFactory(new PropertyValueFactory<>("heureDepartEscale"));
        heure_arrivé.setCellValueFactory(new PropertyValueFactory<>("heureArriveEscale"));
         durée.setCellValueFactory(new PropertyValueFactory<>("durée"));

    }
      

    @FXML
    private void AjouteEscale(MouseEvent event) {
          try {
            Parent parent = FXMLLoader.load(getClass().getResource("../gui/AjouteEscale.fxml"));
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
    private void deleteEscale(MouseEvent event) {
         escale = EscaleTable.getSelectionModel().getSelectedItem();
                                   EscaleService es = new EscaleService();
                               
                                
        
          int input = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment supprimer ?",
                  "Choisir une option...",             
                  JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);  
            if (input == 0) { 
                es.deleteEscale(escale.getId_escale()); 
                loadDate();
            }   else if (input == 1)
            {             loadDate();  
            }     
                               

      
    }

    @FXML
    private void updateEscale(MouseEvent event) {
         escale = EscaleTable.getSelectionModel().getSelectedItem();
        EscaleService es = new EscaleService();
        escale=es.getEscaleById(escale.getId_escale());
     
          FXMLLoader loader = new FXMLLoader ();
          loader.setLocation(getClass().getResource("../GUI/AjouteEscale.fxml"));
             try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(GestionVolsController.class.getName()).log(Level.SEVERE, null, ex);
                            }
          AjouteEscaleController ajouteEscaleController= loader.getController();
                          
                            ajouteEscaleController.setTextField(
                                    escale.getId_escale(),
                                    String.valueOf(escale.getAeroport_depart()),
                                    String.valueOf(escale.getAeroport_destination()) ,
                                    escale.getHeureArriveEscale(),
                                    escale.getHeureDepartEscale(),
                                    escale.getDurée());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
    }

    @FXML
    private void chercher(MouseEvent event) {
    }

    @FXML
    private void refreshTable(MouseEvent event) {
        EscaleList.clear();
          try {
            Connection con =MaConnexion.getInstance().getCnx();
           ResultSet rs = con.createStatement().executeQuery("select * from escale");
            
           
            
            while (rs.next()){
                EscaleList.add(new  Escale(
                       
                        rs.getInt("aeroport_depart"),
                        rs.getInt("aeroport_destination"),
                        rs.getString("heureArriveEscale"),
                        rs.getString("heureDepartEscale"),
                        rs.getString("durée"),
                        rs.getInt("id_escale") 
                       
                    
                        
                ));
               
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(GestionVolsController.class.getName()).log(Level.SEVERE, null, ex);
        }
         loadDate();
        EscaleTable.setItems(EscaleList);
      
    }
    
}
