/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Services.BagagesCrud;
import entities.Bagage;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class BagageController implements Initializable {

    @FXML
    private TextField search;
    @FXML
    private TableView<Bagage> TableBagage;
    ObservableList<Bagage> BagageListV = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Bagage, String> id;
    @FXML
    private TableColumn<Bagage, String> num_valise;
    @FXML
    private TableColumn<Bagage, String> PoidsS;
    @FXML
    private TableColumn<Bagage, String> poidsM;
    @FXML
    private TableColumn<Bagage, String> poids;
    @FXML
    private TableColumn<Bagage, String> dimension;
    @FXML
    private TextField bagageInputid;
    @FXML
    private TextField bagageInputnum_valise;
    @FXML
    private TextField bagageInputpoidsS;
    @FXML
    private TextField bagageInputpoidsM;
    @FXML
    private TextField bagageInputpoids;
    @FXML
    private TextField bagageInputdimension;
    @FXML
    private Button btnSaveBagage;
    private DatePicker datePrise;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LocalDate now = LocalDate.now();  
            System.out.println(now);  
            datePrise.setValue(now);
             try {
            BagagesCrud Bc = new BagagesCrud  ();
            List<Bagage> ListBagage = Bc.getAllBagages();

            if (!ListBagage.isEmpty()) {
                for (int i = 0; i < ListBagage.size(); i++) {
                    BagageListV.add(ListBagage.get(i));
                }
            }
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            num_valise.setCellValueFactory(new PropertyValueFactory<>("num_valise"));
            poidsM.setCellValueFactory(new PropertyValueFactory<>("poidsM"));
            PoidsS.setCellValueFactory(new PropertyValueFactory<>("PoidsS"));
            poids.setCellValueFactory(new PropertyValueFactory<>("Poids"));
            dimension.setCellValueFactory(new PropertyValueFactory<>("dimension"));
            TableBagage.setItems(BagageListV);
    } catch (SQLException ex) {
            System.out.println(ex);
        }
    }    
            
            
            
            
          
    @FXML
    private void editBagage(ActionEvent event) {
         Bagage bc = TableBagage.getSelectionModel().getSelectedItem();
      bagageInputid.setText(String.valueOf(bc.getId()));
      bagageInputnum_valise.setText(String.valueOf(bc.getNum_valise()));
      bagageInputpoidsS.setText(bc.getPoidsS());
      bagageInputpoidsM.setText(bc.getPoidsM());
      bagageInputpoids.setText(bc.getPoids());
      bagageInputdimension.setText(bc.getDimension());
    }

    @FXML
    private void deleteBagage(ActionEvent event) {
    }

    @FXML
    private void qq(KeyEvent event) {
    }

    @FXML
    private void saveBagage(ActionEvent event) {
    }

    
}
