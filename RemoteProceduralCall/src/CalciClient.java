import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class CalciClient {
	Socket socket;
	int port;

	public CalciClient(int port) {
		this.port = port;
	}

	public void sndReq() throws Exception {
		socket = new Socket("localhost", port);

		DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
		DataInputStream din = new DataInputStream(socket.getInputStream());
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String str = "";
		int num1, num2;
		System.out.println("1. Addition \n 2. Subtraction \n 3. Multiplication \n 4. Division \n 5.Exit");
		System.out.println("\n Enter Your Choice ");
		int choice = Integer.parseInt(in.readLine());
		System.out.println("Value =" + choice);
		// int choice =1; //Integer.parseInt(in.readUTF());

		switch (choice) {
		case 1:
			str += choice + "-";
			System.out.println("Enter First Number: ");
			num1 = Integer.parseInt(in.readLine());
			str += num1 + "-";
			System.out.println("Enter Second Number: ");
			num2 = Integer.parseInt(in.readLine());
			str += num2;
			break;
		case 2:
			str += choice + "-";
			System.out.println("Enter First Number: ");
			num1 = Integer.parseInt(in.readLine());
			str += num1 + "-";
			System.out.println("Enter Second Number: ");
			num2 = Integer.parseInt(in.readLine());
			str += num2;
			break;
		case 3:
			str += choice + "-";
			System.out.println("Enter First Number: ");
			num1 = Integer.parseInt(in.readLine());
			str += num1 + "-";
			System.out.println("Enter Second Number: ");
			num2 = Integer.parseInt(in.readLine());
			str += num2;
			break;
		case 4:
			str += choice + "-";
			System.out.println("Enter First Number: ");
			num1 = Integer.parseInt(in.readLine());
			str += num1 + "-";
			System.out.println("Enter Second Number: ");
			num2 = Integer.parseInt(in.readLine());
			str += num2;
			break;
		case 5:
			System.out.println("Program Exited!!");
			break;
		default:
			System.out.println("Invalid Choice!");
			break;
		}
		System.out.println(str);
		dout.writeUTF(str);
		dout.flush();

		String result = din.readUTF();
		System.out.println("Result is: " + result);

		din.close();
		dout.close();
		socket.close();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			CalciClient cc = new CalciClient(5000);
			cc.sndReq();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

}
