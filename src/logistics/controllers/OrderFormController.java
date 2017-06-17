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
    private JFXTextField txtOrderDate;
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
            String costName = c.getString("cost_name");
            String costUnit = c.getString("unit_measurement");
            costList.add(costName);
            
            if (costName.equals("Service Tax"))
                {
                    costsListView.getItems().add(costName);
                }
            
            
    });
        
        cbCosts.setItems(costList);
   
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
                    String consigneeEmailObj = dataManagement.getUserMap().get(cbConsignee.getSelectionModel().getSelectedItem());
                    consigneeObj = User.findFirst("email = ?", consigneeEmailObj);
                    
                    showAddDialog(event, "/resources/AddressForm.fxml");
                    
                    
                }
        
    }
    @FXML
    private void btnAssignDriverAction(ActionEvent event) {
        
       // System.out.println(dataManagement.getUserMap());
        String emailObj = dataManagement.getUserMap().get(cbDriver.getSelectionModel().getSelectedItem());  
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
            e.printStackTrace();
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
            dataManagement.showNotification("Order has been added");
            
            String consignerEmailObjTest = dataManagement.getUserMap().get(cbConsigner.getSelectionModel().getSelectedItem());
            User consignerTest = User.findFirst("email = ?", consignerEmailObjTest);
            order.add(consignerTest);
            System.out.println("Order: " + order.getId() + " Consginer: " + consignerTest);
            
            String shipperEmailObjTest = dataManagement.getUserMap().get(cbShipper.getSelectionModel().getSelectedItem());
            User shipperTest = User.findFirst("email = ?", shipperEmailObjTest);
            order.add(shipperTest);
            System.out.println("Order: " + order.getId() + " Shipper: " + shipperTest);
            
            String consigneeEmailObjTest = dataManagement.getUserMap().get(cbConsignee.getSelectionModel().getSelectedItem());
            User consigneeTest = User.findFirst("email = ?", consigneeEmailObjTest);
            order.add(consigneeTest);
            System.out.println("Order: " + order.getId() + " Consginee: " + consigneeTest);
            
            
           
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
        System.out.println("ref Id will be requested...");
        order.set("order_ref_id", order.setOrderRefIdProperty().get());
        System.out.println("From order form controller: " + order.setOrderRefIdProperty());
        System.out.println("ref Id obtained...");
        
        
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
             dataManagement.getAddressList().removeAll(dataManagement.getAddressList());
             String emailObj = dataManagement.getUserMap().get(cbConsignee.getSelectionModel().getSelectedItem());   
            
             User userObj = User.findFirst("email = ?", emailObj);
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
            
            Cost cost = Cost.findFirst("cost_name = ?", cbCosts.getSelectionModel().getSelectedItem());
        String costName = cost.getString("cost_name");
        double costRate = cost.getDouble("rate");
        if (costsListView.getItems().contains(costName))
                {
                    System.out.print("already in list");
                }
        else
        {
            
        System.out.println(cost.getId());
                System.out.println(order.getId());
            order.add(cost);
          double multiplier = Double.parseDouble(txtMultiplier.getText());
            double costTotal = costRate * multiplier;
            OrderCost orderCost = OrderCost.findFirst("order_id = ? and cost_id = ?", order.getId(), cost.getId());
            orderCost.set("cost_multiplier", multiplier).set("cost_total", costTotal).saveIt();
          
            costsListView.getItems().add(cbCosts.getSelectionModel().getSelectedItem());
        }
        
        }
        catch(Exception e)
        {
            System.out.print("not saved");
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
            
        
    }
    
    @FXML
    private void cbShipperAction(ActionEvent event) {
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
