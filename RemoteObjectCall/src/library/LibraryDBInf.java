package library;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LibraryDBInf extends Remote {
	public String getData(String strQry) throws RemoteException;
	public String insertData(String strQry) throws RemoteException;

}
