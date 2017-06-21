/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logistics;

/**
 *
 * @author Chewa
 */
public class Status {
    
    //Private Constructor
    private Status()
    {
        
    }
    
    private static final String  AVAILABLE = "Available";
    private static final String  UNAVAILABLE = "Unavailable";
    
    //DRIVER STATUS AND VEHICLE STATUS
    public static final String VEHICLEAVAILABLE = AVAILABLE;
    public static final String VEHICLEUNAVAILABLE = UNAVAILABLE;
    public static final String DRIVERAVAILABLE = AVAILABLE;
    public static final String DRIVERUNAVAILABLE = UNAVAILABLE;
    
    //ORDER STATUS
    public static final String RELEASED = "Released";
    public static final String PENDINGEXTERNAL = "Pending(External)";
    public static final String INTRANSIT = "In Transit";
    public static final String PENDINGINTERNAL = "Pending(Internal)";
    public static final String DISPATCHED = "Dispatched";
    public static final String COMPLETE = "Complete";
    
    
    
    
    
}
