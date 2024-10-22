import java.rmi.*;
import java.rmi.registry.LocateRegistry;

public class Server {

	public static void main(String[] args) throws UnknownHostException{
		// TODO Auto-generated method stub
		try {
			System.out.println("Caculator Service Server");
			adder stub = new CalcOperation();

			System.out.println("Calcualtor Service Binding .......");
			LocateRegistry.createRegistry(5000);
			Naming.rebind("rmi://localhost:5000/CalcOpService", stub);
			System.out.println("Calculator Service is registered in service");

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

}
