package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import msg.Msg;

public class Receiver implements Runnable {
	
	InputStream is;
	ObjectInputStream ois;

	OutputStream os;
	ObjectOutputStream oos;

	Socket socket;
	
	public Receiver() {
		
	}
	
	public Receiver(Socket socket) {
		
		this.socket = socket;
		
		try {
			
			is = socket.getInputStream();
			ois = new ObjectInputStream(is);
			os = socket.getOutputStream();
			oos = new ObjectOutputStream(os);
		
		} catch (IOException e) {
			//e.printStackTrace();
		}
		
	}
	
	@Override
	public void run() {
		while(ois!=null) {
			Msg msg = null;
			try {
				msg = (Msg) ois.readObject();
				ActiveConnection.ipToOos.put(socket.getInetAddress().toString(),oos);
				ActiveConnection.idToIp.put(msg.getSrcID(),socket.getInetAddress().toString());
				System.out.println("Connected : "+msg.getSrcID() + ", 접속 수 : " + ActiveConnection.ipToOos.size());	
					
					
				//Web 이 접속했을 경우에만 Pad 로 Send
				if(!msg.getSrcID().contains("tab")) {
					Runnable r= new Sender(msg);
					Thread senderThread = new Thread(r);
					senderThread.start();
					System.out.println("Sender Thread started");
					
				}
				
			} catch (ClassNotFoundException | IOException e) {
				//e.printStackTrace();
				ActiveConnection.ipToOos.remove(socket.getInetAddress().toString());
					
				//value 값으로 key 값 찾기
				for(String id : ActiveConnection.idToIp.keySet()) {
					if(socket.getInetAddress().toString().equals(ActiveConnection.idToIp.get(id))) {
						ActiveConnection.idToIp.remove(id);
					}		
				}
				System.out.println("Disconnected : " + socket.getInetAddress().toString());
				System.out.println("접속 수 : " + ActiveConnection.ipToOos.size());
				
				break;
			}//catch
		}//while
		
		try {
			if (ois != null) {
				ois.close();
			}
			if (socket != null) {
				socket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
