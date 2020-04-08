package infomatics;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import msg.Msg;

public class SerialServer implements SerialPortEventListener {
	
	static String[] devices = { "10000000", "10000001", "10000002", "10000003", "10000004" };
	CommPortIdentifier commPortIdentifier;
	CommPort commPort;
	InputStream in;
	BufferedInputStream bin;
	static OutputStream out;
	
	String id;
	String data;
	Msg msg = new Msg ("forklift01","tabletServer");

	boolean flag = false;

	public SerialServer() {
	}

	public SerialServer(String portName) throws Exception {
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
				serialPort.setSerialPortParams(921600, 
						SerialPort.DATABITS_8, 
						SerialPort.STOPBITS_1, 
						SerialPort.PARITY_NONE); 
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
			byte[] readBuffer = new byte[30];
			try {
				while (bin.available() > 0) {
					int numBytes = bin.read(readBuffer);
				}

				String receiveStr = new String(readBuffer);
				System.out.println("Receive Data:" + receiveStr);
				
				//ECU 로부터 can 송신하면
				if(receiveStr.substring(0,2).equals("U28")) {
					//id 가 Battery 면
					id = receiveStr.substring(3,10);
					data = receiveStr.substring(25,28);
					
					System.out.println("id : "+id);
					System.out.println("data : "+data);
					if(id.equals("10000001")) { //battery
						msg.getForkLift().setBattery(Integer.parseInt(data));
					}else if (id.equals("10000002")){ //location
						msg.getForkLift().setLocX(Integer.parseInt(data.substring(0, 1)));
						msg.getForkLift().setLocY(Integer.parseInt(data.substring(2, 3)));
					}else if (id.equals("10000003")){ //temperature
						msg.getForkLift().setTemperature(Integer.parseInt(data));
					}
					
					Runnable r = new Sender(msg);
					Main.executorService.submit(r);
					
					
				}
				
				
				
				
				
				
////				if (flag) {
//				try {
//					System.out.println(ss.substring(28, 30));
//					if (!ss.substring(0, 6).equals(":G01A8") && !ss.substring(0, 6).equals(":W2810")
//							&& !ss.substring(0, 5).equals("W2810")) {
//						String data = ss.substring(26, 28);
//						if (!data.equals("00")) {
//							System.out.println("data : " + data);
//							//cid = setCID(ss.substring(4, 12));
//							//Msg msg = new Msg(cid, data, null);
//							//sender.setMsg(msg);
//							//Thread.sleep(1000);
//							//new Thread(sender).start();
//							//System.out.println("txt " + msg.getTxt());
//						}
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}// serialEvent method

	
}
