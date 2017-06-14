/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logistics.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TableView;
import logistics.DataManagement;
import logistics.Layout;
import logistics.models.User;
import logistics.LayoutInterface;
import logistics.models.Vehicle;

/**
 * FXML Controller class
 *
 * @author chewa
 */
public class UserFormController implements Initializable, LayoutInterface {

   @FXML
    private JFXTextField txtFirstName;

    @FXML
    private JFXTextField txtLastName;

    @FXML
    private JFXTextField txtPhoneNumber;

    @FXML
    private JFXTextField txtEmail;

    private JFXTextField txtUsername;


    @FXML
    private JFXComboBox<String> cbUserType;

    

    
private String userType;
private String firstName;
private String lastName;
private String phoneNumber;
private String email;


private User user;
private Layout layout;
 
 
    
private boolean userIsSaved = false;
private boolean isStaff = false;
private boolean isConsigner = false;
private boolean isShipper = false;
private boolean isDriver = false;
private boolean isConsignee = false;

 
//DriverStatus combobox
private final String driverUnavailable = "Available";
private final String driverAvailable = "Unavailable";

//UserType combobox
private final String admin = "Admin";
private final String employee = "Employee";
private final String consigner = "Consigner";
private final String shipper = "Shipper";
private final String driver = "Driver";
private final String consignee = "Consignee";

//Empty textbox validationMessage
private final String validationMessage = "This field cannot be empty";


//Empty textBox validators
private final RequiredFieldValidator userTypeValidator = new RequiredFieldValidator();
private final RequiredFieldValidator firstNameValidator = new RequiredFieldValidator();
private final RequiredFieldValidator lastNameValidator = new RequiredFieldValidator();
private final RequiredFieldValidator phoneNumberValidator = new RequiredFieldValidator();
private final RequiredFieldValidator emailValidator = new RequiredFieldValidator();
private final RequiredFieldValidator usernameValidator = new RequiredFieldValidator();
private final RequiredFieldValidator passwordValidator= new RequiredFieldValidator();
private final RequiredFieldValidator passwordConfirmValidator = new RequiredFieldValidator();
private final RequiredFieldValidator bankNameValidator = new RequiredFieldValidator();
private final RequiredFieldValidator bankAddressValidator = new RequiredFieldValidator();
private final RequiredFieldValidator driverStatusValidator = new RequiredFieldValidator();
        
   
    
ObservableList<RequiredFieldValidator> userFormValidations = FXCollections.observableArrayList(
           userTypeValidator,
           firstNameValidator, 
           lastNameValidator,
           phoneNumberValidator,
           emailValidator
           
);
   
ObservableList<String> driverStatusList = FXCollections.observableArrayList(driverAvailable, driverUnavailable);
ObservableList<String> userTypeList = FXCollections.observableArrayList(
            admin, 
            employee,
            consigner,
            shipper ,
            driver, 
            consignee);
    @FXML
    private JFXButton btnNextStep;
    @FXML
    private JFXDrawer userDrawer;
    @FXML
    private Label lblUserType;

   

    @Override
    public void setLayout()
    {
        layout = new Layout();
         layout.setUserFormController(this);
         
         
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setLayout();
        
         
        cbUserType.setItems(userTypeList);
            
        txtFirstName.getValidators().add(firstNameValidator);
        txtLastName.getValidators().add(lastNameValidator);
        txtPhoneNumber.getValidators().add(phoneNumberValidator);
        txtEmail.getValidators().add(emailValidator);
       
       
        userFormValidations.forEach((ufv) -> {
            ufv.setMessage(validationMessage);
        });
        
                
    }   
    
    @FXML
    private void btnNextStepAction(ActionEvent event) {
        
        saveUser(event);


       
    }
    
    @FXML
    void cbUserTypeAction(ActionEvent event) {
        
        
       
       
    }
    
    public void saveUser(ActionEvent event)
    {
        
                firstName = txtFirstName.getText();
        lastName = txtLastName.getText();
        email = txtEmail.getText();
        phoneNumber = txtPhoneNumber.getText();
        userType = cbUserType.getSelectionModel().getSelectedItem();
        
       
       
       if ((!firstName.isEmpty()) 
                 && (!lastName.isEmpty()) 
                 && (!email.isEmpty()) 
                 && (!phoneNumber.isEmpty())
                 && (!userType.isEmpty()))
                 
        {
        
                lblUserType.setVisible(false);
                                   try{
                               user = new User();
                           user.set("user_type", userType);
                            if(userType.equals(driver))
                           {
                               user.set("driver_status", "Available");
                           }
                           user.set("first_name", firstName).set("last_name", lastName)
                          .set("phone_number", phoneNumber).set("email", email).set("is_active",true).saveIt();
                           
                           System.out.println(user);
                            
                           if (userType.equals(admin) || userType.equals(employee))
                           {
                            layout.initPopup(userDrawer, "/resources/AdminEmployeeInfo.fxml");
                           layout.showPopup(userDrawer);
                                     
                           }
                           else if(userType.equals(consignee))
                        {
                               layout.initPopup(userDrawer, "/resources/ConsigneeInfo.fxml"); 
                               layout.showPopup(userDrawer);
                                }
                           else
                           {
                               layout.showModal("/resources/AddressForm.fxml", event);
                           }
                          
                           if(userDrawer.isShown())
                        {
                            txtFirstName.setDisable(true);
                        txtLastName.setDisable(true);
                        }
                           
                        txtFirstName.setDisable(true);
                        txtLastName.setDisable(true);
                        
                           }
                           catch(Exception e)
                           {
                               System.out.println(e.getMessage());
                               System.out.println("error");
                               

                           } 
                       
            
        }
        else
        {
       txtFirstName.validate();
       txtLastName.validate();
       txtEmail.validate();
       txtPhoneNumber.validate();
       lblUserType.setVisible(true);
       
       
                    
                    
                        
 
                   
      // cbUserType.getSelectionModel().clearSelection();
        }
    }
   
    public User getUser()
    {
        return user;
    }
    
   
    
  /*  
    public boolean userSaveValidate(String userType, String firstName, String lastName, String email, String phoneNumber)
    {
        
    }
   */

    
    
    
}
 
    

