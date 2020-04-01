package com.pentacore.tabletserver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import logistics.ForkLift;
import logistics.ForkLiftViewSet;
import logistics.Warehouse;
import msg.Msg;
import network.Client;
import network.SendInTcpip;
import network.Server;

public class MainActivity extends AppCompatActivity {

    static Warehouse warehouse;
    ForkLift forkLift1, forkLift2, forkLift3, forkLift4;
    static ForkLiftViewSet forkLiftViewSet1, forkLiftViewSet2, forkLiftViewSet3, forkLiftViewSet4;

    public static Map forkLiftMap;
    static Map forkLiftViewSetMap;

    int WORKING = 0;
    int WAITING = 1;
    int CHARGING = 2;

    static ConstraintLayout layoutWarehouseMap;
    public static Queue taskQueue;
    public static Queue waitingForkLiftQueue;

    public static ExecutorService executorService = Executors.newFixedThreadPool(10);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Run TabletServer
        int port=8888;
        Runnable server = new Server(port);
        executorService.execute(server);

        // Connect to TCP/IP Server
        String dstnIP = "192.168.0.225";
        int dstnPort = 8888;
        Runnable client = new Client(dstnIP, dstnPort);
        executorService.execute(client);

        warehouse = new Warehouse(26, 14);
        forkLift1 = new ForkLift("forkLift1");
        forkLift2 = new ForkLift("forkLift2");
        forkLift3 = new ForkLift("forkLift3");
        forkLift4 = new ForkLift("forkLift4");

        forkLiftMap = new HashMap<String, ForkLift>();
        forkLiftMap.put(forkLift1.getName(), forkLift1);
        forkLiftMap.put(forkLift2.getName(), forkLift2);
        forkLiftMap.put(forkLift3.getName(), forkLift3);
        forkLiftMap.put(forkLift4.getName(), forkLift4);

        forkLiftViewSet1 = new ForkLiftViewSet();
        forkLiftViewSet1.status = findViewById(R.id.forkLift1_status);
        forkLiftViewSet1.taskContent = findViewById(R.id.forkLift1_taskContent);
        forkLiftViewSet1.batteryCurrent = findViewById(R.id.forkLift1_batteryCurrent);
        forkLiftViewSet1.forkLiftView = findViewById(R.id.forkLift1);

        forkLiftViewSet2 = new ForkLiftViewSet();
        forkLiftViewSet2.status = findViewById(R.id.forkLift2_status);
        forkLiftViewSet2.taskContent = findViewById(R.id.forkLift2_taskContent);
        forkLiftViewSet2.batteryCurrent = findViewById(R.id.forkLift2_batteryCurrent);
        forkLiftViewSet2.forkLiftView = findViewById(R.id.forkLift2);

        forkLiftViewSet3 = new ForkLiftViewSet();
        forkLiftViewSet3.status = findViewById(R.id.forkLift3_status);
        forkLiftViewSet3.taskContent = findViewById(R.id.forkLift3_taskContent);
        forkLiftViewSet3.batteryCurrent = findViewById(R.id.forkLift3_batteryCurrent);
        forkLiftViewSet3.forkLiftView = findViewById(R.id.forkLift3);

        forkLiftViewSet4 = new ForkLiftViewSet();
        forkLiftViewSet4.status = findViewById(R.id.forkLift4_status);
        forkLiftViewSet4.taskContent = findViewById(R.id.forkLift4_taskContent);
        forkLiftViewSet4.batteryCurrent = findViewById(R.id.forkLift4_batteryCurrent);
        forkLiftViewSet4.forkLiftView = findViewById(R.id.forkLift4);

        forkLiftViewSetMap = new HashMap<String, ForkLiftViewSet>();
        forkLiftMap.put("forkLift1", forkLiftViewSet1);
        forkLiftMap.put("forkLift2", forkLiftViewSet2);
        forkLiftMap.put("forkLift3", forkLiftViewSet3);
        forkLiftMap.put("forkLift4", forkLiftViewSet4);

        taskQueue = new LinkedList();
        waitingForkLiftQueue = new LinkedList();

        View button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnClick();
            }
        });
    }

    boolean firstFocusOnWindowFlag = true;
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if(!firstFocusOnWindowFlag) return;
        layoutWarehouseMap = findViewById(R.id.layoutWarehouseMap);
        warehouse.setScreenSize(layoutWarehouseMap.getWidth(), layoutWarehouseMap.getHeight());

        locateForkLift(forkLiftViewSet1.forkLiftView, 11, 13);
        locateForkLift(forkLiftViewSet2.forkLiftView, 12, 13);
        locateForkLift(forkLiftViewSet3.forkLiftView, 13, 13);
        locateForkLift(forkLiftViewSet4.forkLiftView, 14, 13);
        firstFocusOnWindowFlag = false;
    }

    public void btnClick() {
        locateForkLift(forkLiftViewSet1.forkLiftView, (int)(Math.random()*26), (int)(Math.random()*14));
        locateForkLift(forkLiftViewSet2.forkLiftView, (int)(Math.random()*26), (int)(Math.random()*14));
        locateForkLift(forkLiftViewSet3.forkLiftView, (int)(Math.random()*26), (int)(Math.random()*14));
        locateForkLift(forkLiftViewSet4.forkLiftView, (int)(Math.random()*26), (int)(Math.random()*14));
    }

    public static void locateForkLift(View forkLiftView, int dstnX, int dstnY) {
        ConstraintSet set = new ConstraintSet();
        set.clone(layoutWarehouseMap);
        set.connect(forkLiftView.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, (int)warehouse.axisX[dstnX]);
        set.connect(forkLiftView.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, (int)warehouse.axisY[dstnY]);
        set.applyTo(layoutWarehouseMap);
    }

    public static void updateForkLiftUI(String forkLiftID) {
        ForkLiftViewSet forkLiftViewset = (ForkLiftViewSet)forkLiftViewSetMap.get(forkLiftID);
        ForkLift forkLift = (ForkLift)forkLiftMap.get(forkLiftID);

        ((TextView)forkLiftViewset.status).setText(forkLift.getStatus());
        ((TextView)forkLiftViewset.taskContent).setText(forkLift.getCurrentTask());
        ((TextView)forkLiftViewset.batteryCurrent).setText(forkLift.getBattery());
        locateForkLift((ImageView)forkLiftViewset.forkLiftView, forkLift.getCurrentX(), forkLift.getCurrentY());
    }

    public void printConsole(String log) {
        TextView textView = findViewById(R.id.consoleContent);
        String consoleContent = textView.getText().toString();
        consoleContent += (log+"\n");
        textView.setText(consoleContent);
    }

    public static void assignTask() {
        if (!waitingForkLiftQueue.isEmpty() && !taskQueue.isEmpty()) {
            waitingForkLiftQueue.poll(); // 여기서 가져온 ID를 밑에 Msg에 담아서
            Msg msg = new Msg(); // srcID, srcIP, blahblah
            // msg.setTask(taskQueue.poll());
            Runnable sendInTcpip = new SendInTcpip(msg); //전송
            MainActivity.executorService.submit(sendInTcpip);
            //Console.log(n번 지게차에 task 내용 뭐뭐 를 할당하였습니다.);
            // 그러면 msg가 지게차 Infomatics로 가서 지게차는 얘를 할당받고
            // 지게차가 받은 msg를 다시 태블릿으로 보낼 때 태블릿 UI가 업데이트 된다.

            // taskUI 바꿔주는 메소드 호출
        }
    }

//    public void translateAnim(float xStart, float xEnd, float yStart, float yEnd, int duration, View view) {
//        TranslateAnimation translateAnimation = new TranslateAnimation(
//                Animation.RELATIVE_TO_SELF,xStart,
//                Animation.RELATIVE_TO_SELF, xEnd,
//                Animation.RELATIVE_TO_SELF,yStart,
//                Animation.RELATIVE_TO_SELF,yEnd);
//        translateAnimation.setDuration(duration);
//        translateAnimation.setFillAfter(true);
//        view.startAnimation(translateAnimation);
//    }

}
