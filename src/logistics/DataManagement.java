/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logistics;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TableView;
import logistics.models.Address;
import logistics.models.User;
import logistics.models.Vehicle;

/**
 *
 * @author Chewa
 */
public class DataManagement implements LayoutInterface {
    
    private final String vehicleAvailable = "Available";
    private final String vehicleDispatched = "Dispatched";
    private final String vehicleInRepair = "In Repair";
    private final String available = vehicleAvailable;
    
    public static final String ADMIN = "Admin";
    public static final String EMPLOYEE = "Employee";
    public static final String CONSIGNER = "Consigner";
    public static final String SHIPPER = "Shipper";
    public static final String DRIVER = "Driver";
    public static final String CONSIGNEE = "Consignee";
    
    private List<Vehicle> vehiclesList;
    private List<User> consignersList;
    private List<User> shippersList;
    private List<User> driversList;
    private List<User> consigneesList;
    
    private Layout layout;
   
    
    
    @Override
    public void setLayout()
    {
        layout = new Layout();
    }
    
    private final ObservableList<String> vehicleStatusList = FXCollections.observableArrayList(vehicleAvailable,
            vehicleDispatched,
            vehicleInRepair
            );
    private ObservableList<String> consignerFullNamesList = FXCollections.observableArrayList();
    private ObservableList<String> shipperFullNamesList = FXCollections.observableArrayList();
    private ObservableList<String> driverFullNamesList = FXCollections.observableArrayList();
    private ObservableList<String> consigneeFullNamesList = FXCollections.observableArrayList();
    private ObservableList<String> vehicleNumberPlateList = FXCollections.observableArrayList();
    private ObservableList<String> addressList = FXCollections.observableArrayList();

   
    private static HashMap<String, String> consignerMap = new HashMap<>();
    private static HashMap<String, String> shipperMap = new HashMap<>();
    private static HashMap<String, String> driverMap = new HashMap<>();
    private static HashMap<String, String> consigneeMap = new HashMap<>();
   private static HashMap<String, String> userMap = new HashMap<>();
    private static HashMap<String, Integer> vehicleMap = new HashMap<>();

    public ObservableList<String> getVehicleStatusList() {
        return vehicleStatusList;
    }

    
    private ObservableList<Vehicle> vehicleList = FXCollections.observableArrayList();


    public void setComboItems(JFXComboBox statusComboBox)
    {
        statusComboBox.setItems(vehicleStatusList);
    }
    
    public void loadVehiclesTable(ObservableList<Vehicle> vehicleList, TableView vehiclesTable)
    {
      
      vehicleList.removeAll(vehicleList);
      List<Vehicle> vehicleData = Vehicle.findAll();
      
      vehicleData.forEach((v) -> {
          vehicleList.add(new Vehicle(v.getString("number_plate"), v.getString("vehicle_status")));
        });
              //System.out.println(vehicleList);
              vehiclesTable.setItems(vehicleList);
            
          
   }
    public boolean saveVehicle(Vehicle vehicle, String numberPlate, String vehicleStatus)
    {   
        try{
        
        //Method chaining
        
        vehicle = new Vehicle();
        
        vehicle.set("number_plate", numberPlate);
               vehicle.set("vehicle_status", vehicleStatus).saveIt();
               setLayout();
               
        
        
        return true;
        }
        
        catch(Exception e)
        {
            
            System.out.println(e.getMessage());
            return false;
        }
    
}
    
    
    
   
    
    public void saveAddress(User user, JFXTextField txtState, JFXTextField txtTown, JFXTextField txtZipcode)
    {
         Address address = new Address();
        address.set("state", txtState.getText())
                .set("town", txtTown.getText())
                .set("zipcode", txtZipcode.getText());
        user.add(address);
        address.saveIt();
    }
    
    public void setConsignersList(JFXComboBox consignerComboBox)
    {
        
        
      //  adminsList = User.where("user_type", admin);
        //employeesList = User.where("user_type", employee);
        consignerMap.clear();
        consignerFullNamesList.removeAll(consignerFullNamesList);
        consignersList = User.where("user_type = ?", UserTypes.CONSIGNER);
        consignersList.forEach((c) -> {
             String consignerFirstName = c.getString("first_name");
             String consignerLastName = c.getString("last_name");
             String consignerEmail = c.getString("email");
             String consignerFullNames = consignerFirstName + " " + consignerLastName;
             consignerMap.put(consignerFullNames, consignerEmail);
             
             consignerFullNamesList.add(consignerFullNames);
         });
        
        consignerComboBox.setItems(consignerFullNamesList);
        
    }
    
    
     public void setShippersList(JFXComboBox shipperComboBox)
    {
        shipperMap.clear();
        shipperFullNamesList.removeAll(shipperFullNamesList);
       shippersList = User.where("user_type = ?", UserTypes.SHIPPER);
        shippersList.forEach((s) -> {
             String shipperFirstName = s.getString("first_name");
             String shipperLastName = s.getString("last_name");
             String shipperEmail = s.getString("email");
             String shipperFullNames = shipperFirstName + " " + shipperLastName;
             shipperMap.put(shipperFullNames, shipperEmail);
             shipperFullNamesList.add(shipperFullNames);
             
         });
        shipperComboBox.setItems(shipperFullNamesList);
    }
     
      public void setDriversList(JFXComboBox driverComboBox)
    {
        driverMap.clear();
        driverFullNamesList.removeAll(driverFullNamesList);
        driversList = User.where("user_type = ? and driver_status = ?", UserTypes.DRIVER, available);
        driversList.forEach((d) -> {
             String driverFirstName = d.getString("first_name");
             String driverLastName = d.getString("last_name");
             String driverEmail = d.getString("email");
             String driverFullNames = driverFirstName + " " + driverLastName;
             driverMap.put(driverFullNames, driverEmail);
             driverFullNamesList.add(driverFullNames);
             
         });
        driverComboBox.setItems(driverFullNamesList);
    }
      
       public void setConsigneesList(JFXComboBox consigneeComboBox)
    {
        consigneeMap.clear();
        consigneeFullNamesList.removeAll(consigneeFullNamesList);
        consigneesList = User.where("user_type = ?", UserTypes.CONSIGNEE);
        consigneesList.forEach((cc) -> {
             String consigneeFirstName = cc.getString("first_name");
             String consigneeLastName = cc.getString("last_name");
             String consigneeEmail = cc.getString("email");
             String consigneeFullNames = consigneeFirstName + " " + consigneeLastName;
             consigneeMap.put(consigneeFullNames, consigneeEmail);
             consigneeFullNamesList.add(consigneeFullNames);
             
         });
        consigneeComboBox.setItems(consigneeFullNamesList);
    }
        
        public void setVehicleList(JFXComboBox vehicleComboBox)
    {
        vehicleMap.clear();
        vehicleNumberPlateList.removeAll(vehicleNumberPlateList);
        vehiclesList = Vehicle.where("vehicle_status = ?", available);
        vehiclesList.forEach((v) -> {
            int vehicleId = v.getInteger("vehicle_id");
             String numberPlate = v.getString("number_plate");
             vehicleMap.put(numberPlate, vehicleId);
             vehicleNumberPlateList.add(numberPlate);
             
         });
        vehicleComboBox.setItems(vehicleNumberPlateList);
    }
        
        
        
     public ObservableList<String> getConsignerFullNamesList() {
        return consignerFullNamesList;
    }

    public void setConsignerFullNamesList(ObservableList<String> consignerFullNamesList) {
        this.consignerFullNamesList = consignerFullNamesList;
    }

    public ObservableList<String> getShipperFullNamesList() {
        return shipperFullNamesList;
    }

    public void setShipperFullNamesList(ObservableList<String> shipperFullNamesList) {
        this.shipperFullNamesList = shipperFullNamesList;
    }

    public ObservableList<String> getDriverFullNamesList() {
        return driverFullNamesList;
    }

    public void setDriverFullNamesList(ObservableList<String> driverFullNamesList) {
        this.driverFullNamesList = driverFullNamesList;
    }

    public ObservableList<String> getConsigneeFullNamesList() {
        return consigneeFullNamesList;
    }

    public void setConsigneeFullNamesList(ObservableList<String> consigneeFullNamesList) {
        this.consigneeFullNamesList = consigneeFullNamesList;
    }

    public ObservableList<String> getVehicleNumberPlateList() {
        return vehicleNumberPlateList;
    }

    public void setVehicleNumberPlateList(ObservableList<String> vehicleNumberPlateList) {
        this.vehicleNumberPlateList = vehicleNumberPlateList;
    }
     public ObservableList<String> getAddressList() {
        return addressList;
    }

    public void setAddressList(ObservableList<String> addressList) {
        this.addressList = addressList;
    }
    
     
    public HashMap<String, String> getUserMap() {
        return userMap;
    }

    public void setUserMap(HashMap<String, String> userMap) {
        DataManagement.userMap = userMap;
    }

    public HashMap<String, Integer> getVehicleMap() {
        return vehicleMap;
    }

    public void setVehicleMap(HashMap<String, Integer> vehicleMap) {
        DataManagement.vehicleMap = vehicleMap;
    }

    public HashMap<String, String> getConsignerMap() {
        return consignerMap;
    }

    public HashMap<String, String> getShipperMap() {
        return shipperMap;
    }

    public HashMap<String, String> getDriverMap() {
        return driverMap;
    }

    public HashMap<String, String> getConsigneeMap() {
        return consigneeMap;
    }
    
    public void setUserTypes(String admin, String employee, String consigner, String shipper, String driver, String consignee)
    {
      
       /*admin = this.admin;
       employee = this.employee;
       consigner = this.consigner;
       shipper = this.shipper;
       driver = this.driver;
       consignee = this.consignee;*/
        
    }


}
