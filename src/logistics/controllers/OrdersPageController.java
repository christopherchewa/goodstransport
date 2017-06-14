/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logistics.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import logistics.models.OrderUser;
import logistics.models.User;

/**
 * FXML Controller class
 *
 * @author Chewa
 */
public class OrdersPageController implements Initializable {

    @FXML
    private TableView<?> orderUsersTable;
    @FXML
    private TableColumn<?, ?> colOrderId;
    @FXML
    private TableColumn<?, ?> colEmail;
    @FXML
    private TableColumn<?, ?> colUserType;
    
    private User user;
    /**
     * Initializes the controller class.
     */
    
    
     private final HashMap<String, String> orderUsersMap = new HashMap<>();
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        List<OrderUser> orderUsers = OrderUser.findAll();
        
        orderUsers.forEach((ou)->{
            
            int userId = ou.getInteger("user_id");
            
             User userObj = User.findFirst("user_id = ?", userId);
             String email = userObj.getString("email");
             String userType = userObj.getString("user_type");
             
            orderUsersMap.put(email , userType);
            System.out.println("User Id: "+ userId + " Email: "+ email + " " + " User Type: " + userType);
            
        });
       
        
        
        
       setupTable();
    }    
    
    
    public void setupTable()
    {
       
       
    }
}
