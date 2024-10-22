package RMIDemo;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface DateTimeService extends Remote {
    // Method to get current date and time from the server
    Date getCurrentDateTime() throws RemoteException;
    
    // Method to get the current day from the server
    String getCurrentDay() throws RemoteException;
}