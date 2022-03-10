/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.CompagnieModel;
import Services.CompagnieService;
import Tools.DBConnexion;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCombination;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Anis
 */
public class GestionCompagnieController implements Initializable {

    @FXML
    private TableView<CompagnieModel> TCompagnie;
    @FXML
    private TableColumn<CompagnieModel, String> NomCom;
    @FXML
    private TableColumn<CompagnieModel, String> Link;
    @FXML
    private TableColumn<CompagnieModel, String> Pays;
    @FXML
    private TableColumn<CompagnieModel, Integer> NumTel;
    @FXML
    private TableColumn<CompagnieModel, String> Siege;
    @FXML
    private TableColumn<CompagnieModel, String> AeBase;
    @FXML
    private TableColumn<CompagnieModel, Integer> NumP;
    @FXML
    private TableColumn<CompagnieModel, String> Description;
    ObservableList<CompagnieModel> compagnieList = FXCollections.observableArrayList();
    @FXML
    private TextField CI;
    @FXML
    private TextField NM;
    @FXML
    private TextField SW;
    @FXML
    private ChoiceBox<String> PS;
    @FXML
    private TextField NT;
    @FXML
    private TextField SG;
    @FXML
    private TextField ADB;
    @FXML
    private TextField NP;
    @FXML
    private TextArea DS;
    @FXML
    private Button btnSaveCompagnie;
    @FXML
    private Button BtnEditCompagnie;
    @FXML
    private Button btneupdateCompagnie1;
    private String[] Payss = {"Tunisia", "Algeria", "Morraco", "France", "Germany", "Italy", "Spain", "USA", "Brazil", "Argentina", "KSA", "England", "Canada"};
    Connection cnx;
    private Label Err;
    private Label Err1;
    @FXML
    private TextField recherchetxt;

    public GestionCompagnieController() {

        cnx = DBConnexion.getInstance().getCnx();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PS.getItems().addAll(Payss);

        CompagnieService CS = new CompagnieService();
        List<CompagnieModel> C = CS.afficherCompagnie();
        String res = "";
        if (!C.isEmpty()) {
            for (int i = 0; i < C.size(); i++) {
                compagnieList.add(C.get(i));
                System.out.println(C.get(i));
            }

            NomCom.setCellValueFactory(new PropertyValueFactory<>("NomCom"));
            Link.setCellValueFactory(new PropertyValueFactory<>("Link"));
            Pays.setCellValueFactory(new PropertyValueFactory<>("Pays"));
            NumTel.setCellValueFactory(new PropertyValueFactory<>("Number"));
            Siege.setCellValueFactory(new PropertyValueFactory<>("Siege"));
            AeBase.setCellValueFactory(new PropertyValueFactory<>("AeBase"));
            NumP.setCellValueFactory(new PropertyValueFactory<>("PassagerNum"));
            Description.setCellValueFactory(new PropertyValueFactory<>("Description"));

            TCompagnie.setItems(compagnieList);
        }
    }

    public void loadCompagnie() {
        NomCom.setCellValueFactory(new PropertyValueFactory<>("NomCom"));
        Link.setCellValueFactory(new PropertyValueFactory<>("Link"));
        Pays.setCellValueFactory(new PropertyValueFactory<>("Pays"));
        NumTel.setCellValueFactory(new PropertyValueFactory<>("Number"));
        Siege.setCellValueFactory(new PropertyValueFactory<>("Siege"));
        AeBase.setCellValueFactory(new PropertyValueFactory<>("AeBase"));
        NumP.setCellValueFactory(new PropertyValueFactory<>("PassagerNum"));
        Description.setCellValueFactory(new PropertyValueFactory<>("Description"));

    }

    @FXML
    private void saveCompagnie(ActionEvent event) {
        try {
            Integer.parseInt(NT.getText());
        } catch (NumberFormatException e) {
            Err.setVisible(true);
        }
        try {
            Integer.parseInt(NP.getText());
        } catch (NumberFormatException e) {
            Err1.setVisible(true);
        }
        StringBuilder errors = new StringBuilder();

        if (CI.getText().trim().isEmpty()) {
            errors.append("Ajouter un code  compagnie\n");
        }
        if (NM.getText().trim().isEmpty()) {
            errors.append("Ajouter une nom de compagnie\n");
        }
        if (NT.getText().trim().isEmpty()) {
            errors.append("Ajouter un numero de compagnie\n");
        }
        if (SW.getText().trim().isEmpty()) {
            errors.append("Ajouter un site de compagnie\n");
        }
        if (SG.getText().trim().isEmpty()) {
            errors.append("Ajouter un siege de compagnie\n");
        }
        if (ADB.getText().trim().isEmpty()) {
            errors.append("Ajouter un aeroport de base de compagnie\n");
        }
        if (NP.getText().trim().isEmpty()) {
            errors.append("Ajouter un nombre de passager de compagnie\n");
        }
        if (DS.getText().trim().isEmpty()) {
            errors.append("Ajouter un descriptionde compagnie\n");
        }
        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errors");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        } else {

            String CoIA = CI.getText();
            String No = NM.getText();
            String Lin = SW.getText();
            String Pay = PS.getValue();
            int Nu = Integer.parseInt(NT.getText());

            String Sg = SG.getText();
            String Adb = ADB.getText();
            int Np = Integer.parseInt(NP.getText());
            String Ds = DS.getText();
            CompagnieModel C = new CompagnieModel(CoIA, No, Lin, Pay, Nu, Sg, Adb, Np, Ds);
            CompagnieService CS = new CompagnieService();
            CS.ajouterCompagnie(C);
        }
    }

    @FXML
    private void deleteCompagnie(ActionEvent event) {

        CompagnieService cs = new CompagnieService();
        CompagnieModel comp = new CompagnieModel();
        comp = TCompagnie.getSelectionModel().getSelectedItem();
        int input = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment supprimer ?",
                "Choisir une option...",
                JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
        if (input == 0) {
            cs.deleteCompagnie(comp.getCode_IATA());
            loadCompagnie();
        } else if (input == 1) {
            loadCompagnie();
        }

    }

    @FXML
    private void editCompagnie(ActionEvent event) {
        CompagnieModel C = TCompagnie.getSelectionModel().getSelectedItem();
        CI.setText(C.getCode_IATA());
        NM.setText(C.getNomCom());
        SW.setText(C.getLink());
        PS.setValue(C.getPays());
        NT.setText(String.valueOf(C.getNumber()));
        SG.setText(C.getSiege());
        ADB.setText(C.getAeBase());
        NP.setText(String.valueOf(C.getPassagerNum()));
        DS.setText(C.getDescription());
    }

    @FXML
    private void UpdateCompagnie(ActionEvent event) {

        String CoIA = CI.getText();
        String No = NM.getText();
        String Lin = SW.getText();
        String Pay = PS.getValue();
        int Nu = Integer.parseInt(NT.getText());
        String Sg = SG.getText();
        String Adb = ADB.getText();
        int Np = Integer.parseInt(NP.getText());
        String Ds = DS.getText();
        CompagnieModel C = new CompagnieModel(CoIA, No, Lin, Pay, Nu, Sg, Adb, Np, Ds);
        CompagnieService CS = new CompagnieService();
        CS.modifierCompagnie(CoIA, C);
    }

    private void goToAvCom(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AvionCompagnie.fxml"));

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
    private void Back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CompListe.fxml"));
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
    private void refreshTable() {
        compagnieList.clear();
        try {
            ;
            ResultSet rs = cnx.createStatement().executeQuery("select * from compagnie");

            while (rs.next()) {
                compagnieList.add(new CompagnieModel(
                        rs.getString("NomCom"),
                        rs.getString("Link"),
                        rs.getString("Pays"),
                        rs.getInt("Number"),
                        rs.getString("Siege"),
                        rs.getString("AeBase"),
                        rs.getInt("PassagerNum"),
                        rs.getString("Description")
                ));

            }

        } catch (SQLException ex) {
            Logger.getLogger(GestionCompagnieController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadCompagnie();
        TCompagnie.setItems(compagnieList);

    }

    @FXML
    private void pdf(ActionEvent event) throws IOException, SQLException {
        List<CompagnieModel> comp = new ArrayList<>();
        try {
            String query = "select NomCom,Description from compagnie";
            PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs = ste.executeQuery();
            String av = ("compagnie.pdf");
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(av));
            document.open();
            while (rs.next()) {
                CompagnieModel A = new CompagnieModel();
                A.setNomCom(rs.getString("NomCom"));
                A.setDescription(rs.getString("Description"));
                comp.add(A);
                Paragraph para = new Paragraph("Nom du compagnie :" + A.getNomCom() + " Description : " + A.getDescription());

                document.add(para);
            }
            document.close();
            System.out.println("aaaaaa");
        } catch (Exception e) {
            System.err.println(e);

        }

    }

    @FXML
    private void chercher() {
        FilteredList<CompagnieModel> filtereddata = new FilteredList<>(compagnieList, b -> true);

        recherchetxt.textProperty().addListener((observable, oldvalue, newValue) -> {
            filtereddata.setPredicate(comp -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowercasefilter = newValue.toLowerCase();
                if (comp.getCode_IATA().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (comp.getNomCom().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (comp.getLink().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (comp.getPays().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (String.valueOf(comp.getNumber()).indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (comp.getSiege().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (comp.getAeBase().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (String.valueOf(comp.getPassagerNum()).indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (comp.getDescription().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;

                } else {
                    return false;
                }

            });

        });
        //System.out.println(filtereddata);
        SortedList<CompagnieModel> sorteddata = new SortedList<>(filtereddata);
        sorteddata.comparatorProperty().bind(TCompagnie.comparatorProperty());
        TCompagnie.setItems(filtereddata);
    }
}
