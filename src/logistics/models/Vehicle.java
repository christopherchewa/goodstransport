/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logistics.models;

import javafx.beans.property.StringProperty;
import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.IdName;
import org.javalite.activejdbc.annotations.Table;
import org.javalite.activejdbc.annotations.Table;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;



/**
 *
 * @author Chewa
 */

@Table("vehicles")
@IdName("vehicle_id")
public class Vehicle extends Model {

    
    
   
    private StringProperty numberPlate;
    private StringProperty vehicleStatus;
   
   
    
    public Vehicle()
    {
        this(null, null);
    }
    
    public Vehicle(String numberPlate, String vehicleStatus)
    {
        
        this.numberPlate = new SimpleStringProperty(numberPlate);
        this.vehicleStatus = new SimpleStringProperty(vehicleStatus);
    }
     
    
    public StringProperty numberPlateProperty()
    {
        return numberPlate;
    }
    
    public StringProperty vehicleStatusProperty()
    {
        return vehicleStatus;
    }
    
}
