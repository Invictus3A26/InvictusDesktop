/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import Entities.AvionModel;
import Services.AvionService;
import Tools.DBConnexion;
import com.mysql.cj.Query;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCombination;
import javafx.stage.Screen;
import javafx.stage.Stage;
   

/**
 * FXML Controller class
 *
 * @author Anis
 */
public class AvionController implements Initializable {
    @FXML
    private TableView<AvionModel> Avion;
    Connection cnx;
     ObservableList<AvionModel> avionlist = FXCollections.observableArrayList();

  @FXML
    private TextField CA;
    @FXML
    private TextField TA;
    @FXML
    private TextField MD;
    @FXML
    private TextField PN;
      @FXML
    private TextField CC;
    @FXML
    private Button BtnEditAvion;
    @FXML
    private Button BtnDeleteAvion;
    @FXML
    private TableColumn<AvionModel,String > CodeA;
    @FXML
   private TableColumn<AvionModel,String> TypeA; 
    @FXML
    private TableColumn<AvionModel,String > Model;
    @FXML
    private TableColumn<AvionModel,String >PassagerN;
    @FXML
    private TableColumn<AvionModel,String > CodeC;
    @FXML
    private Button btnSaveAvion;
    @FXML
    private Button btneupdateAvion;
    @FXML
    private TableColumn<?, ?> Model1;
  public AvionController() {
        
        cnx=DBConnexion.getInstance().getCnx();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                  
            AvionService CS= new AvionService();
            List<AvionModel> A = CS.afficherAvion();
            String res ="";
         if (!A.isEmpty()) {
                for (int i = 0; i < A.size(); i++) {
                    avionlist.add(A.get(i));
                    System.out.println(A.get(i));
                }             
            
        //    CodeA.setCellValueFactory(new PropertyValueFactory<>("CodeAvion"));
            TypeA.setCellValueFactory(new PropertyValueFactory<>("TypeA"));
            Model.setCellValueFactory(new PropertyValueFactory<>("Model"));
            PassagerN.setCellValueFactory(new PropertyValueFactory<>("PassagerN"));
          //CodeC.setCellValueFactory(new PropertyValueFactory<>("CodeC"));
         
            Avion.setItems(avionlist);
        }
        }
    @FXML
    private void saveAvion(ActionEvent event)  {
   
        String CoIA = CA.getText();
         String No = TA.getText();
         String Lin = MD.getText();
         int Nu = Integer.parseInt(PN.getText());
         String Pay = CC.getText();
          AvionModel A = new AvionModel(CoIA,No,Lin,Nu,Pay);
         AvionService CS =new AvionService();
         CS.ajouterAvion(A);    
    }
    @FXML
    private void deleteAvion(ActionEvent event) {
         AvionModel A = Avion.getSelectionModel().getSelectedItem();
                                AvionService CS = new AvionService();
                                CS.deleteAvion(A.getCodeAvion());
    }
@FXML
    private void selectAvion(ActionEvent event) {
         AvionModel A = Avion.getSelectionModel().getSelectedItem();
      CA.setText(A.getCodeAvion());
      TA.setText(A.getTypeA());
    MD.setText(A.getModel());
     PN.setText(String.valueOf(A.getPassagerN()));
       CC.setText(A.getCodeC());
    }
      @FXML
    private void UpdateAvion(ActionEvent event)  {
   
        String CoIA = CA.getText();
         String No = TA.getText();
         String Lin = MD.getText();
         int Nu = Integer.parseInt(PN.getText());
         String Pay = CC.getText();
         AvionModel A = new AvionModel(CoIA,No,Lin,Nu,Pay);
         AvionService CS =new AvionService();
         CS.modifierAvion(CoIA,A);    
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
     @FXML
    private void goToCom(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Compagnie.fxml"));
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
