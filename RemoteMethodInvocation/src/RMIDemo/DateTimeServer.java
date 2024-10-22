package RMIDemo;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class DateTimeServer {
    public static void main(String[] args) {
        try {
            // Create and export the remote object
            DateTimeService dateTimeService = new DateTimeServiceImpl();
            
            // Start the RMI registry on port 1099
            LocateRegistry.createRegistry(1900);
            
            // Bind the remote object to the registry
            Naming.rebind("rmi://localhost:1900/DateTimeService", dateTimeService);
            
            System.out.println("DateTimeServer is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
