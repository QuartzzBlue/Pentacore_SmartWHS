package network;

import com.pentacore.tabletserver.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ThreadPoolExecutor;

import msg.ForkLift;
import msg.Msg;

public class ServerReceiver implements Runnable {

	InputStream is;
	ObjectInputStream ois;

	OutputStream os;
	ObjectOutputStream oos;

	Socket socket;

	int WORKING = 0;
	int WAITING = 1;
	int CHARGING = 2;

	public ServerReceiver() {

	}

	public ServerReceiver(Socket socket) {
		System.out.println("ServerReceiver 객체 생성");
		this.socket = socket;

		try {
			os = socket.getOutputStream();
			oos = new ObjectOutputStream(os);
			is = socket.getInputStream();
			ois = new ObjectInputStream(is);
			ActiveConnection.ipToOos.put(socket.getInetAddress().toString(), oos);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) MainActivity.executorService;
		int poolSize = threadPoolExecutor.getPoolSize();//스레드 풀 사이즈 얻기
		String threadName = Thread.currentThread().getName();//스레드 풀에 있는 해당 스레드 이름 얻기
		System.out.println("ServerReceiver [총 스레드 개수:" + poolSize + "] 작업 스레드 이름: " + threadName);

		Msg msg = null;
		while(ois!=null) {
			try {
				msg = (Msg) ois.readObject();
				msg.ForkLift forkLiftFromMsg = msg.getForkLift();
				msg.Task taskFromMsg = msg.getTask();
				StringBuilder sb = new StringBuilder();
				if(taskFromMsg.getIo()==0) sb.append("[출고] ");
				else if(taskFromMsg.getIo()==1) sb.append("[입고] ");
				sb.append(taskFromMsg.getName()).append(" ")
					.append(taskFromMsg.getQty()).append("개 ")
					.append("(").append(taskFromMsg.getLocX())
					.append(", ").append(taskFromMsg.getLocY()).append(")");

				MainActivity.printConsole(msg.getSrcID()+"로부터 수신, Status:"+forkLiftFromMsg.getStatus()+", Battery"+forkLiftFromMsg.getBattery()+", XY:"+forkLiftFromMsg.getLocX()+","+forkLiftFromMsg.getLocY());

				// forklift 상태 정보 업데이트
				logistics.ForkLift forkLift = (logistics.ForkLift) MainActivity.forkLiftMap.get(msg.getSrcID());
				forkLift.setCurrentTask(sb.toString());
				forkLift.setTemparature(forkLiftFromMsg.getTemperature());
				forkLift.setBattery(forkLiftFromMsg.getBattery());
				forkLift.setCurrentX(forkLiftFromMsg.getLocX());
				forkLift.setCurrentY(forkLiftFromMsg.getLocY());
				forkLift.setStatus(forkLiftFromMsg.getStatus());
				MainActivity.updateForkLiftUI(msg.getSrcID());


			} catch (Exception e) {
//			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
				ActiveConnection.ipToOos.remove(socket.getInetAddress().toString());

				//value 값으로 key 값 찾기
				for (String id : ActiveConnection.idToIp.keySet()) {
					if (socket.getInetAddress().toString().equals(ActiveConnection.idToIp.get(id))) {
						ActiveConnection.idToIp.remove(id);
					}
				}

				MainActivity.printConsole("Disconnected : " + socket.getInetAddress().toString());
				MainActivity.printConsole("접속 수 : " + ActiveConnection.ipToOos.size());
				break;
			}
		}



			// 화면 우측 forklift UI 업데이트 + fokLift View 업데이트
//			MainActivity.taskQueueAdapter.notifyDataSetChanged();
//			MainActivity.updateForkLiftUI(msg.getSrcID());

			// 상태가 변했다는 말
			// HTTP로 msg객체의 내용 전달하기
//			if(forkLift.getStatus() != forkLiftFromMsg.getStatus()) {
//				System.out.println("지게차" + forkLift.getName() + "의 상태가 " + forkLiftFromMsg.getStatus() + "로 변경되었습니다.");
//				MainActivity.printConsole("지게차" + forkLift.getName() + "의 상태가 " + forkLiftFromMsg.getStatus() + "로 변경되었습니다.");
//
//				JSONObject jsonObject = new JSONObject();
//				try {
//					jsonObject.accumulate("forkliftid", msg.getSrcID());
//					jsonObject.accumulate("battery", forkLiftFromMsg.getBattery());
//				} catch (JSONException e) {
//					e.printStackTrace();
//				}
//
//				Runnable sendInHttp = new SendInHttp(jsonObject);
//				MainActivity.executorService.submit(sendInHttp);
//			}
//
//			if(forkLiftFromMsg.getStatus()==WAITING) {
//				MainActivity.waitingForkLiftQueue.offer(forkLift);
//				MainActivity.printConsole("지게차"+forkLift.getName()+"을 대기열에 추가했습니다.");
//				MainActivity.assignTask();
//			}




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