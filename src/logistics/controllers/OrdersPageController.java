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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import logistics.Layout;
import logistics.LayoutInterface;
import logistics.UserTypes;
import logistics.models.Cost;
import logistics.models.Freight;
import logistics.models.Order;
import logistics.models.OrderUser;
import logistics.models.User;
import logistics.models.Vehicle;

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
    private Label lblShipper;
    @FXML
    private Label lblConsigner;
    @FXML
    private JFXComboBox<String> cbOrderStatus;
    @FXML
    private Label lblOrderId;
    @FXML
    private Label lblConsignee;
    @FXML
    private JFXListView<String> driversListView;
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
    
    private final ObservableList<Order> ordersList = FXCollections.observableArrayList();
    
    private final ObservableList<User> usersList = FXCollections.observableArrayList();
    
    private final ObservableList<String> costsList = FXCollections.observableArrayList();
    private final ObservableList<String> freightsList = FXCollections.observableArrayList();
    
    //for the drivers list view
    private final ObservableList<String> driversVehiclesList = FXCollections.observableArrayList();
    
     private User user;
     private Layout layout;
   
    @FXML
    private AnchorPane myOrderTopAnchorPane;
     
     @Override
    public void setLayout() {
        layout = new Layout();
        layout.setOrdersPageController(this);
//        myOrderTopAnchorPane.getChildren().add(layout.loadTopBar());
         
    }
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        setLayout();
        colOrderRefId.setCellValueFactory(cellData -> cellData.getValue().getOrderRefIdProperty());
       colOrderStatus.setCellValueFactory(cellData -> cellData.getValue().orderStatusProperty());
       colPaid.setCellValueFactory(cellData -> cellData.getValue().paidAmountProperty().asObject());
       colBalance.setCellValueFactory(cellData -> cellData.getValue().balanceAmountProperty().asObject());
        
       setupTable();
       addChangeListener();
       
       
       
       
    }    
    
    
    public void setupTable()
    {
        
        ordersList.removeAll(ordersList);
      
            List <Order> ordersData = Order.findAll();
       
       ordersData.forEach((o)->{
        
        ordersList.add(new Order(o.getInteger("order_id").toString(),
        o.getString("order_ref_id"),
                o.getString("order_status"),     
                o.getDouble("paid"),
                o.getDouble("balance")
               ));   
         
        System.out.println(o);
    });
       
      
       ordersTable.setItems(ordersList);
       
       
    }


    @FXML
    public void tableOrdersAction(MouseEvent event) throws Exception {
       
          
          
    }

    public void addChangeListener()
    {
         //Add change listener
        ordersTable.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            
            if (ordersTable.getSelectionModel().getSelectedItem() != null) {
                
                    //clear lists
                    driversVehiclesList.removeAll(driversVehiclesList);
                    costsList.removeAll(costsList);
                    freightsList.removeAll(freightsList);
                    
                    //get selected order id from property
                    int orderId = Integer.parseInt(newValue.orderIdProperty().get());
                    Order order = Order.findById(orderId);
                    
                    
                    
                    List<User> usersModel = order.getAll(User.class);
                    //get all costs of an order
                    List<Cost> costsModel = order.getAll(Cost.class);
                    costsModel.forEach((cm)->{
                    String costInfo = cm.getString("cost_name");
                    costsList.add(costInfo);
                     });
                    
                    //get all freight of an order
                    List<Freight> freightsModel = order.getAll(Freight.class);
                    freightsModel.forEach((fm)->{
                        String freightInfo = "Freight: " + fm.getString("name") + "     " + "Weight: " + fm.getDouble("weight")  + " KGs";
                        freightsList.add(freightInfo);
                    });
                    
                    
                    
                    
                    
                    usersModel.forEach((u)->{
                        
                        //this is to transfer the users from a List to an observable list
                        usersList.add(u);
                        String userType = u.getString("user_type");
                         int userId = u.getInteger("user_id");

                        //if else: driver, add to driverlist to display driver list view  etc
                        switch (userType) {
                            case UserTypes.DRIVER:
                                OrderUser orderVehicleObj = OrderUser.findFirst("order_id = ? and user_id = ?", orderId, userId);
                                Vehicle driverVehicle = Vehicle.findFirst("vehicle_id = ?", orderVehicleObj.getInteger("vehicle_id"));
                                String driverInfo = u.getString("first_name") + " " +u.getString("last_name") + " - " + driverVehicle.getString("number_plate");
                                driversVehiclesList.add(driverInfo);
                                break;
                            case UserTypes.CONSIGNER:
                                 String consignerInfo = u.getString("first_name") + " " +u.getString("last_name");
                                lblConsigner.setText(consignerInfo);
                                
                                break;
                            case UserTypes.SHIPPER:
                                 String shipperInfo = u.getString("first_name") + " " +u.getString("last_name");
                                lblShipper.setText(shipperInfo);
                                
                                break;
                            case UserTypes.CONSIGNEE:
                                 String consigneeInfo = u.getString("first_name") + " " +u.getString("last_name");
                                lblConsignee.setText(consigneeInfo);
                                break;
                           /* case UserTypes.ADMIN:
                                 String adminInfo = u.getString("first_name") + " " +u.getString("last_name") + " - " + userType;
                                lblServedBy.setText(adminInfo);
                                break;
                                */
                                //EMPLOYEE
                            default:
                              /*  String employeeInfo = u.getString("first_name") + " " +u.getString("last_name") + " - " + userType;
                                lblServedBy.setText(employeeInfo);*/
                                break;
                        }

                    });
                    driversListView.setItems(driversVehiclesList);
                    costsListView.setItems(costsList);
                    freightsListView.setItems(freightsList);
                    
                    //set Order Ref Id
                    lblOrderId.setText(newValue.getOrderRefIdProperty().get());
                    cbOrderStatus.getSelectionModel().select(newValue.orderStatusProperty().get());
                
            }
        });
    }

    public AnchorPane getMyOrderTopAnchorPane() {
        return myOrderTopAnchorPane;
    }

    public void setMyOrderTopAnchorPane(AnchorPane myOrderTopAnchorPane) {
        this.myOrderTopAnchorPane = myOrderTopAnchorPane;
    }

    
    
    
    
}
