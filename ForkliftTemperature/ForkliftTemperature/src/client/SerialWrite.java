package client;

import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;

public class SerialWrite implements Runnable {

	String data;

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
		int poolSize = threadPoolExecutor.getPoolSize();
		String threadName = Thread.currentThread().getName();

//		while (SerialClient.out != null) {
//			
//			System.out.println("SerialWrite");
//
//			String tmp = SerialClient.id + SerialClient.data;
//
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e1) {
//				e1.printStackTrace();
//			}
//
//			if (temperature < 50) {
//				flag2 = 1;
//			} else if (temperature > 100)
//				flag2 = -1;
//
//			temperature += (int) (Math.random() * 2) * flag2;
//
//			// x,y �쐞移� 湲몄씠媛� 4臾몄옄媛� �릺寃�
//			if (temperature < 10) {
//				temperatureStr = "0" + temperature;
//			} else {
//				temperatureStr = temperature + "";
//			}
//
//			int temperatureStrlen = temperatureStr.length();
//
//			tmp = tmp.substring(0, tmp.length() - temperatureStrlen) + temperatureStr;
//
//			tmp = tmp.toUpperCase();
//			SerialClient.msg = "W28" + tmp;
//			// W28 00000000 0000000000000000
//
//			// checkSum 怨꾩궛
//			char[] c = SerialClient.msg.toCharArray();
//			int checkSum = 0;
//			for (char ch : c) {
//				checkSum += ch;
//			}
//			checkSum = (checkSum & 0xFF);
//
//			String result = ":";
//			result += SerialClient.msg + Integer.toHexString(checkSum).toUpperCase() + "\r";
//			System.out.println("result : " + result);
//			this.data = result;
      
			byte[] outData = data.getBytes();
			try {
				SerialClient.out.write(outData);// �̷��� data�� CAN Network Area�� ���.
			} catch (IOException e) {
				e.printStackTrace();
			}


//		} // While


	}

}
