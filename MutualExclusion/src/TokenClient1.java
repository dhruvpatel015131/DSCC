import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class TokenClient1 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		InetAddress lclhost;
		BufferedReader br;
		String str = "";
		TokenClient12 tkcl, tkser;

		boolean hasToken;
		boolean setSendData;

		while (true) {
			lclhost = InetAddress.getLocalHost();
			tkcl = new TokenClient12(lclhost);
			tkser = new TokenClient12(lclhost);

			tkcl.setSendPort(9004);
			tkcl.setRecPort(8002);
			lclhost = InetAddress.getLocalHost();
			tkser.setSendPort(9000);
			if (tkcl.hasToken == true) {
				System.out.println("Do You want to enter the Data -> YES/NO");
				br = new BufferedReader(new InputStreamReader(System.in));
				str = br.readLine();
				if (str.equalsIgnoreCase("yes")) {
					System.out.println("Ready to Send Data");
					tkser.setSendData = true;
					tkser.sendData();
					tkser.setSendData = false;
				} else if (str.equalsIgnoreCase("no")) {
					System.out.println("I am in Else");
					tkcl.hasToken = false;
					tkcl.sendData();
					tkcl.recData();
					System.out.println("i am leaving Else");
				}
			} else {
				System.out.println("ENTERING RECIVING MODE");
				tkcl.recData();
				hasToken = true;
			}
		}

	}

}

class TokenClient12 {
	InetAddress lclhost;
	int sendport, recport;
	boolean hasToken = true;
	boolean setSendData = false;
	TokenClient12 tkcl, tkser;
	
	TokenClient12(InetAddress lclhost){
		this.lclhost = lclhost;
	}
	void setSendPort(int sendport) {
		this.sendport = sendport;
	}
	void setRecPort(int recport) {
		this.recport = recport;
	}
	
	void sendData() throws Exception{
		BufferedReader br;
		String str = "TOKEN";
		DatagramSocket ds;
		DatagramPacket dp;
		if(setSendData == true) {
			System.out.println("Sending");
			System.out.println("Enter the Data");
			br = new BufferedReader(new InputStreamReader(System.in));
			str = "Client one..."+br.readLine();
			System.out.println("Now Sending");
		}
		ds = new DatagramSocket(sendport);
		dp = new DatagramPacket(str.getBytes(), str.length(), lclhost,sendport-1000);
		ds.send(dp);
		ds.close();
		setSendData = false;
		hasToken = false;
	}
	
	void recData() throws Exception{
		String msgStr;
		byte buffer[] = new byte[256];
		DatagramSocket ds;
		DatagramPacket dp;
		ds = new DatagramSocket(recport);
		dp = new DatagramPacket(buffer, buffer.length);
		ds.receive(dp);
		ds.close();
		msgStr = new String(dp.getData(),0, dp.getLength());
		System.out.println("The Data is "+ msgStr);
		if(msgStr.equals("TOKEN")) {
			hasToken = true;
		}
	}
}
