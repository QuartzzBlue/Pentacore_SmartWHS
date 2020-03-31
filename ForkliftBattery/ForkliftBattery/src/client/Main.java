package client;

import java.util.Enumeration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import gnu.io.CommPortIdentifier;

public class Main {

	static ExecutorService executorService = Executors.newFixedThreadPool(5);

	public static void main(String[] args) throws Exception {

		Client client = null;

		try {
			String address = "192.168.10.1";
			client = new Client(address, 7777);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
