import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class DBServiceSrv {

	public DBServiceSrv() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			StudDBInf skeleton = new StudDBOperations();
			LocateRegistry.createRegistry(1900);
			Naming.rebind("rmi://localhost:1900/ROCforStudDB", skeleton);
			System.out.println("Server Registerd");
		
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	

}
