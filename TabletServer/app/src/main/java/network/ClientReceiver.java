package network;

import com.pentacore.tabletserver.MainActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.concurrent.ThreadPoolExecutor;

public class ClientReceiver implements Runnable {

    InputStream is;
    ObjectInputStream ois;

    Socket socket;

    public ClientReceiver() {

    }

    public ClientReceiver(Socket socket) {
        System.out.println("ClientReceiver 객체 생성");
        this.socket = socket;

        try {
            is = socket.getInputStream();
            ois = new ObjectInputStream(is);
            System.out.println("Connected to TCP/IP Server : "+socket.getInetAddress().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {

        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) MainActivity.executorService;
        int poolSize = threadPoolExecutor.getPoolSize();//스레드 풀 사이즈 얻기
        String threadName = Thread.currentThread().getName();//스레드 풀에 있는 해당 스레드 이름 얻기
        System.out.println("ServerReceiver [총 스레드 개수:" + poolSize + "] 작업 스레드 이름: "+threadName);

    }
}
