package RMIDemo;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTimeServiceImpl extends UnicastRemoteObject implements DateTimeService {

   
	private static final long serialVersionUID = 1L;

	protected DateTimeServiceImpl() throws RemoteException {
        super();
    }

    // Method to get the current date and time
    @Override
    public Date getCurrentDateTime() throws RemoteException {
        return new Date(); // returns the current date and time
    }

    // Method to get the current day
    @Override
    public String getCurrentDay() throws RemoteException {
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.ENGLISH); // get day name
        return dayFormat.format(new Date());
    }
}
