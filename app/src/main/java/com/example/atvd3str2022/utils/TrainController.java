package com.example.atvd3str2022.utils;

/**
 * This interface is a train controller
 */
public interface TrainController {

    int getSpeed();
    void setSpeed(int speed);
    void startStopRoutine();
    Boolean isTerminated();
}
