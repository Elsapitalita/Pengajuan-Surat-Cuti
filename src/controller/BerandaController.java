/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import hibernate.entity.Pengajuan;
import org.hibernate.Query;
import org.hibernate.Session;

public class BerandaController implements Initializable {

    /**
     * Initializes the controller class.
     * 
     */
    
    @FXML
    private TextField nama_tf;

    @FXML
    private TextField alamat_tf;

    @FXML
    private TextField tujuan_tf;

    @FXML
    private TextArea keterangan_ta;
    
    
    @FXML
    private void login(ActionEvent event) throws IOException{
        FXMLLoader tujuan = new FXMLLoader(getClass().getResource("/view/Login.fxml"));
        Parent parent = tujuan.load();            
        Scene x = new Scene(parent);            
        Stage myStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        myStage.setScene(x);
        myStage.show();
        
    }
    
    @FXML
    private void exit(){
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("“Apakah Anda hendak keluar dari Aplikasi?”");
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
        FXMLLoader tujuan = new FXMLLoader(getClass().getResource("/view/Beranda.fxml"));
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
    @FXML
    void konfirmasi(ActionEvent event) throws IOException{
        FXMLLoader tujuan = new FXMLLoader(getClass().getResource("/view/Konfirmasi.fxml"));
        Parent parent = tujuan.load();            
        Scene x = new Scene(parent);            
        Stage myStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        myStage.setScene(x);
        myStage.show();
    }
    @FXML
    void kirim(ActionEvent event) throws IOException{
        Session ss = util.HibernateUtil.getSessionFactory().openSession();
        ss.beginTransaction();
        
        
        Query query = ss.createQuery("from Pengajuan");

        List list = query.list();
        int id = list.size()+1;
        Pengajuan pengajuan = new Pengajuan();
        pengajuan.setId(id);
        
        pengajuan.setNama(this.nama_tf.getText());
        pengajuan.setAlamat(this.alamat_tf.getText());
        pengajuan.setTujuan(this.tujuan_tf.getText());
        pengajuan.setKeterangan(this.keterangan_ta.getText());
        pengajuan.setStatus("Menunggu");
        
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy  HH:mm");  
        Date sekarang = new Date();
        pengajuan.setWaktu(dateFormat.format(sekarang));
        
        
        ss.save(pengajuan);
        JOptionPane.showMessageDialog(null, "Data anda terkirim dan sedang di proses.");
        ss.getTransaction().commit();
        
        ss.close();
                
        
        pengajuan(event);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
