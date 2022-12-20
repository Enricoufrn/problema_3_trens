package com.example.atvd3str2022;

import android.service.controls.Control;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.atvd3str2022.model.ControlPanel;
import com.example.atvd3str2022.model.Track;
import com.example.atvd3str2022.model.TrackSegment;
import com.example.atvd3str2022.model.Train;
import com.example.atvd3str2022.model.TrainRoute;
import com.example.atvd3str2022.utils.TrainController;
import com.example.atvd3str2022.utils.TrainControllerListener;
import com.example.atvd3str2022.utils.TrainTrainControllerImpl;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MainActivity extends AppCompatActivity implements TrainControllerListener {

    private Track track1, track2, track3;
    private TrainRoute route1, route2, route3;

    private Train train1, train2, train3;

    private TrackSegment[] segments;

    private View segmentView1, segmentView2, segmentView3, segmentView4, segmentView5, segmentView6, segmentView7, segmentView8, segmentView9, segmentView10,
            segmentView11, segmentView12, segmentView13, segmentView14, segmentView15, segmentView16, segmentView17, segmentView18, segmentView19, segmentView20,
            segmentView21, segmentView22, segmentView23, segmentView24, segmentView25, segmentView26, segmentView27, segmentView28, segmentView29, segmentView30,
            segmentView31, segmentView32, segmentView33, segmentView34, segmentView35, segmentView36, segmentView37, segmentView38, segmentView39, segmentView40,
            segmentView41, segmentView42, segmentView43, segmentView44, segmentView45, segmentView46, segmentView47, segmentView48, segmentView49, segmentView50,
            segmentView51, segmentView52, segmentView53, segmentView54, segmentView55;

    private View[] segmentViews;

    private ImageButton increaseSpeedBtnController1, increaseSpeedBtnController2, increaseSpeedBtnController3, decreaseSpeedBtnController1,
            decreaseSpeedBtnController2, decreaseSpeedBtnController3, startStopBtnController1, startStopBtnController2, startStopBtnController3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find all track segments
        findAllSegment();
        initializeAllTrackSegments();

        configAllTrainRoutes();

        // Create the trains
        Train train1 = new Train(getResources().getColor(R.color.train_1), 1, route1);
        Train train2 = new Train(getResources().getColor(R.color.train_2), 2, route2);
        Train train3 = new Train(getResources().getColor(R.color.train_3), 3, route3);


        // Instance the threads for each train
        ExecutorService executorService1 = Executors.newSingleThreadExecutor();
        ExecutorService executorService2 = Executors.newSingleThreadExecutor();
        ExecutorService executorService3 = Executors.newSingleThreadExecutor();

        // Instance the controllers
        TrainController controller1 = new TrainTrainControllerImpl(this, train1, executorService1);
        TrainController controller2 = new TrainTrainControllerImpl(this, train2, executorService2);
        TrainController controller3 = new TrainTrainControllerImpl(this, train3, executorService3);

        // find all controllers buttons
        findAllControllersButtons();

        // Instance the control panels
        ControlPanel controlPanel1 = new ControlPanel(increaseSpeedBtnController1, decreaseSpeedBtnController1, startStopBtnController1, controller1);
        ControlPanel controlPanel2 = new ControlPanel(increaseSpeedBtnController2, decreaseSpeedBtnController2, startStopBtnController2, controller2);
        ControlPanel controlPanel3 = new ControlPanel(increaseSpeedBtnController3, decreaseSpeedBtnController3, startStopBtnController3, controller3);

    }

    public void findAllControllersButtons(){
        increaseSpeedBtnController1 = findViewById(R.id.increase_speed_btn_controller_1);
        increaseSpeedBtnController2 = findViewById(R.id.increase_speed_btn_controller_2);
        increaseSpeedBtnController3 = findViewById(R.id.increase_speed_btn_controller_3);

        decreaseSpeedBtnController1 = findViewById(R.id.decrease_speed_btn_controller_1);
        decreaseSpeedBtnController2 = findViewById(R.id.decrease_speed_btn_controller_2);
        decreaseSpeedBtnController3 = findViewById(R.id.decrease_speed_btn_controller_3);

        startStopBtnController1 = findViewById(R.id.start_stop_train_btn_controller_1);
        startStopBtnController2 = findViewById(R.id.start_stop_train_btn_controller_2);
        startStopBtnController3 = findViewById(R.id.start_stop_train_btn_controller_3);
    }

    public void initializeAllTrackSegments(){
        segments = new TrackSegment[55];
        segmentViews = new View[]{
                segmentView1, segmentView2, segmentView3, segmentView4, segmentView5, segmentView6, segmentView7, segmentView8, segmentView9, segmentView10,
                segmentView11, segmentView12, segmentView13, segmentView14, segmentView15, segmentView16, segmentView17, segmentView18, segmentView19, segmentView20,
                segmentView21, segmentView22, segmentView23, segmentView24, segmentView25, segmentView26, segmentView27, segmentView28, segmentView29, segmentView30,
                segmentView31, segmentView32, segmentView33, segmentView34, segmentView35, segmentView36, segmentView37, segmentView38, segmentView39, segmentView40,
                segmentView41, segmentView42, segmentView43, segmentView44, segmentView45, segmentView46, segmentView47, segmentView48, segmentView49, segmentView50,
                segmentView51, segmentView52, segmentView53, segmentView54, segmentView55};
        for(int i = 0; i < segments.length; i++){
            ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
            segments[i] = new TrackSegment(readWriteLock);
            segments[i].setSegment(segmentViews[i]);
        }
    }

    public void configAllTrainRoutes(){
        Queue<TrackSegment> segmentQueue1 = new LinkedList<>();
        segmentQueue1.add(segments[5]);
        segmentQueue1.add(segments[6]);
        segmentQueue1.add(segments[7]);
        segmentQueue1.add(segments[8]);
        segmentQueue1.add(segments[9]);
        segmentQueue1.add(segments[10]);
        segmentQueue1.add(segments[11]);
        segmentQueue1.add(segments[12]);
        segmentQueue1.add(segments[13]);
        segmentQueue1.add(segments[14]);
        segmentQueue1.add(segments[19]);
        segmentQueue1.add(segments[18]);
        segmentQueue1.add(segments[17]);
        segmentQueue1.add(segments[16]);
        segmentQueue1.add(segments[15]);
        segmentQueue1.add(segments[4]);
        segmentQueue1.add(segments[3]);
        segmentQueue1.add(segments[2]);
        segmentQueue1.add(segments[1]);
        segmentQueue1.add(segments[0]);
        route1 = new TrainRoute(segmentQueue1);
        Queue<TrackSegment> segmentQueue2 = new LinkedList<>();
        segmentQueue2.add(segments[40]);
        segmentQueue2.add(segments[41]);
        segmentQueue2.add(segments[42]);
        segmentQueue2.add(segments[43]);
        segmentQueue2.add(segments[44]);
        segmentQueue2.add(segments[20]);
        segmentQueue2.add(segments[21]);
        segmentQueue2.add(segments[22]);
        segmentQueue2.add(segments[23]);
        segmentQueue2.add(segments[24]);
        segmentQueue2.add(segments[49]);
        segmentQueue2.add(segments[48]);
        segmentQueue2.add(segments[47]);
        segmentQueue2.add(segments[46]);
        segmentQueue2.add(segments[45]);
        segmentQueue2.add(segments[14]);
        segmentQueue2.add(segments[13]);
        segmentQueue2.add(segments[12]);
        segmentQueue2.add(segments[11]);
        segmentQueue2.add(segments[10]);
        route2 = new TrainRoute(segmentQueue2);
        Queue<TrackSegment> segmentQueue3 = new LinkedList<>();
        segmentQueue3.add(segments[50]);
        segmentQueue3.add(segments[51]);
        segmentQueue3.add(segments[52]);
        segmentQueue3.add(segments[53]);
        segmentQueue3.add(segments[54]);
        segmentQueue3.add(segments[34]);
        segmentQueue3.add(segments[33]);
        segmentQueue3.add(segments[32]);
        segmentQueue3.add(segments[31]);
        segmentQueue3.add(segments[30]);
        segmentQueue3.add(segments[29]);
        segmentQueue3.add(segments[28]);
        segmentQueue3.add(segments[27]);
        segmentQueue3.add(segments[26]);
        segmentQueue3.add(segments[25]);
        segmentQueue3.add(segments[15]);
        segmentQueue3.add(segments[16]);
        segmentQueue3.add(segments[17]);
        segmentQueue3.add(segments[18]);
        segmentQueue3.add(segments[19]);
        segmentQueue3.add(segments[45]);
        segmentQueue3.add(segments[46]);
        segmentQueue3.add(segments[47]);
        segmentQueue3.add(segments[48]);
        segmentQueue3.add(segments[49]);
        segmentQueue3.add(segments[35]);
        segmentQueue3.add(segments[36]);
        segmentQueue3.add(segments[37]);
        segmentQueue3.add(segments[38]);
        segmentQueue3.add(segments[39]);
        route3 = new TrainRoute(segmentQueue3);
    }

    public void findAllSegment(){
        segmentView1 = findViewById(R.id.l1_seg_1);
        segmentView2 = findViewById(R.id.l1_seg_2);
        segmentView3 = findViewById(R.id.l1_seg_3);
        segmentView4 = findViewById(R.id.l1_seg_4);
        segmentView5 = findViewById(R.id.l1_seg_5);

        segmentView6 = findViewById(R.id.l2_l5_seg_1);
        segmentView7 = findViewById(R.id.l2_l5_seg_2);
        segmentView8 = findViewById(R.id.l2_l5_seg_3);
        segmentView9 = findViewById(R.id.l2_l5_seg_4);
        segmentView10 = findViewById(R.id.l2_l5_seg_5);

        segmentView11 = findViewById(R.id.l3_seg_1);
        segmentView12 = findViewById(R.id.l3_seg_2);
        segmentView13 = findViewById(R.id.l3_seg_3);
        segmentView14 = findViewById(R.id.l3_seg_4);
        segmentView15 = findViewById(R.id.l3_seg_5);

        segmentView16 = findViewById(R.id.l4_l7_seg_1);
        segmentView17 = findViewById(R.id.l4_l7_seg_2);
        segmentView18 = findViewById(R.id.l4_l7_seg_3);
        segmentView19 = findViewById(R.id.l4_l7_seg_4);
        segmentView20 = findViewById(R.id.l4_l7_seg_5);

        segmentView21 = findViewById(R.id.l6_seg_1);
        segmentView22 = findViewById(R.id.l6_seg_2);
        segmentView23 = findViewById(R.id.l6_seg_3);
        segmentView24 = findViewById(R.id.l6_seg_4);
        segmentView25 = findViewById(R.id.l6_seg_5);

        segmentView26 = findViewById(R.id.l10_seg_1);
        segmentView27 = findViewById(R.id.l10_seg_2);
        segmentView28 = findViewById(R.id.l10_seg_3);
        segmentView29 = findViewById(R.id.l10_seg_4);
        segmentView30 = findViewById(R.id.l10_seg_5);

        segmentView31 = findViewById(R.id.l9_seg_1);
        segmentView32 = findViewById(R.id.l9_seg_2);
        segmentView33 = findViewById(R.id.l9_seg_3);
        segmentView34 = findViewById(R.id.l9_seg_4);
        segmentView35 = findViewById(R.id.l9_seg_5);

        segmentView36 = findViewById(R.id.l8_seg_1);
        segmentView37 = findViewById(R.id.l8_seg_2);
        segmentView38 = findViewById(R.id.l8_seg_3);
        segmentView39 = findViewById(R.id.l8_seg_4);
        segmentView40 = findViewById(R.id.l8_seg_5);

        segmentView41 = findViewById(R.id.l2_l5_seg_6);
        segmentView42 = findViewById(R.id.l2_l5_seg_7);
        segmentView43 = findViewById(R.id.l2_l5_seg_8);
        segmentView44 = findViewById(R.id.l2_l5_seg_9);
        segmentView45 = findViewById(R.id.l2_l5_seg_10);

        segmentView46 = findViewById(R.id.l4_l7_seg_6);
        segmentView47 = findViewById(R.id.l4_l7_seg_7);
        segmentView48 = findViewById(R.id.l4_l7_seg_8);
        segmentView49 = findViewById(R.id.l4_l7_seg_9);
        segmentView50 = findViewById(R.id.l4_l7_seg_10);

        segmentView51 = findViewById(R.id.l9_seg_10);
        segmentView52 = findViewById(R.id.l9_seg_9);
        segmentView53 = findViewById(R.id.l9_seg_8);
        segmentView54 = findViewById(R.id.l9_seg_7);
        segmentView55 = findViewById(R.id.l9_seg_6);
    }

    @Override
    public void showTrainPosition(Train train, int oper) {
        int color;
        TrackSegment segment;
        if(oper == Train.ENTRY){
            color = train.getColor();
            segment = train.getPosition();
        }else{
            color = R.color.segment_color;
            segment = train.getLastSegment();
        }
        runOnUiThread(() ->{
            setSegmentColor(color, segment);
        });
    }

    public void setSegmentColor(int color, TrackSegment segment){
        segment.getSegment().setBackgroundColor(color);
    }
}