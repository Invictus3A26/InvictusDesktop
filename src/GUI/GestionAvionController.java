/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.AvionModel;
import Entities.CompagnieModel;
import Services.AvionService;
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCombination;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Anis
 */
public class GestionAvionController implements Initializable {

     @FXML
    private TableView<AvionModel> Avion;
    Connection cnx;
     ObservableList<AvionModel> avionlist = FXCollections.observableArrayList();

  @FXML
    private TextField CA;
    @FXML
    private ChoiceBox<String> TA;
    @FXML
    private TextField MD;
    @FXML
    private TextField PN;
      @FXML
    private TextField CC;
    @FXML
   private TableColumn<AvionModel,String> TypeA; 
    @FXML
    private TableColumn<AvionModel,String > Model;
    @FXML
    private TableColumn<AvionModel,String >PassagerN;
     private String[] Type = {"Cargo", "Passager", "Affaire"};
    @FXML
    private Button btnSaveCompagnie;
    @FXML
    private Button btneupdateCompagnie1;
    @FXML
    private Button BtnEditCompagnie;
    @FXML
    private Button Refresh;
    @FXML
    private TextField recherchetxt;
    @FXML
    private Label Err;
  
   public GestionAvionController() {
        
        cnx=DBConnexion.getInstance().getCnx();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                  TA.getItems().addAll(Type);
            AvionService CS= new AvionService();
            List<AvionModel> A = CS.afficherAvion();
            String res ="";
         if (!A.isEmpty()) {
                for (int i = 0; i < A.size(); i++) {
                    avionlist.add(A.get(i));
                    System.out.println(A.get(i));
                }             
            
        //    CodeA.setCellValueFactory(new PropertyValueFactory<>("CodeAvion"));
            TypeA.setCellValueFactory(new PropertyValueFactory<>("TypeA"));
            Model.setCellValueFactory(new PropertyValueFactory<>("Model"));
            PassagerN.setCellValueFactory(new PropertyValueFactory<>("PassagerN"));
          //CodeC.setCellValueFactory(new PropertyValueFactory<>("CodeC"));
         
            Avion.setItems(avionlist);
        }
        }
    @FXML
    private void Back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AvionListe.fxml"));
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
    private void saveAvion(ActionEvent event)  {
   try {
            Integer.parseInt(PN.getText());
        } catch (NumberFormatException e) {
            Err.setVisible(true);
        }
        StringBuilder errors=new StringBuilder();

        if(CA.getText().trim().isEmpty()){
            errors.append("Ajouter un code  Avion\n");
        }
        if(TA.getValue().trim().isEmpty()){
            errors.append("Ajouter Type Avion\n");
        }
        if(MD.getText().trim().isEmpty()){
            errors.append("Ajouter Model Avion\n");
        }
  if(PN.getText().trim().isEmpty()){
            errors.append("Ajouter Nombres de places\n");
        }
  if(CC.getText().trim().isEmpty()){
            errors.append("Ajouter un Code compagnie\n");
        }
        String CoIA = CA.getText();
         String No = TA.getValue();
         String Lin = MD.getText();
         int Nu = Integer.parseInt(PN.getText());
         String Pay = CC.getText();
          AvionModel A = new AvionModel(CoIA,No,Lin,Nu,Pay);
         AvionService CS =new AvionService();
         CS.ajouterAvion(A);    
    }
    @FXML
    private void deleteAvion(ActionEvent event) {
         AvionModel A = Avion.getSelectionModel().getSelectedItem();
                                AvionService CS = new AvionService();
                                CS.deleteAvion(A.getCodeAvion());
    }
@FXML
    private void selectAvion(ActionEvent event) {
         AvionModel A = Avion.getSelectionModel().getSelectedItem();
      CA.setText(A.getCodeAvion());
      TA.setValue(A.getTypeA());
    MD.setText(A.getModel());
     PN.setText(String.valueOf(A.getPassagerN()));
       CC.setText(A.getCodeC());
    }
      public void loadAvion() {
        TypeA.setCellValueFactory(new PropertyValueFactory<>("TypeA"));
        Model.setCellValueFactory(new PropertyValueFactory<>("Model"));
        PassagerN.setCellValueFactory(new PropertyValueFactory<>("PassagerN"));
      

    }
    @FXML
    private void refreshTable() {
        avionlist.clear();
        try {
            ;
            ResultSet rs = cnx.createStatement().executeQuery("select * from compagnie");

            while (rs.next()) {
                avionlist.add(new AvionModel(
                        rs.getString("TypeA"),
                        rs.getString("Model"),
                       
                        rs.getInt("PassagerN")
                     
                )  );

            }

        } catch (SQLException ex) {
            Logger.getLogger(GestionCompagnieController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadAvion();
        Avion.setItems(avionlist);

    } 
      @FXML
    private void UpdateAvion(ActionEvent event)  {
   
        String CoIA = CA.getText();
         String No = TA.getValue();
         String Lin = MD.getText();
         int Nu = Integer.parseInt(PN.getText());
         String Pay = CC.getText();
         AvionModel A = new AvionModel(CoIA,No,Lin,Nu,Pay);
         AvionService CS =new AvionService();
         CS.modifierAvion(CoIA,A);    
    }

    private void goToAvCom(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AvionCompagnie.fxml"));
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
    private void goToCom(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Compagnie.fxml"));
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
    private void chercher() {
  FilteredList<AvionModel> filtereddata = new FilteredList<>(avionlist, b -> true);

        recherchetxt.textProperty().addListener((observable, oldvalue, newValue) -> {
            filtereddata.setPredicate(av -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowercasefilter = newValue.toLowerCase();
                if (av.getCodeAvion().toLowerCase().indexOf(lowercasefilter) != -1)  {
                    return true;
                } else if (av.getTypeA().toLowerCase().indexOf(lowercasefilter) != -1)  {
                    return true;
                } else if (av.getModel().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (String.valueOf(av.getPassagerN()).indexOf(lowercasefilter) != -1) {
                    return true;
                } else  if(av.getCodeC().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
               
                }
                else {
                    return false;
                }

            });

        });
        //System.out.println(filtereddata);
        SortedList<AvionModel> sorteddata = new SortedList<>(filtereddata);
        sorteddata.comparatorProperty().bind(Avion.comparatorProperty());
        Avion.setItems(filtereddata);
    }

     @FXML
    private void pdf (ActionEvent event) throws IOException, SQLException{
        List<AvionModel> avion = new ArrayList<>();
         try {
 String query="select * from avion";
         PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs= ste.executeQuery();           
     String av = ("C:\\Users\\Anis\\Desktop\\avion.pdf");
     Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(av));
        document.open();
           while(rs.next()){
              AvionModel A = new AvionModel();
                A.setTypeA(rs.getString("TypeA"));
                A.setModel(rs.getString("Model"));
                A.setPassagerN(rs.getInt("PassagerN"));
                
                avion.add(A);
            Paragraph para = new Paragraph("Type D'avion :" +A.getTypeA()+" Model : " +A.getModel()+" Nombres des Places : " +A.getPassagerN());
  
           
            document.add(para);
           }
        document.close();
            System.out.println("aaaaaa");
    }
             
    catch (Exception e){
    System.err.println(e);
    
    }
        
    }
}
