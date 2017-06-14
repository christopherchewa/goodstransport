/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logistics.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.util.Duration;
import logistics.DataManagement;
import logistics.DataManagementInterface;
import logistics.Layout;
import logistics.models.Vehicle;
import logistics.LayoutInterface;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Chewa
 */
public class VehicleFormController implements Initializable, LayoutInterface, DataManagementInterface {
    
    @FXML
    private JFXTextField txtNumberPlate;
    @FXML
    private JFXComboBox<String> cbVehicleStatus;
    
     @FXML
    private JFXButton btnVehicleSave;
    
    private Layout layout;
    private DataManagement dataManagement;
    
    private Vehicle vehicle;
    
    private String numberPlate;
    
    private String vehicleStatus;
//    private VehiclesPageController vehiclesPageController = new VehiclesPageController();
    
    
   
    @FXML
    private Label lblVehicleStatus;
    @FXML
    private FontAwesomeIconView truckIcon;
    
    private ObservableList<Vehicle> vehicleList = FXCollections.observableArrayList();
    
    @Override
    public void setLayout()
    {
        layout = new Layout();
        layout.setVehicleFormController(this);
      
    }
    @Override
    public void manageData() {
        dataManagement = new DataManagement();
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setLayout();
        manageData();
        dataManagement.setComboItems(cbVehicleStatus);
        cbVehicleStatus.getSelectionModel().selectFirst();
    }
    
     
     
    @FXML
   public void btnVehicleSaveAction(ActionEvent event) {
        
        dataManagement.showNotification("Vehicle has been added");
        
        
    }

     
    
}

    
    
    

