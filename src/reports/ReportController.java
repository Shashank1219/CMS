/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reports;

import cms.DbConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author mohammed
 */
public class ReportController implements Initializable {
     private DbConnection dc;
    private BarChart<String ,Integer> sales;
    @FXML
    private LineChart<String ,Integer> sales1;
    @FXML
    private NumberAxis total1;
    @FXML
    private CategoryAxis date1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc = new DbConnection();
        
        chart();
    }    
    
  
    
     public void chart(){
        XYChart.Series<String ,Integer> s = new XYChart.Series<>();
           try {
            Connection conn = dc.Connect();
          
            ResultSet rs = conn.createStatement().executeQuery("SELECT billdate,sum(amount) FROM billdetail group by billdate order by billdate asc");//SELECT date,sum(total) FROM bills group by date order by date asc
            while (rs.next()) {
                s.getData().add(new XYChart.Data<>(rs.getString(1), rs.getInt(2)));
               
            }
         sales1.getData().add(s);
         
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, ex);
        }
    }
   
}
