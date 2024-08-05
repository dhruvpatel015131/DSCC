import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPClient {

	public static void main(String[] args) throws SocketException {
		// TODO Auto-generated method stub
		DatagramPacket dpac;
		DatagramSocket dsoc = new DatagramSocket(1314);
		String data = "No Data";
		byte b[] = new byte[64];
		System.out.println("Client Up");
		try
		{
			while(true)
			{
				dpac = new DatagramPacket(b, b.length);
				dsoc.receive(dpac);
				data = new String(dpac.getData());
				System.out.println("Recived Data "+data);
				
			}
		}
		catch(IOException e)
		{
			System.out.println("Error Occured! "+e );
		}
		dsoc.close();
	}

}
