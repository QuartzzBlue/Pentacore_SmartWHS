package network;

import com.pentacore.tabletserver.MainActivity;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ThreadPoolExecutor;

import msg.Msg;

public class Client implements Runnable {
    String srcID;
    String dstnID;
    String dstnIP;
    int dstnPort;
    Socket socket;

    OutputStream os;
    ObjectOutputStream oos;

    public Client() {}
    public Client(String dstnIP,int dstnPort) {
        this.dstnIP = dstnIP;
        this.dstnPort = dstnPort;
        srcID = "tabletServer";
        dstnID = "tcpipServer";

        try {
            makeSocket(socket, dstnIP,dstnPort);

        } catch (Exception outerE) {
//            outerE.getMessage();
            while (true) {
                System.out.println("Retry..");
                try {
                    Thread.sleep(1000);
                    makeSocket(socket, dstnIP,dstnPort);
                    break;
                } catch (Exception innerE) {
                    innerE.printStackTrace();
                }
            }
        }

        System.out.println("Connected to TCP/IP Server → "+dstnIP+":"+dstnPort);

    }

    @Override
    public void run() {
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) MainActivity.executorService;
        int poolSize = threadPoolExecutor.getPoolSize();//스레드 풀 사이즈 얻기
        String threadName = Thread.currentThread().getName();//스레드 풀에 있는 해당 스레드 이름 얻기
        System.out.println(" Client [총 스레드 개수:" + poolSize + "] 작업 스레드 이름: "+threadName);

        Runnable clientReceiver = new ClientReceiver(socket);
        MainActivity.executorService.submit(clientReceiver);
    }

    public void makeSocket(Socket socket, String dstnIP,int dstnPort) throws IOException {
        socket = new Socket(dstnIP, dstnPort);
        os = socket.getOutputStream();
        oos = new ObjectOutputStream(os);
        oos.writeObject(new Msg(null, srcID, dstnIP, dstnID, "TabletServer Connected"));
    }
}
