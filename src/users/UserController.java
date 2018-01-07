/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

import cms.DbConnection;
import cms.set;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author mohammed
 */
public class UserController implements Initializable {

    @FXML
    private JFXTextField firstname;
    @FXML
    private JFXTextField lastname;
    @FXML
    private JFXTextField password;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField phone;

    /**
     * Initializes the controller class.
     */
    
    Connection conn = null;
    ResultSet rs = null ;
    PreparedStatement pst = null ;
     private DbConnection dc;
     private ObservableList<UserDetails> data= FXCollections.observableArrayList();
    @FXML
    private TableView<UserDetails> table;
    @FXML
    private TableColumn<UserDetails, String> firstnamecol;
    @FXML
    private TableColumn<UserDetails, String> lastnamecol;
    @FXML
    private TableColumn<UserDetails, String> passcol;
    @FXML
    private TableColumn<UserDetails, String> emailcol;
    @FXML
    private TableColumn<UserDetails, String> phonecol;
    @FXML
    private TextField fname;
    @FXML
    private TextField lname;
    @FXML
    private TextField pass;
    @FXML
    private TextField em;
    @FXML
    private TextField ph;
    @FXML
    private JFXButton update;
    @FXML
    private JFXButton delete;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc = new DbConnection();
        loaddata();
        mouseclikedd();
    }    

    @FXML
    private void save(ActionEvent event) {
        
        String  fn = firstname.getText();
        String  ln = lastname.getText();
        String  pass = password.getText();
        String  mail = email.getText();
        String  pn = phone.getText();
          try {
            conn = dc.Connect();
            pst= conn.prepareStatement("insert into users values(?,?,?,?,?)");
            pst.setString(1, fn);
            pst.setString(2, ln);
            pst.setString(3, pass);
            pst.setString(4, mail);
            pst.setString(5, pn);

            int i = pst.executeUpdate();

            if(i>0){
                Notifications n = Notifications.create()
                        .title("New User is saved")
                        .text("it is saved to user table")
                        .graphic(null)
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_RIGHT);
                    
               n.darkStyle();
                n.showInformation();
            }
            else {
                JOptionPane.showMessageDialog(null, "data is not saved");
            }
        } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,ex);
        }
         firstname.setText(null);
        lastname.setText(null);
        password.setText(null);
        email.setText(null);
        phone.setText(null);
        data.clear();
        loaddata();
    }
    
    public void loaddata(){
         try {
           
              conn = dc.Connect();
          
           
             rs = conn.createStatement().executeQuery("SELECT * FROM users");
            while (rs.next()) {
                //get string from db,whichever way 
                data.add(new UserDetails(rs.getString(1), rs.getString(2) , rs.getString(3) , rs.getString(4)  , rs.getString(5)));
            }

        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, ex);
        }
        
        //Set cell value factory to tableview.
        //NB.PropertyValue Factory must be the same with the one set in model class.
        firstnamecol.setCellValueFactory(new PropertyValueFactory<>("fname"));
        lastnamecol.setCellValueFactory(new PropertyValueFactory<>("lname"));
        passcol.setCellValueFactory(new PropertyValueFactory<>("pass"));
        emailcol.setCellValueFactory(new PropertyValueFactory<>("email"));
        phonecol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        
        table.setItems(null);
        table.setItems(data);
        

    }

    @FXML
    private void updatebutton(ActionEvent event) {
        
         String fn = fname.getText();
        String ln = lname.getText();
        String passs = pass.getText();
        String emm = em.getText();
        String pn = ph.getText();

        
        try {
            conn = dc.Connect();
            pst= conn.prepareStatement("update users set fistname=? , lastname=? , password=? , email=? , phone=? where fistname=?");
            pst.setString(1, fn);
            pst.setString(2, ln);
            pst.setString(3, passs);
            pst.setString(4, emm);
            pst.setString(5, pn);
            pst.setString(6, fn);

            int i = pst.executeUpdate();
            if(i>0){
                Notifications nv = Notifications.create()
                        .title(" Update")
                        .text(" User is updated")
                        .graphic(null)
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_RIGHT);
                    
                   nv.darkStyle();
                  nv.showInformation();
                     loaddata();
         fname.setText(null);
        lname.setText(null);
        pass.setText(null);
        em.setText(null);
        ph.setText(null);
            }
            else {
                JOptionPane.showMessageDialog(null, "not updated");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
        data.clear();
        loaddata();
    }

    @FXML
    private void deletebutton(ActionEvent event) throws SQLException {
        String n = fname.getText();
        String p = ph.getText();

        
        try {
            if(set.confirm("User will be deleted")){
             conn = dc.Connect();
            pst= conn.prepareStatement("delete from users where fistname=? and phone=?");
            pst.setString(1, n);
            pst.setString(2, p);

            int i = pst.executeUpdate();
            if(i>0){
                 Notifications nv = Notifications.create()
                        .title(" Delete")
                        .text(" User is deleted")
                        .graphic(null)
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_RIGHT);
                    
               nv.darkStyle();
                nv.showInformation();
                 loaddata();
         fname.setText(null);
        lname.setText(null);
        pass.setText(null);
        em.setText(null);
        ph.setText(null);
            }
            else {
                JOptionPane.showMessageDialog(null, "data is not deleted");
            }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex);
        }finally{
                conn.close();
            }
       data.clear();
        loaddata();
    }

  
      
    ///////////////////////////////////////////////  /      
            
     public static class UserDetails{
    private final StringProperty fname;
    private final StringProperty lname;
    private final StringProperty pass;
    private final StringProperty email;
    private final StringProperty phone;
    
     public UserDetails(String fnamee, String lnamee , String passs , String emaill, String phonee) {
        this.fname = new SimpleStringProperty(fnamee);
        this.lname = new SimpleStringProperty(lnamee);
        this.pass = new SimpleStringProperty(passs);
        this.email = new SimpleStringProperty(emaill);
        this.phone = new SimpleStringProperty(phonee);
        
    }
   ////getter
        public String getFname() {
            return fname.get();
        }

        public String getLname() {
            return lname.get();
        }

        public String getPass() {
            return pass.get();
        }

        public String getEmail() {
            return email.get();
        }

        public String getPhone() {
            return phone.get();
        }
     
     
     
     //setter
                  
       public void setFname(String v) {
             fname.setValue(v);
        }

        public void setLname(String v) {
             lname.setValue(v);
        }

        public void setPass(String v) {
             pass.setValue(v);
        }

        public void setEmail(String v) {
             email.setValue(v);
        }

        public void setPhone(String v) {
             phone.setValue(v);
        }
     
     ///Property values
         public StringProperty fnameProperty() {
        return fname;
         }
         
          public StringProperty lnameProperty() {
        return lname;
         }
          
           public StringProperty passProperty() {
                  return pass;
         }
           
            public StringProperty emailProperty() {
        return email;
         }
            
             public StringProperty phoneProperty() {
        return phone;
         }
        
     }
     
 ////////////////////////////////////////////////////////////
     
          private void mouseclikedd(){
                 table.setOnMouseClicked((MouseEvent event) -> {
                     UserDetails u = table.getItems().get(table.getSelectionModel().getSelectedIndex());
                     fname.setText(u.getFname());
                     lname.setText(u.getLname());
                     pass.setText(u.getPass());
                     em.setText(u.getEmail());
                     ph.setText(u.getPhone());
                 });
        
            }
}///////////////////////////
