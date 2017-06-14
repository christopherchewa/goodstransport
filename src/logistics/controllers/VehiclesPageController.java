/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logistics.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import insidefx.undecorator.UndecoratorScene;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
 
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logistics.DataManagement;
import logistics.DataManagementInterface;
import logistics.DbConnection;
import logistics.Layout;
import logistics.models.User;
import logistics.models.Vehicle;
import logistics.LayoutInterface;

/**
 * FXML Controller class
 *
 * @author Chewa
 */
public class VehiclesPageController implements Initializable, LayoutInterface, DataManagementInterface {

    /**
     * Initializes the controller class.
     */
    
    
    
    private final ObservableList<Vehicle> vehicleList = FXCollections.observableArrayList();
    FilteredList<Vehicle> filteredVehicleList = new FilteredList<>(vehicleList, event -> true);
  
    
    @FXML
    private StackPane vehiclesStackPane;
    
    
    
    @FXML
    private TableView<Vehicle> vehiclesTable;
    @FXML
    private JFXTextField txtSearch;

    public TableView<Vehicle> getVehiclesTable() {
        return vehiclesTable;
    }

    public TableColumn<Vehicle, String> getColVehicleStatus() {
        return colVehicleStatus;
    }

    public JFXComboBox<String> getCbVehicleStatus() {
        return cbVehicleStatus;
    }
  
    @FXML
    private TableColumn<Vehicle, String> colNumberPlate;

    @FXML
    private TableColumn<Vehicle, String> colVehicleStatus;   
    
    @FXML
    private JFXButton btnAddNewVehicle;


    @FXML
    private JFXTextField txtNumberPlate;
    @FXML
    private JFXComboBox<String> cbVehicleStatus;
    
    private Vehicle vehicle;
    private Layout layout;   
    private VehicleFormController vehicleFormController;
    private DataManagement dataManagement;
    
    
    
    
    
    
    
    

     @Override
    public void initialize(URL location, ResourceBundle resources) {
    
     colNumberPlate.setCellValueFactory(cellData -> cellData.getValue().numberPlateProperty());
     colVehicleStatus.setCellValueFactory(cellData -> cellData.getValue().vehicleStatusProperty());
     setLayout();
     manageData();
     
    dataManagement.loadVehiclesTable(vehicleList, vehiclesTable);
    dataManagement.setComboItems(cbVehicleStatus);
    cbVehicleStatus.getSelectionModel().selectFirst();
    
    
    
    
     
    }
    @Override
    public void manageData() {
        
        dataManagement = new DataManagement();
    }
    
    @Override
    public void setLayout() {
       
        layout = new Layout();
        layout.setVehiclesPageController(this);
        
        
    }
    
    

    @FXML
    private void btnAddNewVehicleAction(ActionEvent event) {
        
        if (!txtNumberPlate.getText().isEmpty() && !cbVehicleStatus.getSelectionModel().isEmpty())
        {
            if(dataManagement.saveVehicle(vehicle, txtNumberPlate.getText(), cbVehicleStatus.getSelectionModel().getSelectedItem()))
                {
                    
                   // System.out.println("Number Plate: " + vehicle.getString("number_plate"));
                    dataManagement.showNotification("Vehicle has been added");
                 dataManagement.loadVehiclesTable(vehicleList, vehiclesTable);
        txtNumberPlate.clear();
        cbVehicleStatus.valueProperty().set(null);
                   
                }
        
        }
        else
        {
         System.out.println("Unable to save");
        }
        
        
        
    }

    @FXML
    private void searchFunctionAction(KeyEvent event) {
        
       txtSearch.textProperty().addListener((observableValue, oldValue, newValue) ->{
         
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
    
    
    }
    
    

    
    


   




