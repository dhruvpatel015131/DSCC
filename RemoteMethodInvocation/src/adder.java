import java.rmi.*;

public interface adder extends Remote{
	public int getAddition(int num1, int num2) throws RemoteException;
	public int getSubtraction(int num1, int num2) throws RemoteException;
}
