/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import entities.avis;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ComboBox;
import service.AvisServices;
import tools.MaConnexion;

import java.net.URL;
import java.sql.PreparedStatement;
import java.util.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;
import service.AvisServices;
/**
 * FXML Controller class
 *
 * @author AMEN
 */
public class RatingController implements Initializable {

    @FXML
    private TextField commentaire;
    @FXML
    private TextField titre;
    @FXML
    private Rating rating;
    @FXML
    private Button ajouterButton;
    @FXML
    private Button supprimerButton;
    @FXML
    private TableColumn<avis, String> titret;
    @FXML
    private TableColumn<avis, String> commentairet;

    @FXML
    private TableView<avis> table;
    
        ObservableList<avis> data=FXCollections.observableArrayList();
 
    AvisServices as =new AvisServices();






    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           AvisServices as = new AvisServices();
 
       
       titret.setCellValueFactory(new PropertyValueFactory<>("titre"));
      commentairet.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
        // TODO
    }    
    
    
    public void refreshlist(){
        data.clear();
        data=FXCollections.observableArrayList(as.afficher());

          commentairet.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
          titret.setCellValueFactory(new PropertyValueFactory<>("titre"));

        table.setItems(data);
    }
    

    public void initialiserlist() throws SQLException{
             try {
            Connection cnx = MaConnexion.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM avis");
         
            } catch (SQLException ex) {
            Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
      

    } 
    
    
    private void getSelected(javafx.scene.input.MouseEvent event) throws SQLException {
        
        int index = table.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    
            Connection cnx = MaConnexion.getInstance().getCnx();
            
     //ResultSet rsd =null ;
     TableColumn.CellEditEvent edittedcell = null;
        avis a = gettempAvis(edittedcell);
 
    titre.setText(titret.getCellData(index).toString());
    commentaire.setText(commentairet.getCellData(index).toString());

    }
    
     public avis gettempAvis(TableColumn.CellEditEvent edittedCell) {
        avis test = table.getSelectionModel().getSelectedItem();
        return test;
    }
      
    
    
    @FXML
    private void ajouterAvis(ActionEvent event) {
        
       StringBuilder errors=new StringBuilder();
        if(titre.getText().trim().isEmpty()){
            errors.append("- Please enter un Titre\n");
        }
        
        else{
            avis a =new avis();

            
            a.setTitre(titre.getText());
            a.setCommentaire(commentaire.getText());

    
            as.ajouterAvis(a);

        }
        refreshlist();
        

    }

    @FXML
     public void supprimer(ActionEvent event) throws SQLException {
        
         //viewProduit.getItems().removeAll(viewProduit.getSelectionModel().getSelectedItem());
 
   TableColumn.CellEditEvent edittedcell = null;
        avis a = gettempAvis(edittedcell);

        if (a != null) {

            String i = a.getTitre();
            AvisServices cat = new AvisServices();

            int s = cat.supprimerAvis(i);
            if (s == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Avis supprimé");
                alert.showAndWait();
              
                data.clear();
                initialiserlist(); 
                refreshlist();
                table.refresh();
    titre.setText("");
    commentaire.setText("");

         
  
    

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
    
      
    
