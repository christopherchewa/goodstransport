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
    
        private final String consigner = "Consigner";
    private final String shipper = "Shipper";
    private final String driver = "Driver";
    private final String consignee = "Consignee";
    
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
    
    public void showNotification(String message)
    {
    setLayout();
    layout.getNotifcationTray(message).show();
    }
    
    public void performSearch(JFXTextField vehicleSearch, TableView vehiclesTable, FilteredList<Vehicle> filteredVehicleList)
    {
        vehicleSearch.textProperty().addListener((observableValue, oldValue, newValue) ->{
         
            filteredVehicleList.setPredicate((Predicate<? super Vehicle>) vehicle ->{
              if(newValue == null || newValue.isEmpty() || newValue == "")
                {
                     return true;   
                }
              String lowerCaseFilter = newValue.toLowerCase();
                 if(vehicle.getString("number_plate").toLowerCase().contains(lowerCaseFilter))
                {
                    return true;
                }
              
              return false;
          }); 
        });
        
        SortedList<Vehicle> sortedData = new SortedList<>(filteredVehicleList);
        
        sortedData.comparatorProperty().bind(vehiclesTable.comparatorProperty());
        vehiclesTable.setItems(sortedData);
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
        consignersList = User.where("user_type = ?", consigner);
        consignersList.forEach((c) -> {
             String consginerFirstName = c.getString("first_name");
             String consginerLastName = c.getString("last_name");
             String consginerFullNames = consginerFirstName + " " + consginerLastName;
             consignerFullNamesList.add(consginerFullNames);
         });
        
        consignerComboBox.setItems(consignerFullNamesList);
        
    }
    
    
     public void setShippersList(JFXComboBox shipperComboBox)
    {
        
       shippersList = User.where("user_type = ?", shipper);
        shippersList.forEach((s) -> {
             String shipperFirstName = s.getString("first_name");
             String shipperLastName = s.getString("last_name");
             String shipperFullNames = shipperFirstName + " " + shipperLastName;
             shipperFullNamesList.add(shipperFullNames);
             
         });
        shipperComboBox.setItems(shipperFullNamesList);
    }
     
      public void setDriversList(JFXComboBox driverComboBox)
    {
        userMap.clear();
        driverFullNamesList.removeAll(driverFullNamesList);
        driversList = User.where("user_type = ? and driver_status = ?", driver, available);
        driversList.forEach((d) -> {
             String driverFirstName = d.getString("first_name");
             String driverLastName = d.getString("last_name");
             String driverEmail = d.getString("email");
             String driverFullNames = driverFirstName + " " + driverLastName;
             userMap.put(driverFullNames, driverEmail);
             driverFullNamesList.add(driverFullNames);
             
         });
        driverComboBox.setItems(driverFullNamesList);
    }
      
       public void setConsigneesList(JFXComboBox consigneeComboBox)
    {
        consigneesList = User.where("user_type = ?", consignee);
        consigneesList.forEach((cc) -> {
             String consigneeFirstName = cc.getString("first_name");
             String consigneeLastName = cc.getString("last_name");
             String consigneeEmail = cc.getString("email");
             String consigneeFullNames = consigneeFirstName + " " + consigneeLastName;
             userMap.put(consigneeFullNames, consigneeEmail);
             consigneeFullNamesList.add(consigneeFullNames);
             
         });
        consigneeComboBox.setItems(consigneeFullNamesList);
    }
        
        public void setVehicleList(JFXComboBox vehicleComboBox)
    {
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
        this.userMap = userMap;
    }

    public HashMap<String, Integer> getVehicleMap() {
        return vehicleMap;
    }

    public void setVehicleMap(HashMap<String, Integer> vehicleMap) {
        this.vehicleMap = vehicleMap;
    }
    
    

}
