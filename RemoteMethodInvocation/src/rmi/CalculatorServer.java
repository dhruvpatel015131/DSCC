package rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalculatorServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Calculator calculator = new CalculatorImplementation();
			 Registry registry = LocateRegistry.createRegistry(1099);
	         registry.rebind("Calculator", calculator);
	         System.out.println("Calculator Server is ready.");
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
