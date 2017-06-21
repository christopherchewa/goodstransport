/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logistics.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import logistics.DataManagement;
import logistics.Layout;
import logistics.models.Address;
import logistics.LayoutInterface;
import logistics.models.Vehicle;

/**
 * FXML Controller class
 *
 * @author Chewa
 */

public class HomePageController implements Initializable, LayoutInterface {

    /**
     * Initializes the controller class.
     */
    private Layout layout;
    private String userType;
    
    
    @FXML
    private AnchorPane myHomeTopAnchorPane;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
         setLayout();
         layout.setHomePageController(this);
         

       
        
    }    
    
    @Override
    public void setLayout()
    {   
        
        layout = new Layout();
        
        
        
    }
    

   public AnchorPane getMyHomeTopAnchorPane() {
        return myHomeTopAnchorPane;
    }

    public void setMyHomeTopAnchorPane(AnchorPane myHomeTopAnchorPane) {
        this.myHomeTopAnchorPane = myHomeTopAnchorPane;
    }

    
    
    
}
