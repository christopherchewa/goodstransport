/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logistics;

import org.javalite.activejdbc.Base;

/**
 *
 * @author Chewa
 */
public class DbConnection {
    
    public static void connect()
    {
         Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/logisticsdb", "root", "");
    }
    public static void disconnect()
    {
        Base.close();
    }
    
}
