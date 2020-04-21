package client;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Timer;
import java.util.TimerTask;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class SerialClient implements SerialPortEventListener{
	
	CommPortIdentifier commPortIdentifier;
	CommPort commPort;
	InputStream in;
	BufferedInputStream bin;
	static String receiveStr;
	static int stockLocX;
	static int stockLocY;
	static String id = "15000005";
	static String data = "3000000000000000";
	static String msg = id + data;
	static OutputStream out;
	static TimerTask task;
	static Timer timer;
	boolean flag = false;
	
	public SerialClient() {
	}

	public SerialClient(String portName) throws Exception {
		commPortIdentifier = CommPortIdentifier.getPortIdentifier(portName);
		System.out.println("Identified Com Port!");
		connect();
		System.out.println("Connect Com Port!");
		Runnable r = new SerialWrite();
		Main.executorService.submit(r);
		System.out.println("SerialWrite Thread Run");
		System.out.println("Start CAN Network!!!");
	}
	
	public void connect() throws Exception {
		if (commPortIdentifier.isCurrentlyOwned()) {
			System.out.println("Port is currently in use....");
		} else {
			commPort = commPortIdentifier.open(this.getClass().getName(), 5000);
		
			if (commPort instanceof SerialPort) {
				SerialPort serialPort = (SerialPort) commPort;
				serialPort.addEventListener(this);
				serialPort.notifyOnDataAvailable(true);
				serialPort.setSerialPortParams(921600, // 통신속도
						SerialPort.DATABITS_8, // 데이터 비트
						SerialPort.STOPBITS_1, // stop 비트
						SerialPort.PARITY_NONE); // 패리티
				in = serialPort.getInputStream();
				bin = new BufferedInputStream(in);
				out = serialPort.getOutputStream();
			} else {
				System.out.println("this port is not serial ");
			} 
				
		}
	}
	
	@Override
	public void serialEvent(SerialPortEvent event) {
		switch (event.getEventType()) {
		case SerialPortEvent.BI:
		case SerialPortEvent.OE:
		case SerialPortEvent.FE:
		case SerialPortEvent.PE:
		case SerialPortEvent.CD:
		case SerialPortEvent.CTS:
		case SerialPortEvent.DSR:
		case SerialPortEvent.RI:
		case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
			break;
		case SerialPortEvent.DATA_AVAILABLE:
			byte[] readBuffer = new byte[128];
			try {
				while (bin.available() > 0) {
					int numBytes = bin.read(readBuffer);
				}
				receiveStr = new String(readBuffer);
				//System.out.println("Receive Data:" + receiveStr);
				
				if(receiveStr.substring(1,4).equals("U28")) {
					System.out.println("Receive Data:" + receiveStr);
					if(receiveStr.substring(4,12).equals(id)) {
						stockLocX = Integer.parseInt(receiveStr.substring(24, 26));
						stockLocY = Integer.parseInt(receiveStr.substring(26, 28));
					}
				}
				
//				if (!ss.substring(0, 6).equals(":G01A8") && !ss.substring(0, 6).equals(":W2810")) { // ':G01A8' -> 처음
//																									// 연결될 때 받는 메시지 (데이터
//																									// 값 아님)
//					String controller = ss.substring(12, 13);
//					System.out.println("controller : " + controller);
//					if (ss.substring(4, 12).equals("10000000") || ss.substring(4, 12).equals("10000002")) { // 전체 알림이거나
//																											// 메시지일 때만
//						if (controller.equals("1")) {
//							flag = false;
//							System.out.println("Stop Client...");
//							timer.cancel();
//						} else if (controller.equals("0")) {
//							flag = true;
//							String id = "10000002";
//							String data = "30000000000000";
//							String msg = id + data;
//							System.out.println("Restart Client...");
//							try {
//								task = new TimerTask() {
//									@Override
//									public void run() {
//										new Thread(new SerialWrite(msg)).start();
//									}
//								};
//								timer = new Timer();
//								timer.scheduleAtFixedRate(task, 1234, 3232);
//							} catch (Exception e) {
//								e.printStackTrace();
//							}
//						}
//					}
//				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
	}// serialEvent method

	

}
