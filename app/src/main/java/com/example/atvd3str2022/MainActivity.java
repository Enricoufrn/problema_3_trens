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
        // Rota pro trilho
        Queue<TrackSegment> track1Segments = new LinkedList<>();
        track1Segments.add(segments[5]);
        track1Segments.add(segments[6]);
        track1Segments.add(segments[7]);
        track1Segments.add(segments[8]);
        track1Segments.add(segments[9]);
        Queue<TrackSegment> track2Segments = new LinkedList<>();
        track2Segments.add(segments[10]);
        track2Segments.add(segments[11]);
        track2Segments.add(segments[12]);
        track2Segments.add(segments[13]);
        track2Segments.add(segments[14]);
        Queue<TrackSegment> track3Segments = new LinkedList<>();
        track3Segments.add(segments[19]);
        track3Segments.add(segments[18]);
        track3Segments.add(segments[17]);
        track3Segments.add(segments[16]);
        track3Segments.add(segments[15]);
        Queue<TrackSegment> track4Segments = new LinkedList<>();
        track4Segments.add(segments[4]);
        track4Segments.add(segments[3]);
        track4Segments.add(segments[2]);
        track4Segments.add(segments[1]);
        track4Segments.add(segments[0]);
        Track track1 = new Track(track1Segments);
        Track track2 = new Track(track2Segments);
        Track track3 = new Track(track3Segments);
        Track track4 = new Track(track4Segments);
        Queue<Track> tracks1 = new LinkedList<>();
        tracks1.add(track1);
        tracks1.add(track2);
        tracks1.add(track3);
        tracks1.add(track4);
        route1 = new TrainRoute(tracks1);

        Queue<TrackSegment> track5Segments = new LinkedList<>();
        track5Segments.add(segments[40]);
        track5Segments.add(segments[41]);
        track5Segments.add(segments[42]);
        track5Segments.add(segments[43]);
        track5Segments.add(segments[44]);
        Queue<TrackSegment> track6Segments = new LinkedList<>();
        track6Segments.add(segments[20]);
        track6Segments.add(segments[21]);
        track6Segments.add(segments[22]);
        track6Segments.add(segments[23]);
        track6Segments.add(segments[24]);
        Queue<TrackSegment> track7Segments = new LinkedList<>();
        track7Segments.add(segments[49]);
        track7Segments.add(segments[48]);
        track7Segments.add(segments[47]);
        track7Segments.add(segments[46]);
        track7Segments.add(segments[45]);
        Queue<TrackSegment> track8Segments = new LinkedList<>();
        track8Segments.add(segments[14]);
        track8Segments.add(segments[13]);
        track8Segments.add(segments[12]);
        track8Segments.add(segments[11]);
        track8Segments.add(segments[10]);
        Track track5 = new Track(track5Segments);
        Track track6 = new Track(track6Segments);
        Track track7 = new Track(track7Segments);
        Track track8 = new Track(track8Segments);
        Queue<Track> tracks2 = new LinkedList<>();
        tracks2.add(track5);
        tracks2.add(track6);
        tracks2.add(track7);
        tracks2.add(track8);
        route2 = new TrainRoute(tracks2);

        Queue<TrackSegment> track9Segments = new LinkedList<>();
        track9Segments.add(segments[50]);
        track9Segments.add(segments[51]);
        track9Segments.add(segments[52]);
        track9Segments.add(segments[53]);
        track9Segments.add(segments[54]);
        Queue<TrackSegment> track10Segments = new LinkedList<>();
        track10Segments.add(segments[34]);
        track10Segments.add(segments[33]);
        track10Segments.add(segments[32]);
        track10Segments.add(segments[31]);
        track10Segments.add(segments[30]);
        Queue<TrackSegment> track11Segments = new LinkedList<>();
        track11Segments.add(segments[29]);
        track11Segments.add(segments[28]);
        track11Segments.add(segments[27]);
        track11Segments.add(segments[26]);
        track11Segments.add(segments[25]);
        Queue<TrackSegment> track12Segments = new LinkedList<>();
        track12Segments.add(segments[15]);
        track12Segments.add(segments[16]);
        track12Segments.add(segments[17]);
        track12Segments.add(segments[18]);
        track12Segments.add(segments[19]);
        Queue<TrackSegment> track13Segments = new LinkedList<>();
        track13Segments.add(segments[45]);
        track13Segments.add(segments[46]);
        track13Segments.add(segments[47]);
        track13Segments.add(segments[48]);
        track13Segments.add(segments[49]);
        Queue<TrackSegment> track14Segments = new LinkedList<>();
        track14Segments.add(segments[35]);
        track14Segments.add(segments[36]);
        track14Segments.add(segments[37]);
        track14Segments.add(segments[38]);
        track14Segments.add(segments[39]);
        Track track9 = new Track(track9Segments);
        Track track10 = new Track(track10Segments);
        Track track11 = new Track(track11Segments);
        Track track12 = new Track(track12Segments);
        Track track13 = new Track(track13Segments);
        Track track14 = new Track(track14Segments);
        Queue<Track> tracks3 = new LinkedList<>();
        tracks3.add(track9);
        tracks3.add(track10);
        tracks3.add(track11);
        tracks3.add(track12);
        tracks3.add(track13);
        tracks3.add(track14);
        route3 = new TrainRoute(tracks3);
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