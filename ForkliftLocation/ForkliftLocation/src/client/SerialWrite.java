package client;

import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;

public class SerialWrite implements Runnable {

	String data;

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
		// W28 00000000 0000000000000000
		char[] c = msg.toCharArray();
		int checkSum = 0;
		for (char ch : c) {
			checkSum += ch;
		}
		checkSum = (checkSum & 0xFF);
		String result = ":";
		result += msg + Integer.toHexString(checkSum).toUpperCase() + "\r";
		System.out.println("Send Data : " + result);
		return result;
	}

	@Override
	public void run() {

		ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Main.executorService;
		int poolSize = threadPoolExecutor.getPoolSize();// 스레드 풀 사이즈 얻기
		String threadName = Thread.currentThread().getName();// 스레드 풀에 있는 해당 스레드 이름 얻기

//		while (SerialClient.out != null) {
//
//			System.out.println("SerialWrite [총 스레드 개수:" + poolSize + "] 작업 스레드 이름: " + threadName);
//
//			String tmp = SerialClient.id + SerialClient.data;
//
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e1) {
//				e1.printStackTrace();
//			}
//
//			// 수신한 stock 의 x 좌표와 y좌표에 따라 forklift 의 위치가 변경되는 로직 구현
//			if (SerialClient.stockLocX % 4 == 2 && !done) { // 랙의 왼쪽에 위치
//
//				if (!back) {
//					if (forkliftLocX != SerialClient.stockLocX - 2) {
//						forkliftLocX--;
//						
//					} else if (forkliftLocY != SerialClient.stockLocY) {
//						forkliftLocY--;
//					
//					} else if (forkliftLocY == SerialClient.stockLocY && forkliftLocX == SerialClient.stockLocX - 2) {
//						forkliftLocX++;
//						
//						back = true;
//					}
//				}
//
//				else {
//
//					if (forkliftLocY != 12) {
//						forkliftLocY++;
//					} else if (forkliftLocX != 11) {
//						forkliftLocX++;
//					} else if (forkliftLocY == 12 && forkliftLocX == 11) {
//						forkliftLocY = 13;
//						SerialClient.id = "14000005";
//						done = true;
//					}
//
//				}
//
//			} else if (SerialClient.stockLocX %4==3 && !done) { // 랙의 오른쪽에 위치
//				if (!back) {
//					if (forkliftLocX < SerialClient.stockLocX + 1) {
//						if(!flag) {
//							forkliftLocY--;
//							flag = true;
//						}
//						else forkliftLocX++;
//						
//					}else if (forkliftLocX > SerialClient.stockLocX+1) {
//						forkliftLocX--;
//					}
//					
//					else if (forkliftLocY != SerialClient.stockLocY) {
//						forkliftLocY--;
//					
//					} else if (forkliftLocY == SerialClient.stockLocY && forkliftLocX == SerialClient.stockLocX +1) {
//						back = true;
//					}
//				}
//
//				else {
//
//					if (forkliftLocY != 13) {
//						forkliftLocY++;
//					} else if (forkliftLocX < 11) {
//						forkliftLocX++;
//					} else if (forkliftLocY>11) {
//						forkliftLocX--;
//					}
//					
//					if (forkliftLocY == 13 && forkliftLocX == 11) {
//						done = true;
//					}
//
//				}
//			}
//		
//
//			System.out.println(forkliftLocX);
//			System.out.println(forkliftLocY);
//
//			// x,y 위치 길이가 4문자가 되게
//			if (forkliftLocX < 10) {
//				forkliftLocXStr = "0" + forkliftLocX;
//			} else {
//				forkliftLocXStr = forkliftLocX + "";
//			}
//
//			if (forkliftLocY < 10) {
//				forkliftLocYStr = "0" + forkliftLocY;
//			} else {
//				forkliftLocYStr = forkliftLocY + "";
//			}
//
//			String locationStr = forkliftLocXStr + forkliftLocYStr;
//			int locationStrlen = locationStr.length();
//
//			tmp = tmp.substring(0, tmp.length() - locationStrlen) + locationStr;
//
//			tmp = tmp.toUpperCase();
//			SerialClient.msg = "W28" + tmp;
//			// W28 00000000 0000000000000000
//
//			// checkSum 계산
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
			SerialClient.out.write(outData);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// }

}
