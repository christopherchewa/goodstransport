/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logistics.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import logistics.DataManagement;
import logistics.DataManagementInterface;
import logistics.DbConnection;
import logistics.Layout;
import logistics.models.User;
import logistics.LayoutInterface;
import logistics.UserTypes;
import logistics.models.Address;
import logistics.models.Order;
import logistics.models.Vehicle;


/**
 * FXML Controller class
 *
 * @author Chewa
 */
public class UsersPageController implements Initializable, LayoutInterface, DataManagementInterface {

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
    private TableColumn<User, String>colUserType;
    
     @FXML
    private Button btnAddUser;

    @FXML
    private TableColumn<User, String> colEmail;
    @FXML
    private JFXButton btnDeleteUser;
    @FXML
    private ListView<String> addressesListView;
    @FXML
    private ListView<String> otherInfoListView;
    
    private final ObservableList<String> addList = FXCollections.observableArrayList();
    private final ObservableList<String> otherInfoList = FXCollections.observableArrayList();
    private DataManagement dataManagement;
    
    private String admin;
    private String employee;
    private String consigner;
    private String shipper;
    private String driver;
    private String consignee;
    
    
    
    @FXML
    private JFXTextField txtSearch;

    @Override
    public void setLayout() {
        layout = new Layout();
        layout.setUsersPageController(this);
        
    }
    @Override
    public void manageData() {
        dataManagement = new DataManagement();

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        manageData();
        addUserChangesListener();
        colFirstName.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        colLastName.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        colUserType.setCellValueFactory(cellData -> cellData.getValue().userTypeProperty());
        colEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
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


    public void addUserChangesListener()
    {
        
         //Add change listener
        usersTable.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            //Check whether item is selected and set value of selected item to Label
            if (usersTable.getSelectionModel().getSelectedItem() != null) {
                addList.removeAll(addList);
                otherInfoList.removeAll(otherInfoList);
                
                User user = User.findById(Integer.parseInt(newValue.userIDProperty().get()));
                //Addresses ListView
                List<Address> addresses = user.getAll(Address.class);
                if (addresses.isEmpty())
                    {
                        addList.add("No addresses available");
                    }
                    else
                    {
                     addresses.forEach((a)->{
                     String addressString = a.getString("state") + " " +a.getString("town") + " , " +a.getString("zipcode");
                     addList.add(addressString);
                
                        });
                    }
                //Other info List view
                if ((newValue.userTypeProperty().get().equals(UserTypes.ADMIN)) || (newValue.userTypeProperty().get().equals(UserTypes.EMPLOYEE)))
                        {
                            
                             String username = "Username: " + newValue.usernameProperty().get();
                             otherInfoList.add(username);
                        }
                else if((newValue.userTypeProperty().get().equals(UserTypes.DRIVER)))
                {
                     String driverStatus = "Driver Status: " + newValue.driverStatusProperty().get();
                     otherInfoList.add(driverStatus);
                }
                 else if((newValue.userTypeProperty().get().equals(UserTypes.CONSIGNEE)))
                {
                     String bankName = "Consignee Bank Name: " + newValue.consigneeBankNameProperty().get();
                     String bankAddress = "Consignee Bank Address: " + newValue.consigneeBankAddressProperty().get();
                     otherInfoList.addAll(bankName, bankAddress);
                }
                
               try
               {
                  String phoneNumber = "Phone Number: " + newValue.phoneNumberProperty().get();
                  String dateJoined = "Added on: " + newValue.createdAtProperty().get();
                  System.out.println(dateJoined);
                  otherInfoList.addAll(phoneNumber, dateJoined);
               }
               catch(Exception e)
                       {
                            System.out.println(e.getMessage());
                       }
                
                
                
                addressesListView.setItems(addList);
                otherInfoListView.setItems(otherInfoList);
             
                
                
            }
        });
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
                  u.getString("driver_status"),
                  u.getDate("created_at").toString(),
                  u.getDate("updated_at").toString()
          ));
        });
              
              usersTable.setItems(userList);
            
          
   }

    @FXML
    private void btnDeleteUserAction(ActionEvent event) {
    }

    @FXML
    private void searchFunctionAction(KeyEvent event) {
    }

    

    


    
}
