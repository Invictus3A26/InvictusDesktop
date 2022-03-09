/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import chat.ClientController;
import chat.ServerController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author AMEN
 */
public class ChatFXMLController implements Initializable {

    @FXML
    private Button Chat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void messagerie(ActionEvent event) {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/chat/Server.fxml"));
        Parent root;
        try {

            root = loader.load();
            ServerController S1 = loader.getController();
            Chat.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
