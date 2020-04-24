package infomatics;

import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;

public class SerialWrite implements Runnable {

	static String data;
	static String sendId = "10000001";
	String sendData = "0000000000000000";
	String msg = sendId + sendData;

	public SerialWrite() {
		this.data = ":G11A9\r";
	}

	public SerialWrite(String str) {
		this.data = str;
	}

	@Override
	public void run() {

		ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Main.executorService;
		int poolSize = threadPoolExecutor.getPoolSize();// �뒪�젅�뱶 �� �궗�씠利� �뼸湲�
		String threadName = Thread.currentThread().getName();// �뒪�젅�뱶 ���뿉 �엳�뒗 �빐�떦 �뒪�젅�뱶 �씠由� �뼸湲�

		System.out.println("SerialWrite");

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

		byte[] outData = data.getBytes();
		try {
			SerialServer.out.write(outData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}// run method
