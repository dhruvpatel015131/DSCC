package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculatorImplementation extends UnicastRemoteObject implements Calculator {
	
	private static final long serialVersionUID = 1L;

	public CalculatorImplementation() throws RemoteException {
	        super();
	    }
	 
	@Override
	public double add(double a, double b) throws RemoteException {
		// TODO Auto-generated method stub
		return a+b;
	}

	@Override
	public double subtract(double a, double b) throws RemoteException {
		// TODO Auto-generated method stub
		return a-b;
	}

	@Override
	public double multiply(double a, double b) throws RemoteException {
		// TODO Auto-generated method stub
		return a*b;
	}

	@Override
	public double divide(double a, double b) throws RemoteException {
		// TODO Auto-generated method stub
		return a/b;
	}

}
