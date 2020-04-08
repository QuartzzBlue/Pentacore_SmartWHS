package infomatics;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ThreadPoolExecutor;

import msg.Msg;

class Receiver implements Runnable {

	InputStream is;
	ObjectInputStream ois;
	
	OutputStream os;
	ObjectOutputStream oos;

	Socket socket;
	
	public Receiver() {}

	public Receiver(Socket socket) throws IOException {
		this.socket = socket;
		is = socket.getInputStream();
		ois = new ObjectInputStream(is);
		os = socket.getOutputStream();
		oos = new ObjectOutputStream(os);
	}

	@Override
	public void run() {

		ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Main.executorService;
		int poolSize = threadPoolExecutor.getPoolSize();// 스레드 풀 사이즈 얻기
		String threadName = Thread.currentThread().getName();// 스레드 풀에 있는 해당 스레드 이름 얻기

		System.out.println("Connected : "+socket.getInetAddress() + ", 접속 수 : " + ActiveConnection.ipToOos.size());		
		
		
	

		while (ois != null) {
			Msg msg = null;
			try {
				System.out.println("Receiver [총 스레드 개수:" + poolSize + "] 작업 스레드 이름: "+threadName);
				msg = (Msg) ois.readObject();
				System.out.println("source ID : "+msg.getSrcID());		
				int  battery = msg.getForkLift().getBattery();
				
				System.out.println("battery : "+battery);
				msg.setSrcID("Forklift01");
				Runnable r = new Sender(msg);
				Main.executorService.submit(r);
				
//				if(msg.getTask()!=null) {
//					int data = 1;
//					Runnable r = new SerialWrite(data);
//					Main.executorService.submit(r);
//				}

			} catch (Exception e) {
				System.out.println("Server Die");
				ActiveConnection.ipToOos.remove(socket.getInetAddress().toString());
				//value 값으로 key 값 찾기
				for(String id : ActiveConnection.idToIp.keySet()) {
					if(socket.getInetAddress().toString().equals(ActiveConnection.idToIp.get(id))) {
						ActiveConnection.idToIp.remove(id);
					}		
				}
				System.out.println("Disconnected : " + socket.getInetAddress().toString());
				System.out.println("접속 수 : " + ActiveConnection.ipToOos.size());
				break;
			}
		}

		try {
			if (ois != null) {
				ois.close();
			}
			if (socket != null) {
				socket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
