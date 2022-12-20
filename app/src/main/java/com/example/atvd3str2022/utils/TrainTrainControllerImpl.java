package com.example.atvd3str2022.utils;

import com.example.atvd3str2022.model.Train;

import java.util.concurrent.ExecutorService;

public class TrainTrainControllerImpl implements TrainController{

    private TrainControllerListener listener;
    private volatile boolean running;
    private Train train;
    private ExecutorService executorService;

    public TrainTrainControllerImpl(TrainControllerListener listener, Train train, ExecutorService executorService) {
        this.listener = listener;
        this.train = train;
        this.running = false;
        this.executorService = executorService;
    }

    public TrainControllerListener getListener() {
        return listener;
    }

    public void setListener(TrainControllerListener listener) {
        this.listener = listener;
    }


    @Override
    public void startStopRoutine() {
        this.running = !running;
        if(running){
            this.executorService.submit(() ->{
                while(running){
                    this.train.move(Train.ENTRY);
                    listener.showTrainPosition(this.train, Train.ENTRY);
                    try {
                        Thread.sleep((1000L)/this.train.getSpeed());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.train.move(Train.GO_OUT);
                    listener.showTrainPosition(this.train, Train.GO_OUT);
                }
            });
        }
    }

    @Override
    public Boolean isTerminated() {
        if(this.executorService != null)
            return this.executorService.isTerminated();
        else
            return null;
    }

    @Override
    public int getSpeed() {
        return this.train.getSpeed();
    }

    @Override
    public void setSpeed(int speed) {
        this.train.setSpeed(speed);
    }

}
