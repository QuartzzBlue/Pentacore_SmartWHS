package client;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	static ExecutorService executorService = Executors.newFixedThreadPool(5);
	static int battery = 999;
	static int d = -2;
	static String id = "13000003";
	static String data = "0000000000000000";
	static String msg = id + data;
	
	public static void main(String[] args) throws Exception {
		
		String port;
		System.out.println("Port Number : ");
		Scanner sc = new Scanner(System.in);
		port = sc.nextLine();
		
		SerialClient serialClient = new SerialClient(port);
		
		while(true) {
			
			
			if (battery >= 999) {
				battery = 999;
			}
			
			String batteryStr = battery + "";
			int batteryLen = batteryStr.length();

			msg = msg.substring(0, msg.length() - batteryLen) + batteryStr;

			new Thread(new SerialWrite(msg)).start();
			
			battery += d;
			
			Thread.sleep(1000);
		}
		

	}

}
