/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logistics.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import logistics.Layout;
import logistics.LayoutInterface;
import logistics.models.User;

/**
 * FXML Controller class
 *
 * @author Chewa
 */
public class ConsigneeInfoController implements Initializable, LayoutInterface {

    /**
     * Initializes the controller class.
     */
     private Layout layout;
    private User user;
    private UserFormController userFormController;
    @FXML
    private JFXTextField txtBankName;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private JFXButton btnAddressPage;
    
    
    @Override
    public void setLayout() {
        layout = new Layout();
        
        layout.setConsigneeInfoController(this);
       userFormController = layout.getUserFormController();
       
       user =  userFormController.getUser();
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setLayout();
    }    

    @FXML
    private void btnAddressPageAction(ActionEvent event) {
        
        try
            {
                 
                 user.set("consignee_bank_name", txtBankName.getText()).set("consignee_bank_address", txtAddress.getText()).saveIt();
                  layout.showModal("/resources/AddressForm.fxml", event);
                  
            }
        catch(Exception e)
        {
            System.out.println("unable to save consignee");
        }
        
               
    }
    
}
