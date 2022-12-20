package com.example.atvd3str2022.model;

import android.view.View;
import android.widget.ImageButton;

import com.example.atvd3str2022.utils.TrainController;

/**
 * This class represents a train control panel
 */
public class ControlPanel {
    /**
     * Button to control the train speed and start/stop
     */
    private ImageButton increaseSpeedBtn;
    private ImageButton decreaseSpeedBtn;
    private ImageButton startStopBtn;

    /**
     * The train controller
     */
    private TrainController controller;

    public ControlPanel(ImageButton increaseSpeedBtn, ImageButton decreaseSpeedBtn, ImageButton startStopBtn, TrainController controller) {
        this.increaseSpeedBtn = increaseSpeedBtn;
        this.decreaseSpeedBtn = decreaseSpeedBtn;
        this.startStopBtn = startStopBtn;
        this.controller = controller;
        configureAllButtons();
    }

    public void configureAllButtons(){
        if(increaseSpeedBtn != null && decreaseSpeedBtn != null && startStopBtn != null && controller != null){
            increaseSpeedBtn.setOnClickListener(view -> controller.setSpeed(controller.getSpeed() + 1));
            decreaseSpeedBtn.setOnClickListener(view -> controller.setSpeed(controller.getSpeed() - 1));
            startStopBtn.setOnClickListener(view -> {
                controller.startStopRoutine();
            });
        }
    }
}
