package com.example.atvd3str2022.model;

import java.util.Queue;

/**
 * This class represents a route of any train
 */
public class TrainRoute {

    private Queue<TrackSegment> segments;

    public TrainRoute(Queue<TrackSegment> segments) {
        this.segments = segments;
    }

    public TrackSegment getNextPosition(){
        TrackSegment segment = this.segments.poll();
        this.segments.add(segment);
        return segment;
    }
}
