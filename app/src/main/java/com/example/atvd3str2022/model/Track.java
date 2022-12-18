package com.example.atvd3str2022.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * This class represents a track
 */
public class Track {

    private int id;

    /**
     * List of trains
     */
    private List<Train> trains;
    /**
     * List of segments
     */
    private Queue<TrackSegment> segments;

    public Track(int id, Queue<TrackSegment> segments) {
        this.id = id;
        this.segments = segments;
        this.trains =  new ArrayList<>();
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
     * @param segment Segment
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

    //    // Método para verificar se há deadlocks possíveis entre os trens
//    public boolean isDeadlockPossible() {
//        // ... implementação aqui
//    }
}
