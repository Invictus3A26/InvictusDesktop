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
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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
        Stat();
    }

    private void Stat() {

        List<User> List = new ArrayList<>();
        //ServiceAchat sa = new ServiceAchat();
        //ServiceArticle sArticle = new ServiceArticle();
        
        UserService sa = new UserService();
        User s = new User();
        //Achat s = new Achat();
        // List<Command> list = sp.readAll();
        
        //list.addAll(sa.readAll());
        List.addAll(sa.afficher());
        Scene scene = new Scene(new Group());
        Stage stage = new Stage();
        // stage.setTitle("Type Reclamation stats");
        stage.setWidth(500);
        stage.setHeight(500);
        HashMap<Integer,Integer> detailsAchat = new HashMap<>();
        List<User> listAchat = sa.afficher();
        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                        
                        
                );
        listAchat.stream().forEach(System.out::print);
        listAchat.stream().forEach((a)->{
            pieChartData.add(new PieChart.Data(a.getRole().toString(),50.0));
            
        });
        final PieChart chart = new PieChart(pieChartData);
        stat.setData(pieChartData);
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
        numberAchatLB.setText(Integer.toString(us.affichercount("reclamation")));
        //numberAchatLB.setText(Integer.toString(us.affichercount("reclamation")));

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

    @FXML
    private void Reclamations(ActionEvent event) throws IOException {
        AnchorPane panee = FXMLLoader.load(getClass().getResource("Reclamation.fxml"));
        Dashboard.getChildren().setAll(panee);
    }

}
