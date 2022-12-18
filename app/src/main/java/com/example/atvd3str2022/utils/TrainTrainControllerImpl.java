package com.example.atvd3str2022.utils;

import com.example.atvd3str2022.model.Train;

public class TrainTrainControllerImpl implements TrainController{

    private TrainControllerListener listener;
    private boolean running;
    protected Train train;

    public TrainTrainControllerImpl(TrainControllerListener listener, Train train) {
        this.listener = listener;
        this.train = train;
    }

    public TrainControllerListener getListener() {
        return listener;
    }

    public void setListener(TrainControllerListener listener) {
        this.listener = listener;
    }


    @Override
    public void startStopRoutine(boolean running) throws InterruptedException {
        this.running = running;
        while(running){
            this.train.move(Train.ENTRY);
            listener.showTrainPosition(this.train, Train.ENTRY);
            Thread.sleep(this.train.getSpeed()* 1000L);
            this.train.move(Train.GO_OUT);
            listener.showTrainPosition(this.train, Train.GO_OUT);
        }
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
