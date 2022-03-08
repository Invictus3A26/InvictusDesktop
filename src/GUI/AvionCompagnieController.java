/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.AvCom;
import Entities.AvionModel;
import Entities.CompagnieModel;
import Services.AvionService;
import Services.CompagnieService;
import Tools.DBConnexion;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.orsoncharts.demo.PieChart3D1;
import com.orsoncharts.demo.fx.OrsonChartsFXDemo;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCombination;
import javafx.stage.Screen;
import javafx.stage.Stage;
import com.orsoncharts.demo.fx.PieChart3DFXDemo1;
import static com.orsoncharts.demo.fx.PieChart3DFXDemo1.createDemoNode;
import java.awt.Component;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.PreparedStatement;
import javafx.scene.layout.StackPane;
import org.jfree.chart.JFreeChart;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.shape.Arc;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.jdbc.JDBCCategoryDataset;
import jxl.*;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.jfree.*;
/**
 * FXML Controller class
 *
 * @author Anis
 */
public class AvionCompagnieController implements Initializable {
        @FXML
        private Button BLC;
        @FXML
        ObservableList<CompagnieModel> compagnieList = FXCollections.observableArrayList();
        @FXML
        private BarChart <?,?> ArC;
        @FXML
        private CategoryAxis x;
       @FXML
       private CategoryAxis y;
  Connection cnx;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
    }
     public AvionCompagnieController() {
        
        cnx=DBConnexion.getInstance().getCnx();
    }
 @FXML
  private void Chart1(ActionEvent event) throws IOException, SQLException {
      XYChart.Series set1 = new XYChart.Series<>();
      try{
       
      String query="select NomCom,PassagerNum from compagnie";
      PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs= ste.executeQuery();
            while (rs.next()) {
                String    A = rs.getString("NomCom");
            int i = rs.getInt("PassagerNum");
            set1.getData().add(new XYChart.Data(A,i));
                    ArC.getData().add(set1);
            }
          
          
  
           
      }
      catch (Exception e){
          JOptionPane.showMessageDialog(null, e);
      }
    }
    @FXML
    private void Chart2(ActionEvent event) throws IOException, SQLException {
      try{
       AvCom ac = new AvCom();
       DefaultPieDataset pieDataset = new DefaultPieDataset();
      String query="select NomC,numbAvC from avcom";
         PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs= ste.executeQuery();
            while (rs.next()) {
          String    A = rs.getString("NomC");
            int i = rs.getInt("numbAvC");
            
               
              pieDataset.setValue(A,i);
            }

                  JFreeChart chart = ChartFactory.createPieChart3D("Nombre D'avion pour chaque compagnie", pieDataset,true,true,true);
                   PiePlot3D p = (PiePlot3D) chart.getPlot();
                      //       p.setForegroundAlpha(Component.TOP_ALIGNMENT);
          ChartFrame frame = new ChartFrame("Pie chart",chart);
          frame.setVisible(true);
          frame.setSize(500,600);
            }
            
      catch (Exception e){
          JOptionPane.showMessageDialog(null, e);
      }
    }
    @FXML
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
    private void pdf (ActionEvent event) throws IOException, SQLException{
        List<CompagnieModel> comp = new ArrayList<>();
         try {
 String query="select NomCom,Description from compagnie";
         PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs= ste.executeQuery();           
     String av = ("C:\\Users\\Anis\\Desktop\\avion.pdf");
     Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(av));
        document.open();
           while(rs.next()){
              CompagnieModel A = new CompagnieModel();
                A.setNomCom(rs.getString("NomCom"));
                A.setDescription(rs.getString("Description"));
                comp.add(A);
            Paragraph para = new Paragraph("Nom du compagnie :" +A.getNomCom()+" Description : " +A.getDescription());
  
           
            document.add(para);
           }
        document.close();
            System.out.println("aaaaaa");
    }
         
        
    catch (Exception e){
    System.err.println(e);
    
    }
        
    }
    

         @FXML
    private void goToAv(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Avion.fxml"));
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

}
