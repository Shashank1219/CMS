
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package bill;

/*import cms.DbConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import reports.productlist; */

/**
 * FXML Controller class
 *
 */
/*public class Generate_billController implements Initializable {

    @FXML
    private JFXTextField pid1;
    @FXML
    private TextField pname1;
    @FXML
    private JFXTextField quan1;
    @FXML
    private TableView<orderlist> tableorder;
    @FXML
    private TableColumn<orderlist, Integer> colno;
    @FXML
    private TableColumn<orderlist, String> colpid;
    @FXML
    private TableColumn<orderlist, String> colpname;
    @FXML
    private TableColumn<orderlist, Double> colprice;
    @FXML
    private TableColumn<orderlist, Integer> colqty;
    @FXML
    private TableColumn<orderlist, Double> colam;

      Connection conn =null;
       ResultSet rs = null  ;
        PreparedStatement pst = null  ;
    
    private ObservableList<orderlist> data;
    private ObservableList<billlist> data1 = FXCollections.observableArrayList();
        private ObservableList<billlist> data2 = FXCollections.observableArrayList();

    private DbConnection dc;
     String productid;
     String productname;
     int pricee;
     int qtty = 0;
     double amount =0.0;
     int no = 0 ;
     
    @FXML
    private TextField price1;
    @FXML
    private Label grandtotal;
    private double grand=0.0;
    @FXML
    private TextField invoiceno;
    @FXML
    private JFXDatePicker orderdate;
    @FXML
    private JFXTextField cusnametxt;
    @FXML
    private JFXTextField cusnotxt;
    @FXML
    private TableView<billlist> billtable;
    @FXML
    private TableColumn<billlist, String> colbillno2;
    @FXML
    private TableColumn<billlist, String> colpid2;
    @FXML
    private TableColumn<billlist, String> coldate2;
    @FXML
    private TableColumn<billlist, Integer> colcus2;
    @FXML
    private TableColumn<billlist, String> colcusname2;
    @FXML
    private TextField searchtxt;
    @FXML
    private TextField display; */
    /**
     * Initializes the controller class.
     */
    /*@Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
        dc = new DbConnection();
        // conn = dc.Connect(); you can use this to connect for all
        invoiceno.setText(autoinvoiceno());
        handlescan();
        loaddata();
       orderdate.setValue(LocalDate.now());
       loaddatatobilltable();
       searchproduct();
     
    
    }    
    
    
     public void loaddata(){

        //Set cell value factory to tableview.
        //NB.PropertyValue Factory must be the same with the one set in model class.
        colno.setCellValueFactory(new PropertyValueFactory<>("no"));
        colpid.setCellValueFactory(new PropertyValueFactory<>("pido"));
        colpname.setCellValueFactory(new PropertyValueFactory<>("pnameo"));
        colprice.setCellValueFactory(new PropertyValueFactory<>("priceo"));
        colqty.setCellValueFactory(new PropertyValueFactory<>("qtyo"));
        colam.setCellValueFactory(new PropertyValueFactory<>("amounto"));
        
       data = FXCollections.observableArrayList();
     // tableorder.setItems(data); i can use this 

    }
      
     
     public void loaddatatobilltable(){
        
         try {
            Connection conn = dc.Connect();
          
           
            ResultSet rs = conn.createStatement().executeQuery("SELECT orderid,pid,billdate,cusphone,cusname FROM billdetail");
            while (rs.next()) {
                //get string from db,whichever way 
                data1.add(new billlist(rs.getString(1) , rs.getString(2)  , rs.getString(3), rs.getInt(4), rs.getString(5) , null , 0 , 0, 0.0 ,0.0) );
            }

        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, ex);
        }
        
        //Set cell value factory to tableview.
        //NB.PropertyValue Factory must be the same with the one set in model class.
        colbillno2.setCellValueFactory(new PropertyValueFactory<>("billno"));
        colpid2.setCellValueFactory(new PropertyValueFactory<>("pid"));
        coldate2.setCellValueFactory(new PropertyValueFactory<>("date"));
        colcus2.setCellValueFactory(new PropertyValueFactory<>("cusphone"));
        colcusname2.setCellValueFactory(new PropertyValueFactory<>("cusname"));
        
        billtable.setItems(null);
        billtable.setItems(data1);
           
    }
     
      private void searchproduct(){
                searchtxt.setOnKeyReleased(e->{
                    if(searchtxt.getText().equals("")){
                        loaddatatobilltable();
                    }else{
                         data1.clear();
                         
                         String sql = "SELECT orderid,pid,billdate,cusphone,cusname FROM billdetail where orderid like '%"+searchtxt.getText()+"%'"
                                      +"union SELECT orderid,pid,billdate,cusphone,cusname FROM billdetail where pid like '%"+searchtxt.getText()+"%'"
                                      +"union SELECT orderid,pid,billdate,cusphone,cusname FROM billdetail where billdate like '%"+searchtxt.getText()+"%'"
                                      +"union SELECT orderid,pid,billdate,cusphone,cusname FROM billdetail where cusphone like '%"+searchtxt.getText()+"%'"
                                      +"union SELECT orderid,pid,billdate,cusphone,cusname FROM billdetail where cusname like '%"+searchtxt.getText()+"%'";
                                   
                    try {
                        pst = conn.prepareStatement(sql);
                          rs =pst.executeQuery();
                           while(rs.next()){
                        data1.add(new billlist( rs.getString(1), rs.getString(2)  , rs.getString(3), rs.getInt(4), rs.getString(5) , null , 0 , 0, 0.0 , 0.0));
                          
                           }
                           billtable.setItems(null);
                           billtable.setItems(data1);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                    
                }
                });
            }
      
     private void handlescan(){
         
         
   //here i am doing it manually other wise set it with barscanner
         
        pid1.setOnKeyPressed((KeyEvent event) -> {
            switch (event.getCode()){
                case ENTER:
                 String pid = pid1.getText();
        try {
            conn = dc.Connect();
            pst= conn.prepareStatement("select * from products where product_id=? ");
            pst.setString(1, pid);
            rs =pst.executeQuery();
            
            while (rs.next()) {
                 productid = rs.getString(1);
                 productname = rs.getString(2);
                 pricee = rs.getInt(5);
                
                 pname1.setText(productname);
                 price1.setText(""+pricee);
                 quan1.requestFocus();
                
            }
                 
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, ex);
               }
            }
                     
                 });
     }
     
 //this function will handle the order of bill 
      int quan = 0;
    @FXML
    private void handleorder(ActionEvent event) {
       quan= Integer.parseInt(quan1.getText());
       
        if(quan != 0){
            amount = pricee * quan;
            grand += amount;
            //this code to update the quantity where the quantity has same product is 
            for(orderlist i : data){
                if(i.getPido().equals(productid)){
                int table_qty = i.getQtyo() + quan;
                double table_amount = i.getAmounto() + amount;
                i.setQtyo(table_qty);
                i.setAmounto(table_amount);
                grandtotal.setText(""+grand);
                tableorder.getItems().set(tableorder.getItems().indexOf(i), i);
                cleardata();
                return;
                }
               
            }
           data.add(new orderlist(++no, productid, productname, pricee, quan, amount));
            tableorder.setItems(data);
            grandtotal.setText(""+grand);
            cleardata();
            
        }
    }
    
    
    private void cleardata(){
        
        
        pid1.clear();
        pname1.clear();
        price1.clear();
        quan1.clear();
        
        pid1.requestFocus();
        
    }
    
    private String autoinvoiceno(){
        String orderi = "inv00000";
        
        try {
           conn = dc.Connect();
           
            pst = conn.prepareStatement("select max(orderid) from orderproduct");
            rs = pst.executeQuery();
            while(rs.next()){
                orderi=rs.getString(1);
                int n = Integer.parseInt(orderi.substring(3)) + 1;
                int x =String.valueOf(n).length();
                orderi =orderi.substring(0, 8-x) + String.valueOf(n);
            }
           rs.close();
        } catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, ex);
         }
        return orderi;
    }

    @FXML
    private void printinvoice(ActionEvent event) {
        String sql = "insert into orderproduct values(?,?)";
        try {
            conn = dc.Connect();
            pst = conn.prepareStatement(sql);
            pst.setString(1,invoiceno.getText());
            pst.setDate(2, java.sql.Date.valueOf(orderdate.getValue()));
            int i = pst.executeUpdate();
            
            if(i==1){
                conn = dc.Connect();
               sql = "insert into billdetail(orderid,pid,pname,price,quantity,amount,grandtotal,billdate,cusname,cusphone) values(?,?,?,?,?,?,?,?,?,?)" ;
              for(orderlist ol : data){
                 conn = dc.Connect();
                  pst = conn.prepareStatement(sql);
                  pst.setString(1, invoiceno.getText());
                  pst.setString(2, ol.getPido());
                  pst.setString(3, ol.getPnameo());
                  pst.setInt(4, ol.getPriceo());
                  pst.setInt(5, ol.getQtyo());
                  pst.setDouble(6, ol.getAmounto());
                  pst.setString(7, grandtotal.getText());
                  pst.setDate(8, java.sql.Date.valueOf(orderdate.getValue()));
                  pst.setString(9, cusnametxt.getText());
                  pst.setString(10, cusnotxt.getText());
                  
                  int j = pst.executeUpdate();
                  if(j==1){
                      JOptionPane.showMessageDialog(null,"completed");
                      
                  }
              }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
         printinvoicefromjasper();
        invoiceno.setText(autoinvoiceno());
        data.clear();
        cleardata();
        grand = 0.0;
    }

        private void printinvoicefromjasper(){
           String Sourcepath = "C:\\Users\\shash\\Documents\\NetBeansProjects\\CMS\\src\\reports\\billreport.jrxml";
//String Sourcepath = "/home/mohammed/NetBeansProjects/CMS/src/reports/billreport.jrxml";
        try {
            conn = dc.Connect();
            JasperReport jr = JasperCompileManager.compileReport(Sourcepath);
            HashMap<String , Object> para = new HashMap<>();
            para.put("cashier","mohammed");//user get from login 
            para.put("grandtotal",grandtotal.getText());//grandtotal is a variable in the report 
            para.put("billdata",String.valueOf(orderdate.getValue()));
            para.put("billno",invoiceno.getText());
            ArrayList<productlist>  plist  =  new ArrayList<>();
            for(orderlist ol : data){
               plist.add(new productlist(ol.getPnameo(),""+ ol.getPriceo(),""+ ol.getQtyo(),""+ ol.getAmounto()));
                
            }
            JRBeanCollectionDataSource jcs = new JRBeanCollectionDataSource(plist);
            JasperPrint jp = JasperFillManager.fillReport(jr,para,jcs);
            //JasperViewer.viewReport(jp);
             JasperViewer jv = new JasperViewer(jp,false);
             jv.setVisible(true);
           
    
        } catch (JRException ex) {
            Logger.getLogger(Generate_billController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }

  
        
       

    @FXML
    private void clear(ActionEvent event) {
         data.clear();
        data.removeAll(new orderlist(no=0, productid, productname, pricee, quan, amount));
       grand = 0.0;
       pid1.requestFocus();
    }

 ///////////////////////////////////true
   
    @FXML
    private void mouseclicked2(MouseEvent event) {
         billlist u = billtable.getItems().get(billtable.getSelectionModel().getSelectedIndex());
                     display.setText(u.getBillno());
             if(event.getClickCount() == 2){
                     loaddatafromsqltoobslist();
                     printinvoicefromanother();
                     }
    }
    String bilno ;
    double grandtt = 0.0;
    String date ;
 // load data from sql to absorvable list and i can use the data as i want 
    public void loaddatafromsqltoobslist(){
        data2.clear();
         try {
            conn = dc.Connect();
          bilno  = display.getText();
          
           
          
          pst = conn.prepareStatement("SELECT orderid,pid,billdate,cusphone,cusname,pname,price,quantity,amount,grandtotal from billdetail where orderid =?");
          pst.setString(1,bilno);
          ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                //get string from db,whichever way 
               data2.add(new billlist(rs.getString(1), rs.getString(2)  , rs.getString(3), rs.getInt(4), rs.getString(5) , rs.getString(6) , rs.getInt(7) , rs.getInt(8), rs.getDouble(9) , rs.getDouble(10)  ));
                 grandtt = rs.getDouble("grandtotal");
                 date = rs.getString("billdate");
            }

        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, ex);
        }
    }
 private void printinvoicefromanother(){
            String Sourcepath = "C:\\Users\\shash\\Documents\\NetBeansProjects\\CMS\\src\\reports\\billreport.jrxml";
//String Sourcepath = "/home/mohammed/NetBeansProjects/CMS/src/reports/billreport.jrxml";
        try {
            conn = dc.Connect();
            JasperReport jr = JasperCompileManager.compileReport(Sourcepath);
            HashMap<String , Object> para = new HashMap<>();
            para.put("cashier","mohammed");//user get from login 
          para.put("grandtotal",""+grandtt);//grandtotal is a variable in the report 
            para.put("billdata",date);//String.valueOf(orderdate.getValue())
           para.put("billno",bilno);
            ArrayList<productlist>  plist  =  new ArrayList<>();
            for(billlist ol : data2){
               plist.add(new productlist(ol.getPname(),""+ ol.getPrice(),""+ ol.getQuantity(),""+ ol.getAmount()));
                
            }
            JRBeanCollectionDataSource jcs = new JRBeanCollectionDataSource(plist);
            JasperPrint jp = JasperFillManager.fillReport(jr,para,jcs);
            //JasperViewer.viewReport(jp);
            JasperViewer jv = new JasperViewer(jp,false);
            jv.setVisible(true);
            
           
    
        } catch (JRException ex) {
            Logger.getLogger(Generate_billController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }

   
}////////////
*/

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bill;

import cms.DbConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import reports.productlist;

/**
 * FXML Controller class
 *
 */
public class Generate_billController implements Initializable {

    @FXML
    private JFXTextField pid1;
    @FXML
    private TextField pname1;
    @FXML
    private JFXTextField quan1;
    @FXML
    private TableView<orderlist> tableorder;
    @FXML
    private TableColumn<orderlist, Integer> colno;
    @FXML
    private TableColumn<orderlist, String> colpid;
    @FXML
    private TableColumn<orderlist, String> colpname;
    @FXML
    private TableColumn<orderlist, Double> colprice;
    @FXML
    private TableColumn<orderlist, Integer> colqty;
    @FXML
    private TableColumn<orderlist, Double> colam;

      Connection conn =null;
       ResultSet rs = null  ;
        PreparedStatement pst = null  ;
    
    private ObservableList<orderlist> data;
    private ObservableList<billlist> data1 = FXCollections.observableArrayList();
        private ObservableList<billlist> data2 = FXCollections.observableArrayList();

    private DbConnection dc;
     String productid;
     String productname;
     int pricee;
     int qtty = 0;
     double amount =0.0;
     int no = 0 ;
     
    @FXML
    private TextField price1;
    @FXML
    private Label grandtotal;
    private double grand=0.0;
    @FXML
    private TextField invoiceno;
    @FXML
    private JFXDatePicker orderdate;
    @FXML
    private JFXTextField cusnametxt;
    @FXML
    private JFXTextField cusnotxt;
    @FXML
    private TableView<billlist> billtable;
    @FXML
    private TableColumn<billlist, String> colbillno2;
    @FXML
    private TableColumn<billlist, String> colpid2;
    @FXML
    private TableColumn<billlist, String> coldate2;
    @FXML
    private TableColumn<billlist, Integer> colcus2;
    @FXML
    private TableColumn<billlist, String> colcusname2;
    @FXML
    private TextField searchtxt;
    @FXML
    private TextField display;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
        dc = new DbConnection();
        // conn = dc.Connect(); you can use this to connect for all
        invoiceno.setText(autoinvoiceno());
        handlescan();
        loaddata();
       orderdate.setValue(LocalDate.now());
       loaddatatobilltable();
       searchproduct();
     
    
    }    
    
    
     public void loaddata(){

        //Set cell value factory to tableview.
        //NB.PropertyValue Factory must be the same with the one set in model class.
        colno.setCellValueFactory(new PropertyValueFactory<>("no"));
        colpid.setCellValueFactory(new PropertyValueFactory<>("pido"));
        colpname.setCellValueFactory(new PropertyValueFactory<>("pnameo"));
        colprice.setCellValueFactory(new PropertyValueFactory<>("priceo"));
        colqty.setCellValueFactory(new PropertyValueFactory<>("qtyo"));
        colam.setCellValueFactory(new PropertyValueFactory<>("amounto"));
        
       data = FXCollections.observableArrayList();
     // tableorder.setItems(data); i can use this 

    }
      
     
     public void loaddatatobilltable(){
        
         try {
            Connection conn = dc.Connect();
          
           
            ResultSet rs = conn.createStatement().executeQuery("SELECT orderid,pid,billdate,cusphone,cusname FROM billdetail");
            while (rs.next()) {
                //get string from db,whichever way 
                data1.add(new billlist(rs.getString(1) , rs.getString(2)  , rs.getString(3), rs.getInt(4), rs.getString(5) , null , 0 , 0, 0.0 ,0.0) );
            }

        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, ex);
        }
        
        //Set cell value factory to tableview.
        //NB.PropertyValue Factory must be the same with the one set in model class.
        colbillno2.setCellValueFactory(new PropertyValueFactory<>("billno"));
        colpid2.setCellValueFactory(new PropertyValueFactory<>("pid"));
        coldate2.setCellValueFactory(new PropertyValueFactory<>("date"));
        colcus2.setCellValueFactory(new PropertyValueFactory<>("cusphone"));
        colcusname2.setCellValueFactory(new PropertyValueFactory<>("cusname"));
        
        billtable.setItems(null);
        billtable.setItems(data1);
           
    }
     
      private void searchproduct(){
                searchtxt.setOnKeyReleased(e->{
                    if(searchtxt.getText().equals("")){
                        loaddatatobilltable();
                    }else{
                         data1.clear();
                         
                         String sql = "SELECT orderid,pid,billdate,cusphone,cusname FROM billdetail where orderid like '%"+searchtxt.getText()+"%'"
                                      +"union SELECT orderid,pid,billdate,cusphone,cusname FROM billdetail where pid like '%"+searchtxt.getText()+"%'"
                                      +"union SELECT orderid,pid,billdate,cusphone,cusname FROM billdetail where billdate like '%"+searchtxt.getText()+"%'"
                                      +"union SELECT orderid,pid,billdate,cusphone,cusname FROM billdetail where cusphone like '%"+searchtxt.getText()+"%'"
                                      +"union SELECT orderid,pid,billdate,cusphone,cusname FROM billdetail where cusname like '%"+searchtxt.getText()+"%'";
                                   
                    try {
                        pst = conn.prepareStatement(sql);
                          rs =pst.executeQuery();
                           while(rs.next()){
                        data1.add(new billlist( rs.getString(1), rs.getString(2)  , rs.getString(3), rs.getInt(4), rs.getString(5) , null , 0 , 0, 0.0 , 0.0));
                          
                           }
                           billtable.setItems(null);
                           billtable.setItems(data1);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                    
                }
                });
            }
      
     private void handlescan(){
         
         
   //here i am doing it manually other wise set it with barscanner
         
        pid1.setOnKeyPressed((KeyEvent event) -> {
            switch (event.getCode()){
                case ENTER:
                 String pid = pid1.getText();
        try {
            conn = dc.Connect();
            pst= conn.prepareStatement("select * from products where product_id=? ");
            pst.setString(1, pid);
            rs =pst.executeQuery();
            
            while (rs.next()) {
                 productid = rs.getString(1);
                 productname = rs.getString(2);
                 pricee = rs.getInt(5);
                
                 pname1.setText(productname);
                 price1.setText(""+pricee);
                 quan1.requestFocus();
                
            }
                 
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, ex);
               }
            }
                     
                 });
     }
     
 //this function will handle the order of bill 
      int quan = 0;
    @FXML
    private void handleorder(ActionEvent event) {
       quan= Integer.parseInt(quan1.getText());
       
        if(quan != 0){
            amount = pricee * quan;
            grand += amount;
            //this code to update the quantity where the quantity has same product is 
            for(orderlist i : data){
                if(i.getPido().equals(productid)){
                int table_qty = i.getQtyo() + quan;
                double table_amount = i.getAmounto() + amount;
                i.setQtyo(table_qty);
                i.setAmounto(table_amount);
                grandtotal.setText(""+grand);
                tableorder.getItems().set(tableorder.getItems().indexOf(i), i);
                cleardata();
                return;
                }
               
            }
           data.add(new orderlist(++no, productid, productname, pricee, quan, amount));
            tableorder.setItems(data);
            grandtotal.setText(""+grand);
            cleardata();
            
        }
    }
    
    
    private void cleardata(){
        
        
        pid1.clear();
        pname1.clear();
        price1.clear();
        quan1.clear();
        
        pid1.requestFocus();
        
    }
    
    private String autoinvoiceno(){
        String orderi = "inv00000";
        
        try {
           conn = dc.Connect();
           
            pst = conn.prepareStatement("select max(orderid) from orderproduct");
            rs = pst.executeQuery();
            while(rs.next()){
                orderi=rs.getString(1);
                int n = Integer.parseInt(orderi.substring(3)) + 1;
                int x =String.valueOf(n).length();
                orderi =orderi.substring(0, 8-x) + String.valueOf(n);
            }
           rs.close();
        } catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, ex);
         }
        return orderi;
    }

    @FXML
    private void printinvoice(ActionEvent event) {
        String sql = "insert into orderproduct values(?,?)";
        try {
            conn = dc.Connect();
            pst = conn.prepareStatement(sql);
            pst.setString(1,invoiceno.getText());
            pst.setDate(2, java.sql.Date.valueOf(orderdate.getValue()));
            int i = pst.executeUpdate();
            
            if(i==1){
                conn = dc.Connect();
               sql = "insert into billdetail(orderid,pid,pname,price,quantity,amount,grandtotal,billdate,cusname,cusphone) values(?,?,?,?,?,?,?,?,?,?)" ;
              for(orderlist ol : data){
                 conn = dc.Connect();
                  pst = conn.prepareStatement(sql);
                  pst.setString(1, invoiceno.getText());
                  pst.setString(2, ol.getPido());
                  pst.setString(3, ol.getPnameo());
                  pst.setInt(4, ol.getPriceo());
                  pst.setInt(5, ol.getQtyo());
                  pst.setDouble(6, ol.getAmounto());
                  pst.setString(7, grandtotal.getText());
                  pst.setDate(8, java.sql.Date.valueOf(orderdate.getValue()));
                  pst.setString(9, cusnametxt.getText());
                  pst.setString(10, cusnotxt.getText());
                  
                  int j = pst.executeUpdate();
                  if(j==1){
                      JOptionPane.showMessageDialog(null,"completed");
                      
                  }
              }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
         printinvoicefromjasper();
        invoiceno.setText(autoinvoiceno());
        data.clear();
        cleardata();
        grand = 0.0;
    }

        private void printinvoicefromjasper(){
           String Sourcepath = "C:\\Users\\shash\\Documents\\NetBeansProjects\\CMS\\src\\reports\\billreport.jrxml";
//String Sourcepath = "/home/mohammed/NetBeansProjects/CMS/src/reports/billreport.jrxml";
        try {
            conn = dc.Connect();
            JasperReport jr = JasperCompileManager.compileReport(Sourcepath);
            HashMap<String , Object> para = new HashMap<>();
            para.put("cashier","mohammed");//user get from login 
            para.put("grandtotal",grandtotal.getText());//grandtotal is a variable in the report 
            para.put("billdata",String.valueOf(orderdate.getValue()));
            para.put("billno",invoiceno.getText());
            ArrayList<productlist>  plist  =  new ArrayList<>();
            for(orderlist ol : data){
               plist.add(new productlist(ol.getPnameo(),""+ ol.getPriceo(),""+ ol.getQtyo(),""+ ol.getAmounto()));
                
            }
            JRBeanCollectionDataSource jcs = new JRBeanCollectionDataSource(plist);
            JasperPrint jp = JasperFillManager.fillReport(jr,para,jcs);
            //JasperViewer.viewReport(jp);
             JasperViewer jv = new JasperViewer(jp,false);
             jv.setVisible(true);
           
    
        } catch (JRException ex) {
            Logger.getLogger(Generate_billController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }

  
        
       

    @FXML
    private void clear(ActionEvent event) {
         data.clear();
        data.removeAll(new orderlist(no=0, productid, productname, pricee, quan, amount));
       grand = 0.0;
       pid1.requestFocus();
    }

 ///////////////////////////////////true
   
    @FXML
    private void mouseclicked2(MouseEvent event) {
         billlist u = billtable.getItems().get(billtable.getSelectionModel().getSelectedIndex());
                     display.setText(u.getBillno());
             if(event.getClickCount() == 2){
                     loaddatafromsqltoobslist();
                     printinvoicefromanother();
                     }
    }
    String bilno ;
    double grandtt = 0.0;
    String date ;
 // load data from sql to absorvable list and i can use the data as i want 
    public void loaddatafromsqltoobslist(){
        data2.clear();
         try {
            conn = dc.Connect();
          bilno  = display.getText();
          
           
          
          pst = conn.prepareStatement("SELECT orderid,pid,billdate,cusphone,cusname,pname,price,quantity,amount,grandtotal from billdetail where orderid =?");
          pst.setString(1,bilno);
          ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                //get string from db,whichever way 
               data2.add(new billlist(rs.getString(1), rs.getString(2)  , rs.getString(3), rs.getInt(4), rs.getString(5) , rs.getString(6) , rs.getInt(7) , rs.getInt(8), rs.getDouble(9) , rs.getDouble(10)  ));
                 grandtt = rs.getDouble("grandtotal");
                 date = rs.getString("billdate");
            }

        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, ex);
        }
    }
 private void printinvoicefromanother(){
            String Sourcepath = "C:\\Users\\shash\\Documents\\NetBeansProjects\\CMS\\src\\reports\\billreport.jrxml";
//String Sourcepath = "/home/mohammed/NetBeansProjects/CMS/src/reports/billreport.jrxml";
        try {
            conn = dc.Connect();
            JasperReport jr = JasperCompileManager.compileReport(Sourcepath);
            HashMap<String , Object> para = new HashMap<>();
            para.put("cashier","mohammed");//user get from login 
          para.put("grandtotal",""+grandtt);//grandtotal is a variable in the report 
            para.put("billdata",date);//String.valueOf(orderdate.getValue())
           para.put("billno",bilno);
            ArrayList<productlist>  plist  =  new ArrayList<>();
            for(billlist ol : data2){
               plist.add(new productlist(ol.getPname(),""+ ol.getPrice(),""+ ol.getQuantity(),""+ ol.getAmount()));
                
            }
            JRBeanCollectionDataSource jcs = new JRBeanCollectionDataSource(plist);
            JasperPrint jp = JasperFillManager.fillReport(jr,para,jcs);
            //JasperViewer.viewReport(jp);
            JasperViewer jv = new JasperViewer(jp,false);
            jv.setVisible(true);
            
           
    
        } catch (JRException ex) {
            Logger.getLogger(Generate_billController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }

   
}////////////
