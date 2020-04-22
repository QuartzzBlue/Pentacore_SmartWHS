package client;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	static ExecutorService executorService = Executors.newFixedThreadPool(5);

	public static void main(String[] args) throws Exception {
		
		String port;
		System.out.println("Port Number : ");
		Scanner sc = new Scanner(System.in);
		port = sc.nextLine();
		
		SerialClient serialClient = new SerialClient(port);

	}

}

