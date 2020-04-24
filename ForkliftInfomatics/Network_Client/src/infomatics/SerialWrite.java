package infomatics;

import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;

public class SerialWrite implements Runnable {

	static String data;
	static String sendId = "10000001";
	static String sendData = "0000000000000000";
	static String msg = sendId + sendData;

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

	@Override
	public void run() {

		ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Main.executorService;
		int poolSize = threadPoolExecutor.getPoolSize();// �뒪�젅�뱶 �� �궗�씠利� �뼸湲�
		String threadName = Thread.currentThread().getName();// �뒪�젅�뱶 ���뿉 �엳�뒗 �빐�떦 �뒪�젅�뱶 �씠由� �뼸湲�

		byte[] outData = data.getBytes();
		try {
			SerialServer.out.write(outData);
			System.out.println("***********Msg Send**********");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}// run method
