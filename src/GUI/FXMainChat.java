/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;


import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.application.Application.launch;
/**
 *
 * @author AMEN
 */
 

public class FXMainChat extends Application {
    
    
    public void start(Stage primaryStage) {
       try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ChatFXML.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setTitle("Gestion Reclamation");
            primaryStage.setScene(scene);
            primaryStage.show();
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
      
      
    }



    


     
    public static void main(String[] args) {
        launch(args);
    }
    
}
 

    
    

