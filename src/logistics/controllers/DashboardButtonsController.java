/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logistics.controllers;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import logistics.Layout;
import logistics.LayoutInterface;

/**
 * FXML Controller class
 *
 * @author Chewa
 */
public class DashboardButtonsController implements Initializable, LayoutInterface {

    @FXML
    private JFXButton btnNewOrder;
    @FXML
    private JFXButton btnNewCost;
    private Layout layout;
    private static StackPane orderFormStackPane;

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
        
    }    

    @FXML
    private void btnNewOrderAction(ActionEvent event) {
        layout.getBorderPane().setCenter(orderFormStackPane);
    }

    @FXML
    private void btnNewCostAction(ActionEvent event) {
    }

    public void setOrderFormStackPane(StackPane orderFormStackPane)
    {
        DashboardButtonsController.orderFormStackPane = orderFormStackPane;
    }
    
}
