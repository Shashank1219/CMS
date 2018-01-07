/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock;

import cms.DbConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author mohammed
 */
public class ProductsController implements Initializable {

    @FXML
    private JFXTextField productidtxt;
    @FXML
    private JFXTextField productnametxt;
    @FXML
    private JFXComboBox<String> comcategory;
    @FXML
    private JFXComboBox<String> combrand;
    @FXML
    private JFXTextField pricetxt;
    @FXML
    private JFXComboBox<String> comsize;
    @FXML
    private JFXTextField colortxt;
    @FXML
    private JFXTextField quantitytxt;

    /**
     * Initializes the controller class.
     */
     Connection conn = null;
    ResultSet rs = null ;
    PreparedStatement pst = null ;
     private DbConnection dc;
    private ObservableList<proDetails> data= FXCollections.observableArrayList();
    private ObservableList<Branddetails> d= FXCollections.observableArrayList();

    @FXML
    private JFXComboBox<String> comgender1;
    @FXML
    private Label labelid;
    @FXML
    private TableColumn<proDetails, String> namecol;
    @FXML
    private TableColumn<proDetails, String> gendercol;
    @FXML
    private TableColumn<proDetails, String> categorycol;
    @FXML
    private TableColumn<proDetails, String> brandcol;
    @FXML
    private TableColumn<proDetails, String> pricecol;
    @FXML
    private TableColumn<proDetails, String> sizecol;
    @FXML
    private TableColumn<proDetails, String> colorcol;
    @FXML
    private TableColumn<proDetails, String> quantitycol;
    @FXML
    private TableView<proDetails> table;
    @FXML
    private TableColumn<proDetails, String> idcol;
    @FXML
    private TextField pname2;
    @FXML
    private ComboBox<String> comgender2;
    @FXML
    private ComboBox<String> comcat2;
    @FXML
    private ComboBox<String> combrand2;
    @FXML
    private TextField price2;
    @FXML
    private ComboBox<String> comsize2;
   
    @FXML
    private TextField color2;
    @FXML
    private TextField quan2;
    @FXML
    private TableColumn<Branddetails,String> brandidcol;
    @FXML
    private TableColumn<Branddetails,String> brandnamecol;
    @FXML
    private TableView<Branddetails> tablebrand;
    @FXML
    private JFXTextField brandnametxt;
    @FXML
    private Label brandidlabel;
    @FXML
    private JFXTextField pid3;
    @FXML
    private Label quan3;
    @FXML
    private JFXTextField quannew3;
    @FXML
    private JFXButton saveexisting;
    @FXML
    private TextField searchtxt;
    @FXML
    private BarChart<String, Integer> barchartproducts;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc = new DbConnection();
        fillcombox();
        loaddata();
        mouseclikedd();
        loadbranddata();
        mouseclikedbrand();
        onkeypressed();
        searchproduct();
        chart();
    }    

    @FXML
    private void save(ActionEvent event) {
        
         String p_id = productidtxt.getText();
        String p_name = productnametxt.getText();
        String cate = comcategory.getValue();
        String bran = combrand.getValue();
        String price = pricetxt.getText();
        String siz = comsize.getValue();
        String co = colortxt.getText();
        String quan = quantitytxt.getText();
        String gen = comgender1.getValue();

        try {
            conn = dc.Connect();
            pst= conn.prepareStatement("insert into products values(?,?,?,?,?,?,?,?,?)");
            pst.setString(1, p_id);
            pst.setString(2, p_name);
            pst.setString(3, cate);
            pst.setString(4, bran);
            pst.setString(5, price);
            pst.setString(6, siz);
            pst.setString(7, co);
            pst.setString(8, quan);
            pst.setString(9,gen);

            int i = pst.executeUpdate();

            if(i>0){
               Notifications n = Notifications.create()
                        .title(" Product saved")
                        .text("New product is saved")
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
        
       productidtxt.setText(null);
        productnametxt.setText(null);
        comcategory.setValue(null);
        combrand.setValue("");
        pricetxt.setText(null);
        comsize.setValue(null);
        comgender1.setValue("");
        colortxt.setText(null);
        quantitytxt.setText(null);
        loaddata();
    
    }
    
    
     private void fillcombox(){
        
        comgender1.getItems().addAll("MEN" , "WOMEN","KIDS");
        comgender2.getItems().addAll("MEN" , "WOMEN","KIDS");
        comsize.getItems().addAll("S" , "M","L","XL","XXL");
        comsize2.getItems().addAll("S" , "M","L","XL","XXL");
        comcategory.getItems().addAll("Jeans" , "T-Shirts","Casual Shirts","Formal Shirt","Formal Trousers","Sports" ,"other");
        comcat2.getItems().addAll("Jeans" , "T-Shirts","Casual Shirts","Formal Shirt","Formal Trousers","Sports" ,"other");
        
        try{
        conn = dc.Connect();
        pst = conn.prepareStatement("select brandname from brands");
        rs = pst.executeQuery();
       
        while (rs.next()) {
            
          
           combrand.getItems().addAll(rs.getString("brandname"));
            combrand2.getItems().addAll(rs.getString("brandname"));
        }
        
       pst.close();
        rs.close();
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
    }
    
    }
     
      /*   public void setproductidcombox(){
    
        
        
    try{
        conn = dc.Connect();
        pst = conn.prepareStatement("select product_id from products");
        rs = pst.executeQuery();
       
        while (rs.next()) {
            
          
           productidtxt2.getItems().addAll(rs.getString("product_id"));
            
        }
        
       pst.close();
        rs.close();
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
    }
}
    */  
     
         public void loaddata(){
              data.clear();
         try {
               
              conn = dc.Connect();
          
           
             rs = conn.createStatement().executeQuery("SELECT product_id,product_name,gender,category,brand,price,size,color,quantity FROM products");
            while (rs.next()) {
                //get string from db,whichever way 
                data.add(new proDetails(rs.getString(1), rs.getString(2) , rs.getString(3) , rs.getString(4)  , rs.getString(5) , rs.getString(6) , rs.getString(7) , rs.getString(8) , rs.getString(9)));
            }

        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, ex);
        }
     
        //Set cell value factory to tableview.
        //NB.PropertyValue Factory must be the same with the one set in model class.
        idcol.setCellValueFactory(new PropertyValueFactory<>("pid"));
        namecol.setCellValueFactory(new PropertyValueFactory<>("pname"));
        gendercol.setCellValueFactory(new PropertyValueFactory<>("gen"));
        categorycol.setCellValueFactory(new PropertyValueFactory<>("cat"));
        brandcol.setCellValueFactory(new PropertyValueFactory<>("brand"));
        pricecol.setCellValueFactory(new PropertyValueFactory<>("price"));
        sizecol.setCellValueFactory(new PropertyValueFactory<>("size"));
        colorcol.setCellValueFactory(new PropertyValueFactory<>("co"));
        quantitycol.setCellValueFactory(new PropertyValueFactory<>("qu"));
       
        
        table.setItems(null);
        table.setItems(data);
        

    }

    @FXML
    private void update(ActionEvent event) {
        
        String p_id = labelid.getText();
        String p_name = pname2.getText();
        String cate = comcat2.getValue();
        String bran = combrand2.getValue();
        String price = price2.getText();
        String siz = comsize2.getValue();
        String co = color2.getText();
        String quan = quan2.getText();
        String gend = comgender2.getValue();

        try {
            conn = dc.Connect();
            pst= conn.prepareStatement("update products set product_name=? , category=? , brand=? , price=? , size=? , color=?, quantity=? , gender=? where product_id=?");
            pst.setString(1, p_name);
            pst.setString(2, cate);
            pst.setString(3, bran);
            pst.setString(4, price);
            pst.setString(5, siz);
            pst.setString(6, co);
            pst.setString(7, quan);
            pst.setString(8, gend);
            pst.setString(9, p_id);

            int i = pst.executeUpdate();
            if(i>0){
                 Notifications n = Notifications.create()
                        .title(" Product updation")
                        .text("Data is updated")
                        .graphic(null)
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_RIGHT);
                    
               n.darkStyle();
                n.showInformation();
            }
            else {
                JOptionPane.showMessageDialog(null, "not updated");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
         loaddata();
    }

    @FXML
    private void delete(ActionEvent event) {
         String p_id = labelid.getText();

        try {
             conn = dc.Connect();
            pst= conn.prepareStatement("delete from products where product_id=? ");
            pst.setString(1, p_id);

            int i = pst.executeUpdate();
            if(i>0){
                 Notifications n = Notifications.create()
                        .title(" Product deletion")
                        .text("Data is deleted")
                        .graphic(null)
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_RIGHT);
                    
               n.darkStyle();
                n.showInformation();
            }
            else {
                JOptionPane.showMessageDialog(null, "data is not deleted");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
        
        loaddata();
         labelid.setText(null);
        pname2.setText(null);
        comcat2.setValue(null);
        combrand2.setValue("");
        price2.setText(null);
        comsize2.setValue(null);
        comgender2.setValue(null);
        color2.setText(null);
        quan2.setText(null);
    }

    @FXML
    private void addbrand(ActionEvent event) {
        
         String brandn = brandnametxt.getText();
       

        try {
            conn = dc.Connect();
            pst= conn.prepareStatement("insert into brands(brandname) values(?)");
            pst.setString(1, brandn);
            
            int i = pst.executeUpdate();

            if(i>0){
                         Notifications n = Notifications.create()
                        .title(" BRAND")
                        .text("New brand is saved")
                        .graphic(null)
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_RIGHT);
                    
               n.darkStyle();
                n.showInformation();
            }
            else {
                 Notifications n = Notifications.create()
                        .title(" BRAND")
                        .text("Brand is not saved")
                        .graphic(null)
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_RIGHT);
                    
               n.darkStyle();
                n.showError();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
        d.clear();
      loadbranddata();
      brandidlabel.setText(null);
        brandnametxt.setText(null);
    }


    @FXML
    private void deletebrand(ActionEvent event) {
          String brandi = brandidlabel.getText();

        try {
            pst= conn.prepareStatement("delete from brands where brandid=? ");
            pst.setString(1, brandi);

            int i = pst.executeUpdate();
            if(i>0){
                 Notifications n = Notifications.create()
                        .title(" BRAND")
                        .text("Brand is deleted")
                        .graphic(null)
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_RIGHT);
                    
               n.darkStyle();
                n.showInformation();
            }
            else {
               Notifications n = Notifications.create()
                        .title(" BRAND")
                        .text("Brand is not deleted")
                        .graphic(null)
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_RIGHT);
                    
               n.darkStyle();
                n.showError();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
         d.clear();
        loadbranddata();
        brandidlabel.setText(null);
        brandnametxt.setText(null);
         
    }

    @FXML
    private void addexistproduct(ActionEvent event) {
        String pid5= pid3.getText();
        
      int  quan5= Integer.parseInt(String.valueOf(quan3.getText()));
      int  quann5= Integer.parseInt(String.valueOf(quannew3.getText()));
        int result = quan5+quann5;
        
        
        try {
            conn = dc.Connect();
            pst= conn.prepareStatement("update products set  quantity=?  where product_id=?");
            pst.setInt(1, result);
            pst.setString(2, pid5);
            
            
            
            
            int i = pst.executeUpdate();
            if(i>0){
                         Notifications n = Notifications.create()
                        .title(" BRAND")
                        .text("New brand is saved")
                        .graphic(null)
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_RIGHT);
                    
               n.darkStyle();
                n.showInformation();
            }
            else {
                 Notifications n = Notifications.create()
                        .title(" BRAND")
                        .text("Brand is not saved")
                        .graphic(null)
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_RIGHT);
                    
               n.darkStyle();
                n.showError();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
        data.clear();
       loaddata();
       pid3.setText(null);
       quan3.setText(null);
       quannew3.setText(null);
    }

    @FXML
    private void onkeypressed() {
        
          pid3.setOnKeyPressed((KeyEvent event) -> {
            switch (event.getCode()){
                case ENTER:
                     String pid = pid3.getText();
        try {
             conn = dc.Connect();
            pst= conn.prepareStatement("select quantity from products where product_id=? ");
            pst.setString(1, pid);
            rs =pst.executeQuery();
            String quant4 =null ;
            while (rs.next()) {
                 quant4 = rs.getString("quantity");
                
            }
                   quan3.setText(quant4);
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, ex);
               }
            }
                     
                 });
    
    }
     
   
      public static class proDetails{
    private final StringProperty pid;
    private final StringProperty pname;
    private final StringProperty gen;
    private final StringProperty cat;
    private final StringProperty brand;
    private final StringProperty price;
    private final StringProperty size;
    private final StringProperty co;
    private final StringProperty qu;

        public proDetails(String pid, String pname, String gen, String cat, String brand, String price, String size, String co, String qu) {
            this.pid = new SimpleStringProperty(pid);
            this.pname = new SimpleStringProperty(pname);
            this.gen = new SimpleStringProperty(gen);
            this.cat = new SimpleStringProperty(cat);
            this.brand = new SimpleStringProperty(brand);
            this.price =new SimpleStringProperty(price);
            this.size = new SimpleStringProperty(size);
            this.co = new SimpleStringProperty(co);
            this.qu = new SimpleStringProperty(qu);
        }

        public String getPid() {
            return pid.get();
        }

        public String getPname() {
            return pname.get();
        }

        public String getGen() {
            return gen.get();
        }

        public String getCat() {
            return cat.get();
        }

        public String getbrand() {
            return brand.get();
        }

        public String getPrice() {
            return price.get();
        }

        public String getSize() {
            return size.get();
        }

        public String getCo() {
            return co.get();
        }

        public String getQu() {
            return qu.get();
        }
      
      
      ///setter
         public void setPid(String v) {
             pid.setValue(v);
        }
      
          public void setPname(String v) {
             pname.setValue(v);
        }
          
           public void setGen(String v) {
             gen.setValue(v);
        }
           
            public void setCat(String v) {
             cat.setValue(v);
        }
            
             public void setbrand(String v) {
             brand.setValue(v);
        }
             
            
               public void setPrice(String v) {
             price.setValue(v);
        }
               
                public void setSize(String v) {
             size.setValue(v);
        }
                
                 public void setCo(String v) {
             co.setValue(v);
        }
                 
                    public void setQu(String v) {
             qu.setValue(v);
        }    
                 
      ///////////////////////////////
               public StringProperty pidProperty() {
               return pid;
         }
         
          public StringProperty pnameProperty() {
        return pname;
         }
          
           public StringProperty genProperty() {
                  return gen;
         }
           
            public StringProperty catProperty() {
        return cat;
         }
            
             public StringProperty brandProperty() {
        return brand;
         }
               
               public StringProperty priceProperty() {
        return price;
         }
         
          public StringProperty sizeProperty() {
        return size;
         }
          
           public StringProperty coProperty() {
                  return co;
         }
           
            public StringProperty quProperty() {
        return qu;
         }
            
         
        
                    
      }
      
      
      
         private void mouseclikedd(){
                 table.setOnMouseClicked((MouseEvent event) -> {
                     proDetails u = table.getItems().get(table.getSelectionModel().getSelectedIndex());
                     labelid.setText(u.getPid());
                     pname2.setText(u.getPname());
                     comgender2.setValue(u.getGen());
                     comcat2.setValue(u.getCat());
                      combrand2.setValue(u.getbrand());
                    // com2.getItems().add(u.getbrand()); 
                   
                     price2.setText(u.getPrice());
                     comsize2.setValue(u.getSize());
                     color2.setText(u.getCo());
                     quan2.setText(u.getQu());
                 });
        
            }
   public void loadbranddata(){
         try {
             conn = dc.Connect();
          
           
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM brands");
            while (rs.next()) {
                //get string from db,whichever way 
                d.add(new Branddetails(rs.getString(1), rs.getString(2)));
            }

        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, ex);
        }
        
        //Set cell value factory to tableview.
        //NB.PropertyValue Factory must be the same with the one set in model class.
        brandidcol.setCellValueFactory(new PropertyValueFactory<>("brandid"));
        brandnamecol.setCellValueFactory(new PropertyValueFactory<>("brandname"));
        
        tablebrand.setItems(null);
        tablebrand.setItems(d);

    }

         
           public static class Branddetails {
    
    private final StringProperty brandid;
    private final StringProperty brandname;
    

    //Default constructor
    public Branddetails(String brandid, String brandname) {
        this.brandid = new SimpleStringProperty(brandid);
        this.brandname = new SimpleStringProperty(brandname);
        
    }

    //Getters
    public String getBrandid() {
        return brandid.get();
    }

    public String getBrandname() {
        return brandname.get();
    }

    //Setters
    public void setBrandid(String value) {
        brandid.set(value);
    }

    public void setBrandname(String value) {
        brandname.set(value);
    }

  
    //Property values
    public StringProperty brandidProperty() {
        return brandid;
    }

    public StringProperty brandnameProperty() {
        return brandname;
    }

  
    }

            private void mouseclikedbrand(){
                 tablebrand.setOnMouseClicked((MouseEvent event) -> {
                     Branddetails u = tablebrand.getItems().get(tablebrand.getSelectionModel().getSelectedIndex());
                     brandidlabel.setText(u.getBrandid());
                     brandnametxt.setText(u.getBrandname());
                     
                 });
        
            }
     
       private void searchproduct(){
                searchtxt.setOnKeyReleased(e->{
                    if(searchtxt.getText().equals("")){
                        loaddata();
                    }else{
                         data.clear();
                         String sql = "select * from products where product_id like '%"+searchtxt.getText()+"%'"
                                      +"union select * from products where product_name like '%"+searchtxt.getText()+"%'"
                                  +"union select * from products where brand like '%"+searchtxt.getText()+"%'"
                         +"union select * from products where gender like '%"+searchtxt.getText()+"%'"
                         +"union select * from products where category like '%"+searchtxt.getText()+"%'"
                         +"union select * from products where price like '%"+searchtxt.getText()+"%'"
                         +"union select * from products where size like '%"+searchtxt.getText()+"%'"
                         +"union select * from products where color like '%"+searchtxt.getText()+"%'"
                         +"union select * from products where quantity like '%"+searchtxt.getText()+"%'";
                    try {
                        pst = conn.prepareStatement(sql);
                          rs =pst.executeQuery();
                           while(rs.next()){
                         data.add(new proDetails(rs.getString(1), rs.getString(2) , rs.getString(9) , rs.getString(3)  , rs.getString(4) , rs.getString(5) , rs.getString(6) , rs.getString(7) , rs.getString(8)));
                          
                           }
                           table.setItems(null);
                           table.setItems(data);
                    } catch (SQLException ex) {
                        Logger.getLogger(ProductsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                });
            }

////// barchart for products 
       
        public void chart(){
        XYChart.Series<String ,Integer> s = new XYChart.Series<>();
           try {
             conn = dc.Connect();
          
            ResultSet rs = conn.createStatement().executeQuery("SELECT product_id,quantity FROM products order by quantity asc");//SELECT date,sum(total) FROM bills group by date order by date asc
            while (rs.next()) {
                s.getData().add(new XYChart.Data<>(rs.getString(1), rs.getInt(2)));
               
            }
         barchartproducts.getData().add(s);
        
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, ex);
        }
    }


}///////////
 


                   