/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logistics.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import logistics.Layout;
import logistics.LayoutInterface;
import logistics.models.Order;
import logistics.models.OrderUser;
import logistics.models.User;

/**
 * FXML Controller class
 *
 * @author Chewa
 */
public class OrdersPageController implements Initializable, LayoutInterface {

   
     
     
    @FXML
    private TableView<Order> ordersTable;
    
    @FXML
    private TableColumn<Order, String> colOrderRefId;
    
    @FXML
    private TableColumn<Order, String> colOrderStatus;
    @FXML
    private TableColumn<Order, Double> colPaid;
    @FXML
    private TableColumn<Order, Double> colBalance;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private Label lblShipper;
    @FXML
    private Label lblConsigner;
    @FXML
    private JFXComboBox<String> cbOrderStatus;
    @FXML
    private Label lblOrderId;
    @FXML
    private Label lblConsigee;
    @FXML
    private ListView<String> driversListView;
    @FXML
    private JFXListView<String> freightsListView;
    @FXML
    private JFXListView<String> costsListView;
    @FXML
    private Label lblPickupDate;
    @FXML
    private Label lblOrderDate;
    @FXML
    private Label lblDeliveryDate;
    
    private ObservableList<Order> ordersList = FXCollections.observableArrayList();
    
     private User user;
     private Layout layout;
     
     @Override
    public void setLayout() {
        layout = new Layout();
        layout.setOrdersPageController(this);
    }
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        setLayout();
       colOrderStatus.setCellValueFactory(cellData -> cellData.getValue().orderStatusProperty());
       colPaid.setCellValueFactory(cellData -> cellData.getValue().paidAmountProperty().asObject());
       colBalance.setCellValueFactory(cellData -> cellData.getValue().balanceAmountProperty().asObject());
        
       setupTable();
       
       
       
       
    }    
    
    
    public void setupTable()
    {
        
        ordersList.removeAll(ordersList);
      
            List <Order> ordersData = Order.findAll();
       
       ordersData.forEach((o)->{
        
        ordersList.add(new Order(o.getString("order_status"),
        o.paidAmountProperty().doubleValue(),
        o.balanceAmountProperty().doubleValue()));   
         
        System.out.println(o);
    });
       
      // System.out.println(ordersList);
       ordersTable.setItems(ordersList);
       
       
    }

    @FXML
    private void btnUpdateAction(ActionEvent event) {
    }

    
}
