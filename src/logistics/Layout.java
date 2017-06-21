/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logistics;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import insidefx.undecorator.Undecorator;
import insidefx.undecorator.UndecoratorScene;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.RotateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import logistics.controllers.AddressFormController;
import logistics.controllers.AdminEmployeeInfoController;
import logistics.controllers.ConsigneeInfoController;
import logistics.controllers.CostsPageController;
import logistics.controllers.DashboardController;
import logistics.controllers.HomePageController;
import logistics.controllers.LoginController;
import logistics.controllers.MainLayoutController;
import logistics.controllers.UsersPageController;
import logistics.controllers.VehicleFormController;
import logistics.controllers.VehiclesPageController;
import logistics.controllers.OrderFormController;
import logistics.controllers.OrdersPageController;
import logistics.controllers.UserFormController;
import logistics.models.User;
import org.controlsfx.control.Notifications;

/**
 *
 * @author Chewa
 */
 public class Layout {
    
    
    private static BorderPane borderPane;
    private static AnchorPane dialogAnchorPane;
    private static UndecoratorScene mainUndecoratorScene;
    private static Stage dialogStage;
    private static FXMLLoader mainFxmlLoader ;
    private static FXMLLoader dashFxml;
    private static FXMLLoader loginFxmlLoader; 
    private FXMLLoader smallFxmlLoader;
    private static FXMLLoader dialogFxmlLoader;
    private static Stage mainStage;

    private static AnchorPane dashboard;
    
    
    
    private static LoginController loginController;
    private static MainLayoutController mainLayoutController;
    private static HomePageController homePageController;
  
     private static VehicleFormController vehicleFormController;
      private static OrderFormController orderFormController;
      private static UserFormController userFormController;
     private static AddressFormController addressFormController;
     private static AdminEmployeeInfoController adminEmployeeInfoController;
     private static ConsigneeInfoController ConsigneeInfoController;
     
     private static UsersPageController usersPageController;
     private static VehiclesPageController vehiclesPageController;
     private static DashboardController dashboardController;    
     private static CostsPageController costsPageController;
     private static OrdersPageController ordersPageController;
     private Notifications notificationBuilder;
     private Image img;
    private static AnchorPane myHomeTopAnchorPane;
    private static AnchorPane myOrderTopAnchorPane;
    private Label homeUserTypeLabel;
    private Label orderUserTypeLabel;
        
        
        
        public Layout() {

        }

    
    public void initLoginLayout(Stage loginStage,String resource){
                       
                  try {
                      loginFxmlLoader = new FXMLLoader();
                      loginFxmlLoader.setLocation(getClass().getResource(resource));
                      AnchorPane root = (AnchorPane)loginFxmlLoader.load();
                      final UndecoratorScene undecoratorScene = new UndecoratorScene(loginStage, StageStyle.UNDECORATED ,root, "/resources/stageUtilityDecoration.fxml");
                      undecoratorScene.setFadeInTransition();
                      loginStage.setScene(undecoratorScene);
                      loginStage.toFront();
                      loginStage.show();
                  } catch (IOException ex) {
                      ex.printStackTrace();
                  }
    }
    
    
    public void initMainLayout(){
        
        try {         
                      mainStage = new Stage();
                     mainFxmlLoader = new FXMLLoader();
                      mainFxmlLoader.setLocation(getClass().getResource("/resources/MainLayout.fxml"));
                      borderPane = (BorderPane) mainFxmlLoader.load();  
                      mainUndecoratorScene = new UndecoratorScene(mainStage, borderPane);
                      mainUndecoratorScene.getStylesheets().add(getClass().getResource("/resources/custombuttons.css").toExternalForm());
                      mainUndecoratorScene.setFadeInTransition();
                      mainStage.setScene(mainUndecoratorScene);
                      mainStage.setTitle("FAST TRUCK LOGISTICS");
                      mainStage.show();
                  } catch (IOException ex) {
                      ex.printStackTrace();
                  }
       
        
    }
     
    public StackPane loadPage(String resource)
    {
        StackPane stackPane = null;
        
        try {
             smallFxmlLoader = new FXMLLoader();
            smallFxmlLoader.setLocation(getClass().getResource(resource)); 
                stackPane = (StackPane) smallFxmlLoader.load();
            

        } catch (IOException ex) {
            Logger.getLogger(Layout.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
    
      return stackPane;  
    }
    
    
    
    
     
    public boolean showModal(String resource, ActionEvent event)  {
    
         try {
         dialogStage = new Stage();
         dialogFxmlLoader = new FXMLLoader();
        dialogFxmlLoader.setLocation(getClass().getResource(resource));
        dialogAnchorPane = (AnchorPane) dialogFxmlLoader.load();
        final UndecoratorScene dialogUndecoratorScene = new UndecoratorScene(dialogStage, StageStyle.UNDECORATED ,dialogAnchorPane, "/resources/stageUtilityDecoration.fxml");
        //dialogUndecoratorScene.getStylesheets().add("resources/custom.css");
        dialogUndecoratorScene.setAsStageDraggable(dialogStage, borderPane);
        dialogStage.setScene(dialogUndecoratorScene);
        dialogStage.setResizable(false);
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.initOwner(
        ((Node)event.getSource()).getScene().getWindow() );
        dialogStage.toFront();
        dialogStage.centerOnScreen();
        dialogStage.show();
                                    
            
        } catch (IOException ex) {
            Logger.getLogger(Layout.class.getName()).log(Level.SEVERE, null, ex);
        }
         
     return true;
}
  
    public Notifications getNotifcationTray(String notificationTitle)
    {
        
           img = new Image("/resources/smalltick.png");
           notificationBuilder = Notifications.create()
                    .title("Complete!")
                    .text(notificationTitle)
                    .graphic(new ImageView(img))
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_RIGHT);
           notificationBuilder.darkStyle();
           
           return notificationBuilder;
           
    }
    
    public void showNotification(String message)
    {
    
        getNotifcationTray(message).show();
    }
    
    public void initPopup(JFXDrawer drawer, String drawerResource)
    {
        
        try {
           FXMLLoader l = new FXMLLoader();
           l.setLocation(getClass().getResource(drawerResource));
           AnchorPane a = (AnchorPane)l.load();
           drawer.setSidePane(a);
            
            
        } catch (IOException ex) {
            Logger.getLogger(MainLayoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public void showPopup(JFXDrawer myDrawer)
    {
        if (!myDrawer.isShown())
        {
        myDrawer.toFront();
        myDrawer.open();
        }
        else
        {
            myDrawer.close();
        }
        
    }
    public AnchorPane loadTopBar()
    {
        
         try {   
            dashFxml = new FXMLLoader();
            dashFxml.setLocation(getClass().getResource("/resources/Dashboard.fxml"));
            dashboard = (AnchorPane)dashFxml.load();
            
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         return dashboard;
        
    }
    
    public void setButtonAnimation(JFXButton dashBoardButton)
    {
        RotateTransition rotation = new RotateTransition(Duration.seconds(0.5), dashBoardButton);
        rotation.setCycleCount(1);
        rotation.setByAngle(180);
        rotation.play();
    }
    public BorderPane getBorderPane()
    {
        return Layout.borderPane;
    }
    public Stage getDialogStage()
    {
        return dialogStage;
    }
    public void setDialogStage(Stage dialogStage)
    {
        Layout.dialogStage = dialogStage;
    }

  
    public VehiclesPageController getVehiclesPageController()
{
    return vehiclesPageController;
}
    
    public void setVehiclesPageController(VehiclesPageController vehiclesPageController)
{
    Layout.vehiclesPageController = vehiclesPageController;
}
    
     public UsersPageController getUsersPageController()
{
    return usersPageController;
}
    
    public void setUsersPageController(UsersPageController usersPageController)
{
    Layout.usersPageController = usersPageController;
}
    
     public CostsPageController getCostsPageController()
{
    return costsPageController;
}
    
    public void setCostsPageController(CostsPageController costsPageController)
{
    Layout.costsPageController = costsPageController;
}
    
     public OrdersPageController getOrdersPageController()
{
    return ordersPageController;
}
    
    public void setOrdersPageController(OrdersPageController ordersPageController)
{
    Layout.ordersPageController = ordersPageController;
}
    
    public OrderFormController getOrderFormController()
{
    return orderFormController;
}
    
    public void setOrderFormController(OrderFormController orderFormController)
{
    Layout.orderFormController = orderFormController;
}
   
      public MainLayoutController getMainLayoutController()
{
    return mainLayoutController;
}
    
    public void setMainLayoutController(MainLayoutController mainLayoutController)
{
    Layout.mainLayoutController = mainLayoutController;
}

    public LoginController getLoginController()
{
    return loginController;
}
    
    public void setLoginController(LoginController loginController)
{
    Layout.loginController = loginController;
}
    public void setHomePageController(HomePageController homePageController) {
        Layout.homePageController = homePageController;
    }

    public HomePageController getHomePageController() {
        return homePageController;
    }
    public AddressFormController getAddressFormController() {
        return addressFormController;
    }

    public void setAddressFormController(AddressFormController addressFormController) {
        Layout.addressFormController = addressFormController;
    }

    public AdminEmployeeInfoController getAdminEmployeeInfoController() {
        return adminEmployeeInfoController;
    }

    public void setAdminEmployeeInfoController(AdminEmployeeInfoController adminEmployeeInfoController) {
        Layout.adminEmployeeInfoController = adminEmployeeInfoController;
    }

    public ConsigneeInfoController getConsigneeInfoController() {
        return ConsigneeInfoController;
    }

    public void setConsigneeInfoController(ConsigneeInfoController ConsigneeInfoController) {
        Layout.ConsigneeInfoController = ConsigneeInfoController;
    }

    public UserFormController getUserFormController() {
        return userFormController;
    }

    public void setUserFormController(UserFormController userFormController) {
        Layout.userFormController = userFormController;
    }
     
    public VehicleFormController getVehicleFormController() {
        return vehicleFormController;
    }

    public void setVehicleFormController(VehicleFormController vehicleFormController) {
        Layout.vehicleFormController = vehicleFormController;
    }
    
    public DashboardController getDashboardController() {
        return dashboardController;
    }

    public void setDashboardController(DashboardController dashboardController) {
        Layout.dashboardController = dashboardController;
    }
    
    public Stage getMainStage() {
        return mainStage;
    }

    public void setMainStage(Stage mainStage) {
        Layout.mainStage = mainStage;
    }
    
    public AnchorPane getMyHomeTopAnchorPane()
    {
        return myHomeTopAnchorPane;
    }
    public void setMyHomeTopAnchorPane(AnchorPane myHomeTopAnchorPane)
    {
        Layout.myHomeTopAnchorPane = myHomeTopAnchorPane;
    }
    
     public AnchorPane getMyOrderTopAnchorPane()
    {
        return myOrderTopAnchorPane;
    }
    public void setMyOrderTopAnchorPane(AnchorPane myOrderTopAnchorPane)
    {
        Layout.myOrderTopAnchorPane = myOrderTopAnchorPane;
    }

    public Label setHomeUserTypeLabel() {
         return homeUserTypeLabel;
    }

    public Label setOrderUserTypeLabel() {
        return orderUserTypeLabel;
    }

   
}


