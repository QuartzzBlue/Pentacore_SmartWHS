package com.pentacore.tabletserver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;
import android.view.View;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import logistics.ForkLift;
import logistics.Warehouse;
import network.Server;

public class MainActivity extends AppCompatActivity {

    Warehouse warehouse;
    ForkLift forkLift1, forkLift2, forkLift3, forkLift4;

    int WORKING = 0;
    int WAITING = 1;
    int CHARGING = 2;

    ConstraintLayout layoutWarehouseMap;
    View forkLiftView1, forkLiftView2, forkLiftView3, forkLiftView4;

    public static ExecutorService executorService = Executors.newFixedThreadPool(5);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int port=8888;

        Runnable r = new Server(port);
        //Thread serverThread = new Thread(r);
        //serverThread.start();
        //ActiveConnection.executorService.execute(r);
        executorService.execute(r);

        warehouse = new Warehouse(26, 14);
        forkLift1 = new ForkLift("forkLift1");
        forkLift2 = new ForkLift("forkLift2");
        forkLift3 = new ForkLift("forkLift3");
        forkLift4 = new ForkLift("forkLift4");
        forkLiftView1 = findViewById(R.id.forkLift1);
        forkLiftView2 = findViewById(R.id.forkLift2);
        forkLiftView3 = findViewById(R.id.forkLift3);
        forkLiftView4 = findViewById(R.id.forkLift4);

        View button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnClick();
            }
        });
    }



    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        layoutWarehouseMap = findViewById(R.id.layoutWarehouseMap);
        warehouse.setScreenSize(layoutWarehouseMap.getWidth(), layoutWarehouseMap.getHeight());

        locateForkLift(forkLiftView1, 11, 13);
        locateForkLift(forkLiftView2, 12, 13);
        locateForkLift(forkLiftView3, 13, 13);
        locateForkLift(forkLiftView4, 14, 13);
    }

    public void btnClick() {
        locateForkLift(forkLiftView1, (int)(Math.random()*26), (int)(Math.random()*14));
        locateForkLift(forkLiftView2, (int)(Math.random()*26), (int)(Math.random()*14));
        locateForkLift(forkLiftView3, (int)(Math.random()*26), (int)(Math.random()*14));
        locateForkLift(forkLiftView4, (int)(Math.random()*26), (int)(Math.random()*14));
    }

    public void locateForkLift(View forkLiftView, int dstnX, int dstnY) {
        ConstraintSet set = new ConstraintSet();
        set.clone(layoutWarehouseMap);
        set.connect(forkLiftView.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, (int)warehouse.axisX[dstnX]);
        set.connect(forkLiftView.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, (int)warehouse.axisY[dstnY]);
        set.applyTo(layoutWarehouseMap);
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
