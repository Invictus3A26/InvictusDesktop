/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import Entities.User;
import Services.UserService;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author wajih
 */
public class FXMLDashboardController implements Initializable {

    @FXML
    private Label numberClientLB;
    @FXML
    private Label numberFourn;
    @FXML
    private Label numberArticleLB;
    @FXML
    private Label numberAchatLB;
    @FXML
    private Label currentTimeTF;
    @FXML
    private Label userName;
    @FXML
    private Label numtelLb;
    @FXML
    private Label matfLb;
    @FXML
    private Label emailLb;
    @FXML
    private PieChart stat;
    @FXML
    private Label societeName12;
    @FXML
    private Label numberAchatLB3;
    @FXML
    private Label numberAchatLB1;
    @FXML
    private JFXButton Passagers;
    @FXML
    private AnchorPane Dashboard;
    @FXML
    private JFXButton Employes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initClock();
        info();
    }

    private void initClock() {

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            currentTimeTF.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    private void info() {

        UserService us = new UserService();
        User u = us.findById(Id.user);
        System.out.println(u.getNom());
        numtelLb.setText(u.getEmail());
        emailLb.setText(u.getNom());
        matfLb.setText(u.getPrenom());
        userName.setText(u.getUsername());
        numberClientLB.setText(Integer.toString(us.affichernumber("PASSAGER")));
        numberFourn.setText(Integer.toString(us.affichernumber("EMPLOYE")));
        numberArticleLB.setText(Integer.toString(us.affichercount("reclamation")));

    }

    @FXML
    private void Passagers(ActionEvent event) throws IOException {
        AnchorPane panee = FXMLLoader.load(getClass().getResource("FXMLpassagers.fxml"));
        Dashboard.getChildren().setAll(panee);
    }

    @FXML
    private void Employes(ActionEvent event) throws IOException {
        AnchorPane panee = FXMLLoader.load(getClass().getResource("FXMLemployes.fxml"));
        Dashboard.getChildren().setAll(panee);
    }

}
