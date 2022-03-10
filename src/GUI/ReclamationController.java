/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXTextField;
import entities.Reclamation;
import service.ReclamationServices;
import java.awt.HeadlessException;
import static java.awt.SystemColor.window;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.util.Collections.list;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.DatePicker;
import tools.MaConnexion;
import java.sql.Statement;
import static java.util.Collections.list;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

import java.net.URL;
import java.util.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Calendar;
import static java.util.Collections.list;
import java.util.List;
import java.util.Observable;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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

    @FXML
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

    @FXML
    private DatePicker dpdate;
    private TextField recherchetf;
    
    @FXML
    private TextField filterField;


    @FXML
    private ComboBox<String> combotri;
    
  
    ObservableList<Reclamation> data=FXCollections.observableArrayList();
    
    ReclamationServices rs =new ReclamationServices();
    @FXML
    private TableColumn<Reclamation, Integer> id1;
    @FXML
    private TableColumn<Reclamation, Integer> nb;
    @FXML
    private TableColumn<Reclamation, Integer> t;
    @FXML
    private TableColumn<Reclamation, String> n;
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
    
    long id_modif;
    String nom_modif;
    String erreur;
    int selectedCompteID;
    static Reclamation selectionedReclamation;
    java.sql.Timestamp timestamp = null;
    private PreparedStatement pst = null ;
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



    /**
     * Initializes the controller class.
     */
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        
   
        
          try { 
            initialiserlist();

            

        } catch (SQLException ex)
 {
            Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }refreshlist();
        
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
    if (index <= -1){
    
        return;
    }
    
            Connection cnx = MaConnexion.getInstance().getCnx();
            
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
    
    
    
    
    public void refreshlist(){
        data.clear();
        data=FXCollections.observableArrayList(rs.afficherReclamation());
        id1.setCellValueFactory(new PropertyValueFactory<>("id"));
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
    
        public void initialiserlist() throws SQLException{
             try {
            Connection cnx = MaConnexion.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM reclamation");
         
            } catch (SQLException ex) {
            Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
      

    }
    
    
    @FXML
    private void ajouterReclamation(ActionEvent event) {
        StringBuilder errors=new StringBuilder();
        if(id_nom.getText().trim().isEmpty()){
            errors.append("- Please enter un Nom\n");
        }
        
        else{
            Reclamation r =new Reclamation();

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

        }
        refreshlist();
    }

    @FXML
    private void modifierReclamation(ActionEvent event) throws SQLException {
        try {
            Connection cnx = MaConnexion.getInstance().getCnx();
            String value0 = id_nbr.getText();
            String value1 = id_tel.getText();
            
            String value2 = id_nom.getText();
            
            String value3 = id_prenom.getText();
            String value4 = id_email.getText();
            String value5= id_description.getText();
            String value6 = id_type.getText();
            String value7= id_etat.getText();
            
           
            
            
            String sql = "update reclamation set tel= '"+value1+"',nom= '"+value2+"', prenom= '"+value3+"',email= '"+value4+"',description= '"+value5+"',type= '"+value6+"',etat= '"+value7+"' where nbr='"+value0+"' ";
            pst= cnx.prepareStatement(sql);
            pst.execute();
              
            JOptionPane.showMessageDialog(null, "Update");
            id1.setText("");
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
    
    
  
      
  public Reclamation gettempReclamation(TableColumn.CellEditEvent edittedCell) {
        Reclamation test = tableviewreclamation.getSelectionModel().getSelectedItem();
        return test;
    }
    
    @FXML
   public void supprimer(ActionEvent event) throws SQLException {
  //viewProduit.getItems().removeAll(viewProduit.getSelectionModel().getSelectedItem());
 
   TableColumn.CellEditEvent edittedcell = null;
        Reclamation r = gettempReclamation(edittedcell);

        if (r != null) {

            int i = r.getId();
            ReclamationServices cat = new ReclamationServices();

            int s = cat.supprimerreclamation(i);
            if (s == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Reclamation supprimé");
                alert.showAndWait();
              
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
         
  
    

            }

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Selection un champ SVP");
            alert.showAndWait();
        } 


    }
   
   
  
   
   @FXML
     public void chercher(){
    ReclamationServices rs= new ReclamationServices() ;
    List<Reclamation>results = new ArrayList<>();
    results = rs.afficherReclamation();
     FilteredList<Reclamation> filteredData = new FilteredList<>(data , b -> true);
		Reclamation r = new Reclamation();
		
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(reclamation -> {
				
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (reclamation.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (String.valueOf(r.getNbr()).indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(r.getTel()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
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
            ReclamationServices rs = new ReclamationServices ();
            List<Reclamation> listReclamation = rs.ReclamationParId(search.getText());
            data.clear();
            if (!listReclamation.isEmpty()) {
                for (int i = 0; i < listReclamation.size(); i++) {
                    data.add(listReclamation.get(i));
                    System.out.println(listReclamation.get(i));
                }
            }

         id1.setCellValueFactory(new PropertyValueFactory<>("id"));
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
    
    
    
    
   
   
   
   

    
    private void trilist(ActionEvent event) {
        if(combotri.getValue().equals("Par nom")){
            ObservableList<Reclamation> tri1=FXCollections.observableArrayList();
            tri1=FXCollections.observableArrayList(rs.sortByNom());
            tableviewreclamation.setItems(tri1);
            
        }
        else if(combotri.getValue().equals("Par date")){
            ObservableList<Reclamation> tri2=FXCollections.observableArrayList();
            tri2=FXCollections.observableArrayList(rs.sortByDate());
            tableviewreclamation.setItems(tri2);
        }
    }


    
 
}
