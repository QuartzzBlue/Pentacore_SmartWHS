package client;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadPoolExecutor;

public class SerialWrite implements Runnable {

	String data;
	TimerTask task;
	Timer timer;

	// 모든 데이터는 String
	public SerialWrite() {
		this.data = ":G11A9\r";
	}

	public SerialWrite(String msg) {
		this.data = convertData(msg);
	}
	
	public String convertData(String msg) {
		msg = msg.toUpperCase();
		msg = "W28" + msg;
		//W28 00000000 0000000000000000
		char[] c = msg.toCharArray();
		int checkSum = 0;
		for(char ch:c) {
			checkSum+=ch;
		}
		checkSum = (checkSum & 0xFF);
		String result = ":";
		result += msg + 
				Integer.toHexString(checkSum).toUpperCase()+
				"\r";
		System.out.println("Send Data : "+result);
		return result;
	}

	public synchronized void waitOneSecond() {
		try {
			this.wait(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Main.executorService;
		int poolSize = threadPoolExecutor.getPoolSize();// 스레드 풀 사이즈 얻기
		String threadName = Thread.currentThread().getName();// 스레드 풀에 있는 해당 스레드 이름 얻기

//		byte[] outData = data.getBytes();
//		try {
//			SerialClient.out.write(outData);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		// while (SerialClient.out != null) {

		//System.out.println("SerialWrite [총 스레드 개수:" + poolSize + "] 작업 스레드 이름: " + threadName);

		// String tmp = SerialClient.id + SerialClient.data;

		//waitOneSecond();

//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e1) {
//				e1.printStackTrace();
//			}

//			try {
//				task = new TimerTask() {
//					@Override
//					public void run() {
//						String data2;
//						
//						String batteryStr = battery+"";
//						int batteryLen = batteryStr.length();
//				
//						String tmp2 = tmp.substring(0, tmp.length()-batteryLen)+batteryStr;
//							
//						battery+=d;
//						
//						if(battery >= 999) {
//							battery = 999;
//						}
//						
//						tmp2 = tmp2.toUpperCase();
//						SerialClient.msg = "W28" + tmp2;
//						// W28 00000000 0000000000000000
//						
//						//checkSum 계산
//						char[] c = SerialClient.msg.toCharArray();
//						int checkSum = 0;
//						for (char ch : c) {
//							checkSum += ch;
//						}
//						checkSum = (checkSum & 0xFF);
//						
//						String result = ":";
//						result += SerialClient.msg + Integer.toHexString(checkSum).toUpperCase() + "\r";
//						System.out.println("result : " + result);
//						data2 = result;
//
//						byte[] outData = data2.getBytes();
//						try {
//							SerialClient.out.write(outData);
//						} catch (IOException e) {
//							e.printStackTrace();
//						}
//					}
//				};
//				timer = new Timer();
//				timer.scheduleAtFixedRate(task, 1234, 32320);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}

//		String batteryStr = battery + "";
//		int batteryLen = batteryStr.length();
//
//		tmp = tmp.substring(0, tmp.length() - batteryLen) + batteryStr;
//
//		battery += d;
//
//		if (battery >= 999) {
//			battery = 999;
//		}

//		data = data.toUpperCase();
//		data = "W28" + data;
//		// W28 00000000 0000000000000000
//
//		// checkSum 계산
//		char[] c = data.toCharArray();
//		int checkSum = 0;
//		for (char ch : c) {
//			checkSum += ch;
//		}
//		checkSum = (checkSum & 0xFF);
//
//		String result = ":";
//		result += data + Integer.toHexString(checkSum).toUpperCase() + "\r";
//		System.out.println("result : " + result);
//		this.data = result;

		byte[] outData = data.getBytes();
		try {
			SerialClient.out.write(outData);
		} catch (IOException e) {
			e.printStackTrace();
		}
	} // run method
		// }

}
