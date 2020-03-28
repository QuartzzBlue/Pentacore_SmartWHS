package network;

import com.pentacore.tabletserver.MainActivity;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.concurrent.ThreadPoolExecutor;

import msg.Msg;

public class Sender implements Runnable {

	OutputStream os;
	ObjectOutputStream oos;
	Msg msg;


	public Sender () {

	}

	public Sender(Msg msg) {
		this.msg = msg;
	}

	@Override
	public void run() {


		ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) MainActivity.executorService;
		int poolSize = threadPoolExecutor.getPoolSize();//스레드 풀 사이즈 얻기
		String threadName = Thread.currentThread().getName();//스레드 풀에 있는 해당 스레드 이름 얻기

		//int value = Integer.parseInt("예외");

		System.out.println("Sender [총 스레드 개수:" + poolSize + "] 작업 스레드 이름: "+threadName);


		System.out.println("srcip : "+msg.getSrcIP()+", srcid : "+msg.getSrcID()+", dstnip : "+msg.getDstnIP()
				+", dstnid : "+msg.getDstnID()+", content : "+msg.getContent());

		if(ActiveConnection.idToIp.containsKey(msg.getDstnID())) {
			String getip = ActiveConnection.idToIp.get(msg.getDstnID());
			try {
				ActiveConnection.ipToOos.get(getip).writeObject(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


	}


}