import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {
	public static ArrayList<ClientHandler> clientHandlers = new ArrayList<ClientHandler>();
	private Socket socket;
	private BufferedReader bufferReader;
	private BufferedWriter bufferWriter;
	private String clientUsername;
	private String messageToSend;

	public ClientHandler(Socket socket) {
		try {
			this.socket = socket;
			this.bufferReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.bufferWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			this.clientUsername = bufferReader.readLine();
			clientHandlers.add(this);
			broadcastMessage("SERVER : " + clientUsername + " has Entered the Chat! ");

		} catch (IOException e) {
			// TODO: handle exception
			closeEverything(socket, bufferReader, bufferWriter);

		}

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String messageFromClient;
		while (socket.isConnected()) {
			try {
				messageFromClient = bufferReader.readLine();
				broadcastMessage(messageFromClient);
			} catch (IOException e) {
				// TODO: handle exception
				closeEverything(socket, bufferReader, bufferWriter);
				break;
			}

		}
	}

	public void broadcastMessage(String messageToSend) {
		for (ClientHandler clientHandler : clientHandlers) {
			try {
				if (!clientHandler.clientUsername.equals(clientUsername)) {
					clientHandler.bufferWriter.write(messageToSend);
					clientHandler.bufferWriter.newLine();
					clientHandler.bufferWriter.flush();
				}

			} catch (IOException e) {
				// TODO: handle exception
				closeEverything(socket, bufferReader, bufferWriter);
			}
		}
	}

	public void removeClientHandler() {
		clientHandlers.remove(this);
		broadcastMessage("SERVER: " + clientUsername + " has Left the Chat! ");
	}

	public void closeEverything(Socket socket, BufferedReader bufferReader, BufferedWriter bufferedWriter) {
		removeClientHandler();
		try {
			if (bufferReader != null) {
				bufferReader.close();
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
}
