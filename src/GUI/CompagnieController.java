/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.CompagnieModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import Services.CompagnieService;

/**
 * FXML Controller class
 *
 * @author Anis
 */
public class CompagnieController implements Initializable {

    @FXML
    private TableView<CompagnieModel> TCompagnie;
    @FXML
    private TableColumn<CompagnieModel, String> Code_IATA;
    @FXML
    private TableColumn<CompagnieModel, String> NomCom;
    @FXML
    private TableColumn<CompagnieModel, String> Link;
    @FXML
    private TableColumn<CompagnieModel, String> Pays;
    @FXML
    private TableColumn<CompagnieModel, Integer> NumTel;
    @FXML
    private TableColumn<CompagnieModel, String> Siege;
    @FXML
    private TableColumn<CompagnieModel, String> AeBase;
    @FXML
    private TableColumn<CompagnieModel, Integer> NumP;
    @FXML
    private TableColumn<CompagnieModel, String> Description;
    ObservableList<CompagnieModel> compagnieList = FXCollections.observableArrayList();
    @FXML
    private TextField CI;
    @FXML
    private TextField NM;
    @FXML
    private TextField SW;
    @FXML
    private TextField PS;
    @FXML
    private TextField NT;
    @FXML
    private TextField SG;
    @FXML
    private TextField ADB;
    @FXML
    private TextField NP;
    @FXML
    private TextField DS;
    @FXML
    private Button btnSaveCompagnie;
    @FXML
    private Button BtnDeleteCompagnie;
    @FXML
    private Button BtnEditCompagnie;
    @FXML
    private Button btneupdateCompagnie1;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
    
      
                
            CompagnieService CS= new CompagnieService();
            List<CompagnieModel> C = CS.afficherCompagnie();
            String res ="";
         if (!C.isEmpty()) {
                for (int i = 0; i < C.size(); i++) {
                    compagnieList.add(C.get(i));
                    System.out.println(C.get(i));
                }             
            
            
            Code_IATA.setCellValueFactory(new PropertyValueFactory<>("Code_IATA"));
            NomCom.setCellValueFactory(new PropertyValueFactory<>("NomCom"));
            Link.setCellValueFactory(new PropertyValueFactory<>("Link"));
            Pays.setCellValueFactory(new PropertyValueFactory<>("Pays"));
            NumTel.setCellValueFactory(new PropertyValueFactory<>("Number"));
            Siege.setCellValueFactory(new PropertyValueFactory<>("Siege"));
            AeBase.setCellValueFactory(new PropertyValueFactory<>("AeBase"));
            NumP.setCellValueFactory(new PropertyValueFactory<>("PassagerNum"));
            Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
            
            TCompagnie.setItems(compagnieList);
        }
        }
    @FXML
    private void saveCompagnie(ActionEvent event)  {
   
        String CoIA = CI.getText();
         String No = NM.getText();
         String Lin = SW.getText();
         String Pay = PS.getText();
         int Nu = Integer.parseInt(NT.getText());
         String Sg = SG.getText();
         String Adb = ADB.getText();
         int Np = Integer.parseInt(NP.getText());
         String Ds = DS.getText();
         CompagnieModel C = new CompagnieModel(CoIA,No,Lin,Pay,Nu,Sg,Adb,Np,Ds);
         CompagnieService CS =new CompagnieService();
         CS.ajouterCompagnie(C);    
    }
    @FXML
    private void deleteCompagnie(ActionEvent event) {
         CompagnieModel C = TCompagnie.getSelectionModel().getSelectedItem();
                                CompagnieService CS = new CompagnieService();
                                CS.deleteCompagnie(C.getCode_IATA());
    }
@FXML
    private void editCompagnie(ActionEvent event) {
         CompagnieModel C = TCompagnie.getSelectionModel().getSelectedItem();
      CI.setText(C.getCode_IATA());
      NM.setText(C.getNomCom());
    SW.setText(C.getLink());
      PS.setText(C.getPays());
      NT.setText(String.valueOf(C.getNumber()));
      SG.setText(C.getSiege());
      ADB.setText(C.getAeBase());
     NP.setText(String.valueOf(C.getPassagerNum()));
       DS.setText(C.getDescription());
    }
      @FXML
    private void UpdateCompagnie(ActionEvent event)  {
   
        String CoIA = CI.getText();
         String No = NM.getText();
         String Lin = SW.getText();
         String Pay = PS.getText();
         int Nu = Integer.parseInt(NT.getText());
         String Sg = SG.getText();
         String Adb = ADB.getText();
         int Np = Integer.parseInt(NP.getText());
         String Ds = DS.getText();
         CompagnieModel C = new CompagnieModel(CoIA,No,Lin,Pay,Nu,Sg,Adb,Np,Ds);
         CompagnieService CS =new CompagnieService();
         CS.modifierCompagnie(CoIA,C);    
    }
    @FXML
    private void goToAvCom(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AvionCompagnie.fxml"));
            Parent root = loader.load();
            int width = (int) Screen.getPrimary().getBounds().getWidth();
            int height = (int) Screen.getPrimary().getBounds().getHeight();
            
            Scene scene = new Scene(root,width,height);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setMaximized(true);
            stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
            stage.setScene(scene);
            stage.show();
    }
}