package infomatics;

import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;

public class SerialWrite implements Runnable {

	static String data;
	static String sendId = "10000002";
	String sendData = "0000000000000000";
	String msg = sendId + sendData;
	boolean flag = false;

	public SerialWrite() {
		this.data = ":G11A9\r";
		System.out.println("Begin : " + data);

	}

	public SerialWrite(String str) {
		this.data = str;
	}

	@Override
	public void run() {

		ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Main.executorService;
		int poolSize = threadPoolExecutor.getPoolSize();// 스레드 풀 사이즈 얻기
		String threadName = Thread.currentThread().getName();// 스레드 풀에 있는 해당 스레드 이름 얻기

		System.out.println("SerialWrite [총 스레드 개수:" + poolSize + "] 작업 스레드 이름: " + threadName);

		while (SerialServer.out != null) {

			if (!data.equals(":G11A9\r")) {

				String tmp = sendId + sendData;

				tmp = tmp.substring(0, tmp.length() - data.length()) + data;
				tmp = tmp.toUpperCase();
				tmp = "W28" + tmp;

				char[] c = tmp.toCharArray();
				int checkSum = 0;
				for (char ch : c) {
					checkSum += ch;
				}
				checkSum = (checkSum & 0xFF);
				String result = ":";
				result += tmp + Integer.toHexString(checkSum).toUpperCase() + "\r";
				System.out.println("result : " + result);
				this.data = result;

			}
			
			else if (!flag) {
				byte[] outData = data.getBytes();
				try {
					SerialServer.out.write(outData);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				flag = true;
			}
		}

	}// run method

}
