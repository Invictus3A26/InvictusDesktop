/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Article;
import Services.ArticleService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class FXMLarticleadminController implements Initializable {

    @FXML
    private ListView<Article> listviewarticle;
    @FXML
    private TextField tfrecherche;
    ArticleService as=new ArticleService();
    ObservableList<Article> data=FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        refreshlist();
       recherche_avance();
    }    

    @FXML
    private void supprimer(ActionEvent event) {
        as.supprimer(listviewarticle.getSelectionModel().getSelectedItem().getIdAticle());
        refreshlist();
    }
    public void refreshlist(){
        data=FXCollections.observableArrayList(as.afficher());
        listviewarticle.setItems(data);
    }
    public void recherche_avance(){
        FilteredList<Article> filtereddata=new FilteredList<>(data,b->true);
        tfrecherche.textProperty().addListener((observable,oldvalue,newValue) -> {
            filtereddata.setPredicate(article->{
                if(newValue==null||newValue.isEmpty()){
                    return true;
                }
                String lowercasefilter=newValue.toLowerCase();
                if(article.getContenu().toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else if(article.getDescription().toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else if(article.getTitre().toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else if(String.valueOf(article.getNbrLike()).toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
     
                else{
                    return false;
                }
                
            });
        });
        
        listviewarticle.setItems(filtereddata);
    }
}
