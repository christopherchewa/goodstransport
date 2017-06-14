/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logistics.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import logistics.Layout;
import logistics.LayoutInterface;

/**
 * FXML Controller class
 *
 * @author Chewa
 */
public class DashboardController implements Initializable, LayoutInterface {

    @FXML
    private Label lblUserType;
    @FXML
    private JFXButton btnDashboard;
    @FXML
    private JFXDrawer dashboardDrawer;
    private Layout layout;

    /**
     * Initializes the controller class.
     */
     @Override
    public void setLayout() {
        layout = new Layout();
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setLayout();
        layout.setDashboardController(this);
        
    }    

    @FXML
    private void btnDashboardAction(ActionEvent event) {
    
       
        layout.initPopup(dashboardDrawer, "/resources/DashboardButtons.fxml");
         layout.setButtonAnimation(btnDashboard);
        layout.showPopup(dashboardDrawer);

    }

    public Label getUserTypeLabel()
    {
        return lblUserType;
    }
   
    
}
