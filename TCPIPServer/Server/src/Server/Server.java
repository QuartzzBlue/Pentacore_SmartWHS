package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ThreadPoolExecutor;

public class Server implements Runnable {
	  // ExecutorService 인터페이스 구현객체 Executors 정적메서드를 통해 최대 스레드 개수가 5인 스레드 풀 생성
	
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
		System.out.println("Server Start (Server 객체 생성)");
		
	}

	@Override
	public void run() {
		while (SERVER_RUNNING) {
            
			Socket socket = null;
			try {
				
				socket = serverSocket.accept();
				System.out.println("new Client Accepted : "+socket.getInetAddress());
				
				ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Main.executorService;
				int poolSize = threadPoolExecutor.getPoolSize();//스레드 풀 사이즈 얻기
				String threadName = Thread.currentThread().getName();//스레드 풀에 있는 해당 스레드 이름 얻기
	            
	            System.out.println(" Server [총 스레드 개수:" + poolSize + "] 작업 스레드 이름: "+threadName);
				
				
				Runnable r = new Receiver(socket);
				//Thread ReceiverThread = new Thread(r);
				Main.executorService.submit(r);
				//ReceiverThread.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}


