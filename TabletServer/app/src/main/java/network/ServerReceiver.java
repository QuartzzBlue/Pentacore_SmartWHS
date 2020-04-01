package network;

import android.view.View;

import com.pentacore.tabletserver.MainActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ThreadPoolExecutor;

import logistics.ForkLift;
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
				System.out.println(msg.getSrcID() + "로부터 msg 수신");

				msg = (Msg) ois.readObject();

				// forklift 상태 정보 업데이트
                ForkLift forkLift = (ForkLift)MainActivity.forkLiftMap.get(msg.getSrcID());
				// forkLift.setBattery(msg.getBattery());
				// forkLift.setCurrentTask(msg.getTask());
				// forkLift.setCurrentX(msg.getX());
				// forkLift.setCurrentY(msg.getY());
				// forkLift.setStatus(msg.getStatus());

				// 화면 우측 forklift UI 업데이트 + fokLift View 업데이트
				MainActivity.updateForkLiftUI(msg.getSrcID());

                //if(forkLift.getStatus() != msg.getStatus()) {
                    // 상태가 변했다는 말
                    // HTTP로 msg객체의 내용 전달하기
				Runnable sendInHttp = new SendInHttp("{'jsonInputString' : 'content'}");
				MainActivity.executorService.submit(sendInHttp);

				//if(msg.getStatus()==WAITING) {
				// MainActivity.waitingForkLiftQueue.offer(forkLift);
				MainActivity.assignTask();
				// 두개 queue 비교해서 task할당하는 함수
				//}
                //}

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