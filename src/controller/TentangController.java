/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class TentangController implements Initializable {

   
    @FXML
    private void logout(ActionEvent event) throws IOException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Apakah anda ingin Logout?");
        alert.setHeaderText(null);
        alert.setContentText("Tekan OK untuk Logout");
        Optional result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Alert keluar = new Alert(Alert.AlertType.INFORMATION);            
            keluar.setContentText("Anda memilih Logout");
            keluar.showAndWait();
            
            FXMLLoader tujuan = new FXMLLoader(getClass().getResource("/view/Beranda.fxml"));
            Parent parent = tujuan.load();            
            Scene x = new Scene(parent);            
            Stage myStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            myStage.setScene(x);
            myStage.show();
            
        } else {
            alert.close();
        }
        
    }
    @FXML
    private void exit(){
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("“Apakah Anda hendak keluar dari aplikasi?”");
        alert.setHeaderText(null);
        alert.setContentText("Tekan OK untuk keluar tekan Cancel Untuk batal");
        Optional result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Alert keluar = new Alert(Alert.AlertType.INFORMATION);             
            keluar.setContentText("Anda memilih Keluar");
            keluar.showAndWait();
            
            System.exit(0);
        } else {
            alert.close();
        }
    }
    @FXML
    void pengajuan(ActionEvent event) throws IOException{
        FXMLLoader tujuan = new FXMLLoader(getClass().getResource("/view/BerandaAdmin.fxml"));
        Parent parent = tujuan.load();            
        Scene x = new Scene(parent);            
        Stage myStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        myStage.setScene(x);
        myStage.show();
    }
    @FXML
    void about(ActionEvent event) throws IOException{
        FXMLLoader tujuan = new FXMLLoader(getClass().getResource("/view/Tentang.fxml"));
        Parent parent = tujuan.load();            
        Scene x = new Scene(parent);            
        Stage myStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        myStage.setScene(x);
        myStage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
