package com.example.atvd3str2022.model;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

public class Train {

    public static final int ENTRY = 605;
    public static final int GO_OUT = 812;

    private int color;
    private int speed;
    private TrackSegment position;
    private int id;
    private TrainRoute route;
    private TrackSegment lastSegment;
    private Track currentTrack;

    public Train(int color, int id, TrainRoute route) {
        this.color = color;
        this.id = id;
        this.route = route;
        this.speed = 1;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    /**
     * Setter end Getter the train speed and position
     */
    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public TrackSegment getPosition() {
        return this.position;
    }

    public void setPosition(TrackSegment position) {
        this.position = position;
    }

    public TrackSegment getLastSegment() {
        return lastSegment;
    }

    public void setLastSegment(TrackSegment lastSegment) {
        this.lastSegment = lastSegment;
    }

    /**
     * Change the train position
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void move(int oper) throws InterruptedException {
        if(oper == GO_OUT && this.lastSegment != null){
            this.lastSegment.goOut();
        }else{
//            TrackSegment nextSegment = this.route.getNextPosition();
            if(this.route.getNextTrack() != null){
                while (this.route.getNextTrack().isOccupied()){
                    Log.d("move", "O trem de cor "+this.color+ " encontrou um trilho ocupado!");
                    Thread.sleep(2000);
                }
            }
            TrackSegment nextSegment = this.route.getNextPosition();
            nextSegment.entry(this.color);
//            if(this.route.getCountSegments() == 1)
//                this.route.getCurrentTrack().setNumberOfTrains(this.route.getCurrentTrack().getNumberOfTrains() + 1);
//            if(this.route.getCountSegments() == 4)
//                this.route.getCurrentTrack().setNumberOfTrains(this.route.getCurrentTrack().getNumberOfTrains() - 1);
            this.lastSegment = nextSegment;
            this.position = nextSegment;
        }
    }
}
