/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logistics.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import logistics.DataManagement;
import logistics.DbConnection;
import logistics.Layout;
import logistics.models.User;
import logistics.LayoutInterface;
import logistics.models.Vehicle;


/**
 * FXML Controller class
 *
 * @author Chewa
 */
public class UsersPageController implements Initializable, LayoutInterface {

    final ObservableList<User> userList = FXCollections.observableArrayList();
    //FilteredList<User> filteredUserList = new FilteredList<>(userList, event -> true);
    private Layout layout;
    
   
    @FXML
    private TableView<User> usersTable;

    @FXML
    private TableColumn<User, String> colFirstName;

    @FXML
    private TableColumn<User, String>colLastName;

    @FXML
    private TableColumn<User, String> colPhoneNumber;

    @FXML
    private TableColumn<User, String>colUserType;
    
     @FXML
    private Button btnAddUser;

    @FXML
    private TableColumn<User, String> colEmail;
    @FXML
    private JFXButton btnDeleteUser;

    @Override
    public void setLayout() {
        layout = new Layout();
        layout.setUsersPageController(this);
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        colFirstName.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        colLastName.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        colPhoneNumber.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());
        colUserType.setCellValueFactory(cellData -> cellData.getValue().userTypeProperty());
        loadUsersTable();
        setLayout();
        
        
        
        
    
         
        
    }    
    
    @FXML
    private void btnAddUserAction(ActionEvent event) {
        
    try{
        layout.showModal("/resources/UserForm.fxml", event);
        }
        catch(Exception e)
        {
            System.out.println();
        }
    }


    
    
    
    
    
    public void loadUsersTable()
    {
      
      userList.removeAll(userList);
      List<User> userData = User.findAll();
      
      userData.forEach((u) -> {
          userList.add(new User(u.getString("user_id"),
                  u.getString("user_type"),
                  u.getString("first_name"), 
                  u.getString("last_name"),
                  u.getString("phone_number"),
                  u.getString("email"),
                  u.getBoolean("is_active"),
                  u.getString("username"),
                  u.getString("password"),
                  u.getString("consignee_bank_name"),
                  u.getString("consignee_bank_address"),
                  u.getString("driver_status")
          ));
        });
              
              usersTable.setItems(userList);
            
          
   }

    @FXML
    private void btnDeleteUserAction(ActionEvent event) {
    }

    


    
}
