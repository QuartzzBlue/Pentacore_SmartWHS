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
	static String receiveStr;
	
	static String receiveid;
	static String receivedata;
	Msg msg;
	int battery=0;
	int locX=0;
	int locY=0;
	int temperature=0;
	
	Sender r = new Sender(msg);
	

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
		System.out.println("SerialWrite 생성자");
		
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

				receiveStr = new String(readBuffer);
				System.out.println("Receive Data:" + receiveStr);

				//ECU 로부터 can 송신하면
				if(receiveStr.substring(1,4).equals("U28")) {
					//id 가 Battery 면
					receiveid = receiveStr.substring(4,12);
					receivedata = receiveStr.substring(24,28);
					
					System.out.println("From (id) : " + receiveid);
					System.out.println("Data : " + receivedata);
					
					if(receiveid.equals("10000001")) { //battery
						battery = Integer.parseInt(receivedata);
						System.out.println("battery : " + battery);
						
					}else if (receiveid.equals("10000002")){ //location
						locX = Integer.parseInt(receivedata.substring(0, 2));
						locY = Integer.parseInt(receivedata.substring(2, 4));
						System.out.println("Location (x,y) : " +locX + ","+locY);
					}else if (receiveid.equals("10000003")){ //temperature
						temperature = Integer.parseInt(receivedata);
						System.out.println("temperature : " +temperature);
					}
					msg =new Msg ("forklift01","tabletServer");
					msg.setForkLift(0, locX, locY,battery, temperature);
					r.setMsg(msg);
					Main.executorService.submit(r);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}// serialEvent method

	
}
