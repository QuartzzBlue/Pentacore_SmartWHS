package infomatics;

import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;

public class SerialWrite implements Runnable {

	static String data;
	String sendId = "10000000";
	String sendData = "30000000000000000";
	String msg = sendId + sendData;
	static boolean flag = false;

	public SerialWrite() {
		this.data = ":G11A9\r";
		flag = true;
	}

	public SerialWrite(String str) {
		this.data = str;
	}

	@Override
	public void run() {

		ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Main.executorService;
		int poolSize = threadPoolExecutor.getPoolSize();// 스레드 풀 사이즈 얻기
		String threadName = Thread.currentThread().getName();// 스레드 풀에 있는 해당 스레드 이름 얻기

		if (flag) {

			System.out.println("Begin : " + data);

			byte[] outData = data.getBytes();
			try {
				SerialServer.out.write(outData);
			} catch (Exception e) {
				e.printStackTrace();
			}

			flag = false;

		} else {
			while (SerialServer.out != null && !flag) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("SerialWrite [총 스레드 개수:" + poolSize + "] 작업 스레드 이름: " + threadName);
				if (Receiver.status == 0) { // Task 를 부여 받으면 ForkliftInfomation,Battery 에게 전송
					sendData.charAt(12)='1';
				}
				String tmp = sendId + Receiver.status + ""+sendData;

				tmp = tmp.substring(0, tmp.length() - data.length()) + data;

				System.out.println("tmp : " + tmp);

				tmp = tmp.toUpperCase();
				tmp = "W28" + tmp;
				char[] c = tmp.toCharArray();
				int checkSum = 0;
				for (char ch : c) {
					checkSum += ch;
				}
				String result = ":";
				result += tmp + Integer.toHexString(checkSum).toUpperCase() + "\r";
				System.out.println("result : " + result);

				byte[] outData = data.getBytes();
				try {
					SerialServer.out.write(outData);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}

	}// run method

}
