package com.example.atvd3str2022.model;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * This class represents a track
 */
public class Track {

    private int id;
    private volatile boolean isOccupied;
    private int numberOfTrains;


    /**
     * List of trains
     */
    private List<Train> trains;
    /**
     * List of segments
     */
    private Queue<TrackSegment> segments;

    public Track(Queue<TrackSegment> segments) {
        this.segments = segments;
        this.trains =  new ArrayList<>();
        this.numberOfTrains = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Add and remove an train into this track
     * @param train Train object
     */
    public void addTrain(Train train) {
        this.trains.add(train);
    }

    public void removeTrain(Train train) {
        this.trains.remove(train);
    }

    /**
     * Check if this segment contains a train
     * @param segment Segment ID
     * @return True or False
     */
    public boolean isSegmentOccupied(int segment) {
        for (Train train : trains) {
            if (train.getPosition().getId() == segment) {
                return true;
            }
        }
        return false;
    }

    public Queue<TrackSegment> getSegments() {
        return segments;
    }

    public void setSegments(Queue<TrackSegment> segments) {
        this.segments = segments;
    }

    public TrackSegment getNextSegment(){
        TrackSegment segment = this.segments.poll();
        this.segments.add(segment);
        return segment;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean isOccupied() {
        final int[] free = {0};
        segments.stream().forEach( segment -> {
            if (segment.isOccupied()){
                this.isOccupied = true;
            }else{
                free[0]++;
            }
            if(free[0] == 5)
                this.isOccupied = false;
        });
        return this.isOccupied;
    }

    public int getNumberOfTrains() {
        return numberOfTrains;
    }

    public void setNumberOfTrains(int numberOfTrains) {
        this.numberOfTrains = numberOfTrains;
    }

    //    // Método para verificar se há deadlocks possíveis entre os trens
//    public boolean isDeadlockPossible() {
//        // ... implementação aqui
//    }
}
