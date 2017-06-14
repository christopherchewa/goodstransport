/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logistics.models;

import java.util.ArrayList;
import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.IdName;
import org.javalite.activejdbc.annotations.Table;
@IdName("order_user_id")
/**
 *
 * @author Chewa
 */

@Table("order_users")
public class OrderUser extends Model {
    
    private ArrayList<User> orderUsersList;
    
    public ArrayList<User> getOrderUsersList()
    {
            return orderUsersList;
    }
    
    
}
