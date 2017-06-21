/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logistics.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import logistics.DataManagement;
import logistics.DataManagementInterface;
import logistics.Layout;
import logistics.LayoutInterface;
import logistics.models.Address;
import logistics.models.Cost;
import logistics.models.Freight;
import logistics.models.Order;
import logistics.models.OrderCost;
import logistics.models.OrderUser;
import logistics.models.User;
import logistics.models.Vehicle;
import org.javalite.activejdbc.Model;

/**
 * FXML Controller class
 *
 * @author chewa
 */
public class OrderFormController implements Initializable, LayoutInterface, DataManagementInterface {

    @FXML
    private JFXComboBox<String> cbConsigner;
    @FXML
    private JFXButton btnAddConsigner;
    @FXML
    private Label lblOrderId;
    @FXML
    private JFXComboBox<String> cbShipper;
    @FXML
    private JFXDatePicker pickupDate;
    @FXML
    private JFXButton btnAddShipper;
    @FXML
    private JFXComboBox<String> cbConsignee;
    @FXML
    private JFXButton btnAddConsignee;
    @FXML
    private JFXComboBox<String> cbDropOff;
    @FXML
    private JFXButton btnAddDropOff;
    @FXML
    private JFXComboBox<String> cbDriver;
    @FXML
    private JFXComboBox<String> cbVehicle;
    @FXML
    private JFXButton btnAddDirver;
    @FXML
    private JFXButton btnAddVehicle;
    @FXML
    private JFXButton btnAssignDriver;
    @FXML
    private JFXTextField txtFreightName;
    @FXML
    private JFXTextArea txtFreightDescription;
    @FXML
    private JFXTextField txtFreightWeight;
    @FXML
    private JFXButton btnAddFreight;
    @FXML
    private Label lblCost;
    @FXML
    private JFXListView<String> freightListView;
    @FXML
    private JFXListView<String> driverListView;
    @FXML
    private JFXButton btnRemoveDriver;
    @FXML
    private JFXButton btnRemoveFreight;
    @FXML
    private JFXButton btnCompleteOrder;
    
    private User user;
    private Order order;
private Freight freight;
    private Layout layout;
    private DataManagement dataManagement;
    
    private final String admin = "Admin";
    private final String employee = "Employee";
    private final String consigner = "Consigner";
    private final String shipper = "Shipper";
    private final String driver = "Driver";
    private final String consignee = "Consignee";
    private final String available = "Available";
    
    private int consignerId;
    
    
    private List<User> adminsList;
    private List<User> employeesList;
    private List<User> consignersList;
    private List<User> shippersList;
    private List<User> driversList;
    private List<User> consigneesList;
    private List<Vehicle> vehiclesList;
    
    final ObservableList<String> consignerFullNamesList = FXCollections.observableArrayList();
    final ObservableList<String> shipperFullNamesList = FXCollections.observableArrayList();
    final ObservableList<String> driverFullNamesList = FXCollections.observableArrayList();
    final ObservableList<String> consigneeFullNamesList = FXCollections.observableArrayList();
    final ObservableList<String> vehicleNumberPlateList = FXCollections.observableArrayList();
    final ObservableList<String> addressList = FXCollections.observableArrayList();
    
   // private final HashMap<String, String> consigneeMap = new HashMap<String, String>();
    private final HashMap<String, Integer> vehicleMap = new HashMap<>();
    
    
    @FXML
    private Label lblCost1;
    private JFXDrawer costsDrawer;
    @FXML
    private JFXListView<String> costsListView;
    @FXML
    private JFXComboBox<String> cbCosts;
    @FXML
    private JFXButton btnRemoveCost;
    
    private List<Cost> costs;
    
    private ObservableList<String> costList = FXCollections.observableArrayList();
    @FXML
    private JFXButton btnAddcost;
    @FXML
    private JFXTextField txtMultiplier;
    
     private User consigneeObj;
    
    private String selectedConsigner;
    private String consignerEmail;
    
    private String selectedShipper;
    private String shipperEmail;
    
    private String selectedDriver;
    private String driverEmail;
    
    private String selectedConsignee;
    private String consigneeEmail;
    
    private Cost cost;
    private Cost serviceTax;
    private String costName;
    private double costRate;
    private double multiplier;
    private double costTotal;
    private double serviceTaxTotal;
    private double allCosts;
    private double subtotal;
    private double labelTotal;
    private double taxValue;
    @FXML
    private JFXButton btn;
    @FXML
    private Label lblCost11;
    @FXML
    private Label lblCost111;

    public OrderFormController() {
        this.labelTotal = 0.00;
        this.taxValue = 0.00;
    }

    

    /**
     * Initializes the controller class.
     */
     @Override
    public void manageData() {
        dataManagement = new DataManagement();
        
         dataManagement.setConsignersList(cbConsigner);
       dataManagement.setShippersList(cbShipper);
        dataManagement.setDriversList(cbDriver);
        dataManagement.setConsigneesList(cbConsignee);
        dataManagement.setVehicleList(cbVehicle);
       
    }
    
     @Override
    public void setLayout() 
    {
        layout = new Layout();
        layout.setOrderFormController(this);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        setLayout();
        manageData();
        
        
        costs = Cost.findAll();
        
        costs.forEach((c)->{
            String costNameString = c.getString("cost_name");
            String costUnitString = c.getString("unit_measurement");
            costList.add(costNameString);
            
            if (costNameString.equals("Service Tax"))
                {
                    costsListView.getItems().add(costNameString);
                }
            
            
    });
        
        cbCosts.setItems(costList);
        serviceTax = Cost.findFirst("cost_name = ?", "Service Tax");
   
    }    
    
    
    public JFXComboBox getConsginerCombo()
    {
        return cbConsigner;
    }
    public JFXComboBox getShipperCombo()
    {
        return cbShipper;
    }
    public JFXComboBox getDriverCombo()
    {
        return cbDriver;
    }
    public JFXComboBox getConsigneeCombo()
    {
        return cbConsignee;
    }
    public JFXComboBox getVehicleCombo()
    {
        return cbVehicle;
    }
    
   
    

    @FXML
    private void btnAddConsignerAction(ActionEvent event) {
        
       showAddDialog(event, "/resources/UserForm.fxml");
    }

    @FXML
    private void btnAddShipperAction(ActionEvent event) {
        showAddDialog(event, "/resources/UserForm.fxml");
    }
     @FXML
    private void btnAddDropOffAction(ActionEvent event) {
        
        if(cbConsignee.getSelectionModel().isEmpty())
        {
            System.out.println("Please select a user ");
        }
        else
                {
                    String consigneeEmailObj = dataManagement.getConsigneeMap().get(cbConsignee.getSelectionModel().getSelectedItem());
                    consigneeObj = User.findFirst("email = ?", consigneeEmailObj);
                    
                    showAddDialog(event, "/resources/AddressForm.fxml");
                    
                    
                }
        
    }
    @FXML
    private void btnAssignDriverAction(ActionEvent event) {
        
      
        String emailObj = dataManagement.getDriverMap().get(cbDriver.getSelectionModel().getSelectedItem());  
        int vehicleId = dataManagement.getVehicleMap().get(cbVehicle.getSelectionModel().getSelectedItem());  
        
        try
        {
            User userObj = User.findFirst("email = ?", emailObj);
       int driverId = userObj.getInteger("user_id");
        
            order.add(userObj);
        
        OrderUser driverObj = OrderUser.findFirst("order_id = ? and user_id = ?", order.getId(), userObj.getId());
        driverObj.set("vehicle_id", vehicleId).saveIt();
        
        System.out.println("Order Id: " + order.getId() + " Driver: " + userObj.getString("email"));
        driverListView.getItems().add(cbDriver.getSelectionModel().getSelectedItem());
        
        cbDriver.valueProperty().set(null);
        cbVehicle.valueProperty().set(null);
        
        
        
        }
        catch(Exception e)
        {
            System.out.println("please select a driver and assign a vehicle");
        }
        
        
    }

    @FXML
    private void btnAddFreightAction(ActionEvent event) {
        
        
        try{
                freight = new Freight();
                freight.set("name", txtFreightName.getText())
                .set("description", txtFreightDescription.getText())
                .set("weight", txtFreightWeight.getText());
                        order.add(freight);
                        freight.saveIt();
        freightListView.getItems().add(txtFreightDescription.getText());
        System.out.println("Order Id: " + order.getId() + " Freight: " + freight.getString("name"));
                txtFreightName.clear();
                txtFreightDescription.clear();
                txtFreightWeight.clear();
                }
            catch(Exception e)
                    {
                    System.out.println(e.getMessage());
                    }
        
    }
        
   
    
    @FXML
    private void btnCompleteOrderAction(ActionEvent event)
    {
        
        if(
                (!cbConsigner.getSelectionModel().isEmpty())
                &&
                (!cbShipper.getSelectionModel().isEmpty())
                &&
                (!cbConsignee.getSelectionModel().isEmpty())
                &&
                (!cbDropOff.getSelectionModel().isEmpty())
                &&
                (!driverListView.getItems().isEmpty())
                &&
                (!freightListView.getItems().isEmpty())
                )
            
        {
            
          
           try{
               User orderConsigner = User.findFirst("email = ?", consignerEmail);
           order.add(orderConsigner);
          
           User orderShipper = User.findFirst("email = ?", shipperEmail);
           order.add(orderShipper);
            
           User orderConsignee = User.findFirst("email = ?", consigneeEmail);
           order.add(orderConsignee);
           
           
            order.add(serviceTax);
           // get all costs from costlistView and get the total
            costsListView.getItems().forEach((clv)->{
                Cost thisCost = Cost.findFirst("cost_name = ?", clv);
                
                if(!thisCost.getString("cost_name").equals("Service Tax"))
                {
                   OrderCost orderCost = OrderCost.findFirst("order_id = ? and cost_id = ?", order.getId(), thisCost.getId());
                   allCosts += orderCost.getDouble("cost_total");
                }
           });
          
           serviceTaxTotal = allCosts * serviceTax.getDouble("rate");
           OrderCost serviceTaxCost = OrderCost.findFirst("order_id = ? and cost_id = ?", order.getId(), serviceTax.getId());
            serviceTaxCost.set("cost_total", serviceTaxTotal).saveIt();
            
            subtotal = allCosts + serviceTaxTotal;
            
            order.set("balance", subtotal).saveIt();
           
            
            
           layout.showNotification("Order has been successfully created");
           }
         catch(Exception e)
         {
             System.out.println(e.getMessage());
         }
            
           
        }
        else
        {
            System.out.println("please ensure you have everything in order");
        }
    }
    

   
@FXML
    private void btnNewOrderAction(ActionEvent event) {
        
        order = new Order();
        
        //use get to get String from String property
        order.set("order_ref_id", order.orderRefIdGeneratorProperty().get());
        //System.out.println("From controller: " + order.orderRefIdProperty());
        order.saveIt();
        
    }

    @FXML
    private void cbCostsAction(ActionEvent event) {
        
        Cost cost = Cost.findFirst("cost_name = ?", cbCosts.getSelectionModel().getSelectedItem());
        
        if (!cost.getString("unit_measurement").equals("Constant"))
                {
                    txtMultiplier.setVisible(true);
                }
        else
        {
             txtMultiplier.setVisible(false);
        }
        
    }
    
    @FXML
    private void cbConsigneeAction(ActionEvent event) {
        
        
        
         if(!cbConsignee.getSelectionModel().isEmpty())
         {
          
              selectedConsignee = cbConsignee.getSelectionModel().getSelectedItem();
            consigneeEmail = dataManagement.getConsigneeMap().get(selectedConsignee);
        
             dataManagement.getAddressList().removeAll(dataManagement.getAddressList());
             
            
            
             User userObj = User.findFirst("email = ?", consigneeEmail);
              List<Address> consigneeAddresses = userObj.getAll(Address.class);
         
                    if (!consigneeAddresses.isEmpty() || consigneeAddresses.size()>0)
                    {
                        consigneeAddresses.forEach((ca) -> {

                        String fullAddress = ca.getString("state") + " " + ca.getString("town") + " " + ca.getString("zipcode");
                        dataManagement.getAddressList().add(fullAddress);
                    });


                       cbDropOff.setItems(dataManagement.getAddressList());
                    }
           
        }
         
    }
    @FXML
    private void btnAddCostAction(ActionEvent event) {
        try{
        
            cost = Cost.findFirst("cost_name = ?", cbCosts.getSelectionModel().getSelectedItem());
        costName = cost.getString("cost_name");
        costRate = cost.getDouble("rate");
        if (costsListView.getItems().contains(costName))
                {
                    System.out.print("already in list");
                }
        else
        {
            order.add(cost);
            multiplier = Double.parseDouble(txtMultiplier.getText());
            costTotal = costRate * multiplier;
            
            OrderCost orderCost = OrderCost.findFirst("order_id = ? and cost_id = ?", order.getId(), cost.getId());
            orderCost.set("cost_multiplier", multiplier).set("cost_total", costTotal).saveIt();
            costsListView.getItems().add(cbCosts.getSelectionModel().getSelectedItem());   
            
             if (labelTotal == 0.00)
            {
                labelTotal += costTotal;
                
                    if(taxValue == 0.00)
                    {
                        
                        taxValue = labelTotal * serviceTax.getDouble("rate");
                        labelTotal += taxValue;
                        lblCost.setText((String.valueOf(labelTotal)));
                    }   
            }
            else
            {
                if(taxValue > 0.00)
                    {
                        labelTotal -= taxValue;
                        labelTotal += costTotal;
                        taxValue = labelTotal * serviceTax.getDouble("rate");
                        labelTotal += taxValue;
                        lblCost.setText((String.valueOf(labelTotal)));
                    }
            }
        }
        
        }
        catch(Exception e)
        {
            System.out.print("not saved");
            e.printStackTrace();
        }
        
       
        
        
    }

         @FXML
    private void btnAddDirverAction(ActionEvent event) {
         showAddDialog(event, "/resources/UserForm.fxml");
    }

    @FXML
    private void btnAddVehicleAction(ActionEvent event) {
        showAddDialog(event, "/resources/VehicleForm.fxml");
    }

    
     @FXML
    private void btnAddConsigneeAction(ActionEvent event) {
         showAddDialog(event, "/resources/UserForm.fxml");
    }

    @FXML
    private void btnRemoveDriverAction(ActionEvent event) {
    }

    @FXML
    private void btnRemoveFreightAction(ActionEvent event) {
    }

    
    
     @FXML
    private void cbConsignerAction(ActionEvent event) {
        
        selectedConsigner = cbConsigner.getSelectionModel().getSelectedItem();
        consignerEmail = dataManagement.getConsignerMap().get(selectedConsigner);
        
            
    }
    
    @FXML
    private void cbShipperAction(ActionEvent event) {
        selectedShipper = cbShipper.getSelectionModel().getSelectedItem();
        shipperEmail = dataManagement.getShipperMap().get(selectedShipper);
    }
    
    @FXML
    private void cbDropOffAction(ActionEvent event) {
    }

   
    @FXML
    private void cbDriverAction(ActionEvent event) {
    }

    @FXML
    private void cbVehicleAction(ActionEvent event) {
    }
    

    @FXML
    private void btnRemoveCostAction(ActionEvent event) {
    }


     public ObservableList<String> getConsignerFullNamesList() {
        return consignerFullNamesList;
    }

    public ObservableList<String> getShipperFullNamesList() {
        return shipperFullNamesList;
    }

    public ObservableList<String> getDriverFullNamesList() {
        return driverFullNamesList;
    }

    public ObservableList<String> getConsigneeFullNamesList() {
        return consigneeFullNamesList;
    }

    public ObservableList<String> getVehicleNumberPlateList() {
        return vehicleNumberPlateList;
    }
    
    public void showAddDialog(ActionEvent event, String resource)
    {
         layout.showModal(resource, event);
    }
    public User getConsigneeObj() {
        return consigneeObj;
    }

    public void setConsigneeObj(User consigneeObj) {
        this.consigneeObj = consigneeObj;
    }

    
    
    
    
}
