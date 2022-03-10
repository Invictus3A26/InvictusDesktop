/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import Entities.Bagage;
import Services.BagagesCrud;
import Services.GeneratePDF;
import Tools.MyConnexion;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import Entities.FraisBagage;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.print.PrinterJob;
import javafx.scene.Node;
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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;


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
    private TextField filterField;
  
    @FXML
    private ComboBox<String> combotri;
    private DatePicker dpdate;
    ObservableList<Bagage> data=FXCollections.observableArrayList();
    BagagesCrud us =new BagagesCrud();
    GeneratePDF pd =new GeneratePDF();
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
    private TextField txtsearch;
    @FXML
    private Button AN1Signup;
    @FXML
    private Button mod;
    @FXML
    private TextField idd;
    private Stage primaryStage;
    @FXML
    private Button impr;
    Notifications no;
    String erreur;
    String ss;

    @FXML
    private Button pdff;
    @FXML
    private ImageView qrr;
   


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         

        try {   QrCode();
            initialiserlist();
            

        } catch (SQLException ex)
 {
            Logger.getLogger(GestBagageController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WriterException ex) {
            Logger.getLogger(GestBagageController.class.getName()).log(Level.SEVERE, null, ex);
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
            Connection cnx = MyConnexion.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM Bagage");
         
            } catch (SQLException ex) {
            Logger.getLogger(GestBagageController.class.getName()).log(Level.SEVERE, null, ex);
        }
      

    }
   
   
   public Bagage gettempBagage(TableColumn.CellEditEvent edittedCell) {
        Bagage test = tableviewbagage.getSelectionModel().getSelectedItem();
        return test;
    }
   
   
    private void getSelected(javafx.scene.input.MouseEvent event) throws SQLException {
        
        int index = tableviewbagage.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    
            Connection cnx = MyConnexion.getInstance().getCnx();
            
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
        if(poids_S.getText().trim().isEmpty()){
            errors.append("- Please enter un poidsS\n");
        }
        if(poids_M.getText().trim().isEmpty()){
            errors.append("- Please enter un poidsM\n");
        }
        if(numero.getText().trim().isEmpty()){
            errors.append("- Please enter un numero\n");
        }
        if(pp.getText().trim().isEmpty()){
            errors.append("- Please enter un poids\n");
        }
        if(dimen.getText().trim().isEmpty()){
            errors.append("- Please enter un dimension\n");
        }
       
      
        
      
        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errors");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        }
        
        
        else{
            Bagage u =new Bagage();
            
            //u.setDate_naissance(java.sql.Date.valueOf(dpdate.getValue()));
            u.setPoidsS(pss.getText());
            u.setPoidsM(pm.getText());
            u.setNum_valise(Integer.parseInt(nm.getText()));
            u.setPoids(p.getText());
            u.setDimension(dm.getText());

          
            us.ajouterBagage(u);
                  Notifications notificationBuilder = Notifications.create()
            .title("Succes").text("Ton Bagage est ajouté !!").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();

        }           
        
        
        refreshlist();
        
    }
    @FXML
    private void modifierBagages(ActionEvent event) {
         Bagage b = tableviewbagage.getSelectionModel().getSelectedItem();
      
      pss.setText(b.getPoidsS());
      pm.setText(b.getPoidsM());
      nm.setText(String.valueOf(b.getNum_valise()));
      p.setText(b.getPoids());
      dm.setText(b.getDimension());
      
      
    }
     @FXML
    private void editbagage(ActionEvent event)  {
        long id = Long.parseLong(idd.getText());
        String ps = pss.getText();
         String pmm = pm.getText();
         int num = Integer.parseInt(nm.getText());
         String poi =p.getText();
         String dim = dm.getText();
         Bagage b = new Bagage(ps,pmm,num,poi,dim);
         BagagesCrud bc =new BagagesCrud();
         bc.modifierBagages(id,b);
         
         no = Notifications.create()
                    .title("Bagage Modifié")
                    .text(erreur)
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(6));
            no.showInformation();    
         
    }
    /*
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
    */
    private void chercherBagage(ActionEvent event) {
        ObservableList<String> items =FXCollections.observableArrayList();
        List<Bagage> listBagage;
        BagagesCrud bs =new BagagesCrud();
        String tchoix=txtsearch.getText();
        try{
            int nchoix = Integer.parseInt(tchoix);
            listBagage = bs.chercherBagage(nchoix);
        } catch (NumberFormatException e) {
            listBagage = bs.chercherBagage(tchoix);
        }
        for(Bagage a : listBagage) {
            String ch = a.toString();
            items.add(ch);
        }
        tableviewbagage.setItems(data);
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
    @FXML
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
        no = Notifications.create()
                    .title("Bagage Supprimé")
                    .text(erreur)
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(6));
            no.showInformation();    


    }



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
     public void chercher() throws SQLException{
    BagagesCrud re= new BagagesCrud() ;
    List<Bagage>results = new ArrayList<>();
    results = re.getAllBagages();
     FilteredList<Bagage> filteredData = new FilteredList<>(data , b -> true);
		Bagage b = new Bagage();
		
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(bagage -> {
				
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (bagage.getPoids().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (String.valueOf(b.getId()).indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(b.getNum_valise()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Bagage> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tableviewbagage.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tableviewbagage.setItems(sortedData);
               
        
    }
     @FXML
    private void btnImprS(ActionEvent event) 
    {
        System.out.println("To Printer!");
         PrinterJob job = PrinterJob.createPrinterJob();
           if(job != null)
           {
    Window primaryStage = null;
           job.showPrintDialog(this.primaryStage); 
            
    Node root = this.tableviewbagage;
           job.printPage(root);
           job.endJob();
            }
    }
     @FXML
    private void pdf(ActionEvent event) throws FileNotFoundException, DocumentException, SQLException {
 
       
                  Bagage r =new Bagage();
        
              
                Document doc = new Document();

                PdfWriter.getInstance(doc, new FileOutputStream("C:\\generate_pdf\\test.pdf"));
                doc.open();
                            Paragraph p =new Paragraph();
                p.add("liste des Bagages"
                        + "");
             doc.add(p);
                 PdfPTable table = new PdfPTable(5);
            PdfPCell c = new PdfPCell(new Phrase("PoidsS"));
            table.addCell(c);
           c = new PdfPCell(new Phrase("PoidsM"));
              
            table.addCell(c);
              c = new PdfPCell(new Phrase("Num_valise"));
            table.addCell(c);
              
             c = new PdfPCell(new Phrase("Poids"));
            table.addCell(c);
            
            c = new PdfPCell(new Phrase("Dimension"));
            table.addCell(c);
            
           
            
            
            doc.add(table);
             
    
             MyConnexion conn = new MyConnexion();
            Connection connection = conn.getCnx();
            String query = "SELECT * FROM Bagage ";
           PreparedStatement smt = connection.prepareStatement(query);
                ResultSet rs= smt.executeQuery();
                PdfPTable t = new PdfPTable(5);
               while(rs.next()){ 
           PdfPCell  c1 = new PdfPCell(new Phrase(rs.getString("PoidsS")));
            t.addCell(c1);
             PdfPCell  c2 = new PdfPCell(new Phrase(rs.getString("PoidsM")));
            t.addCell(c2);
             PdfPCell  c3 = new PdfPCell(new Phrase(rs.getString("Num_valise")));
            t.addCell(c3);
             PdfPCell  c4 = new PdfPCell(new Phrase(rs.getString("Poids")));
                          t.addCell(c4);

             
                       PdfPCell  c5 = new PdfPCell(new Phrase(rs.getString("Dimension")));

            t.addCell(c5);
            
             }
               doc.add(t);
                doc.close();
               
       
           
                 
          
             
               
    }
    @FXML
    private void QrCode() throws WriterException, SQLException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        List<Bagage> bagage = us.getAllBagages();
        String Bagage = String.valueOf(bagage);
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
        qrr.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
    }


    @FXML
    private void fillforum(MouseEvent event) {
    }

    /*@FXML
    private void ConvertirEnPdf(ActionEvent event) {
         
         pdff.con
    }*/

    
    
    
}
