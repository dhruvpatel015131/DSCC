package RMIDemo;

import java.rmi.Naming;
import java.util.Date;

public class DateTimeClient {
    public static void main(String[] args) {
        try {
            // Lookup the remote object from the RMI registry
            DateTimeService dateTimeService = (DateTimeService) Naming.lookup("rmi://localhost:1900/DateTimeService");
            
            // Get the current date and time from the server
            Date serverDateTime = dateTimeService.getCurrentDateTime();
            System.out.println("Current Date and Time from Server: " + serverDateTime.toString());
            
            // Get the current day from the server
            String serverDay = dateTimeService.getCurrentDay();
            System.out.println("Current Day from Server: " + serverDay);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
