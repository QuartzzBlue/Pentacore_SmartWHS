package Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import msg.Msg;

public class Client {
	   Socket socket;
	   Sender sender;
	   boolean flag = false;
	   SendData sendData;
	   Thread temp;
	   
	   public Client() {}
	   public Client(String address,int port) throws IOException {
	      try {
	         socket = new Socket(address, port);
	      }catch(Exception e) {
	         while(true) {
	            System.out.println("Retry..");
	            try {
	               Thread.sleep(1000);
	               socket = new Socket(address, port);
	               break;
	            } catch (Exception e1) {
	               //e1.printStackTrace();
	            }
	         }
	      }
	      
	      System.out.println("Connected Server:"+address);
	      //ecu 연결되면 Receiver 먼저 구현 필요
	      
	      sender = new Sender(socket);
	      Msg msg = new Msg("fl01","tab01");
	      msg.setForkLift(10, 10, 10, 0);
	      sender.setMsg(msg);
	      new Thread(sender).start();
	      
	      new Receiver(socket).start();     
	     
	      //new Thread(new SendData()).start();
	      sendData = new SendData();
	      //sendData.setFlag(true);
	      
	      
	   }
	   
	   class Receiver extends Thread{
	      InputStream is;
	      ObjectInputStream ois;
	      
	      public Receiver(Socket socket) throws IOException {
	         is = socket.getInputStream();
	         ois = new ObjectInputStream(is);
	      }

	      @Override
	      public void run() {
	         while(ois != null) {
	            Msg msg = null;
	            try {
	               msg = (Msg) ois.readObject();
	               System.out.println(msg.getContent());
	               if(msg.getContent().equals("1")) {
	            
	            		   sendData.setFlag(true);
	            		   if(temp==null) {
	            			   temp = new Thread(sendData);
	            			   temp.start();
	            			   System.out.println("새로운 스레드 생성");
	            		   }
	    
	               }else if(msg.getContent().equals("0")) {
	                  sendData.setFlag(false);
	                  temp = null;
	               }
	           
	            }catch(Exception e) {
	               System.out.println("Server Die");
	               break;
	            }
	         }
	         
	         try {
	            if(ois != null) {
	               ois.close();
	            }
	            if(socket != null) {
	               socket.close();
	            }
	         }catch(Exception e) {
	            e.printStackTrace();
	         }
	      }
	      
	   }
	   
	   
	   class Sender implements Runnable{

	      OutputStream os;
	      ObjectOutputStream oos;
	      Msg msg;
	      
	      public Sender(Socket socket) throws IOException {
	         os = socket.getOutputStream();
	         oos = new ObjectOutputStream(os);
	      }
	      public void setMsg(Msg msg) {
	         this.msg = msg;
	      }
	      @Override
	      public void run() {
	         if(oos != null) {
	            try {
	               oos.writeObject(msg);
	            } catch (IOException e) {
	            
	               //e.printStackTrace();
	            }
	         }
	        
	      }
	      
	   }
	  
	   class SendData implements Runnable{
	      boolean flag = true;
	      
	      public void setFlag(boolean flag) {
	         this.flag = flag;
	      }
	      
	      public boolean getFlag(boolean flag) {
	    	  return flag;
	      }

	      public SendData() throws IOException {

	       }

	      @Override
	      public void run() {
	         while(true) {
	 
	            if(this.flag == false) {
	               continue;
	            }
	            try {
	            	int num = (int)(Math.random()*100);
	            	String Num =  Integer.toString(num);
	            	Msg msg = new Msg(cid,"web",Num);
	            	sender.setMsg(msg);
	               Thread.sleep(1000);
	               new Thread(sender).start();
	               System.out.println(msg.getContent());
	            } catch (Exception e) {
	               e.printStackTrace();
	            }
	            
	         }
	      }
	      
	   }
	   
	   
	   public static void main(String[] args) {
	      Client client = null;
	      try {
	         client = new Client(address, port);
	         //client.startClient2();
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
	      //client.startClient();
	   }
	static String address = "70.12.113.200";
	static int port = 8888;
}
