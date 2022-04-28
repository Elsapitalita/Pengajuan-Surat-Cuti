/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import hibernate.entity.Akun;
import org.hibernate.Query;
import org.hibernate.Session;


public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField username_tf;
    @FXML
    private TextField password_tf;
    
    
    @FXML
    private void login(ActionEvent event) throws IOException {
        Session ss = util.HibernateUtil.getSessionFactory().openSession();
        ss.beginTransaction();
        
        Query query = ss.createQuery("from Akun");
        List list = query.list();
        
        
        Akun admin = new Akun();
        for(Object o : list){
            Akun dataAdmin = (Akun)o;
            if(dataAdmin.getUsername().equals(username_tf.getText())){
                admin= dataAdmin;
            }
            
            
        }
        
        try{
            if(admin.getPassword().equals(password_tf.getText())){
                FXMLLoader pindah = new FXMLLoader(getClass().getResource("/view/BerandaAdmin.fxml"));
                Parent parent = pindah.load();            
                Scene x = new Scene(parent);            
                Stage myStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                myStage.setScene(x);

                myStage.show();
                
            }else{
                JOptionPane.showMessageDialog(null, "Password yang anda masukkan salah ");
                password_tf.setText("");
                username_tf.setText("");
            }
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(null, "Akun tidak terdaftar");
            password_tf.setText("");
            username_tf.setText("");
        }   
        
        
    }
    @FXML
    private void reset(ActionEvent event) throws IOException {
        password_tf.setText("");
        username_tf.setText("");
        
        FXMLLoader pindah = new FXMLLoader(getClass().getResource("/view/Beranda.fxml"));
        Parent parent = pindah.load();            
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
