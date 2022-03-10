/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;
import entities.Bagage;
import Services.BagagesCrud;
import Tools.DataSource;
import java.awt.HeadlessException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author Mortadha
 */
public class GestBagageController implements Initializable {

    @FXML
    private TableView<Bagage> tableviewbagage;
    private TextField id;
    @FXML
    private TextField pss;
    @FXML
    private TextField pm;
    @FXML
    private TextField nm;
    @FXML
    private TextField p;
    @FXML
    private TextField dm;
   @FXML
    private TextField recherchetf;
    @FXML
    private ComboBox<String> combotri;
    private DatePicker dpdate;
    ObservableList<Bagage> data=FXCollections.observableArrayList();
    BagagesCrud us =new BagagesCrud();
    @FXML
    private PreparedStatement pst = null ;
    @FXML
    private TableColumn<Bagage, String> Id;
    @FXML
    private TableColumn<Bagage, String> poids_S;
    @FXML
    private TableColumn<Bagage, String> poids_M;
    @FXML
    private TableColumn<Bagage, String> numero;
    @FXML
    private TableColumn<Bagage, String> pp;
    @FXML
    private TableColumn<Bagage, String> dimen;
    @FXML
    private TableColumn<Bagage, Date> dtt;
    long id_modif;
    String username_modif;
    @FXML
    private DatePicker dt;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          try { 
            initialiserlist();
            

        } catch (SQLException ex)
 {
            Logger.getLogger(GestBagageController.class.getName()).log(Level.SEVERE, null, ex);
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

    public void refreshlist(){
        data.clear();
        try {
            data=FXCollections.observableArrayList(us.getAllBagages());
        } catch (SQLException ex) {
            Logger.getLogger(GestBagageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Id.setCellValueFactory(new PropertyValueFactory<>("id"));
        poids_S.setCellValueFactory(new PropertyValueFactory<>("poidsS"));
        poids_M.setCellValueFactory(new PropertyValueFactory<>("poidsM"));
        numero.setCellValueFactory(new PropertyValueFactory<>("num_valise"));
        pp.setCellValueFactory(new PropertyValueFactory<>("poids"));
        dimen.setCellValueFactory(new PropertyValueFactory<>("dimension"));
        
        dtt.setCellValueFactory(new PropertyValueFactory<>("date"));
       
        tableviewbagage.setItems(data);
    }
    
    
    
   public void initialiserlist() throws SQLException{
             try {
            Connection cnx = DataSource.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM bagage");
         
            } catch (SQLException ex) {
            Logger.getLogger(GestBagageController.class.getName()).log(Level.SEVERE, null, ex);
        }
      

    }
   
   
   public Bagage gettempBagage(TableColumn.CellEditEvent edittedCell) {
        Bagage test = tableviewbagage.getSelectionModel().getSelectedItem();
        return test;
    }
   
   
   @FXML
    private void getSelected(javafx.scene.input.MouseEvent event) throws SQLException {
        
        int index = tableviewbagage.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    
            Connection cnx = DataSource.getInstance().getCnx();
            
     //ResultSet rsd =null ;
     TableColumn.CellEditEvent edittedcell = null;
        Bagage u = gettempBagage(edittedcell);
      

    pss.setText(poids_S.getCellData(index).toString());
    pm.setText(poids_M.getCellData(index).toString());
    nm.setText(numero.getCellData(index).toString());
    p.setText(pp.getCellData(index).toString());
    dm.setText(dimen.getCellData(index).toString());

  
              
  
    
    }


 
    
    
    
    @FXML
    private void ajouterBagage(ActionEvent event) {
        StringBuilder errors=new StringBuilder();
        if(pss.getText().trim().isEmpty()){
            errors.append("- Please enter a First Name\n");
        }
        
        else{
            Bagage u =new Bagage();
            
            //u.setDate_naissance(java.sql.Date.valueOf(dpdate.getValue()));
            u.setPoidsM(pm.getText());
            u.setNum_valise(Integer.parseInt(nm.getText()));
            u.setPoids(p.getText());
            u.setDimension(dm.getText());

          
            us.ajouterBagage(u);
          
            
        }
        refreshlist();
    }
    
    @FXML
    private void modifierBagages(ActionEvent event) throws SQLException {
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            
            String value0 = nm.getText();
            
            String value1 = pm.getText();
            
            String value2 = pss.getText();
            String value3 = p.getText();
            String value4= dm.getText();
    
            String sql = "update bagage set poidsM= '"+value1+"',poidsS= '"+value2+"', poids= '"+value3+"',dimension= '"+value4+"' where num_valise='"+value0+"' ";
            pst= cnx.prepareStatement(sql);
            pst.execute();
              
            JOptionPane.showMessageDialog(null, "Update");
            Id.setText("");
             data.clear();
                initialiserlist(); 
                refreshlist();
                tableviewbagage.refresh();
    nm.setText("");
    pm.setText("");
    pss.setText("");
    p.setText("");
    dm.setText("");

      
    
            
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        initialiserlist(); 
                refreshlist();
                tableviewbagage.refresh();

        
    
        
    }
/*
    @FXML
    private void modifierBagage(ActionEvent event) {
        Bagage u=new Bagage();
        StringBuilder errors=new StringBuilder();
        if(id.getText().trim().isEmpty()){
            errors.append("- Please enter a First Name\n");
        }
        
        else{
            u.setPoidsS(pss.getText());
            //u.setDate_naissance(java.sql.Date.valueOf(dpdate.getValue()));
            u.setPoidsM(pm.getText());
            u.setNum_valise(Integer.parseInt(nm.getText()));
            u.setPoids(p.getText());
            u.setDimension(dm.getText());
            //u.setDate_naissance(java.sql.Date.valueOf(dpdate.getValue()));
           
            us.modifierBagages(id_modif, u);
            
        }
        refreshlist();
        
    }*/
public void supprimer(ActionEvent event) throws SQLException {
  //viewProduit.getItems().removeAll(viewProduit.getSelectionModel().getSelectedItem());
 
   TableColumn.CellEditEvent edittedcell = null;
        Bagage u = gettempBagage(edittedcell);

        if (u != null) {

            int i = u.getId();
            BagagesCrud cat = new BagagesCrud();

            int s = cat.supprimerbagage(i);
            if (s == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Bagage supprimé");
                alert.showAndWait();
              
                data.clear();
                initialiserlist(); 
                refreshlist();
                tableviewbagage.refresh();
    pss.setText("");
    pm.setText("");
    nm.setText("");
    p.setText("");
    dm.setText("");

  
    

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
    private void trilist(ActionEvent event) throws SQLException {
        if(combotri.getValue().equals("Par num_valise")){
            ObservableList<Bagage> tri1=FXCollections.observableArrayList();
            tri1=FXCollections.observableArrayList(us.sortByNum_valise());
            tableviewbagage.setItems(tri1);
            
        }
       
    }
     /*@FXML
    private void qq(KeyEvent event) {
        try {
            BagagesCrud bc = new BagagesCrud ();
            List<Bagage> listEquipement = bc.(search.getText());
            equipementListV.clear();
            if (!listEquipement.isEmpty()) {
                for (int i = 0; i < listEquipement.size(); i++) {
                    equipementListV.add(listEquipement.get(i));
                    System.out.println(listEquipement.get(i));
                }
            }

            pn.setCellValueFactory(new PropertyValueFactory<>("pn"));
            sn.setCellValueFactory(new PropertyValueFactory<>("sn"));
            sit.setCellValueFactory(new PropertyValueFactory<>("situation"));
            Soc.setCellValueFactory(new PropertyValueFactory<>("societe"));
            mag.setCellValueFactory(new PropertyValueFactory<>("magasin"));
            dotat.setCellValueFactory(new PropertyValueFactory<>("dotat"));
            lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
            dm.setCellValueFactory(new PropertyValueFactory<>("derniermouvement"));
            pp.setCellValueFactory(new PropertyValueFactory<>("proprietaire"));
            //pp.setCellValueFactory(new PropertyValueFactory<>("position"));
            des.setCellValueFactory(new PropertyValueFactory<>("designation"));
           
            
            TableEqu.setItems(equipementListV);
            
            
            
          
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }*/

   

        /*BagagesCrud bc = new BagagesCrud();
        Bagage u = tableviewbagage.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Merci de confirmer La supression");
        alert.setHeaderText("confirmer la suppression de Bagage avec Id : "+u.getId());
        alert.setContentText("Confirmer:");

        ButtonType buttonTypeOne = new ButtonType("Confirmer");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
            bc.delete(u);
        }List<Bagage> listBagage = bc.getAllBagages();
            data.clear();
            if (!listBagage.isEmpty()) {
                for (int i = 0; i < listBagage.size(); i++) {
                    data.add(listBagage.get(i));
                }
            }
          
            pss.setText("");
            pm.setText("");
            pm.setText("");
            p.setText("");
            dm.setText("");
            
            
            tableviewbagage.setItems(data);

    }
    */
   /* public void recherche_avance(){
        FilteredList<Bagage> filtereddata=new FilteredList<>(data,b->true);
        recherchetf.textProperty().addListener((observable,oldvalue,newValue) -> {
            filtereddata.setPredicate(bagage->{
                if(newValue==null||newValue.isEmpty()){
                    return true;
                }
                String lowercasefilter=newValue.toLowerCase();
                if(bagage.getid.toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else if(bagage.getPrenom().toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else if(user.getAdresse().toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else if(user.getEmail().toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else if(String.valueOf(user.getNumTel()).indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else if(user.getRole().toString().toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else if(user.getUsername().toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else if(user.getDate_naissance().toString().toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else{
                    return false;
                }
                
            });
        });
        */
        
    
  
  
  /*
    private void delete(ActionEvent event) {
        
        int Id=tableviewbagage.getSelectionModel().getSelectedItem().getId();
        Bagage u=us.findById(id_modif);
        us.delete(u.getId());
            refreshlist();
    }*/

    @FXML
    private void imprimer(ActionEvent event) {
    }

   /* @FXML
    private void fillforum(MouseEvent event) {
        User u=tableviewuser.getSelectionModel().getSelectedItem();
        id_modif=u.getId();
        nomtf.setText(u.getNom());
        prenomtf.setText(u.getPrenom());
        emailtf.setText(u.getEmail());
        passwordpf.setText(u.getPassword());
        usernametf.setText(u.getUsername());
        addresstf.setText(u.getAdresse());
        //date to localdate
        Instant instant = Instant.ofEpochMilli(u.getDate_naissance().getTime());
        LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
        dpdate.setValue(ldt.toLocalDate());
        numerotf.setText(Integer.toString(u.getNumTel()));
        comborole.setValue(u.getRole());
        username_modif=u.getUsername();
        
        
    }*/

    @FXML
    private void fillforum(MouseEvent event) {
    }
    
    
    
    
}
