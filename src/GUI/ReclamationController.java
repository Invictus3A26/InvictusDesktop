/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import chat.ClientController;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import Entities.Reclamation;
import Entities.Role;
import Entities.User;
import Services.ReclamationServices;
import Services.UserService;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

import java.net.URL;
import java.util.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.print.PrinterJob;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import Tools.Mailapi;
import Tools.MyConnexion;

/**
 * FXML Controller class
 *
 * @author AMEN
 */
public class ReclamationController implements Initializable {

    @FXML
    private TableView<Reclamation> tableviewreclamation;

    @FXML
    private TextField id_nom;
    @FXML
    private TextField id_nbr;

    private TextField search;
    @FXML
    private TextField id_tel;
    @FXML
    private TextField id_prenom;
    @FXML
    private TextField id_email;
    @FXML
    private TextField id_description;
    @FXML
    private TextField id_type;
    @FXML
    private TextField id_etat;
    private AnchorPane chartNode;
    public static int numeroPDF = 0;
    Document doc = new Document();

    public static long id_user;

    @FXML
    private DatePicker dpdate;
    private Stage primaryStage;
    private TextField recherchetf;
    @FXML
    private AnchorPane recpane;
    @FXML
    private ComboBox<String> combotri;
    @FXML
    private Button statBtn;

    @FXML
    private TextField filterField;
    private ImageView screenshotView;
    File selectedFile;
    Notifications no;
    String erreur;
    @FXML
    private Button btntri;

    ObservableList<Reclamation> data = FXCollections.observableArrayList();
    ObservableList<String> ss = FXCollections.observableArrayList();

    ReclamationServices rs = new ReclamationServices();
    private TableColumn<Reclamation, Integer> id1;
    @FXML
    private TableColumn<Reclamation, Integer> nb;
    @FXML
    private TableColumn<Reclamation, Integer> t;
    @FXML
    private TableColumn<Reclamation, String> p;
    @FXML
    private TableColumn<Reclamation, String> e;
    @FXML
    private TableColumn<Reclamation, String> d;
    @FXML
    private TableColumn<Reclamation, String> ty;
    @FXML
    private TableColumn<Reclamation, String> et;
    @FXML
    private TableColumn<Reclamation, Date> da;
    @FXML
    private TableColumn<Reclamation, String> n;

    long id_modif;
    String nom_modif;

    int selectedCompteID;
    static Reclamation selectionedReclamation;
    java.sql.Timestamp timestamp = null;
    private PreparedStatement pst = null;
    private ImageView nomCheckMark;
    private ImageView telCheckMark;
    private ImageView prenomCheckMark;
    private ImageView emailCheckMark;
    private JFXTimePicker TempsDispoTimePicker;
    private JFXDatePicker DateDispoTimePicker;
    private ImageView dateCheckMark;
    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label date;
    @FXML
    private Label tel;
    @FXML
    private Label nbr;
    @FXML
    private Label email;
    @FXML
    private Label desc;
    @FXML
    private Label type;
    @FXML
    private Label etat;
    @FXML
    private Button r;
    @FXML
    private Button rrrr;
    @FXML
    private Button BtnChat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ss.add("Par Nombre");
        ss.add("Par date");
        ss.add("Par nom");

        // TODO
        combotri.setItems(ss);

        try {
            initialiserlist();

        } catch (SQLException ex) {
            Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        refreshlist();

        /*try {
    Connection cnx = MaConnexion.getInstance().getCnx();
    rs = cnx.createStatement().executeQuery("SELECT id FROM produit");
    while(rs.next())
    id1.getItems().addAll(rs.getInt("id"));
    } catch (SQLException ex)
    {
    Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
    }*/
    }

    @FXML
    private void getSelected(javafx.scene.input.MouseEvent event) throws SQLException {

        int index = tableviewreclamation.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }

        Connection cnx = MyConnexion.getInstance().getCnx();

        //ResultSet rsd =null ;
        TableColumn.CellEditEvent edittedcell = null;
        Reclamation r = gettempReclamation(edittedcell);
        //date to localdate
        Instant instant = Instant.ofEpochMilli(r.getDate_reclamation().getTime());
        LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);

        id_nbr.setText(nb.getCellData(index).toString());
        id_tel.setText(t.getCellData(index).toString());
        id_nom.setText(n.getCellData(index).toString());
        id_prenom.setText(p.getCellData(index).toString());
        id_email.setText(e.getCellData(index).toString());
        id_description.setText(d.getCellData(index).toString());
        id_type.setText(ty.getCellData(index).toString());
        id_etat.setText(et.getCellData(index).toString());

        dpdate.setValue(ldt.toLocalDate());

    }

    public void refreshlist() {
        data.clear();
        data = FXCollections.observableArrayList(rs.afficherReclamation());

        nb.setCellValueFactory(new PropertyValueFactory<>("nbr"));
        t.setCellValueFactory(new PropertyValueFactory<>("tel"));
        n.setCellValueFactory(new PropertyValueFactory<>("nom"));
        p.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        e.setCellValueFactory(new PropertyValueFactory<>("email"));
        d.setCellValueFactory(new PropertyValueFactory<>("description"));
        ty.setCellValueFactory(new PropertyValueFactory<>("type"));
        et.setCellValueFactory(new PropertyValueFactory<>("etat"));
        da.setCellValueFactory(new PropertyValueFactory<>("date_reclamation"));
        tableviewreclamation.setItems(data);
    }

    public void initialiserlist() throws SQLException {
        try {
            Connection cnx = MyConnexion.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM reclamation");

        } catch (SQLException ex) {
            Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private Boolean testMail() {

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (id_email.getText() == null) {
            return false;
        }

        if (pat.matcher(id_email.getText()).matches() == false) {
            emailCheckMark.setImage(new Image("Images/erreurcheckMark.png"));
//                erreur = erreur + ("Veuillez verifier votre adresse email\n");
            return false;
//            

        } else {
            emailCheckMark.setImage(new Image("Images/checkMark.png"));
        }
        return true;

    }

    //   
    private Boolean testTel() {
        if (id_tel.getText().trim().length() == 8) {
            int nbChar = 0;
            for (int i = 1; i < id_tel.getText().trim().length(); i++) {
                char ch = id_tel.getText().charAt(i);
                if (Character.isLetter(ch)) {
                    nbChar++;
                }
            }

            if (nbChar == 0) {
                telCheckMark.setImage(new Image("Images/checkMark.png"));
                return true;
            } else {
                telCheckMark.setImage(new Image("Images/erreurcheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
                return false;

            }
        } else if (id_email.getText().trim().length() != 8) {
//            erreur = erreur + ("Il faut saisir 8 chiffres dans le numéro de telephone\n");
            telCheckMark.setImage(new Image("Images/erreurcheckMark.png"));
            return false;
        }

        return true;

    }

    private Boolean testDate() {
        java.sql.Timestamp today_date = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        if (TempsDispoTimePicker.getValue() != null && DateDispoTimePicker.getValue() != null) {
            timestamp = java.sql.Timestamp.valueOf(DateDispoTimePicker.getValue() + " " + TempsDispoTimePicker.getValue() + ":00");
            if (timestamp.compareTo(today_date) > 0) {
                dateCheckMark.setImage(new Image("Images/checkMark.png"));
                return true;
            } else {
                dateCheckMark.setImage(new Image("Images/erreurcheckMark.png"));
            }
            return false;
        } else if (TempsDispoTimePicker.getValue() == null && DateDispoTimePicker.getValue() == null) {
            return true;
        } else if (TempsDispoTimePicker.getValue() != null && DateDispoTimePicker.getValue() == null) {
            dateCheckMark.setImage(new Image("Images/erreurcheckMark.png"));
        } else if (TempsDispoTimePicker.getValue() == null && DateDispoTimePicker.getValue() != null) {
            dateCheckMark.setImage(new Image("Images/erreurcheckMark.png"));
        }
        return false;

    }

    private Boolean testNom() {
        int nbNonChar = 0;
        for (int i = 1; i < id_nom.getText().trim().length(); i++) {
            char ch = id_nom.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && id_nom.getText().trim().length() >= 3) {
            nomCheckMark.setImage(new Image("Images/checkMark.png"));
            return true;
        } else {
            nomCheckMark.setImage(new Image("Images/erreurcheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }

    }

    private Boolean testPrenom() {
        int nbNonChar = 0;
        for (int i = 1; i < id_prenom.getText().trim().length(); i++) {
            char ch = id_prenom.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && id_prenom.getText().trim().length() >= 3) {
            prenomCheckMark.setImage(new Image("Images/checkMark.png"));
            return true;
        } else {
            prenomCheckMark.setImage(new Image("Images/erreurcheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }

    }

    private Boolean testSaisie() {
        erreur = "";
        if (!testMail()) {
            erreur = erreur + ("Veuillez verifier que votre adresse email est de la forme : ***@***.** \n");
        }
        if (!testTel()) {
            erreur = erreur + ("Telephone doit avoir 8 chiffres et ne doit pas contenir des caracteres \n");
        }
        if (!testDate()) {
            erreur = erreur + ("Veuillez verifier que vous avez choisi une date superieur a la date courante \n");
        }

        if (!testNom()) {
            erreur = erreur + ("Veuillez verifier votre Nom: seulement des caractères et de nombre >= 3 \n");
        }
        if (!testPrenom()) {
            erreur = erreur + ("Veuillez verifier votre Prenom: seulement des caractères et de nombre >= 3");
        }

        if (!testMail() || !testTel() || !testDate() || !testNom() || !testPrenom()) {
            no = Notifications.create()
                    .title("Erreur de format")
                    .text(erreur)
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(6));
            no.showInformation();
        }

        return testMail() && testTel() && testDate() && testNom() && testPrenom();
    }

    @FXML
    private void ajouterReclamation(ActionEvent event) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);

        int p = JOptionPane.showConfirmDialog(null, "Do you really want to Add", "Add", JOptionPane.YES_NO_OPTION);
        if (p == 0) {
            StringBuilder errors = new StringBuilder();
            if (id_nom.getText().trim().isEmpty()) {
                errors.append("- Please enter un Nom\n");
            }
            if (id_prenom.getText().trim().isEmpty()) {
                errors.append("- Please enter un Prenom\n");
            }
            if (id_email.getText().trim().isEmpty()) {
                errors.append("- Please enter un Email\n");
            }
            if (pat.matcher(id_email.getText()).matches() == false) {

                errors.append("Veuillez verifier que votre adresse email est de la forme : ***@***.** \n");

            }

            if (id_description.getText().trim().isEmpty()) {
                errors.append("- Please enter un Description\n");
            }
            if (id_type.getText().trim().isEmpty()) {
                errors.append("- Please enter un Type\n");
            }
            if (id_etat.getText().trim().isEmpty()) {
                errors.append("- Please enter un Etat\n");
            }
            if (id_tel.getText().trim().length() != 8) {
                errors.append("Telephone doit avoir 8 chiffres et ne doit pas contenir des caracteres \n");
            }

            if (dpdate.getValue() == null) {
                errors.append("- Please enter a Date\n");
            }
            try {
                Integer.parseInt(id_tel.getText());
            } catch (NumberFormatException e) {
                errors.append("- Please enter a valid number\n");
            }

            if (errors.length() > 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Errors");
                alert.setContentText(errors.toString());
                alert.showAndWait();
            } else {
                Reclamation r = new Reclamation();

                r.setNbr(Integer.parseInt(id_nbr.getText()));
                r.setTel(Integer.parseInt(id_tel.getText()));
                r.setNom(id_nom.getText());
                r.setPrenom(id_prenom.getText());
                r.setEmail(id_email.getText());
                r.setDescription(id_description.getText());
                r.setType(id_type.getText());
                r.setEtat(id_etat.getText());
                r.setDate_reclamation(java.sql.Date.valueOf(dpdate.getValue()));
                rs.ajouterReclamation(r);
                no = Notifications.create()
                        .title("Reclamation Ajouter")
                        .text(erreur)
                        .graphic(null)
                        .position(Pos.TOP_CENTER)
                        .hideAfter(Duration.seconds(6));
                no.showInformation();

            }
            refreshlist();
        }
    }

    @FXML
    private void modifierReclamation(ActionEvent event) throws SQLException {
        int p = JOptionPane.showConfirmDialog(null, "Do you really want to Modify", "Modify", JOptionPane.YES_NO_OPTION);
        if (p == 0) {
            try {
                Connection cnx = MyConnexion.getInstance().getCnx();
                String value0 = id_nbr.getText();
                String value1 = id_tel.getText();

                String value2 = id_nom.getText();

                String value3 = id_prenom.getText();
                String value4 = id_email.getText();
                String value5 = id_description.getText();
                String value6 = id_type.getText();
                String value7 = id_etat.getText();

                String sql = "update reclamation set tel= '" + value1 + "',nom= '" + value2 + "', prenom= '" + value3 + "',email= '" + value4 + "',description= '" + value5 + "',type= '" + value6 + "',etat= '" + value7 + "' where nbr='" + value0 + "' ";
                pst = cnx.prepareStatement(sql);
                pst.execute();

                no = Notifications.create()
                        .title("Reclamation Modifier")
                        .text(erreur)
                        .graphic(null)
                        .position(Pos.TOP_CENTER)
                        .hideAfter(Duration.seconds(6));
                no.showInformation();

                data.clear();
                initialiserlist();
                refreshlist();
                tableviewreclamation.refresh();
                id_nbr.setText("");
                id_tel.setText("");
                id_nom.setText("");
                id_prenom.setText("");
                id_email.setText("");
                id_description.setText("");
                id_type.setText("");
                id_etat.setText("");

            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }

            initialiserlist();
            refreshlist();
            tableviewreclamation.refresh();

        }

    }

    public Reclamation gettempReclamation(TableColumn.CellEditEvent edittedCell) {
        Reclamation test = tableviewreclamation.getSelectionModel().getSelectedItem();
        return test;
    }

    @FXML
    public void supprimer(ActionEvent event) throws SQLException {
        //viewProduit.getItems().removeAll(viewProduit.getSelectionModel().getSelectedItem());

        int p = JOptionPane.showConfirmDialog(null, "Do you really want to Delete", "Delete", JOptionPane.YES_NO_OPTION);
        if (p == 0) {
            TableColumn.CellEditEvent edittedcell = null;
            Reclamation r = gettempReclamation(edittedcell);

            if (r != null) {

                int i = r.getId();
                ReclamationServices cat = new ReclamationServices();

                int s = cat.supprimerreclamation(i);
                if (s == 1) {
                    data.clear();
                    initialiserlist();
                    refreshlist();
                    tableviewreclamation.refresh();
                    id_nbr.setText("");
                    id_tel.setText("");
                    id_nom.setText("");
                    id_prenom.setText("");
                    id_email.setText("");
                    id_description.setText("");
                    id_type.setText("");
                    id_etat.setText("");

                    no = Notifications.create()
                            .title("Reclamation Supprimer")
                            .text(erreur)
                            .graphic(null)
                            .position(Pos.TOP_CENTER)
                            .hideAfter(Duration.seconds(6));
                    no.showInformation();

                }

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Selection un champ SVP");
                alert.showAndWait();
            }

        }

    }

    @FXML
    public void chercher() {
        ReclamationServices rs = new ReclamationServices();
        List<Reclamation> results = new ArrayList<>();
        results = rs.afficherReclamation();
        FilteredList<Reclamation> filteredData = new FilteredList<>(data, b -> true);
        Reclamation r = new Reclamation();

        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(reclamation -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (reclamation.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (String.valueOf(r.getNbr()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (String.valueOf(r.getTel()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false; // Does not match.
                }
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Reclamation> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tableviewreclamation.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tableviewreclamation.setItems(sortedData);

    }

    private void qq(KeyEvent event) {
        try {
            ReclamationServices rs = new ReclamationServices();
            List<Reclamation> listReclamation = rs.ReclamationParId(search.getText());
            data.clear();
            if (!listReclamation.isEmpty()) {
                for (int i = 0; i < listReclamation.size(); i++) {
                    data.add(listReclamation.get(i));
                    System.out.println(listReclamation.get(i));
                }
            }

            nb.setCellValueFactory(new PropertyValueFactory<>("nbr"));
            t.setCellValueFactory(new PropertyValueFactory<>("tel"));
            n.setCellValueFactory(new PropertyValueFactory<>("nom"));
            p.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            e.setCellValueFactory(new PropertyValueFactory<>("email"));
            d.setCellValueFactory(new PropertyValueFactory<>("description"));
            ty.setCellValueFactory(new PropertyValueFactory<>("type"));
            et.setCellValueFactory(new PropertyValueFactory<>("etat"));
            da.setCellValueFactory(new PropertyValueFactory<>("date_reclamation"));
            tableviewreclamation.setItems(data);

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void print(ActionEvent event) {

        System.out.println("To Printer!");
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            Window primaryStage = null;
            job.showPrintDialog(this.primaryStage);

            Node root = this.tableviewreclamation;
            job.printPage(root);
            job.endJob();
        }

    }

    @FXML
    private void afficherStatistique(ActionEvent event) {

        try {
            Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stageclose.close();
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/statistics.fxml"));
            Stage stage = new Stage();

            Scene scene = new Scene(root);

            stage.setTitle("article");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void trilist(ActionEvent event) {
        if (combotri.getValue().equals("Par nom")) {
            ObservableList<Reclamation> tri3 = FXCollections.observableArrayList();
            tri3 = FXCollections.observableArrayList(rs.sortByNom());
            tableviewreclamation.setItems(tri3);

        } else if (combotri.getValue().equals("Par date")) {
            ObservableList<Reclamation> tri2 = FXCollections.observableArrayList();
            tri2 = FXCollections.observableArrayList(rs.sortByDate());
            tableviewreclamation.setItems(tri2);
        } else if (combotri.getValue().equals("Par Nombre")) {
            ObservableList<Reclamation> tri1 = FXCollections.observableArrayList();
            tri1 = FXCollections.observableArrayList(rs.sortByNbr());
            tableviewreclamation.setItems(tri1);
        }
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        UserService us = new UserService();
        User u = us.findById(Id.user);
        if (u.getRole().toString().equals("ADMIN")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLDashboard.fxml"));
            recpane.getChildren().setAll(pane);
        } else {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLProfileEmploye.fxml"));
            recpane.getChildren().setAll(pane);
        }
    }

    @FXML
    private void msg(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/chat/Client.fxml"));
        Parent root;
        try {

            root = loader.load();
            ClientController c1 = loader.getController();
            BtnChat.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void pdf(ActionEvent event) throws FileNotFoundException, DocumentException, SQLException {

        Reclamation r = new Reclamation();

        Document doc = new Document();

        PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\AMEN\\Desktop\\ListeReclamation.pdf"));
        doc.open();
        Paragraph p = new Paragraph();
        p.add("liste des Reclamations"
                + "");
        doc.add(p);
        PdfPTable table = new PdfPTable(9);
        PdfPCell c = new PdfPCell(new Phrase("Nbr"));
        table.addCell(c);
        c = new PdfPCell(new Phrase("Tel"));
        table.addCell(c);
        c = new PdfPCell(new Phrase("Nom"));

        table.addCell(c);
        c = new PdfPCell(new Phrase("Prenom"));
        table.addCell(c);

        c = new PdfPCell(new Phrase("Email"));
        table.addCell(c);

        c = new PdfPCell(new Phrase("Description"));
        table.addCell(c);

        c = new PdfPCell(new Phrase("Type"));
        table.addCell(c);

        c = new PdfPCell(new Phrase("Etat"));
        table.addCell(c);

        c = new PdfPCell(new Phrase("Date"));
        table.addCell(c);

        doc.add(table);

        Connection cnx = MyConnexion.getInstance().getCnx();
        String query = "SELECT * FROM reclamation ";
        PreparedStatement smt = cnx.prepareStatement(query);
        ResultSet rs = smt.executeQuery();
        PdfPTable t = new PdfPTable(9);
        while (rs.next()) {
            PdfPCell c1 = new PdfPCell(new Phrase(rs.getString("nbr")));
            t.addCell(c1);
            PdfPCell c2 = new PdfPCell(new Phrase(rs.getString("tel")));
            t.addCell(c2);
            PdfPCell c3 = new PdfPCell(new Phrase(rs.getString("nom")));
            t.addCell(c3);
            PdfPCell c4 = new PdfPCell(new Phrase(rs.getString("prenom")));
            t.addCell(c4);

            PdfPCell c5 = new PdfPCell(new Phrase(rs.getString("email")));

            t.addCell(c5);

            PdfPCell c6 = new PdfPCell(new Phrase(rs.getString("description")));
            t.addCell(c3);

            PdfPCell c7 = new PdfPCell(new Phrase(rs.getString("type")));
            t.addCell(c3);

            PdfPCell c8 = new PdfPCell(new Phrase(rs.getString("etat")));
            t.addCell(c3);

            PdfPCell c9 = new PdfPCell(new Phrase(rs.getString("date_reclamation")));
            t.addCell(c3);

        }
        doc.add(t);
        doc.close();

    }

    @FXML
    private void mail(ActionEvent event) {
        Mailapi.send("amenallah.benkhalifa@esprit.tn", "Khalifaamen123", "amen.benkhalifaaa@gmail.com", "test", " test");
    }
}
