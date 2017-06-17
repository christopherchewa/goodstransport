/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logistics.models;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import javafx.beans.property.DoubleProperty;
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
    
    
    private StringProperty orderRefId;
    private StringProperty orderStatus;
   private DoubleProperty paidAmount;
   private DoubleProperty balanceAmount;
   
   
    
    public Order()
    {
        this(null, 0.0, 0.0);
    }
    
    public Order(String orderStatus, Double paidAmount, Double balanceAmount)
    {
       // this.orderRefId = new SimpleStringProperty(orderRefId);
        this.orderStatus = new SimpleStringProperty(orderStatus);
        this.paidAmount = new SimpleDoubleProperty(paidAmount);
        this.balanceAmount = new SimpleDoubleProperty(balanceAmount);
    }
    
       
    
       public StringProperty setOrderRefIdProperty() {
           
        String refNo = "#" + UUID.randomUUID().toString().replace("-", "").toUpperCase().substring(5, 11);
        try{
            Order orderObjList = Order.findFirst("order_ref_id = ? ", refNo);
            if(orderObjList.exists())
            {
                
                return orderRefId = setOrderRefIdProperty();
            }
        }
        catch(Exception e)
                {
                    orderRefId = new SimpleStringProperty(refNo);
                    
                    System.out.println("No similar Ref Id found" + " Is: " + e.getMessage() +"." );
                    System.out.println("From model: " + orderRefId);
                    System.out.println("Generating ref Id...");
                    System.out.println("Ref Id is set.");
                } 
        System.out.println("Ref id has been requested for and has been returned");
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
    
    
}
