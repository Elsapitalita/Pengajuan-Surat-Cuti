/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import hibernate.entity.Pengajuan;
import org.hibernate.Query;
import org.hibernate.Session;


public class KonfirmasiController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML private TableView<Pengajuan> pengajuan_tbl;
    @FXML private TableColumn<Pengajuan,Integer> id_col;
    @FXML private TableColumn<Pengajuan,String> nama_col;
    @FXML private TableColumn<Pengajuan,String> alamat_col;
    @FXML private TableColumn<Pengajuan,String> tujuan_col;
    @FXML private TableColumn<Pengajuan,String> waktu_col;
    @FXML private TableColumn<Pengajuan,String> ket_col;
    @FXML private TableColumn<Pengajuan,String> status_col;
    
    ObservableList<Pengajuan> update = FXCollections.observableArrayList();
    
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Session ss = util.HibernateUtil.getSessionFactory().openSession();
        ss.beginTransaction();
        
        Query query = ss.createQuery("from Pengajuan");
        
        List list = query.list();
  

        
        
        
        for(Object o : list) {
            Pengajuan updatelist = (Pengajuan) o;
            update.add(new Pengajuan(updatelist.getId(),updatelist.getNama(),updatelist.getAlamat(),updatelist.getTujuan(),updatelist.getWaktu(),updatelist.getKeterangan(),updatelist.getStatus()));

        }
        
         //Table
        this.pengajuan_tbl.setItems(update);
        this.id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.nama_col.setCellValueFactory(new PropertyValueFactory<>("nama"));
        this.alamat_col.setCellValueFactory(new PropertyValueFactory<>("alamat"));
        this.tujuan_col.setCellValueFactory(new PropertyValueFactory<>("tujuan"));
        this.waktu_col.setCellValueFactory(new PropertyValueFactory<>("waktu"));
        this.ket_col.setCellValueFactory(new PropertyValueFactory<>("keterangan"));
        this.status_col.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        ss.getTransaction().commit();
        
        ss.close();
        
        
    }    
    
}
