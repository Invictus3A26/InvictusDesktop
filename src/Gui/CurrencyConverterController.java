/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class CurrencyConverterController implements Initializable {

    @FXML
    private AnchorPane AN4;
    @FXML
    private TextField txtamount;
    @FXML
    private Button BTajouclient;
    @FXML
    private ComboBox<?> txtfrom;
    @FXML
    private ComboBox<?> txtto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjoutClient(ActionEvent event) {
    }
     /*private void ConvertActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
        Double convert;
        Double amount = Double.parseDouble(txtamount.getText());
        
        if(txtfrom.getSelectedItem().toString()=="USD" && txtto.getSelectedItem().toString()=="BD TK"){
            convert = amount * 84.05;
            JOptionPane.showMessageDialog(this, "The amount is: "+convert.toString()+" BD TK");
        }
        else if(txtfrom.getSelectedItem().toString()=="USD" && txtto.getSelectedItem().toString()=="Indian Rupees"){
            convert = amount * 75.05;
            JOptionPane.showMessageDialog(this, "The amount is: "+convert.toString()+" INR");
        }
        else if(txtfrom.getSelectedItem().toString()=="EUR" && txtto.getSelectedItem().toString()=="BD TK"){
            convert = amount * 100.03;
            JOptionPane.showMessageDialog(this, "The amount is: "+convert.toString()+" BD TK");
        }
         else if(txtfrom.getSelectedItem().toString()=="EUR" && txtto.getSelectedItem().toString()=="Indian Rupees"){
            convert = amount * 95.2;
            JOptionPane.showMessageDialog(this, "The amount is: "+convert.toString()+" INR");
        }
        
    }                                       

    
     * @param args the command line arguments
     
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CurrencyConverter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CurrencyConverter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CurrencyConverter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CurrencyConverter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form *//*
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CurrencyConverter().setVisible(true);
            }
        });
    }*/

}
    

