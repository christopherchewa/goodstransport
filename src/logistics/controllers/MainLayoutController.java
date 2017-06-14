/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logistics.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXPopup;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import logistics.DataManagement;
import logistics.Layout;
import logistics.models.User;
import logistics.LayoutInterface;
import logistics.models.Vehicle;


/**
 * FXML Controller class
 *
 * @author Chewa
 */
public class MainLayoutController implements Initializable, LayoutInterface  {

    private Layout layout;
    
    
   
    
     @FXML
    private JFXButton btnMyProfile;

    @FXML
    private JFXButton btnOrders;

    @FXML
    private JFXButton btnUsers;

    @FXML
    private JFXButton btnVehicles;

    @FXML
    private JFXButton btnCosts;

    @FXML
    private JFXButton btnReports;

    
    @FXML
    private Label lblUsername;
    
    
    
    private static User user;
    
    private String usernameDisplay;
    private LoginController loginController;
    
    @FXML
    private JFXButton btnAccount;
    
    
    private static StackPane usersStackPane;
    private static StackPane homeStackPane;
    private static StackPane vehiclesStackPane;
    private static StackPane ordersStackPane;
    private static StackPane orderFormStackPane;
    private static StackPane costsStackPane;
    private static StackPane reportsStackPane;
    
    
    @FXML
    private JFXButton btnLogout;
    @FXML
    private FontAwesomeIconView logoutIcon;
   
    
    
    
            
    @Override
    public void setLayout() {
        layout = new Layout();
       
        
        
        //
        
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setLayout();
//        layout.initPopup(logoutDrawer, "/resources/LogoutButton.fxml");
        setUsernameLabel(layout.getLoginController().getUsername());
//        lblUserType.setText(layout.getLoginController().getUserType());
        if ("Employee".equals(layout.getLoginController().getUsername()))
            {
                btnCosts.setVisible(false);
                btnReports.setVisible(false);
            }
    }    
    
    
    
    public void setUsernameLabel(String username)
    {
        lblUsername.setText(username);
    }
    @FXML
    void btnOrdersAction(ActionEvent event) {
        layout.getBorderPane().setCenter(ordersStackPane);
    }
    
    void btnReportsAction(ActionEvent event) {

    }
    
    @FXML
     void btnMyProfileAction(ActionEvent event) {
      
         
        
        layout.getBorderPane().setCenter(homeStackPane);
    }
    @FXML
    void btnCostsAction(ActionEvent event) {
        
        
        layout.getBorderPane().setCenter(costsStackPane);
    }
    
    @FXML
    void btnUsersAction(ActionEvent event) {
        
        
        layout.getBorderPane().setCenter(usersStackPane);
    }

    @FXML
    void btnVehiclesAction(ActionEvent event) {
        
        
       layout.getBorderPane().setCenter(vehiclesStackPane);  
    }

     
    

    @FXML
    private void btnAccountAction(ActionEvent event) {
        
       //layout.loadPage("/resources/viewProfile.fxml");
        
          
            
        
        
        
    }

    @FXML
    private void btnLogoutAction(ActionEvent event) {
    }

    
     public void setHomeStackPane(StackPane homeStackPane)
    {
        MainLayoutController.homeStackPane = homeStackPane;
    }
    public void setUsersStackPane(StackPane usersStackPane)
    {
        MainLayoutController.usersStackPane = usersStackPane;
    }
    public void setVehiclesStackPane(StackPane vehiclesStackPane)
    {
        MainLayoutController.vehiclesStackPane =  vehiclesStackPane;
    }
    public void setOrdersStackPane(StackPane ordersStackPane)
    {
        MainLayoutController.ordersStackPane = ordersStackPane;
    }
     public void setOrderFormStackPane(StackPane orderFormStackPane)
    {
        MainLayoutController.orderFormStackPane = orderFormStackPane;
    }
    public void setCostsStackPane(StackPane costsStackPane)
    {
        MainLayoutController.costsStackPane = costsStackPane;
    }
    
    public void setReportsStackPane(StackPane reportsStackPane)
    {
        MainLayoutController.reportsStackPane = reportsStackPane;
    }

    @FXML
    private void logoutButtonOnHover(MouseEvent event) {
        
        logoutIcon.setFill(Paint.valueOf(Color.RED.toString()));
    }

    @FXML
    private void logoutButtonOffHover(MouseEvent event) {
        logoutIcon.setFill(Paint.valueOf(Color.WHITE.toString()));
    }
            
          
  

}