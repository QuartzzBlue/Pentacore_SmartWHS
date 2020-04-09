package client;

import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;

public class SerialWrite implements Runnable {

	String data;
	static int forkliftLocX = 12;
	static int forkliftLocY = 0;
	String forkliftLocXStr;
	String forkliftLocYStr;
	static boolean flag = false;

	// 모든 데이터는 String
	public SerialWrite() {
		this.data = ":G11A9\r";
		flag = true;

	}

//	public SerialWrite(String msg) {
//		this.data = convertData(msg);
//	}

//	public String convertData(String msg) {
//		int num = (int) (Math.random() * 100);
//		if (num < 10) {
//			msg += ("0" + num);
//		} else {
//			msg += num;
//		}
//		msg = msg.toUpperCase();
//		msg = "W28" + msg;
//		// W28 00000000 0000000000000000
//		char[] c = msg.toCharArray();
//		int checkSum = 0;
//		for (char ch : c) {
//			checkSum += ch;
//		}
//		checkSum = (checkSum & 0xFF);
//		String result = ":";
//		result += msg + Integer.toHexString(checkSum).toUpperCase() + "\r";
//		System.out.println("result : " + result);
//		return result;
//	}

	@Override
	public void run() {

		ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Main.executorService;
		int poolSize = threadPoolExecutor.getPoolSize();// 스레드 풀 사이즈 얻기
		String threadName = Thread.currentThread().getName();// 스레드 풀에 있는 해당 스레드 이름 얻기

		if (flag) {
			System.out.println("Begin : " + data);
			byte[] outData = data.getBytes();
			try {
				SerialClient.out.write(outData);// 이렇게 data를 CAN Network Area에 쏜다.
			} catch (IOException e) {
				e.printStackTrace();
			}

			flag = false;

		} 
			while (SerialClient.out != null && !flag) {

				System.out.println("SerialWrite [총 스레드 개수:" + poolSize + "] 작업 스레드 이름: " + threadName);

				String tmp = SerialClient.id + SerialClient.data;

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}

				// 수신한 stock 의 x 좌표와 y좌표에 따라 forklift 의 위치가 변경되는 로직 구현
//			if(SerialClient.stockLocX!=0) {
//				
//			}
//			
//			if(SerialClient.stockLocY!=0) {
//				if(forkliftLocY!=SerialClient.stockLocY){
//					forkliftLocY++;
//				}
//			}

				// Web 에게 보내주기 위한 Test 용. x,y 가 번갈아가며 1씩 증가
				
//				if (flag) {
//
//					forkliftLocX++;
//
//					flag = false;
//				} else {
//					forkliftLocY++;
//					flag = true;
//				}

				// x,y 위치 길이가 4문자가 되게
				if (forkliftLocX < 10) {
					forkliftLocXStr = "0" + forkliftLocX;
				} else {
					forkliftLocXStr = forkliftLocX + "";
				}

				if (forkliftLocY < 10) {
					forkliftLocYStr = "0" + forkliftLocY;
				} else {
					forkliftLocYStr = forkliftLocY + "";
				}

				String locationStr = forkliftLocXStr + forkliftLocYStr;
				int locationStrlen = locationStr.length();

				tmp = tmp.substring(0, tmp.length() - locationStrlen) + locationStr;

				System.out.println("tmp : " + tmp);

				tmp = tmp.toUpperCase();
				SerialClient.msg = "W28" + tmp;
				// W28 00000000 0000000000000000

				// checkSum 계산
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
					SerialClient.out.write(outData);// 이렇게 data를 CAN Network Area에 쏜다.
				} catch (IOException e) {
					e.printStackTrace();
				}
			} // run method
		
	}

}
