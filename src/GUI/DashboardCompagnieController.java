/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.AvCom;
import Tools.DBConnexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;

/**
 * FXML Controller class
 *
 * @author Anis
 */
public class DashboardCompagnieController implements Initializable {

    @FXML
    private BarChart<?, ?> ArC;
      Connection cnx;
    @FXML
    private Button BLC1;
    
 public DashboardCompagnieController() {
        
        cnx=DBConnexion.getInstance().getCnx();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private void Chart1(MouseEvent event) {
    }

          @FXML
    private void BackHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Test.fxml"));

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
    
}
