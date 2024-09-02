import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	private Socket socket;
	private BufferedReader bufferReader;
	private BufferedWriter bufferWriter;
	private String username;

	public Client(Socket socket, String username) {
		try {
			this.socket = socket;
			this.username = username;
			this.bufferWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			this.bufferReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		} catch (IOException e) {
			closeEverything(socket, bufferReader, bufferWriter);
		}
	}

	public void sendMessage() {
		try {
			bufferWriter.write(username);
			bufferWriter.newLine();
			bufferWriter.flush();
			Scanner scanner = new Scanner(System.in);
			while (socket.isConnected()) {
				String messageToSend = scanner.nextLine();
				bufferWriter.write(username + ": " + messageToSend);
				bufferWriter.newLine();
				bufferWriter.flush();
			}
		} catch (IOException e) {
			// TODO: handle exception
			closeEverything(socket, bufferReader, bufferWriter);

		}

	}

	public void listenForMessage() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				String msgFromGroupChat;
				while (socket.isConnected()) {
					try {
						msgFromGroupChat = bufferReader.readLine();
						System.out.println(msgFromGroupChat);
					} catch (IOException e) {
						// TODO: handle exception
						closeEverything(socket, bufferReader, bufferWriter);
					}
				}

			}
		}).start();
	}
	public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
		try {
			if (bufferedReader != null) {
				bufferedReader.close();
			}
			if (bufferedWriter != null) {
				bufferedWriter.close();
			}
			if (socket != null) {
				socket.close();
			}
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws UnknownHostException, IOException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your Username for the group chat ");
		String username = scanner.nextLine();
		Socket socket = new Socket("localhost", 8867);
		Client client = new Client(socket, username);
		client.listenForMessage();
		client.sendMessage();
	}
}