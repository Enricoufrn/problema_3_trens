package com.example.atvd3str2022;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
            segmentView41, segmentView42, segmentView43, segmentView44, segmentView45, segmentView46, segmentView47, segmentView48, segmentView49, segmentView50;

    private View[] segmentViews;

    private ExecutorService executorService1, executorService2, executorService3;

    private Button button;

    private boolean start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findAllSegment();
        segments = new TrackSegment[50];
        segmentViews = new View[]{
                segmentView1, segmentView2, segmentView3, segmentView4, segmentView5, segmentView6, segmentView7, segmentView8, segmentView9, segmentView10,
                segmentView11, segmentView12, segmentView13, segmentView14, segmentView15, segmentView16, segmentView17, segmentView18, segmentView19, segmentView20,
                segmentView21, segmentView22, segmentView23, segmentView24, segmentView25, segmentView26, segmentView27, segmentView28, segmentView29, segmentView30,
                segmentView31, segmentView32, segmentView33, segmentView34, segmentView35, segmentView36, segmentView37, segmentView38, segmentView39, segmentView40,
                segmentView41, segmentView42, segmentView43, segmentView44, segmentView45, segmentView46, segmentView47, segmentView48, segmentView49, segmentView50};
        for(int i = 0; i < segments.length; i++){
            ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
            segments[i] = new TrackSegment(readWriteLock);
            segments[i].setSegment(segmentViews[i]);
        }
        configAllTrainRoutes();
        Train train1 = new Train(getResources().getColor(R.color.train_1), 1, route1);
        train1.setSpeed(1);
        Train train2 = new Train(getResources().getColor(R.color.train_2), 2, route2);
        Train train3 = new Train(getResources().getColor(R.color.train_3), 3, route3);
        TrainController controller1 = new TrainTrainControllerImpl(this, train1);
        TrainController controller2 = new TrainTrainControllerImpl(this, train2);
        TrainController controller3 = new TrainTrainControllerImpl(this, train3);
        button = findViewById(R.id.start);
        start = false;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start = !start;
                executorService1.submit(() ->{
                    try {
                        controller1.startStopRoutine(start);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        });
        executorService1 = Executors.newSingleThreadExecutor();

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
//        Queue<TrackSegment> segmentQueue2 = new LinkedList<>();
//        segmentQueue1.add(segment41);
//        segmentQueue1.add(segment42);
//        segmentQueue1.add(segment43);
//        segmentQueue1.add(segment44);
//        segmentQueue1.add(segment45);
//        segmentQueue1.add(segment21);
//        segmentQueue1.add(segment22);
//        segmentQueue1.add(segment23);
//        segmentQueue1.add(segment24);
//        segmentQueue1.add(segment25);
//        segmentQueue1.add(segment46);
//        segmentQueue1.add(segment47);
//        segmentQueue1.add(segment48);
//        segmentQueue1.add(segment49);
//        segmentQueue1.add(segment50);
//        segmentQueue1.add(segment11);
//        segmentQueue1.add(segment12);
//        segmentQueue1.add(segment13);
//        segmentQueue1.add(segment14);
//        segmentQueue1.add(segment15);
//        route2 = new TrainRoute(segmentQueue2);
//        Queue<TrackSegment> segmentQueue3 = new LinkedList<>();
//        segmentQueue3.add(segment31);
//        segmentQueue3.add(segment32);
//        segmentQueue3.add(segment33);
//        segmentQueue3.add(segment34);
//        segmentQueue3.add(segment35);
//        segmentQueue3.add(segment26);
//        segmentQueue3.add(segment27);
//        segmentQueue3.add(segment28);
//        segmentQueue3.add(segment29);
//        segmentQueue3.add(segment30);
//        segmentQueue3.add(segment16);
//        segmentQueue3.add(segment17);
//        segmentQueue3.add(segment18);
//        segmentQueue3.add(segment19);
//        segmentQueue3.add(segment20);
//        segmentQueue3.add(segment46);
//        segmentQueue3.add(segment47);
//        segmentQueue3.add(segment48);
//        segmentQueue3.add(segment49);
//        segmentQueue3.add(segment50);
//        segmentQueue3.add(segment36);
//        segmentQueue3.add(segment37);
//        segmentQueue3.add(segment38);
//        segmentQueue3.add(segment39);
//        segmentQueue3.add(segment40);
//        route3 = new TrainRoute(segmentQueue3);
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