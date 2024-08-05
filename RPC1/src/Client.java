import java.io.*;
import java.net.*;
public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
			Socket s = new Socket("localhost",2222);
			//2. Create I/O streams for communicating with the server.

			// Get an output file handle from the socket

			DataOutputStream dout=new DataOutputStream(s.getOutputStream());

			// Get an input file handle from the socket and read the input
			DataInputStream dis = new DataInputStream(s.getInputStream());

			DataInputStream in = new DataInputStream(System.in); System.out.println("Enter a message for the server...");
			 String str = in.readLine();

			dout.writeUTF(str);

			String str1=dis.readUTF();
			System.out.println("message="+str1);

			//4. flush and close
			 dout.flush();

			dout.close();

			dis.close();

			//5. Close
			 s.close();
			
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}

}
