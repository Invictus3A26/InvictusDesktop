/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Aeroport;
import Entities.Escale;
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
import Services.AeroportService;
import Services.VolsService;
import Tools.MaConnexion;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author islem
 */
public class GestionAeroportController implements Initializable {

    @FXML
    private TableView<Aeroport> AeroportTable;
    @FXML
    private TableColumn<Aeroport, String> nom_aeroport;
    @FXML
    private TableColumn<Aeroport, String> pays_aeroport;
    @FXML
    private TableColumn<Aeroport, String> ville_aeroport;
    @FXML
    private TextField rechercheTxt;
    
    
    Aeroport aeroport = null;
         ObservableList<Aeroport> AeroportList = FXCollections.observableArrayList();
    @FXML
    private AnchorPane airportt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          try {
           Connection con =MaConnexion.getInstance().getCnx();
           ResultSet rs = con.createStatement().executeQuery("select * from airport");
            
           
            
            while (rs.next()){
                AeroportList.add(new  Aeroport(
                      
                        rs.getInt("id_aeroport"),
                        rs.getString("nom_aeroport"),
                        rs.getString("ville_aeroport"),
                        rs.getString("pays")
                        
                        
                       
                      
                        
                    
                        
                ));
               
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(GestionVolsController.class.getName()).log(Level.SEVERE, null, ex);
        }
         loadDate();
        AeroportTable.setItems(AeroportList);
                
    }    
    
    private void loadDate(){
        nom_aeroport.setCellValueFactory(new PropertyValueFactory<>("nom_aeroport"));
        ville_aeroport.setCellValueFactory(new PropertyValueFactory<>("ville_aeroport"));
        pays_aeroport.setCellValueFactory(new PropertyValueFactory<>("pays"));

    }

    @FXML
    private void AjouteAeroport(MouseEvent event) {
          try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/AjouteAeroport.fxml"));
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
    private void deleteAeroport(MouseEvent event) {
          aeroport = AeroportTable.getSelectionModel().getSelectedItem();
                                   AeroportService es = new AeroportService();
                                
                              
        
          int input = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment supprimer ?",
                  "Choisir une option...",             
                  JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);  
            if (input == 0) { 
               es.deleteAeroport(aeroport.getId_aeroport());  
                loadDate();
            }   else if (input == 1)
            {             loadDate();  
            }     

      
    
    }

    @FXML
    private void updateAeroport(MouseEvent event) {
         aeroport = AeroportTable.getSelectionModel().getSelectedItem();
        AeroportService es = new AeroportService();
        aeroport=es.getAeroportById(aeroport.getId_aeroport());
     
          FXMLLoader loader = new FXMLLoader ();
          loader.setLocation(getClass().getResource("/GUI/AjouteAeroport.fxml"));
             try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(GestionVolsController.class.getName()).log(Level.SEVERE, null, ex);
                            }
          AjouteAeroportController ajouteAeroportController= loader.getController();
                          
                            ajouteAeroportController.setTextField(
                                    aeroport.getId_aeroport(),
                                    aeroport.getNom_aeroport(),
                                    aeroport.getVille_aeroport(),
                                    aeroport.getPays());
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
        
      AeroportList.clear();
          try {
           Connection con =MaConnexion.getInstance().getCnx();
           ResultSet rs = con.createStatement().executeQuery("select * from airport");
            
           
            
            while (rs.next()){
                AeroportList.add(new  Aeroport(
                        rs.getInt("id_aeroport"),
                      
                  
                        rs.getString("nom_aeroport"),
                        rs.getString("ville_aeroport"),
                        rs.getString("pays")
                        
                       
                      
                        
                    
                        
                ));
               
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(GestionVolsController.class.getName()).log(Level.SEVERE, null, ex);
        }
         loadDate();
        AeroportTable.setItems(AeroportList);
      
    }

    @FXML
    private void Reclamation(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/Reclamation.fxml"));
        airportt.getChildren().setAll(pane);
    }

    @FXML
    private void LogOut(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/Authentification.fxml"));
        airportt.getChildren().setAll(pane);
    }

    @FXML
    private void companies(ActionEvent event) throws IOException {
        
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/Compagnie.fxml"));
        airportt.getChildren().setAll(pane);
    }

    @FXML
    private void flights(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLFlights.fxml"));
        airportt.getChildren().setAll(pane);
    }

    @FXML
    private void luggage(ActionEvent event) throws IOException {
        
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/Home.fxml"));
        airportt.getChildren().setAll(pane);
    }

    @FXML
    private void Infra(ActionEvent event) throws IOException {
        
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/Homeaziz.fxml"));
        airportt.getChildren().setAll(pane);
    }

    @FXML
    private void profile(ActionEvent event) throws IOException {
        
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/ProfileEmploye.fxml"));
        airportt.getChildren().setAll(pane);
    }

}
