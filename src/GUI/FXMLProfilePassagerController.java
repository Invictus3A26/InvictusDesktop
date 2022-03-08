/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import Entities.User;
import Services.UserService;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author wajih
 */
public class FXMLProfilePassagerController implements Initializable {

    @FXML
    private Label Passagername;
    @FXML
    private JFXTextField lib_txt;
    @FXML
    private JFXTextField lib_txt1;
    @FXML
    private JFXTextField lib_txt2;
    @FXML
    private DatePicker lib_txt3;
    @FXML
    private JFXTextField lib_txt4;
    @FXML
    private JFXTextField lib_txt6;
    @FXML
    private JFXTextField lib_txt5;
    @FXML
    private Button save;
    @FXML
    private Button delete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        info();
    }

    private void info() {

        UserService us = new UserService();
        User u = us.findById(Id.user);
        System.out.println(Id.user);
        Passagername.setText("Hello " + u.getUsername() + "!");
        ////////////////////////
        lib_txt.setText(u.getNom());
        lib_txt1.setText(u.getPrenom());
        lib_txt2.setText(u.getUsername());
        Instant instant = Instant.ofEpochMilli(u.getDate_naissance().getTime());
        LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
        lib_txt3.setValue(ldt.toLocalDate());
        lib_txt4.setText(u.getAdresse());
        lib_txt5.setText(Integer.toString(u.getNumTel()));
        lib_txt6.setText(u.getEmail());

    }

    @FXML
    private void LogOut(ActionEvent event) {

        try {
            Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stageclose.close();
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/Authentification.fxml"));
            Stage stage = new Stage();

            Scene scene = new Scene(root);

            stage.setTitle("Articles");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AuthentificationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Save(ActionEvent event) {
        UserService us = new UserService();
        System.out.println("enter");

        User u = new User();
        StringBuilder errors = new StringBuilder();
        if (lib_txt.getText().trim().isEmpty()) {
            errors.append("- Please enter a First Name\n");//string s --- s+="erreur"
        }
        if (lib_txt1.getText().trim().isEmpty()) {

            errors.append("- Please enter a Last Name\n");
        }
        if (lib_txt6.getText().trim().isEmpty()) {
            errors.append("- Please enter a Email\n");
        }
        if (lib_txt2.getText().trim().isEmpty()) {
            errors.append("- Please enter a Username\n");
        }

        if (lib_txt4.getText().trim().isEmpty()) {
            errors.append("- Please enter Adress\n");
        }
        if (lib_txt5.getText().trim().isEmpty()) {
            errors.append("- Please enter a Phone number\n");
        }
        if (lib_txt3.getValue() == null) {
            errors.append("- Please enter a Birthday\n");
        }
        try {
            Integer.parseInt(lib_txt5.getText());
        } catch (NumberFormatException e) {
            errors.append("- Please enter a valid number\n");
        }
        /* if(us.usernameExist(usernametf.getText()) && usernametf.getText().equals(username_modif)){
            errors.append("- Username already exist");
        }*/
        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errors");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        } else {

            System.out.println("test requette");
            User uu = us.findById(Id.user);

            u.setAdresse(lib_txt4.getText());
            u.setDate_naissance(java.sql.Date.valueOf(lib_txt3.getValue()));
            u.setEmail(lib_txt6.getText());
            u.setNom(lib_txt.getText());
            u.setNumTel(Integer.parseInt(lib_txt5.getText()));
            u.setPrenom(lib_txt1.getText());
            u.setUsername(lib_txt2.getText());
            u.setRole(uu.getRole());
            us.modifier(Id.user, u);
            System.out.println("test done");
            TrayNotification tray = new TrayNotification();

            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Your account have been modified");
            tray.setMessage("Your account have been modified");
            tray.setNotificationType(NotificationType.NOTICE);
            tray.showAndDismiss(Duration.millis(1000));

        }

    }

    @FXML
    private void Delete(ActionEvent event) {
        UserService us = new UserService();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Need confirmation");
        alert.setContentText("Are you sure you want to delete you account? !");

        Optional<ButtonType> rslt = alert.showAndWait();
        if (rslt.get() == ButtonType.OK) {

            us.supprimer(Id.user);
            TrayNotification tray = new TrayNotification();

            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Your account have been deleted");
            tray.setMessage("Your account have been deleted");
            tray.setNotificationType(NotificationType.WARNING);
            tray.showAndDismiss(Duration.millis(1000));
            System.exit(0);

        }

        //  User u = us.findById(Id.user);
    }
}
