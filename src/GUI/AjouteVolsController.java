/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Aeroport;
import entities.Vols;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import services.VolsService;
import tools.MaConnexion;

/**
 * FXML Controller class
 *
 * @author islem
 */
public class AjouteVolsController implements Initializable {

    @FXML
    private TextField num_vol;
    @FXML
    private DatePicker date_depart_vol;
    @FXML
    private DatePicker date_arrivé_vol;
    @FXML
    private TextField heure_depart_vol;
    @FXML
    private TextField heure_arrivé_vol;
    @FXML
    private ComboBox<String> id_aeroport;
    @FXML
    private ComboBox<String> type_avion;
    @FXML
    private ChoiceBox<String> type_vol;
    @FXML
    private ComboBox<String> escale;
    @FXML
    private ComboBox<String> compagnie;
    @FXML
    private TextField nombre_passager;
    @FXML
    private TextField durée_retard;
    @FXML
    private TextField annulé;
    int id_vol;
    
    ObservableList options = FXCollections.observableArrayList();
     ObservableList avion = FXCollections.observableArrayList();
     ObservableList escale1 = FXCollections.observableArrayList();
     ObservableList compagnie1 = FXCollections.observableArrayList();
     String[] type={"Depart","Arrivée"}; 
    @FXML
    private CheckBox volEscale;
    @FXML
    private Button ajoute1;
    @FXML
    private Button ajoute2;
    @FXML
    private Button mod2;
    @FXML
    private Button mod1;
    @FXML
    private Label errorNum;
    @FXML
    private Label num_vol_error;
    @FXML
    private Label date_depart_error;
    @FXML
    private Label date_arrivé_error;
    @FXML
    private Label heure_depart_error;
    @FXML
    private Label heure_arrivé_error;
    @FXML
    private Label type_error;
    @FXML
    private Label type_vol_error;
    @FXML
    private Label compagnie_error;
    @FXML
    private Label nombre_error;
    @FXML
    private Label duré_error;
    @FXML
    private Label annulé_error;
    
  
    
     

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboAeroport();
        comboAvion();
        comboEscale();
        comboCompagnie();
        id_aeroport.getItems().addAll(options);
        escale.getItems().addAll(escale1);
        compagnie.getItems().addAll(compagnie1);
        type_avion.getItems().addAll(avion);
        type_vol.getItems().addAll(type);
        
    }    
    
    public void handleCheckBox(){
        
    }
    
    public void comboAeroport(){
          try {
           Connection con =MaConnexion.getInstance().getCnx();
           ResultSet rs = con.createStatement().executeQuery("SELECT id_aeroport FROM `airport`");
            
           
            
            while (rs.next()){
                options.add(
                      
                        rs.getString("id_aeroport")
                    
                        
                        
                       
                      
                        
                    
                        
                );
               
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(GestionVolsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
     public void comboAvion(){
          try {
           Connection con =MaConnexion.getInstance().getCnx();
           ResultSet rs = con.createStatement().executeQuery("SELECT CodeAvion FROM `avion`");
            
           
            
            while (rs.next()){
                avion.add(
                      
                        rs.getString("CodeAvion")
                    
                        
                        
                       
                      
                        
                    
                        
                );
               
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(GestionVolsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     
        public void comboEscale(){
          try {
           Connection con =MaConnexion.getInstance().getCnx();
           ResultSet rs = con.createStatement().executeQuery("SELECT id_escale FROM `escale`");
            
           
            
            while (rs.next()){
                escale1.add(
                      
                        rs.getString("id_escale")
                    
                        
                        
                       
                      
                        
                    
                        
                );
               
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(GestionVolsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
        
           public void comboCompagnie(){
          try {
           Connection con =MaConnexion.getInstance().getCnx();
           ResultSet rs = con.createStatement().executeQuery("SELECT Code_IATA FROM `compagnie`");
            
           
            
            while (rs.next()){
                compagnie1.add(
                      
                        rs.getString("Code_IATA")
                    
                        
                        
                       
                      
                        
                    
                        
                );
               
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(GestionVolsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    

    @FXML
    private void ajouterVol(MouseEvent event) {
         errorHandler();
         int numVol = Integer.parseInt( num_vol.getText());
         LocalDate dateDepartVol = date_depart_vol.getValue();
         LocalDate dateArrivéVol = date_arrivé_vol.getValue();
         String heureArrivéVol = heure_arrivé_vol.getText();
         String heureDepartVol = heure_depart_vol.getText();
         int idAeroport = Integer.parseInt(id_aeroport.getValue());
         String typeAvion = type_avion.getValue();
         String typeVol = type_vol.getValue();
        
         String Compagnie = compagnie.getValue();
         int nombrePass = Integer.parseInt(nombre_passager.getText());
         String duréRetard = durée_retard.getText();
         int Annulé = Integer.parseInt(annulé.getText());
         
         Vols v = new Vols(numVol, idAeroport, nombrePass, Annulé,  dateDepartVol.toString(), dateArrivéVol.toString(), heureDepartVol, heureArrivéVol, typeVol, duréRetard, typeAvion, Compagnie);
        VolsService sv = new VolsService();
         errorNum.setVisible(sv.getUtilisateurByEmail(numVol));
      
         sv.ajouterVols1(v);
         
         
        
         

      
       
        
         
        
        
         
         
         
    }
    
     @FXML
    private void ajouterVol2(MouseEvent event) {
    
       
         errorHandler();
                                 
                    
              
         int numVol = Integer.parseInt( num_vol.getText());
         LocalDate dateDepartVol = date_depart_vol.getValue();
         LocalDate dateArrivéVol = date_arrivé_vol.getValue();
         String heureArrivéVol = heure_arrivé_vol.getText();
         String heureDepartVol = heure_depart_vol.getText();
    
         String typeAvion = type_avion.getValue();
         String typeVol = type_vol.getValue();
        int idEscale= Integer.parseInt(escale.getValue());
         String Compagnie = compagnie.getValue();
         int nombrePass = Integer.parseInt(nombre_passager.getText());
         String duréRetard = durée_retard.getText();
         int Annulé = Integer.parseInt(annulé.getText());
         
         Vols v = new Vols(numVol,  nombrePass, Annulé,  dateDepartVol.toString(),idEscale, dateArrivéVol.toString(), heureDepartVol, heureArrivéVol, typeVol, duréRetard, typeAvion, Compagnie);
        VolsService sv = new VolsService();
       
       errorNum.setVisible(sv.getUtilisateurByEmail(numVol));
         sv.ajouterVols2(v);
         
       
        
       
        
         
        
        
         
         
         
    }
public void errorHandler(){
            if ((num_vol.getText()).isEmpty() ){
         num_vol_error.setVisible(true);
         }
             if ((heure_arrivé_vol.getText()).isEmpty() ){
         heure_arrivé_error.setVisible(true);
         }
              if ((heure_depart_vol.getText()).isEmpty() ){
         heure_depart_error.setVisible(true);
         }
               if ((nombre_passager.getText()).isEmpty() ){
         nombre_error.setVisible(true);
         }
               if ((date_depart_vol.getValue())==null ){
         date_depart_error.setVisible(true);
         }
          if ((date_arrivé_vol.getValue())==null ){
         date_arrivé_error.setVisible(true);
         }
           if ((type_avion.getValue())==null ){
         type_error.setVisible(true);
         }
            if ((type_vol.getValue())==null ){
         type_vol_error.setVisible(true);
         }
         if ((compagnie.getValue())==null ){
         compagnie_error.setVisible(true);
         }
         if ((nombre_passager.getText()).isEmpty() ){
         nombre_error.setVisible(true);
         }
          if ((durée_retard.getText()).isEmpty() ){
         duré_error.setVisible(true);
         }
           if ((annulé.getText()).isEmpty() ){
         annulé_error.setVisible(true);
         }
}
    
    public void setTextField(int Id_vol,String Num_vol, String Nombre_passager, String Date_depart_vol, String Date_arrivé_vol, String Type_vol,String duré_arrivé_vol,String annulation,
            String heure_arrivé,String heure_Depart,String aeroport,String typeAvion,String Escale,String comp) {
     id_vol=Id_vol;
     num_vol.setText(Num_vol);
     nombre_passager.setText(Nombre_passager);
    
     type_vol.setValue(Type_vol);
     durée_retard.setText(duré_arrivé_vol);
     annulé.setText(annulation);
     heure_arrivé_vol.setText(heure_arrivé);
     heure_depart_vol.setText(heure_Depart);
     
     id_aeroport.setValue(aeroport);
     type_avion.setValue(typeAvion);
     escale.setValue(Escale);
     compagnie.setValue(comp);
     
     
     
      
     
        


    }

    @FXML
    private void ModifierVol1(MouseEvent event) {
         errorHandler();
         int numVol = Integer.parseInt( num_vol.getText());
         LocalDate dateDepartVol = date_depart_vol.getValue();
         LocalDate dateArrivéVol = date_arrivé_vol.getValue();
         String heureArrivéVol = heure_arrivé_vol.getText();
         String heureDepartVol = heure_depart_vol.getText();
         int idAeroport = Integer.parseInt(id_aeroport.getValue());
         String typeAvion = type_avion.getValue();
         String typeVol = type_vol.getValue();
         
         String Compagnie = compagnie.getValue();
         int nombrePass = Integer.parseInt(nombre_passager.getText());
         String duréRetard = durée_retard.getText();
         int Annulé = Integer.parseInt(annulé.getText());
         Vols v = new Vols(numVol, idAeroport, nombrePass, Annulé,  dateDepartVol.toString(), dateArrivéVol.toString(), heureDepartVol, heureArrivéVol, typeVol, duréRetard, typeAvion, Compagnie);
       
        VolsService vs = new VolsService();
          errorNum.setVisible(vs.getUtilisateurByEmail(numVol));
        vs.updateVols1(v, id_vol);
       
      
        
        
        
    }
    
     @FXML
    private void ModifierVol2(MouseEvent event) {
         errorHandler();
         int numVol = Integer.parseInt( num_vol.getText());
         LocalDate dateDepartVol = date_depart_vol.getValue();
         LocalDate dateArrivéVol = date_arrivé_vol.getValue();
         String heureArrivéVol = heure_arrivé_vol.getText();
         String heureDepartVol = heure_depart_vol.getText();
         
         String typeAvion = type_avion.getValue();
         String typeVol = type_vol.getValue();
         int idEscale = Integer.parseInt(escale.getValue());
         String Compagnie = compagnie.getValue();
         int nombrePass = Integer.parseInt(nombre_passager.getText());
         String duréRetard = durée_retard.getText();
         int Annulé = Integer.parseInt(annulé.getText());
         Vols v = new Vols(numVol,  nombrePass, Annulé,  dateDepartVol.toString(),idEscale, dateArrivéVol.toString(), heureDepartVol, heureArrivéVol, typeVol, duréRetard, typeAvion, Compagnie);
        
        VolsService vs = new VolsService();
         errorNum.setVisible(vs.getUtilisateurByEmail(numVol));
        vs.updateVols2(v, id_vol);
      
        
        
    }
    

    @FXML
    private void Change(ActionEvent event) {
        
          if(volEscale.isSelected()) {
              id_aeroport.setVisible(false);
              escale.setVisible(true);
              ajoute2.setVisible(true);
              ajoute1.setVisible(false);
              mod1.setVisible(false);
              mod2.setVisible(true);
  
  }
  else {
   escale.setVisible(false);
   id_aeroport.setVisible(true);
      ajoute2.setVisible(false);
              ajoute1.setVisible(true);
              mod1.setVisible(true);
              mod2.setVisible(false);
  }
    }
    
    
   
}
