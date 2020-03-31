package infomatics;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Client{

	static Socket socket;
	
	public Client() {
	}

	public Client(String address, int port) throws IOException {
		
		try {
			socket = new Socket(address, port);
		} catch (Exception e) {
			while (true) {
				System.out.println("Retry..");
				try {
					Thread.sleep(1000);
					socket = new Socket(address, port);
					break;
				} catch (Exception e1) {
					// e1.printStackTrace();
				}
			}
		}

		System.out.println("Connected Tablet Server : " + address);
		
		//Runnable r = new Receiver(socket);
		//Main.executorService.submit(r);
	
	}
}


