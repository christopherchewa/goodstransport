/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logistics.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import logistics.DbConnection;
import logistics.Transport;
import logistics.models.User;
import com.jfoenix.validation.RequiredFieldValidator;
import insidefx.undecorator.UndecoratorScene;
import java.util.List;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.util.Duration;
import logistics.DataManagement;
import logistics.Layout;
import logistics.controllers.MainLayoutController;
import logistics.models.Address;
import logistics.models.Vehicle;
import logistics.LayoutInterface;
import org.controlsfx.control.Notifications;


/**
 *
 * @author Chewa
 */
public class LoginController implements Initializable, LayoutInterface {
    
    @FXML
    private JFXTextField txtUsername;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXButton btnLogin;

    
    private Layout layout;
    
    private Stage mainStage;
    private BorderPane borderPane;
    private static StackPane homeStackPane;
    private User user;
    private List<Address> userAddresses;
    
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String userType;
    private String username;
    private String password;
    
    private String state;
    private String town;
    private String zipcode;
    
    
     
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        DbConnection.connect();
        
        RequiredFieldValidator usernameValidator = new RequiredFieldValidator();
        RequiredFieldValidator passwordValidator = new RequiredFieldValidator();
        
        txtUsername.getValidators().add(usernameValidator);
        usernameValidator.setMessage("Please enter a valid username");
        txtPassword.getValidators().add(passwordValidator);
        passwordValidator.setMessage("Please enter a valid password");
        
        setLayout();
        //DbConnection.disconnect();
    }    

    @Override
    public void setLayout() {
        
        layout = new Layout();
        layout.setLoginController(this);
        
        
       
      
        
    }

    
    @FXML
    public void btnLoginAction(ActionEvent event) throws Exception {
       
       String usernameString = txtUsername.getText();
      String passwordString = txtPassword.getText(); 
        
        
           if(validated(usernameString, passwordString))
           {
               layout.initMainLayout();
           layout.getBorderPane().setCenter(homeStackPane); 
           layout.getDashboardController().getUserTypeLabel().setText(userType);
           }
           
         
        
    }
    
    public void setHomeStackPane(StackPane homeStackPane)
    {
        LoginController.homeStackPane = homeStackPane;
    }
    public StackPane getHomeStackPane()
    {
        return homeStackPane;
    }
   
  public boolean validated(String usernameText, String passwordText)
  { 
        
      if ((!usernameText.isEmpty())&&(!passwordText.isEmpty()))
      {   
             
            try
              {
                    user = User.findFirst("username = ? ", usernameText);
                    username = user.getString("username");
                    password = user.getString("password");
                    userType = user.getString("user_type");
                    firstName = user.getString("first_name");
                    lastName = user.getString("last_name");
                    phoneNumber= user.getString("phone_number");
                    email= user.getString("email");
                    userAddresses = user.getAll(Address.class);
                    int addLen = userAddresses.size();
                    userAddresses.forEach((a) -> {
                        state = a.getString("state");
                        town = a.getString("town");
                        zipcode = a.getString("zipcode");
                        String adds = state + " " + town + " " +zipcode + "\n";    
        });
                    
                    
                    
                    if(usernameText.equals(username) && passwordText.equals(password))
                      {
                          return true;
                      }
                    else
                    {
                       System.out.println("password is incorrect for user"); 
                        return false;
                    }
                        
              }
            catch(Exception e)
              {     
                  System.out.println("user does not exist");
                  System.out.println(e.getMessage());
                  return false;
              }       
      }
      else
      {
      txtUsername.validate();
      txtPassword.validate();
      
       return false;
      }
      
      
  }
  

 public String getUsername(){
  
  return username;
} 
 
 
public String getUserType(){
  
    return userType;
  
} 

 
  }

    
 


