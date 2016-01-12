package com.github.sduc.controller;

import com.github.sduc.model.Grid;
import com.github.sduc.model.Simulator;

/**
 * Created by sduc on 10/01/16.
 */
public class Controller {

    private Simulator simulator;

    void toggleCellState(int x, int y) {
        simulator.toggleCellState(x, y);
    }

    void startSimulation() {
        simulator.start();
    }

    void pauseSimulation() throws InterruptedException {
        simulator.pause();
    }

}
