package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
	
	ServerSocket serverSocket;
	boolean SERVER_RUNNING = true;
	
	public Server() {
		
	}
	
	public Server(int port) {
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Server Start (Server °´Ã¼ »ý¼º)");
		
	}

	@Override
	public void run() {
		while (SERVER_RUNNING) {
			Socket socket = null;
			try {
				
				socket = serverSocket.accept();
				System.out.println("new Client Accepted : "+socket.getInetAddress());
				Runnable r = new Receiver(socket);
				Thread ReceiverThread = new Thread(r);
				ReceiverThread.start();
			} catch (IOException e) {
				//e.printStackTrace();
			}
		}
	}
	
}


