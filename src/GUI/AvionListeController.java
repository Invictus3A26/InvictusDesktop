/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Anis
 */
public class AvionListeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
        private void goToAv(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionAvion.fxml"));
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
    private void BackHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Test.fxml"));

        Parent root = loader.load();
        int width = (int) Screen.getPrimary().getBounds().getWidth();
        int height = (int) Screen.getPrimary().getBounds().getHeight();

        Scene scene = new Scene(root, width, height);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setMaximized(true);
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.setScene(scene);
        stage.show();
    }
   @FXML
    private void OpenPDF (ActionEvent event){
        try{
        Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler "+"C:\\Users\\Anis\\Desktop\\avion.pdf");
                }
    catch(Exception e){
        
    }
    }
}
