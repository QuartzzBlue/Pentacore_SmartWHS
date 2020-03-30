package network;

import com.pentacore.tabletserver.MainActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ThreadPoolExecutor;

import msg.Msg;

public class ServerReceiver implements Runnable {

	InputStream is;
	ObjectInputStream ois;

	OutputStream os;
	ObjectOutputStream oos;

	Socket socket;

	public ServerReceiver() {

	}

	public ServerReceiver(Socket socket) {
		System.out.println("ServerReceiver 객체 생성");
		this.socket = socket;

		try {

			is = socket.getInputStream();
			ois = new ObjectInputStream(is);
			os = socket.getOutputStream();
			oos = new ObjectOutputStream(os);

			ActiveConnection.ipToOos.put(socket.getInetAddress().toString(), oos);
			System.out.println("Connected : " + socket.getInetAddress().toString() + ", 접속 수 : " + ActiveConnection.ipToOos.size());

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {

		ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) MainActivity.executorService;
		int poolSize = threadPoolExecutor.getPoolSize();//스레드 풀 사이즈 얻기
		String threadName = Thread.currentThread().getName();//스레드 풀에 있는 해당 스레드 이름 얻기
		System.out.println("ServerReceiver [총 스레드 개수:" + poolSize + "] 작업 스레드 이름: "+threadName);

		while(ois!=null) {

			Msg msg = null;
			try {

				ActiveConnection.idToIp.put(msg.getSrcID(), socket.getInetAddress().toString());
				System.out.println(msg.getSrcID() + "로부터 msg 수신, 내용 : " + msg.getContent());

				msg = (Msg) ois.readObject();
				if(msg.getSrcID().contains("tcpipServer")) {
					// TCP/IP Server에서 Task 도착한 경우, 큐에 TASK 추가

				} else if (msg.getSrcID().contains("forkLift")) {
					// 1. HTTP로 WebAppSer에 보내서 로그 쌓게 할 수 있게 하기
					// 2. forkLift Status의 경우에는 runOnUiThread()로 화면에 상태변화 표시하기
				}

			} catch (ClassNotFoundException | IOException e) {
				//ActiveConnection.executorService.shutdown();
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
			}//catch
		}//while

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