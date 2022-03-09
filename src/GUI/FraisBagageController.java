/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.FraisBagageCrud;
import Tools.MyConnexion;
import Entities.FraisBagage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class FraisBagageController implements Initializable {

    @FXML
    private TableView<?> tableviewfrais;
    @FXML
    private TableColumn<?, ?> id_b;
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
    @FXML
    private ComboBox<?> combotri;
    @FXML
    private DatePicker dpdate;
    ObservableList<FraisBagage> dd =FXCollections.observableArrayList();
    FraisBagageCrud us =new FraisBagageCrud();
     
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
    private TableColumn<?, ?> date_col;
    @FXML
    private Button fbb;
    @FXML
    private Button btnSavePEC;
    
  String path;
    File selectedFile;
    @FXML
    private Button mcc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

            try { 
            initialiserlist();
            

        } catch (SQLException ex)

      
      {
            Logger.getLogger(FraisBagageController.class.getName()).log(Level.SEVERE, null, ex);
        }
            refreshlist();
        
    }  
    public void refreshlist(){
        dd.clear();
        try {
            dd=FXCollections.observableArrayList(us.getAllFraisBagage());
        } catch (SQLException ex) {
            Logger.getLogger(FraisBagageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        id_b.setCellValueFactory(new PropertyValueFactory<>("id"));
        df.setCellValueFactory(new PropertyValueFactory<>("Dimension"));
        tb.setCellValueFactory(new PropertyValueFactory<>("Tarifs de base"));
        tc.setCellValueFactory(new PropertyValueFactory<>("Tarifs de confort"));
        pf.setCellValueFactory(new PropertyValueFactory<>("poids"));
        mt.setCellValueFactory(new PropertyValueFactory<>("Montant"));
        //tableviewfrais.setItems(dd);

       
        //tableviewfrais.setItems(dd);
    }
     public void initialiserlist() throws SQLException{
             try {
            Connection cnx = MyConnexion.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM fraisbagage");
         
            } catch (SQLException ex) {
            Logger.getLogger(FraisBagageController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }

          public FraisBagage gettempBagage(TableColumn.CellEditEvent edittedCell) {
        FraisBagage test = (FraisBagage) tableviewfrais.getSelectionModel().getSelectedItem();
        return test;}
               

    private void getSelected(javafx.scene.input.MouseEvent event) throws SQLException {
        
        int index = tableviewfrais.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
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
    private void ajouterFraisBagagesPst(ActionEvent event) {
        
           StringBuilder errors=new StringBuilder();
        if(p_t.getText().trim().isEmpty()){
            errors.append("- Please enter un poidsS\n");
        }
        if(d_t.getText().trim().isEmpty()){
            errors.append("- Please enter un poidsM\n");
        }
        if(t_base.getText().trim().isEmpty()){
            errors.append("- Please enter un numero\n");
        }
        if(t_conf.getText().trim().isEmpty()){
            errors.append("- Please enter un poids\n");
        }
        if(mtt.getText().trim().isEmpty()){
            errors.append("- Please enter un dimension\n");
        }
       
      
        
      
        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errors");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        }
        
        
        else{
            FraisBagage u =new FraisBagage();
            
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
    
    
    private void savePEC(ActionEvent event) throws SQLException, IOException {
        
        FraisBagage fb = (FraisBagage) tableviewfrais.getSelectionModel().getSelectedItem();
        System.out.println(fb.getMontant()); 
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Merci de confirmer");
        alert.setHeaderText(
                "\nS/N de l'quipement: "+fb.getMontant());
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

    @FXML
    private void fillforum(MouseEvent event) {
    }

   
    @FXML
    private void modifierUser(ActionEvent event) {
    }

    @FXML
    private void supprimerUser(ActionEvent event) {
    }

    @FXML
    private void imprimer(ActionEvent event) {
    }

    @FXML
    private void btnSavePEC(ActionEvent event) {
    }

   
    
}
