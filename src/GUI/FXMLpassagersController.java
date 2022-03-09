/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Reclamation;
import Entities.Role;
import Entities.User;
import Services.UserService;
import Tools.MyConnexion;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class FXMLpassagersController implements Initializable {

    @FXML
    private TableView<User> tableviewuser;
    @FXML
    private TextField nomtf;
    @FXML
    private TextField prenomtf;
    @FXML
    private TextField emailtf;
    @FXML
    private TextField usernametf;
    @FXML
    private TextField addresstf;
    private PasswordField passwordpf;
    @FXML
    private TextField numerotf;
    @FXML
    private ComboBox<Role> comborole;
    @FXML
    private TextField recherchetf;
    @FXML
    private ComboBox<String> combotri;
    @FXML
    private DatePicker dpdate;
    ObservableList<User> data = FXCollections.observableArrayList();
    UserService us = new UserService();
    @FXML
    private TableColumn<User, String> nom_col;
    @FXML
    private TableColumn<User, String> prenom_col;
    @FXML
    private TableColumn<User, String> email_col;
    @FXML
    private TableColumn<User, String> username_col;
    @FXML
    private TableColumn<User, String> password_col;
    @FXML
    private TableColumn<User, String> add_col;
    @FXML
    private TableColumn<User, Date> date_col;
    @FXML
    private TableColumn<User, Integer> numero_col;
    @FXML
    private TableColumn<User, Role> role_col;
    long id_modif;
    String username_modif;
    @FXML
    private Button btntri;

    ObservableList<String> ss = FXCollections.observableArrayList();
    private AnchorPane signinpane;
    @FXML
    private AnchorPane DashboardUtilis;
    @FXML
    private Button Map;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ss.add("Par nom");
        ss.add("Par date");
        // TODO
        comborole.getItems().setAll(Role.values());
        combotri.setItems(ss);
        refreshlist();
        recherche_avance();

    }

    public void refreshlist() {
        data.clear();
        data = FXCollections.observableArrayList(us.afficherpassager());
        nom_col.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        prenom_col.setCellValueFactory(new PropertyValueFactory<>("Prenom"));

        email_col.setCellValueFactory(new PropertyValueFactory<>("Email"));
        username_col.setCellValueFactory(new PropertyValueFactory<>("Username"));
        password_col.setCellValueFactory(new PropertyValueFactory<>("Password"));
        add_col.setCellValueFactory(new PropertyValueFactory<>("adresse"));

        date_col.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));
        numero_col.setCellValueFactory(new PropertyValueFactory<>("numTel"));
        role_col.setCellValueFactory(new PropertyValueFactory<>("Role"));
        tableviewuser.setItems(data);
    }

    /*  @FXML
    private void ajouterUser(ActionEvent event) {
        StringBuilder errors = new StringBuilder();
        if (nomtf.getText().trim().isEmpty()) {
            errors.append("- Please enter a First Name\n");//string s --- s+="erreur"
        }
        if (prenomtf.getText().trim().isEmpty()) {

            errors.append("- Please enter a Last Name\n");
        }
        if (emailtf.getText().trim().isEmpty()) {
            errors.append("- Please enter a Email\n");
        }
        if (usernametf.getText().trim().isEmpty()) {
            errors.append("- Please enter a Username\n");
        }
        if (passwordpf.getText().trim().isEmpty()) {
            errors.append("- Please enter a Password\n");
        }
        if (addresstf.getText().trim().isEmpty()) {
            errors.append("- Please enter Adress\n");
        }
        if (numerotf.getText().trim().isEmpty()) {
            errors.append("- Please enter a Phone number\n");
        }
        if (dpdate.getValue() == null) {
            errors.append("- Please enter a Birthday\n");
        }
        try {
            Integer.parseInt(numerotf.getText());
        } catch (NumberFormatException e) {
            errors.append("- Please enter a valid number\n");
        }
        if (us.usernameExist(usernametf.getText())) {
            errors.append("- Username already exist");
        }
        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errors");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        } else {
            User u = new User();
            u.setAdresse(addresstf.getText());
            u.setDate_naissance(java.sql.Date.valueOf(dpdate.getValue()));
            u.setEmail(emailtf.getText());
            u.setNom(nomtf.getText());
            u.setNumTel(Integer.parseInt(numerotf.getText()));
            u.setPassword(passwordpf.getText());
            u.setPrenom(prenomtf.getText());
            u.setUsername(usernametf.getText());
            u.setRole(comborole.getValue());
            us.ajouter(u);
            TrayNotification tray = new TrayNotification();

            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Added user Success");
            tray.setMessage("You successufuly added user in ur application");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(1000));
        }
        refreshlist();
    }*/
    @FXML
    private void modifierUser(ActionEvent event) {
        User u = new User();
        StringBuilder errors = new StringBuilder();
        if (nomtf.getText().trim().isEmpty()) {
            errors.append("- Please enter a First Name\n");//string s --- s+="erreur"
        }
        if (prenomtf.getText().trim().isEmpty()) {

            errors.append("- Please enter a Last Name\n");
        }
        if (emailtf.getText().trim().isEmpty()) {
            errors.append("- Please enter a Email\n");
        }
        if (usernametf.getText().trim().isEmpty()) {
            errors.append("- Please enter a Username\n");
        }

        if (addresstf.getText().trim().isEmpty()) {
            errors.append("- Please enter Adress\n");
        }
        if (numerotf.getText().trim().isEmpty()) {
            errors.append("- Please enter a Phone number\n");
        }
        if (dpdate.getValue() == null) {
            errors.append("- Please enter a Birthday\n");
        }
        try {
            Integer.parseInt(numerotf.getText());
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
            u.setAdresse(addresstf.getText());
            u.setDate_naissance(java.sql.Date.valueOf(dpdate.getValue()));
            u.setEmail(emailtf.getText());
            u.setNom(nomtf.getText());
            u.setNumTel(Integer.parseInt(numerotf.getText()));
            // u.setPassword(passwordpf.getText());
            u.setPrenom(prenomtf.getText());
            u.setUsername(usernametf.getText());
            u.setRole(comborole.getValue());
            us.modifier(id_modif, u);
            TrayNotification tray = new TrayNotification();

            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Update user Success");
            tray.setMessage("You successufuly updated user in ur application");
            tray.setNotificationType(NotificationType.NOTICE);
            tray.showAndDismiss(Duration.millis(1000));
        }
        refreshlist();

    }

    @FXML
    private void supprimerUser(ActionEvent event) {

        String username = tableviewuser.getSelectionModel().getSelectedItem().getUsername();
        User u = us.findByUsername(username);
        us.supprimer(u.getId());

        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle("Delete Success");
        tray.setMessage("User is deleted");
        tray.setNotificationType(NotificationType.WARNING);
        tray.showAndDismiss(Duration.millis(1000));
        refreshlist();
    }

    @FXML
    private void Back(ActionEvent event) throws IOException {

        /* try {
            Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stageclose.close();
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/Dash.fxml"));
            Stage stage = new Stage();

            Scene scene = new Scene(root);

            stage.setTitle("Articles");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AuthentificationController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        AnchorPane panee = FXMLLoader.load(getClass().getResource("FXMLDashboard.fxml"));
        DashboardUtilis.getChildren().setAll(panee);
    }

    @FXML
    private void fillforum() {

        String username = tableviewuser.getSelectionModel().getSelectedItem().getUsername();
        User u = us.findByUsername(username);
        id_modif = u.getId();

        nomtf.setText(u.getNom());
        prenomtf.setText(u.getPrenom());
        emailtf.setText(u.getEmail());
        //passwordpf.setText(u.getPassword());
        usernametf.setText(u.getUsername());
        addresstf.setText(u.getAdresse());
        //date to localdate
        Instant instant = Instant.ofEpochMilli(u.getDate_naissance().getTime());
        LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
        dpdate.setValue(ldt.toLocalDate());
        numerotf.setText(Integer.toString(u.getNumTel()));
        comborole.setValue(u.getRole());
        username_modif = u.getUsername();

    }

    @FXML
    public void recherche_avance() {
        System.out.println("*******************");

        System.out.println(Id.user);
        FilteredList<User> filtereddata = new FilteredList<>(data, b -> true);
        System.out.println(recherchetf.getText());
        recherchetf.textProperty().addListener((observable, oldvalue, newValue) -> {
            filtereddata.setPredicate(user -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowercasefilter = newValue.toLowerCase();
                if (user.getNom().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (user.getPrenom().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (user.getAdresse().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (user.getEmail().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (String.valueOf(user.getNumTel()).indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (user.getRole().toString().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (user.getUsername().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (user.getDate_naissance().toString().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });

        });
        //System.out.println(filtereddata);
        SortedList<User> sorteddata = new SortedList<>(filtereddata);
        sorteddata.comparatorProperty().bind(tableviewuser.comparatorProperty());
        tableviewuser.setItems(filtereddata);
    }

    @FXML
    private void trilist(ActionEvent event) {
        if (combotri.getValue().equals("Par nom")) {
            ObservableList<User> tri1 = FXCollections.observableArrayList();
            tri1 = FXCollections.observableArrayList(us.sortByNom());
            tableviewuser.setItems(tri1);

        } else if (combotri.getValue().equals("Par date")) {
            ObservableList<User> tri2 = FXCollections.observableArrayList();
            tri2 = FXCollections.observableArrayList(us.sortByDate());
            tableviewuser.setItems(tri2);
        }
    }

    @FXML
    private void imprimer(ActionEvent event) {
    }

    @FXML
    private void Map(ActionEvent event) throws IOException {
        AnchorPane panee = FXMLLoader.load(getClass().getResource("MapFXML.fxml"));
        DashboardUtilis.getChildren().setAll(panee);
    }

    @FXML
    private void pdf(ActionEvent event) throws FileNotFoundException, DocumentException, SQLException {

        User r = new User();

        Document doc = new Document();

        PdfWriter.getInstance(doc, new FileOutputStream("users"));
        doc.open();
        Paragraph p = new Paragraph();
        p.add("liste des users"
                + "");
        doc.add(p);
        PdfPTable table = new PdfPTable(9);
        PdfPCell c = new PdfPCell(new Phrase("nom"));
        table.addCell(c);
        c = new PdfPCell(new Phrase("prenom"));
        table.addCell(c);
        c = new PdfPCell(new Phrase("email"));

        table.addCell(c);
        c = new PdfPCell(new Phrase("username"));
        table.addCell(c);

        c = new PdfPCell(new Phrase("password"));
        table.addCell(c);

        c = new PdfPCell(new Phrase("adresse"));
        table.addCell(c);

        c = new PdfPCell(new Phrase("date_naissance"));
        table.addCell(c);

        c = new PdfPCell(new Phrase("num_tel"));
        table.addCell(c);

        c = new PdfPCell(new Phrase("role"));
        table.addCell(c);

        doc.add(table);

        Connection cnx = MyConnexion.getCnx();
        String query = "SELECT * FROM user";
        PreparedStatement smt = cnx.prepareStatement(query);
        ResultSet rs = smt.executeQuery();
        PdfPTable t = new PdfPTable(9);
        while (rs.next()) {
            PdfPCell c1 = new PdfPCell(new Phrase(rs.getString("nom")));
            t.addCell(c1);
            PdfPCell c2 = new PdfPCell(new Phrase(rs.getString("prenom")));
            t.addCell(c2);
            PdfPCell c3 = new PdfPCell(new Phrase(rs.getString("email")));
            t.addCell(c3);
            PdfPCell c4 = new PdfPCell(new Phrase(rs.getString("username")));
            t.addCell(c4);

            PdfPCell c5 = new PdfPCell(new Phrase(rs.getString("password")));

            t.addCell(c5);

            PdfPCell c6 = new PdfPCell(new Phrase(rs.getString("adresse")));
            t.addCell(c6);

            PdfPCell c7 = new PdfPCell(new Phrase(rs.getDate("date_naissance").toString()));
            t.addCell(c7);

            PdfPCell c8 = new PdfPCell(new Phrase(rs.getInt("num_tel")));
            t.addCell(c8);

            PdfPCell c9 = new PdfPCell(new Phrase(rs.getString("role")));
            t.addCell(c9);

        }
        doc.add(t);
        doc.close();
        
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle("pdf generated");
        tray.setMessage("pdf generated");
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(1000));
    }

}
