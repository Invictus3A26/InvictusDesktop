/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Bagage;
import Services.FraisBagageCrud;
import Tools.MyConnexion;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import Entities.FraisBagage;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class FraisBagageController implements Initializable {

    @FXML
    private ComboBox<String> combotri;

    @FXML
    private TableView<FraisBagage> tableviewfrais;
    @FXML
    private TableColumn<?, ?> pf;
    @FXML
    private TableColumn<?, ?> df;
    @FXML
    private TableColumn<?, ?> tb;
    @FXML
    private TableColumn<?, ?> tc;
    @FXML
    private TableColumn<?, ?> mt;

    ObservableList<FraisBagage> data = FXCollections.observableArrayList();
    FraisBagageCrud us = new FraisBagageCrud();

    @FXML
    private TextField p_t;
    @FXML
    private TextField d_t;
    @FXML
    private TextField t_base;
    @FXML
    private TextField t_conf;
    @FXML
    private TextField mtt;
    @FXML
    private ComboBox<?> comborole;
    @FXML
    private TextField recherchetf;
    @FXML
    private Button fbb;
    @FXML
    private Button btnSavePEC;
    Notifications no;
    String erreur;
    ObservableList<String> ss = FXCollections.observableArrayList();
    String path;
    File selectedFile;
    @FXML
    private Button mcc;
    @FXML
    private ImageView kr;
    private TextField iddd;
    @FXML
    private Button btntri;
    @FXML
    private AnchorPane filterField;
    private Window primaryStage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ss.add("Par Montant");
        ss.add("Par Tarif Base");

        // TODO
        combotri.setItems(ss);
        try {
            QrCode();
            try {

                initialiserlist();

            } catch (SQLException ex) {
                Logger.getLogger(FraisBagageController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (WriterException ex) {
            Logger.getLogger(FraisBagageController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FraisBagageController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            refreshlist();
        } catch (SQLException ex) {
            Logger.getLogger(FraisBagageController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void refreshlist() throws SQLException {
        data.clear();

        data = FXCollections.observableArrayList(us.getAllFraisBagage());

        pf.setCellValueFactory(new PropertyValueFactory<>("poids"));
        df.setCellValueFactory(new PropertyValueFactory<>("Dimension"));
        tb.setCellValueFactory(new PropertyValueFactory<>("Tarifs_base"));
        tc.setCellValueFactory(new PropertyValueFactory<>("Tarifs_confort"));
        mt.setCellValueFactory(new PropertyValueFactory<>("Montant"));
        tableviewfrais.setItems(data);

        //tableviewfrais.setItems(dd);
    }

    public void initialiserlist() throws SQLException {
        try {
            Connection cnx = MyConnexion.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM FraisBagage");

        } catch (SQLException ex) {
            Logger.getLogger(FraisBagageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public FraisBagage gettempBagage(TableColumn.CellEditEvent edittedCell) {
        FraisBagage test = tableviewfrais.getSelectionModel().getSelectedItem();
        return test;
    }

    private void getSelected(javafx.scene.input.MouseEvent event) throws SQLException {

        int index = tableviewfrais.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }

        Connection cnx = MyConnexion.getInstance().getCnx();

        //ResultSet rsd =null ;
        TableColumn.CellEditEvent edittedcell = null;
        FraisBagage u = gettempBagage(edittedcell);

        p_t.setText(pf.getCellData(index).toString());
        d_t.setText(df.getCellData(index).toString());
        t_base.setText(tb.getCellData(index).toString());
        t_conf.setText(tc.getCellData(index).toString());
        mtt.setText(mt.getCellData(index).toString());

    }

    @FXML
    private void ajouterFraisBagagesPst(ActionEvent event) throws SQLException {

        StringBuilder errors = new StringBuilder();
        if (p_t.getText().trim().isEmpty()) {
            errors.append("- Please enter un poidsS\n");
        }
        if (d_t.getText().trim().isEmpty()) {
            errors.append("- Please enter un poidsM\n");
        }
        if (t_base.getText().trim().isEmpty()) {
            errors.append("- Please enter un numero\n");
        }
        if (t_conf.getText().trim().isEmpty()) {
            errors.append("- Please enter un poids\n");
        }
        if (mtt.getText().trim().isEmpty()) {
            errors.append("- Please enter un dimension\n");
        }

        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errors");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        } else {
            FraisBagage u = new FraisBagage();

            //u.setDate_naissance(java.sql.Date.valueOf(dpdate.getValue()));
            u.setPoids(p_t.getText());
            u.setDimension(d_t.getText());
            u.setTarifs_base(Integer.parseInt(t_base.getText()));
            u.setTarifs_confort(Integer.parseInt(t_conf.getText()));
            u.setMontant(Integer.parseInt(mtt.getText()));

            us.ajouterFraisBagagesPst(u);

        }
        refreshlist();
    }

    private void modifierBagages(ActionEvent event) {
        FraisBagage fb = (FraisBagage) tableviewfrais.getSelectionModel().getSelectedItem();

        p_t.setText(fb.getPoids());
        d_t.setText(fb.getDimension());
        t_base.setText(String.valueOf(fb.getTarifs_base()));
        t_conf.setText(String.valueOf(fb.getTarifs_confort()));
        mtt.setText(String.valueOf(fb.getMontant()));

    }

    private void editbagage(ActionEvent event) {
        long id = Long.parseLong(iddd.getText());
        String pot = p_t.getText();
        String dtt = d_t.getText();
        int tba = Integer.parseInt(t_base.getText());
        int tconf = Integer.parseInt(t_conf.getText());
        int mont = Integer.parseInt(mtt.getText());
        FraisBagage b = new FraisBagage(pot, dtt, tba, tconf, mont);
        FraisBagageCrud bc = new FraisBagageCrud();
        bc.modifierBagages(id, b);

        no = Notifications.create()
                .title("Bagage Modifié")
                .text(erreur)
                .graphic(null)
                .position(Pos.TOP_CENTER)
                .hideAfter(Duration.seconds(6));
        no.showInformation();

    }

    private void savePEC(ActionEvent event) throws SQLException, IOException {

        FraisBagage fb = (FraisBagage) tableviewfrais.getSelectionModel().getSelectedItem();
        System.out.println(fb.getMontant());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Merci de confirmer");
        alert.setHeaderText(
                "\nS/N de l'quipement: " + fb.getMontant());
        alert.setContentText("Confirmer:");

        ButtonType buttonTypeOne = new ButtonType("Confirmer");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

    }

    /* private void ajouterFraisBagage(ActionEvent event) {
        StringBuilder errors=new StringBuilder();
        if(p_t.getText().trim().isEmpty()){
            errors.append("- Please enter a First Name\n");
        }
        
        else{
            FraisBagage u =new FraisBagage();
            
            //u.setDate_naissance(java.sql.Date.valueOf(dpdate.getValue()));
            u.setDimension(d_t.getText());
            u.setTarifs_base(Integer.parseInt(t_base.getText()));
            u.setTarifs_confort(Integer.parseInt(t_conf.getText()));

            u.setMontant(Integer.parseInt(mtt.getText()));

          
            us.ajouterFraisBagage(u);
          
            
        }
        refreshlist();
    }
     */
    @FXML
    private void Convert(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CurrencyConverter.java"));
        Parent root = loader.load();
        mcc.getScene().setRoot(root);
    }

    private void QrCode() throws WriterException, SQLException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        List<FraisBagage> FraisBagage = us.getAllFraisBagage();
        String Bagage = String.valueOf(FraisBagage);
        int width = 300;
        int height = 300;

        BufferedImage bufferedImage = null;
        BitMatrix bytMatrix = qrCodeWriter.encode(Bagage, BarcodeFormat.QR_CODE, width, height);
        bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        bufferedImage.createGraphics();
        Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, width, height);
        graphics.setColor(Color.black);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (bytMatrix.get(i, j)) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }
        kr.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
    }

    @FXML
    private void trilist(ActionEvent event) throws SQLException {
        if (combotri.getValue().equals("Par Montant")) {
            ObservableList<FraisBagage> tri1 = FXCollections.observableArrayList();
            tri1 = FXCollections.observableArrayList(us.sortByMontant());
            tableviewfrais.setItems(tri1);

        } else if (combotri.getValue().equals("Par Tarif Base")) {
            ObservableList<FraisBagage> tri2 = FXCollections.observableArrayList();
            tri2 = FXCollections.observableArrayList(us.sortByTarifs_base());
            tableviewfrais.setItems(tri2);
        }

    }

    @FXML
    private void fillforum(MouseEvent event) {
    }

    @FXML
    private void modifierUser(ActionEvent event) {
    }

    @FXML
    private void imprimer(ActionEvent event) {

        System.out.println("To Printer!");
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            Window primaryStage = null;
            job.showPrintDialog(this.primaryStage);

            Node root = this.tableviewfrais;
            job.printPage(root);
            job.endJob();
        }

    }

    @FXML
    private void btnSavePEC(ActionEvent event) {
    }

    @FXML
    private void delete(ActionEvent event) throws SQLException {
        int p = JOptionPane.showConfirmDialog(null, "Do you really want to Delete", "Delete", JOptionPane.YES_NO_OPTION);
        if (p == 0) {
            TableColumn.CellEditEvent edittedcell = null;
            FraisBagage u = gettempBagage(edittedcell);

            if (u != null) {

                int i = u.getId();
                FraisBagageCrud cat = new FraisBagageCrud();

                int s = cat.supprimer(i);
                if (s == 1) {
                    data.clear();
                    initialiserlist();
                    refreshlist();
                    tableviewfrais.refresh();
                    p_t.setText("");
                    d_t.setText("");
                    t_base.setText("");
                    t_conf.setText("");
                    mtt.setText("");

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

    private void recherche(KeyEvent event) throws SQLException {

        FraisBagageCrud re = new FraisBagageCrud();
        List<FraisBagage> results = new ArrayList<>();
        results = re.getAllFraisBagage();
        FilteredList<FraisBagage> filteredData = new FilteredList<>(data, b -> true);
        FraisBagage b = new FraisBagage();

        recherchetf.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(FraisBagage -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (FraisBagage.getPoids().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (String.valueOf(b.getMontant()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (String.valueOf(b.getDimension()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false; // Does not match.
                }
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<FraisBagage> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tableviewfrais.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tableviewfrais.setItems(sortedData);

    }

}
