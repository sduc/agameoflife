package com.github.sduc.controller;

import com.github.sduc.model.Grid;
import com.github.sduc.model.Simulator;

/**
 * Created by sduc on 10/01/16.
 */
public class Controller {

    private Simulator simulator;
    private SimulatorState state = SimulatorState.INIT;

    public Controller(Simulator simulator) {
        this.simulator = simulator;
    }

    public void toggleCellState(int x, int y) {
        simulator.toggleCellState(x, y);
    }

    public void startSimulation() {
        simulator.start();
        state = SimulatorState.RUNNING;
    }

    public void pauseSimulation() {
        simulator.pause();
        state = SimulatorState.PAUSED;
    }

    public void playSimulation() {
        simulator.play();
        state = SimulatorState.RUNNING;
    }

    public void deccelerate() {
        simulator.deccelerate();
    }

    public void accelerate() {
        simulator.accelerate();
    }

    public void togglePlayPause() {
        switch (state) {
            case INIT:
                startSimulation();
                break;
            case RUNNING:
                pauseSimulation();
                break;
            case PAUSED:
                playSimulation();
                break;
        }
    }
}
