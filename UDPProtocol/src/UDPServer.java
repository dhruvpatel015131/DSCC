import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;

public class UDPServer {

	public static void main(String[] args) throws SocketException {
		// TODO Auto-generated method stub
		DatagramPacket dpac;
		DatagramSocket dsoc = new DatagramSocket();
		System.out.println("Server Up");
		try
		{
			while(true)
			{
				System.out.println("Sending");
				Thread.sleep(1000);
				String time = new Date().toString();
				byte b[] = time.getBytes();
				dpac = new DatagramPacket(b, b.length, InetAddress.getByName("localhost"), 1314);
				dsoc.send(dpac);
				
				}
		}
		catch(IOException | InterruptedException e)
		{
			System.out.println("Error Occured "+e);
		}
		dsoc.close();
		
	}

}
