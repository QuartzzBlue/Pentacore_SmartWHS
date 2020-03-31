package infomatics;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	
	static ExecutorService executorService = Executors.newFixedThreadPool(5);

	public static void main(String[] args) throws Exception {
		
		Runnable r = new Server (7777);
		executorService.submit(r);
		//ECU 의 TCP/IP Server
		//시리얼통신으로 받아오면 필요없음
		
		String address = "192.168.10.1";
		Client client = new Client(address,8888);
		//Tablet Server 의 Client

	}
}
