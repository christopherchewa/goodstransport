/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logistics.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import logistics.DataManagement;
import logistics.Layout;
import logistics.LayoutInterface;
import logistics.models.User;
import logistics.models.Vehicle;

/**
 * FXML Controller class
 *
 * @author Chewa
 */
public class AdminEmployeeInfoController implements Initializable, LayoutInterface {

    @FXML
    private JFXButton btnAddressPage;
    @FXML
    private JFXTextField txtUsername;
    @FXML
    private JFXTextField txtPassword;
    @FXML
    private JFXTextField txtPasswordConfirm;

    private Layout layout;
    private User user;
    /**
     * Initializes the controller class.
     */
private final RequiredFieldValidator usernameValidator = new RequiredFieldValidator();
private final RequiredFieldValidator passwordValidator = new RequiredFieldValidator();
private final RequiredFieldValidator passwordConfirmValidator = new RequiredFieldValidator();
private final String validationMessage = "This field cannot be empty";
private UserFormController userFormController;

@Override
    public void setLayout() {
        layout = new Layout();
        
        layout.setAdminEmployeeInfoController(this);
      // userFormController = layout.getUserFormController();
       user =  layout.getUserFormController().getUser();
        System.out.println("drawer controller" + user);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setLayout();
        
      /*  txtUsername.getValidators().add(usernameValidator);
        txtPassword.getValidators().add(passwordValidator);
        txtPasswordConfirm.getValidators().add(passwordConfirmValidator);
        
        usernameValidator.setMessage(validationMessage);
        passwordValidator.setMessage(validationMessage);
        passwordConfirmValidator.setMessage(validationMessage); */
    }    

    @FXML
    private void btnAddressPageAction(ActionEvent event) {
        
        
        
       if ((!txtUsername.getText().isEmpty()) 
                && (!txtPassword.getText().isEmpty())
                && (!txtPasswordConfirm.getText().isEmpty())
                )
        {
           
            System.out.println(user);
            
            try{
                
                user.set("username", txtUsername.getText()).set("password", txtPassword.getText()).saveIt();
                layout.showModal("/resources/AddressForm.fxml", event);
               // ((Node) (event.getSource())).getScene().getWindow().hide();
                
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
            
        }
        
        else
        {
            txtUsername.validate();
            txtPassword.validate();
            txtPasswordConfirm.validate();
        }
            
    }

    public User getUserData()
    {
        return user;
    }

     
    
    
}
