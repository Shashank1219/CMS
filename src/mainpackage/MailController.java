/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpackage;

import com.jfoenix.controls.JFXRippler;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author mohammed
 */
public class MailController implements Initializable {

    @FXML
    private AnchorPane basepane;
    @FXML
    private AnchorPane paneadmin;
    @FXML
    private AnchorPane paneuser;
    @FXML
    private AnchorPane pamestock;
    @FXML
    private AnchorPane panebill;
    @FXML
    private AnchorPane panere;
    @FXML
    private ImageView paneexit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          //  JFXRippler r = new JFXRippler(panere);
          //  r.setRipplerFill(Paint.valueOf("#ffdd"));
           // r.autosize();
           // basepane.getChildren().add(r);
    }    
void loadWindow(String loc, String title) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.initModality(Modality.APPLICATION_MODAL);
           //stage.setFullScreen(true);
          //  stage.setFullScreenExitHint("he");
          //stage.initStyle(StageStyle.UTILITY);
          //stage.initModality(Modality.WINDOW_MODAL);
         
          
          // stage.initOwner(stage);
          //stage.initStyle(StageStyle.TRANSPARENT);
            stage.showAndWait();
        } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, ex);
        }
    }
    @FXML
    private void admin(MouseEvent event) {
        
               loadWindow("/admin/admin_form.fxml", "Admin");

    }

    @FXML
    private void user(MouseEvent event) {
            loadWindow("/users/user.fxml", "USERS");
    }

    @FXML
    private void stock(MouseEvent event) {
       loadWindow("/stock/products.fxml", "STOCK");
    }

    @FXML
    private void bill(MouseEvent event) {
        loadWindow("/bill/generate_bill.fxml", "BILL");
    }

    @FXML
    private void exit(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void report(MouseEvent event) {
        loadWindow("/reports/report.fxml", "REPORT");
    }
    
}
