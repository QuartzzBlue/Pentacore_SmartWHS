package infomatics;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ThreadPoolExecutor;

import msg.Msg;
import msg.Task;

class Receiver implements Runnable {

//	OutputStream os;
//	ObjectOutputStream oos;
	InputStream is;
	ObjectInputStream ois;

	Socket socket;
	static Task task;

	
	public Receiver() {	}

	public Receiver(Socket socket) {
		System.out.println("Receiver constructed");
		
		try {
			is = socket.getInputStream();
			ois = new ObjectInputStream(is);
//			os = socket.getOutputStream();
//			oos = new ObjectOutputStream(os);			
		} catch (IOException e) {
			e.getMessage();
		}
	}

	@Override
	public void run() {

		ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Main.executorService;
		int poolSize = threadPoolExecutor.getPoolSize();// 스레드 풀 사이즈 얻기
		String threadName = Thread.currentThread().getName();// 스레드 풀에 있는 해당 스레드 이름 얻기

		// get Task from TabletServer
		while (ois != null) {
			Msg msg = null;
			Task task = null;
			
			try {
				System.out.println("Receiver [총 스레드 개수:" + poolSize + "] 작업 스레드 이름: " + threadName);
				
				msg = (Msg) ois.readObject();
				task = msg.getTask();
				Status.status = 0;
				Status.stockX = task.getLocX();
				Status.stockY = task.getLocY();
				Runnable move = new Move();
				Main.executorService.submit(move);
				//Test
				
//				msg = new Msg("tabletServer","ForkliftInfomatics");
//				msg.setTask(1, "PHONE", 1, 3, 20);
//				task = msg.getTask();
//				
				//
				
//				if (msg.getTask() != null) {
//					System.out.println("Received Task - " + "srcid: " + msg.getSrcID() + " dstnid : " + msg.getDstnID()
//							+ " IO : " + msg.getTask().getIo() + " LocX : " + msg.getTask().getLocX() + " LocY : "
//							+ msg.getTask().getLocY() + " itemName : " + msg.getTask().getName() + " Qty : "
//							+ msg.getTask().getQty());
//
//					SerialWrite.sendId = "10000000";
////					SerialWrite.data = msg.getTask().getLocX()+""+msg.getTask().getLocY();
//					Runnable r = new SerialWrite(msg.getTask().getLocX()+""+msg.getTask().getLocY());
//					Main.executorService.submit(r);
//					
//
//				}

			} catch (Exception e) {
				System.out.println("Exception at Receiver.java : "+e.getMessage());
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
