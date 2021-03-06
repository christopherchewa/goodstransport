/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logistics.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import logistics.DataManagement;
import logistics.DataManagementInterface;
import logistics.Layout;
import logistics.LayoutInterface;
import logistics.UserTypes;
import logistics.models.Address;
import logistics.models.User;
import logistics.models.Vehicle;

/**
 * FXML Controller class
 *
 * @author Chewa
 */
public class AddressFormController implements Initializable, LayoutInterface, DataManagementInterface {

    @FXML
    private JFXTextField txtState;
    @FXML
    private JFXTextField txtTown;
    @FXML
    private JFXTextField txtZipcode;
    @FXML
    private JFXButton btnAddUser;
    @FXML
    private Label lblFullNames;
    @FXML
    private Label lblUserType;
    
    private User newUser;
    private User oldUser;
    private User user;
    private Layout layout;
    private DataManagement dataManagement;
    
    private String fullNames;
    private String userType;
    
private String admin = "";
private String employee = "";
private String consigner = "";
private String shipper = "";
private String driver = "";
private String consignee = "";
  


    /**
     * Initializes the controller class.
     */
    @Override
    public void setLayout() {
        layout = new Layout();
        layout.setAddressFormController(this);
        try
        {
          newUser = layout.getUserFormController().getUser();
          user = newUser;
          System.out.println(newUser);
                  
                  
        }
        catch(Exception e)
        {
            oldUser = layout.getOrderFormController().getConsigneeObj();
            user = oldUser;
            System.out.println(oldUser);
        }
        
        fullNames = user.getString("first_name") + " " + user.getString("last_name");
        userType = user.getString("user_type");
        lblFullNames.setText(fullNames);
        lblUserType.setText(userType); 
        
        
    }
    
    
    @Override
    public void manageData() {
        dataManagement = new DataManagement();
        
        
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setLayout();
        manageData();
        
        
        
    }    

    @FXML
    private void btnAddUserAction(ActionEvent event) {
        
        
            if(newUser == null)
            {
                 dataManagement.saveAddress(oldUser, txtState, txtTown, txtZipcode);
            }
            else
            {
                dataManagement.saveAddress(newUser, txtState, txtTown, txtZipcode);

            }
           
        refresh();
        
        
      
    }

     public void refresh()
     {
         
            
            
        switch (user.getString("user_type")) {
            case UserTypes.CONSIGNER:
                dataManagement.getConsignerFullNamesList().removeAll(dataManagement.getConsignerFullNamesList());
                dataManagement.setConsignersList(layout.getOrderFormController().getConsginerCombo());
                //layout.getOrdersPageController().getLists();
                layout.getOrderFormController().getConsginerCombo().getSelectionModel().selectLast();
                // layout.getOrdersPageController().getConsginerCombo().getSelectionModel().getSelectedItem();
                break;
            case UserTypes.SHIPPER:
                dataManagement.getShipperFullNamesList().removeAll(dataManagement.getShipperFullNamesList());
                dataManagement.setShippersList(layout.getOrderFormController().getShipperCombo());
                layout.getOrderFormController().getShipperCombo().getSelectionModel().selectLast();
                //layout.getOrdersPageController().getShipperCombo().getSelectionModel().getSelectedItem();
                break;
            case UserTypes.DRIVER:
                dataManagement.getDriverFullNamesList().removeAll(dataManagement.getDriverFullNamesList());
                dataManagement.setDriversList(layout.getOrderFormController().getDriverCombo());
                layout.getOrderFormController().getDriverCombo().getSelectionModel().selectLast();
                break;
            case UserTypes.CONSIGNEE:
                dataManagement.getConsigneeFullNamesList().removeAll(dataManagement.getConsigneeFullNamesList());
                dataManagement.setConsigneesList(layout.getOrderFormController().getConsigneeCombo());
                if(user.equals(newUser))
                {
                    
                    layout.getOrderFormController().getConsigneeCombo().getSelectionModel().selectLast();
                    
                }
                
                break;
            default:
                break;
        }
        
        
            layout.getUsersPageController().loadUsersTable();
          layout.getDialogStage().close();
        
     }
    
    
}
