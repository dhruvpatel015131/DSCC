import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalcOperation extends UnicastRemoteObject implements adder{
	
	private static final long serialVersionUID = 1L;
	
	 CalcOperation() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getAddition(int num1, int num2) throws RemoteException {
		// TODO Auto-generated method stub
		return num1+num2;
	}

	@Override
	public int getSubtraction(int num1, int num2) throws RemoteException {
		// TODO Auto-generated method stub
		return num1-num2;
	}

}
