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
	static String id = "13000003";
	static String data = "3000000000000000";
	static String msg = id + data;
	String receiveId;
	String receiveData;
	static OutputStream out;
	
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
				System.out.println("Receive Data:" + receiveStr);
				
				receiveId=receiveStr.substring(4,12);
				
				if(receiveId.equals("10000000")){ //working
					SerialWrite.d = -2;
				}else if (receiveId.equals("10000001")){ //waiting
					SerialWrite.d = -1;
				}else if (receiveId.equals("10000002")) { //charging
					SerialWrite.d = 10;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
	}// serialEvent method

	

}
