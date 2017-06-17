package logistics;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.jfoenix.controls.JFXComboBox;
import insidefx.undecorator.UndecoratorScene;
import java.io.IOException;
import logistics.models.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import logistics.controllers.DashboardButtonsController;
import logistics.controllers.LoginController;
import logistics.controllers.MainLayoutController;
import logistics.controllers.UsersPageController;
import logistics.models.Vehicle;

/**
 *
 * @author Chewa
 */
public class Transport extends Application implements LayoutInterface{
            
              private Stage loginStage, mainStage;
              private BorderPane mainLayout;
              private Layout layout;
              private final String homePage = "/resources/HomePage.fxml";
              private final String usersPage = "/resources/UsersPage.fxml";
              private final String costsPage = "/resources/CostsPage.fxml";
              private final String vehiclesPage = "/resources/VehiclesPage.fxml";
              private final String ordersPage = "/resources/OrdersPage.fxml";
              private final String orderForm = "/resources/OrderForm.fxml";
              
              private static StackPane homeStackPane;
              private static StackPane usersStackPane;
              private static StackPane vehiclesStackPane;
              private static StackPane costsStackPane;
              private static StackPane ordersStackPane;
              private static StackPane orderFormStackPane;
              private static StackPane reportsStackPane;
              private LoginController loginController;
              private MainLayoutController mainLayoutController;
              private DashboardButtonsController dashboardButtonsController;
              private UsersPageController usersPageController;

    public Transport() {
        this.mainLayoutController = new MainLayoutController();
        this.loginController = new LoginController();
         this.dashboardButtonsController= new DashboardButtonsController();
         this.usersPageController = new UsersPageController();
    }
              
              
   @Override
    public void setLayout() {
                
                layout = new Layout();
                layout.initLoginLayout(this.loginStage, "/resources/Login.fxml");
                loadLayouts();
                
                
                
                
                
    }
  
    @Override
    public void start(Stage loginStage) throws Exception {
        
        this.loginStage = loginStage;
        setLayout();
       
    }
    
    public void loadLayouts()
    {
      
        homeStackPane = layout.loadPage(homePage);
      loginController.setHomeStackPane(homeStackPane);
        mainLayoutController.setHomeStackPane(homeStackPane);
        
        usersStackPane = layout.loadPage(usersPage);
        mainLayoutController.setUsersStackPane(usersStackPane);
        
        vehiclesStackPane = layout.loadPage(vehiclesPage);
        mainLayoutController.setVehiclesStackPane(vehiclesStackPane);
        
        orderFormStackPane = layout.loadPage(orderForm);
        dashboardButtonsController.setOrderFormStackPane(orderFormStackPane);
        
        ordersStackPane = layout.loadPage(ordersPage);
        mainLayoutController.setOrdersStackPane(ordersStackPane);
        
        costsStackPane = layout.loadPage(costsPage);
        mainLayoutController.setCostsStackPane(costsStackPane);
        
        
        
        
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        launch(args);
        
        
       
       
    }

    



    

    
    
}
