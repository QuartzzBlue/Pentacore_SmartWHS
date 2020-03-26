package Server;

public class Main {
	
	public static void main(String[] args) {
		
		int port=8888; //port ÇÊ¿ä
		
		Runnable r = new Server(port);
		Thread serverThread = new Thread(r);
		serverThread.start();
	}

}
