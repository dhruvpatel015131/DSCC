import java.net.DatagramPacket;
import java.net.DatagramSocket;

class TokenServer {
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		while (true) {
			Server sr = new Server();
			sr.recPort(8000);
			sr.recData();
		}

	}
}

public class Server {
	boolean hasToken = false;
	boolean sendData = false;
	int recport;

	void recPort(int recport) {
		// TODO Auto-generated method stub
		this.recport = recport;

	}

	public void recData() throws Exception {
		// TODO Auto-generated method stub
		byte bu[] = new byte[256];
		DatagramSocket ds;
		DatagramPacket dp;

		String str;
		ds = new DatagramSocket(recport);
		dp = new DatagramPacket(bu, bu.length);

		ds.receive(dp);
		ds.close();

		str = new String(dp.getData(), 0, dp.getLength());

		System.out.println("This message is " + str);
	}

}
