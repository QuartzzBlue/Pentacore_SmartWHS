package client;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	static ExecutorService executorService = Executors.newFixedThreadPool(5);
	static String id = "14000004";
	static String data = "0000000000000000";
	static String msg = id + data;
	static int forkliftLocX = 11;
	static int forkliftLocY = 13;
	static String forkliftLocXStr;
	static String forkliftLocYStr;
	static boolean back = false;
	static boolean flag = false;
	static boolean done = false;


	public static void main(String[] args) throws Exception {

		String port;
		System.out.println("Port Number : ");
		Scanner sc = new Scanner(System.in);
		port = sc.nextLine();

		SerialClient serialClient = new SerialClient(port);

		while (true) {

			// 수신한 stock 의 x 좌표와 y좌표에 따라 forklift 의 위치가 변경되는 로직 구현
			if (SerialClient.stockLocX > 0 && SerialClient.stockLocX % 4 == 2 && !done) { // 랙의 왼쪽에 위치

				if (!back) {
					if (forkliftLocX != SerialClient.stockLocX - 2) {
						forkliftLocX--;
						
					} else if (forkliftLocY != SerialClient.stockLocY) {
						forkliftLocY--;
					
					} else if (forkliftLocY == SerialClient.stockLocY && forkliftLocX == SerialClient.stockLocX - 2) {
						forkliftLocX++;
						
						back = true;
					}
				}

				else {

					if (forkliftLocY != 12) {
						forkliftLocY++;
					} else if (forkliftLocX != 11) {
						forkliftLocX++;
					} else if (forkliftLocY == 12 && forkliftLocX == 11) {
						forkliftLocY = 13;
						done = true;
					}

				}

			} else if (SerialClient.stockLocY > 0 && SerialClient.stockLocX %4==3 && !done) { // 랙의 오른쪽에 위치
				if (!back) {
					if (forkliftLocX < SerialClient.stockLocX + 1) {
						if(!flag) {
							forkliftLocY--;
							flag = true;
						}
						else forkliftLocX++;
						
					}else if (forkliftLocX > SerialClient.stockLocX+1) {
						forkliftLocX--;
					}
					
					else if (forkliftLocY != SerialClient.stockLocY) {
						forkliftLocY--;
					
					} else if (forkliftLocY == SerialClient.stockLocY && forkliftLocX == SerialClient.stockLocX +1) {
						back = true;
					}
				}

				else {

					if (forkliftLocY != 13) {
						forkliftLocY++;
					} else if (forkliftLocX < 11) {
						forkliftLocX++;
					} else if (forkliftLocY>11) {
						forkliftLocX--;
					}
					
					if (forkliftLocY == 13 && forkliftLocX == 11) {
						done = true;
					}

				}
			}
			if(done) {
				back = false;
				flag = false;
				done = false;
				SerialClient.stockLocX=0;
				SerialClient.stockLocY=0;
			}
		

			System.out.println(forkliftLocX);
			System.out.println(forkliftLocY);

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

			msg = msg.substring(0, msg.length() - locationStrlen) + locationStr;

			new Thread(new SerialWrite(msg)).start();

			Thread.sleep(1000);
		}

	}

}
