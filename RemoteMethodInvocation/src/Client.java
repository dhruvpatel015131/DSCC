import java.rmi.*;
public class Client {
	
	public static void main(String[] args)throws UnknownHostException {
		// TODO Auto-generated method stub
		try {
			System.out.println("Client program started");
			adder stub = (adder)Naming.lookup("rmi://localhost:5000/CalcOpService");
			System.out.println("Addition = "+stub.getAddition(34,4));
			System.out.println("Subtraction = "+stub.getSubtraction(34,4));
			
		}catch(Exception ex) {
			System.out.println(ex);
		}
	}

}
