/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logistics.models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.IdName;
import org.javalite.activejdbc.annotations.Table;
import org.javalite.activejdbc.annotations.Many2Many;


/**
 *
 * @author Chewa
 */

@Table("costs")
@IdName("cost_id")
@Many2Many(other=Order.class, join="order_costs", sourceFKName="cost_id", targetFKName="order_id")
public class Cost extends Model {
    
    private final StringProperty costID;
    private final StringProperty costName;
    private final StringProperty unitMeasurement;
    private final DoubleProperty rate;
    
    
            
            
            
    public Cost()
    {
        this(null, null, null, 0.00);
    }
    
    
    public Cost(String costID, String costName, String unitMeasurement, Double rate)
    {
        this.costID = new SimpleStringProperty(costID);
        this.costName = new SimpleStringProperty(costName);
        this.unitMeasurement = new SimpleStringProperty(unitMeasurement);
        this.rate = new SimpleDoubleProperty(rate);
        
        
    }
    
    
    public StringProperty getCostIDProperty() {
        return costID;
    }
    
    public StringProperty getCostNameProperty() {
        return costName;
    }
   
    public StringProperty getUnitMeasurementProperty() {
        return unitMeasurement;
    }
   
    public DoubleProperty getRateProperty() {
        return rate;
    }
    
    
    
    
    
    
}
