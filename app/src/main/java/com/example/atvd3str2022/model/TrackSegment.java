package com.example.atvd3str2022.model;

import android.util.Log;
import android.view.View;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * This class represents a segment of any track
 */
public class TrackSegment {

    private int id;
    private volatile boolean isOccupied;
    private ReadWriteLock lock;
    private View segment;

    public TrackSegment(ReadWriteLock lock) {
        this.lock = lock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public View getSegment() {
        return segment;
    }

    public void setSegment(View segment) {
        this.segment = segment;
    }

    /**
     * Methods to entry or go out of this track segment
     */
    public void entry(int color){
        Lock writeLock = lock.writeLock();
        try {
            writeLock.lock();
            isOccupied = true;
            Log.d("TrainSegment", "entry: o trem de cor " + color + "entrou no segmento");
        }finally {
            writeLock.unlock();
        }
    }

    public void goOut(){
        Lock readLock = lock.readLock();
        try {
            readLock.lock();
            isOccupied = false;
        }finally {
            readLock.unlock();
        }
    }

    public boolean isOccupied(){
        Lock readLock = lock.readLock();
        try {
            readLock.lock();
            return isOccupied;
        }finally {
            readLock.unlock();
        }
    }
}
