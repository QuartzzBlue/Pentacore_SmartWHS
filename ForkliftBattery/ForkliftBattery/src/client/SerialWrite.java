package client;

import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;

public class SerialWrite implements Runnable {

	String data;
	int battery=999;
	static int d=-2;

	// 모든 데이터는 String
	public SerialWrite() {
		this.data = ":G11A9\r";
	}
	
	
	@Override
	public void run() {
		
		ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Main.executorService;
		int poolSize = threadPoolExecutor.getPoolSize();// 스레드 풀 사이즈 얻기
		String threadName = Thread.currentThread().getName();// 스레드 풀에 있는 해당 스레드 이름 얻기
		

		while (SerialClient.out != null ) {
			
			System.out.println("SerialWrite [총 스레드 개수:" + poolSize + "] 작업 스레드 이름: " + threadName);
			
			String tmp = SerialClient.id+SerialClient.data;
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			String batteryStr = battery+"";
			int batteryLen = batteryStr.length();
	
			tmp = tmp.substring(0, tmp.length()-batteryLen)+batteryStr;
				
			battery+=d;
			
			if(battery >= 999) {
				battery = 999;
			}
			
			tmp = tmp.toUpperCase();
			SerialClient.msg = "W28" + tmp;
			// W28 00000000 0000000000000000
			
			//checkSum 계산
			char[] c = SerialClient.msg.toCharArray();
			int checkSum = 0;
			for (char ch : c) {
				checkSum += ch;
			}
			checkSum = (checkSum & 0xFF);
			
			String result = ":";
			result += SerialClient.msg + Integer.toHexString(checkSum).toUpperCase() + "\r";
			System.out.println("result : " + result);
			this.data = result;

			byte[] outData = data.getBytes();
			try {
				SerialClient.out.write(outData);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} // run method
	}

}
