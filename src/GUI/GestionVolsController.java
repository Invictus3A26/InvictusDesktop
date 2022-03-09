/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Vols;
import java.awt.Image;
import java.io.IOException;
import static java.lang.Math.E;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import Services.VolsService;
import Tools.MaConnexion;
import org.controlsfx.control.Notifications;


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
    List<Vols> list = new ArrayList<>();
    
   
    @FXML
    private TextField rechercheTxt;
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
        // loadDate();
    

         
     
                    volTable.setRowFactory(tv -> new TableRow<Vols>() {
         @Override
         protected void updateItem(Vols item, boolean empty) {
             super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
              if (item == null) {
            setStyle("");
        } else if (item.isAnnulation_vol()==1) {
            setStyle("-fx-background-color: #CC5626;");
        } else if (item.getDurée_retard_vol().equals("0")) {
            setStyle("");
        } 
        else {
            setStyle("-fx-background-color: #F0DE0B;");
        }
    }
});
                    
                    
    
                    volTable.setItems(VolsList);
                    loadDate();
                    
                   chercher();
                    
         }
    
                 

        
        
          
       
                
    
    private void loadDate(){
        num_vol.setCellValueFactory(new PropertyValueFactory<>("num_vol"));
         nombre.setCellValueFactory(new PropertyValueFactory<>("nombrePassager_vol"));
         dateDepart.setCellValueFactory(new PropertyValueFactory<>("date_depart_vol"));
        dateArrive.setCellValueFactory(new PropertyValueFactory<>("date_arrivé_vol"));
         type.setCellValueFactory(new PropertyValueFactory<>("type_vol"));
         retard.setCellValueFactory(new PropertyValueFactory<>("durée_retard_vol"));
         annulé.setCellValueFactory(new PropertyValueFactory<>("annulation_vol"));
        // id.setCellValueFactory(new PropertyValueFactory<>("id_vol"));
     
       
        
        
        
        
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
         vol = volTable.getSelectionModel().getSelectedItem();
          int input = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment supprimer ?",
                  "Choisir une option...",             
                  JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);  
            if (input == 0) { 
                vs.deleteVols(vol.getId());  
                loadDate();
            }   else if (input == 1)
            {             loadDate();  
            }     
        }
        
        

    

 

    @FXML
    private void updateVol(MouseEvent event) {
          vol = volTable.getSelectionModel().getSelectedItem();
        VolsService vs = new VolsService();
        vol=vs.getVolById(vol.getId());
     
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
                                    String.valueOf(vol.isAnnulation_vol()),
                                     vol.getHeure_arrivé_vol(),
                                    vol.getHeure_depart_vol(),
                                    String.valueOf(vol.getNom_aeroport()),
                                    vol.getType_avion(),
                                    String.valueOf(vol.getEscale()),
                                    vol.getNomCom());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
           
          
         
        
        
    }

 
       
       

        

        
        
        
        
        
        
    
    
    

    
    
    
      @FXML
    private void refreshTable() {
        VolsList.clear();
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

    @FXML
    private void chercher() {
         FilteredList<Vols> filtereddata = new FilteredList<>(VolsList, b -> true);
       
        rechercheTxt.textProperty().addListener((observable, oldvalue, newValue) -> {
            filtereddata.setPredicate(vol -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowercasefilter = newValue.toLowerCase();
                if (String.valueOf(vol.getNum_vol()).indexOf(lowercasefilter) != -1)  {
                    return true;
                } else if (String.valueOf(vol.getNombrePassager_vol()).indexOf(lowercasefilter) != -1)  {
                    return true;
                } else if (vol.getDate_depart_vol().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (vol.getDate_arrivé_vol().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (String.valueOf(vol.isAnnulation_vol()).indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (vol.getType_vol().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (vol.getDurée_retard_vol().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });

        });
        //System.out.println(filtereddata);
        SortedList<Vols> sorteddata = new SortedList<>(filtereddata);
        sorteddata.comparatorProperty().bind(volTable.comparatorProperty());
        volTable.setItems(filtereddata);
    }
}
