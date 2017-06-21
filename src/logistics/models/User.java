/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logistics.models;



import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.IdName;
import org.javalite.activejdbc.annotations.Many2Many;
import org.javalite.activejdbc.annotations.Table;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Chewa
 */

 

@Table("users")
@IdName("user_id")
@Many2Many(other=Order.class, join="order_users", sourceFKName="user_id", targetFKName="order_id")
public class User extends Model {
    
    private final StringProperty userID;
    private final StringProperty userType;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty phoneNumber;
    private final StringProperty email;
    private final BooleanProperty isActive;
    private final StringProperty username;
    private final StringProperty password;
    private final StringProperty consigneeBankName;
    private final StringProperty consigneeBankAddress;
     private final StringProperty driverStatus;
    private final SimpleStringProperty createdAt;
    private final SimpleStringProperty updatedAt;
    
   
    public User(String userID, String userType, String firstName, 
            String lastName, String phoneNumber, String email, 
            boolean isActive, String username, String password,
            String consigneeBankName, String consigneeBankAddress, String driverStatus, String createdAt, String updatedAt)
    {
        this.userID = new SimpleStringProperty(userID);
        this.userType = new SimpleStringProperty(userType);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.email = new SimpleStringProperty(email);
        this.isActive = new SimpleBooleanProperty(isActive);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.consigneeBankName = new SimpleStringProperty(consigneeBankName);
        this.consigneeBankAddress = new SimpleStringProperty(consigneeBankAddress);
        this.driverStatus = new SimpleStringProperty(driverStatus);
        this.createdAt = new SimpleStringProperty(createdAt);
        this.updatedAt = new SimpleStringProperty(updatedAt);
        
        
    }
    
    
    
    public User()
    {  
        
            this(null, null, null, 
               null, null, null, 
               true, null, null, 
               null, null, null,null, null);
    }
    
     
     public StringProperty userIDProperty()
    {
        return userID;
    }
    public StringProperty userTypeProperty()
    {
        return userType;
    }
    
    public StringProperty firstNameProperty()
    {
        return firstName;
    }
    
    public StringProperty lastNameProperty()
    {
        return lastName;
    }
    public StringProperty phoneNumberProperty()
    {
        return phoneNumber;
    }
    public StringProperty emailProperty()
    {
        return email;
    }
    public BooleanProperty isActiveProperty()
    {
        return isActive;
    }
    public StringProperty usernameProperty()
    {
        return username;
    }
    public StringProperty passwordProperty()
    {
        return password;
    }
    public StringProperty consigneeBankNameProperty()
    {
        return consigneeBankName;
    }
    public StringProperty consigneeBankAddressProperty()
    {
        return consigneeBankAddress;
    }
    public StringProperty driverStatusProperty()
    {
        return driverStatus;
    }

    public SimpleStringProperty createdAtProperty() {
        return createdAt;
    }

    public SimpleStringProperty updatedAtProperty() {
        return updatedAt;
    }
      
    
    
    
}
