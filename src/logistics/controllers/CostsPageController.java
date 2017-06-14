/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logistics.controllers;

import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.beans.property.DoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import logistics.DataManagement;
import logistics.Layout;
import logistics.models.Cost;
import logistics.LayoutInterface;
import logistics.models.Vehicle;


/**
 * FXML Controller class
 *
 * @author Chewa
 */
public class CostsPageController implements Initializable, LayoutInterface {

    /**
     * Initializes the controller class.
     */
    final ObservableList<Cost> costList = FXCollections.observableArrayList();
    FilteredList<Cost> filteredCostList = new FilteredList<>(costList, event -> true);
    private Layout layout;
    
    
    
    @FXML
    private TableView<Cost> costsTable;

    @FXML
    private TableColumn<Cost, String> colCostName;

    @FXML
    private TableColumn<Cost, String>colUnitMeasurement;

    @FXML
    private TableColumn<Cost, Double>colRate;
    
    @Override
    public void setLayout() {
        layout = new Layout();
        layout.setCostsPageController(this);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
       colCostName.setCellValueFactory(cellData -> cellData.getValue().getCostNameProperty());
        colUnitMeasurement.setCellValueFactory(cellData -> cellData.getValue().getUnitMeasurementProperty());
       colRate.setCellValueFactory(cellData -> cellData.getValue().getRateProperty().asObject());
       costList.removeAll(costList);
        loadCostsTable();
       
        
    } 
   private void loadCostsTable(){
       
       
       List<Cost> costData = Cost.findAll();
       
      
      
      costData.forEach((c) -> {
          costList.add(new Cost(c.getString("cost_id"),c.getString("cost_name"), c.getString("unit_measurement"), c.getDouble("rate")));
        });
          
      
      
      //System.out.println(costList);
              costsTable.setItems(costList);
   }

   public CostsPageController getCostsPageController()
{
    return this;
}

     

    
    
    
}
