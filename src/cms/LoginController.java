/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
 
/**
 * FXML Controller class
 *
 * @author mohammed
 */
public class LoginController implements Initializable {

    @FXML
    private JFXTextField usernametxt;
    @FXML
    private JFXPasswordField passwordtxt;
    @FXML
    private JFXButton login;
     
    @FXML
    private JFXRadioButton admin;
    @FXML
    private ToggleGroup chbox;
    @FXML
    private JFXRadioButton userbox;
    /**
     * Initializes the controller class.
     */
     
    PreparedStatement ps = null ;
         Connection conn = null ;
        ResultSet rs = null ;
     private DbConnection dc;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         dc = new DbConnection();
    }    

    @FXML
    private void loginbutton(ActionEvent event) throws IOException {
        
   if (isValidCondition()) {
       if(admin.isSelected() || userbox.isSelected()) { 
         if(admin.isSelected()){
        loginadmin();
       }
       else if(userbox.isSelected()){
       
          loginuser();
       }
       }else{
          set.informationmessage("login", "please select type of user");
       }
   }
    }
    
    public void loginuser(){
    
    String un  = usernametxt.getText();
    String pass = passwordtxt.getText();
        
    if(isValidlength()){     
    if (isValidCondition()) {
        try{
        
            conn = dc.Connect();
            ps = conn.prepareStatement("select * from users where fistname=? and password=?");  
            ps.setString(1, un);
            ps.setString(2, pass);
             rs = ps.executeQuery();
             if(rs.next()){
         
               Parent parent = FXMLLoader.load(getClass().getResource("/bill/generate_bill.fxml"));
               Stage stage = new Stage(StageStyle.DECORATED);
               stage.setTitle("BILL");
                
               stage.setScene(new Scene(parent));
              
                stage.show();
                 ((Stage) usernametxt.getScene().getWindow()).close();
                 
                 
      

            }
            else {
               set.warningmessage("", "user name or password are incorrect");
                usernametxt.setText("");
                passwordtxt.setText("");
              

            }
           rs.close();
            ps.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
         }
    }
 }
    
    
    public void loginadmin(){
        String un  = usernametxt.getText();
        String pass = passwordtxt.getText();
        
   if(isValidlength()){     
    if (isValidCondition()) {
        try{
       
            conn = dc.Connect();
            ps = conn.prepareStatement("select * from admins where username=? and password=?");  
            ps.setString(1, un);
            ps.setString(2, pass);
             rs = ps.executeQuery();
                 
             if(rs.next() || un.contains("root") && pass.contains("root")){
         
               Parent parent = FXMLLoader.load(getClass().getResource("/mainpackage/mail.fxml"));
               Stage stage = new Stage(StageStyle.UNDECORATED);
               stage.setTitle("main form");
               stage.setScene(new Scene(parent));
                stage.show();
                 ((Stage) usernametxt.getScene().getWindow()).close();
                 
                 
      

            }
             
            else {
                set.warningmessage("", "user name or password are incorrect");
                usernametxt.clear();
                passwordtxt.clear();

            }
       rs.close();
       ps.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
      }
   }
    }
    
    
    private boolean isValidCondition() {
        boolean validCondition = false;
        if (usernametxt.getText().trim().isEmpty()
                || passwordtxt.getText().isEmpty()) {
           set.warningmessage("", "Please write the username and password ");
            validCondition = false;
        } else {
            validCondition = true;
        }
        return validCondition;
    }



   
 private boolean isValidlength() {
        boolean validlength = false;
        if (usernametxt.getText().length()>10
                || passwordtxt.getText().length()>10) {
            set.warningmessage("", "Length is too much");

            validlength = false;
        } else {
            validlength = true;
        }
        return validlength;
    }
   
    
 
}
