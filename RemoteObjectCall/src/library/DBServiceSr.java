package library;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class DBServiceSr {
	
	public DBServiceSr() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			LibraryDBInf skeleton = new DBOperationService();
			LocateRegistry.createRegistry(1900);
			Naming.rebind("rmi://localhost:1900/ROCforLibraryDB", skeleton);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
