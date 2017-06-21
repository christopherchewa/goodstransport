/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logistics.models;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.IdName;
import org.javalite.activejdbc.annotations.Many2Many;
import org.javalite.activejdbc.annotations.Table;

/**
 *
 * @author Chewa
 */

@Table("orders")
@IdName("order_id")

public class Order extends Model {
    
    private final StringProperty orderId;
    private StringProperty orderRefId;
    //private final StringProperty orderRefIdItem;
    private final StringProperty orderStatus;
   // private final StringProperty OrderDate;
     
    //private final ObjectProperty<LocalDate> pickupDate;
    //private final ObjectProperty<LocalDate> deliveryDate;
   private final DoubleProperty paidAmount;
   private final DoubleProperty balanceAmount;
   
   
    
    
    
    public Order(String orderId, String orderRefId, String orderStatus, Double paidAmount, Double balanceAmount)
    {
        this.orderId = new SimpleStringProperty(orderId);
       this.orderRefId = new SimpleStringProperty(orderRefId);
        this.orderStatus = new SimpleStringProperty(orderStatus);

       // this.createdAt = new SimpleStringProperty(createdAt);
       // this.pickupDate = null;
       // this.deliveryDate = null;
                
        this.paidAmount = new SimpleDoubleProperty(paidAmount);
        this.balanceAmount = new SimpleDoubleProperty(balanceAmount);
    }
    
public Order()
    {
        this(null, null, null, 0.0, 0.0);
    }

      public StringProperty orderIdProperty() {
        return orderId;
    }
    
       
    
       public StringProperty orderRefIdGeneratorProperty() {
           
           //generate uuid(len - 36), remove dashes, change to uppercase, only get 6,  letters/numbers, concatenate with # at the beginning
        String refNo = "#" + UUID.randomUUID().toString().replace("-", "").toUpperCase().substring(5, 11);
        
        try{
            
       // Order orderObj = Order.findFirst("order_ref_id = ?", refNo);
       Order orderObj = Order.findFirst("order_ref_id = ?", "#D4EECD");
            while(orderObj != null)
            {
            refNo = "#" + UUID.randomUUID().toString().replace("-", "").toUpperCase().substring(5, 11);
            orderObj = Order.findFirst("order_ref_id = ?", refNo);
           
            }
            orderRefId = new SimpleStringProperty(refNo);
            System.out.println("Found matching....generating newRefID");
            System.out.println("Order Ref Id: " + orderRefId);
        }
        catch(Exception e)
        {
          orderRefId = new SimpleStringProperty(refNo);
          System.out.println("New Ref Id generated");
          
        }
        
        System.out.println("From models: " + orderRefId);
        
        return orderRefId;
       
    } 

       
       public StringProperty getOrderRefIdProperty()
       {
           return orderRefId;
       }
      
       
    public StringProperty orderStatusProperty() {
        return orderStatus;
    }

    public DoubleProperty paidAmountProperty() {
        return paidAmount;
    }

    public DoubleProperty balanceAmountProperty() {
        return balanceAmount;
    }

  
/*
    public StringProperty createdAtProperty() {
        return createdAt;
    }

    public StringProperty pickupDateProperty() {
        return pickupDate;
    }

    public StringProperty deliveryDateProperty() {
        return deliveryDate;
    }
    
    */
}
