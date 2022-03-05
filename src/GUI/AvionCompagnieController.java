/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.AvCom;
import Services.CompagnieService;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCombination;
import javafx.stage.Screen;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Anis
 */
public class AvionCompagnieController implements Initializable {
        @FXML
        private Button BLC;
      
@FXML        
private PieChart ACC;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    public void handleButtonActive (ActionEvent event){
        CompagnieService CS = new CompagnieService();
        AvCom AC = new AvCom();
      ObservableList<Data> list = FXCollections.observableArrayList(
     new PieChart.Data(AC.getCodeCom(),AC.getNumbAvC()),
              new PieChart.Data("DZ", 3),
              new PieChart.Data("FR", 4)
      );
     ACC.setData(list);
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
