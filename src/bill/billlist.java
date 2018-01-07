
package bill;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class billlist {
    private  StringProperty billno;
    private  StringProperty pid;
    private  StringProperty date;
    private  IntegerProperty cusphone;
    private  StringProperty cusname;
    private  StringProperty pname;
    private  IntegerProperty price;
    private  IntegerProperty quantity;
   private Double amount;
   private  Double grandt;
   

    public billlist(String billno, String pid, String date, int cusphone, String cusname ,String pname ,int price ,int quan , Double amount ,Double grandt) {
        this.billno =new SimpleStringProperty(billno);
        this.pid =new SimpleStringProperty(pid);
        this.date = new SimpleStringProperty(date);
        this.cusphone = new SimpleIntegerProperty(cusphone);
        this.cusname = new SimpleStringProperty(cusname);
        this.pname = new SimpleStringProperty(pname);
        this.price = new SimpleIntegerProperty(price);
        this.quantity = new SimpleIntegerProperty(quan);
        this.amount = new Double(amount);
        this.grandt = new Double(grandt);
        
       
    }

    public String getBillno() {
        return billno.get();
    }

    public void setBillno(String value) {
        billno.setValue(value);
    }

    public String getPid() {
        return pid.get();
    }

    public void setPid(String value) {
         pid.setValue(value);
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String value) {
         date.setValue(value);
    }

    public int getCusphone() {
        return cusphone.get();
    }

    public void setCusphone(int value) {
         cusphone.setValue(value);
    }

    public String getCusname() {
        return cusname.get();
    }

    public void setCusname(String value) {
         cusname.setValue(value);
    }
    
     public String getPname() {
        return pname.get();
    }

    public void setPname(String value) {
         pname.setValue(value);
    }
    
    
      public int getPrice() {
        return price.get();
    }

    public void setPrice(int value) {
         price.setValue(value);
    }
    
     public int getQuantity() {
        return quantity.get();
    }

    public void setQuantity(int value) {
         quantity.setValue(value);
    }
    
      public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
         this.amount = amount;
    
   }

    public Double getGrandt() {
        return grandt;
    }

    public void setGrandt(Double grandt) {
        this.grandt = grandt;
    }
    
}
