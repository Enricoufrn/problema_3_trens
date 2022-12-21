package com.example.atvd3str2022.model;

import java.util.Queue;

/**
 * This class represents a route of any train
 */
public class TrainRoute {

    private Queue<Track> tracks;
    private Track currentTrack;
    private Track nextTrack;
    private int countSegments;

    public TrainRoute(Queue<Track> tracks) {
        this.countSegments = 0;
        this.tracks = tracks;
    }

    public TrackSegment getNextPosition(){
        Track track;
        if(this.currentTrack == null){
            track = this.tracks.peek();
        } else if(this.nextTrack != null){
            track = this.nextTrack;
        }else{
            track = this.currentTrack;
        }
        this.currentTrack = track;
        TrackSegment nextSegment = track.getNextSegment();
        this.countSegments++;
        if(this.countSegments == 5){
            Track removed = this.tracks.remove();
            this.nextTrack = this.tracks.peek();
            this.tracks.add(removed);
            this.countSegments = 0;
        }else
            this.nextTrack = null;
        return nextSegment;
    }

    public Queue<Track> getTracks() {
        return tracks;
    }

    public void setTracks(Queue<Track> tracks) {
        this.tracks = tracks;
    }

    public Track getCurrentTrack() {
        return currentTrack;
    }

    public void setCurrentTrack(Track currentTrack) {
        this.currentTrack = currentTrack;
    }

    public Track getNextTrack() {
        return nextTrack;
    }

    public void setNextTrack(Track nextTrack) {
        this.nextTrack = nextTrack;
    }

    public int getCountSegments() {
        return countSegments;
    }

    public void setCountSegments(int countSegments) {
        this.countSegments = countSegments;
    }
}
