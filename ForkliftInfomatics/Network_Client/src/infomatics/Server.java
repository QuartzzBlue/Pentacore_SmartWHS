//시리얼통신으로 받으면 삭제 필

package infomatics;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ThreadPoolExecutor;

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
		System.out.println("TCP/IP Server Start");
		
	}

	@Override
	public void run() {
		while (SERVER_RUNNING) {
            
			Socket socket = null;
			try {
				
				ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Main.executorService;
				int poolSize = threadPoolExecutor.getPoolSize();//스레드 풀 사이즈 얻기
				String threadName = Thread.currentThread().getName();//스레드 풀에 있는 해당 스레드 이름 얻기
	            
	            System.out.println("Server [총 스레드 개수:" + poolSize + "] 작업 스레드 이름: "+threadName);
				
				socket = serverSocket.accept();
				System.out.println("new ECU Accepted : "+socket.getInetAddress());
				
				Runnable r = new Receiver(socket);
				Main.executorService.submit(r);
				
				//Runnable r2 = new Sender(Client.socket);
				//Main.executorService.submit(r2);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}


