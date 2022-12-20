package com.example.atvd3str2022.model;

public class Train {

    public static final int ENTRY = 605;
    public static final int GO_OUT = 812;

    private int color;
    private int speed;
    private TrackSegment position;
    private int id;
    private TrainRoute route;
    private TrackSegment lastSegment;

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
    public void move(int oper) {
        if(oper == GO_OUT && this.lastSegment != null){
            this.lastSegment.goOut();
        }else{
            TrackSegment nextSegment = this.route.getNextPosition();
            nextSegment.entry(this.color);
            this.lastSegment = nextSegment;
            this.position = nextSegment;
        }
    }
}
