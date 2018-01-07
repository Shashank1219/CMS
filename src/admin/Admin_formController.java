/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import cms.DbConnection;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.sun.org.apache.bcel.internal.generic.AALOAD;

import java.io.FileInputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 */
public class Admin_formController implements Initializable {

    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private ComboBox<String> comadmins;

    String selectedUser = null;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    private DbConnection dc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc = new DbConnection();
        setvalue();

    }

    public void setvalue() {
        try {
            conn = dc.Connect();
            pst = conn.prepareStatement("select username from admins");
            rs = pst.executeQuery();

            while (rs.next()) {

                comadmins.getItems().removeAll(rs.getString(1));
                comadmins.getItems().addAll(rs.getString(1));

            }

            pst.close();
            rs.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    private void save(ActionEvent event) throws SQLException {
        String un = username.getText();
        String pass = password.getText();
        try {
            conn = dc.Connect();
            pst = conn.prepareStatement("insert into admins values(?,?)");
            pst.setString(1, un);
            pst.setString(2, pass);

            int i = pst.executeUpdate();

            if (i > 0) {

                //   Image im = new Image("/images/com.png") ;
                Notifications n = Notifications.create()
                        .title("New Admin is saved")
                        .text("it is saved to admin table")
                        .graphic(null) //new ImageView(im)

                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_RIGHT);

                n.darkStyle();
                n.showInformation();
                username.clear();
                password.clear();
            } else {
                JOptionPane.showMessageDialog(null, "Data is not saved");
            }

            pst.close();
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            conn.close();
        }

        setvalue();
    }

    @FXML
    private void update(ActionEvent event)  {
        String un = username.getText();
        String passs = password.getText();

        try {
            conn = dc.Connect();

            pst = conn.prepareStatement("update admins set password=? where username=?");
            pst.setString(1, passs);
            pst.setString(2, un);

            int i = pst.executeUpdate();
            if (i > 0) {
                Notifications n = Notifications.create()
                        .title(" Admin is updated")
                        .text("it is updated to admin table")
                        .graphic(null)
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_RIGHT);

                n.darkStyle();
                n.showInformation();
               
            } else {
                JOptionPane.showMessageDialog(null, "not updated");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        } 
        username.clear();
        password.clear();

        setvalue();
        comadmins.setValue(null);
        
    }

    @FXML
    private void delete(ActionEvent event) throws SQLException {

        conn = dc.Connect();
        String unn = username.getText();

        try {

            pst = conn.prepareStatement("delete from admins where username=? ");
            pst.setString(1, unn);

            int i = pst.executeUpdate();
            if (i > 0) {
                Notifications n = Notifications.create()
                        .title("Admin is deleted")
                        .text("it is deleted from admin")
                        .graphic(null)
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_RIGHT);

                n.darkStyle();
                n.showInformation();

            } else {
                JOptionPane.showMessageDialog(null, "data is not deleted");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            conn.close();
        }
        username.clear();
        password.clear();
        comadmins.setValue(null);
        //comadmins.getItems().removeAll();   
        setvalue();

    }


    @FXML
    private void enableclicked(MouseEvent event) {
        username.clear();
        password.clear();
        username.setDisable(false);
        username.setEditable(true);
    }

   

   
    
    

    @FXML
    private void selectuser(ActionEvent event) {
        
        username.setDisable(true);
        String u = comadmins.getSelectionModel().getSelectedItem();
        try {
            conn = dc.Connect();
            pst = conn.prepareStatement("select * from admins where username=?");
            pst.setString(1, u);
            rs = pst.executeQuery();
            while (rs.next()) {
                String add1 = rs.getString("username");
                username.setText(add1);

                String add2 = rs.getString("password");
                password.setText(add2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Admin_formController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        
    }

   
    

    
}
