package server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	
	static ExecutorService executorService = Executors.newFixedThreadPool(5);

	public static void main(String[] args) {
		
		int port = 8888; //port
		
//		Runnable r = new Server(port);
//		executorService.submit(r);
	}
}
