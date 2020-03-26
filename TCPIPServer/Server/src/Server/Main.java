package Server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {
	
	
	static ExecutorService executorService = Executors.newFixedThreadPool(5);

	public static void main(String[] args) {
		
		int port=8888; //port ÇÊ¿ä
		
		Runnable r = new Server(port);
		//Thread serverThread = new Thread(r);
		//serverThread.start();
		//ActiveConnection.executorService.execute(r);
		executorService.execute(r);
	}

}
