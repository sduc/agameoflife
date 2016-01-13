package com.github.sduc.main;

import com.github.sduc.controller.Controller;
import com.github.sduc.model.Simulator;
import com.github.sduc.model.SimulatorFactory;
import com.github.sduc.view.View;

/**
 * Created by sduc on 13/01/16.
 */
public class Main {

    public static final int DFLT_WIDTH = 100;
    public static final int DFLT_HEIGHT = 100;

    public static void main(String[] args) {
        Simulator simulator = SimulatorFactory.createSimulator(DFLT_WIDTH, DFLT_HEIGHT);
        Controller controller = new Controller(simulator);
        View gameOfLifeView = new View(DFLT_WIDTH, DFLT_HEIGHT, controller);
        simulator.setView(gameOfLifeView);

        gameOfLifeView.getJFrame().setVisible(true);
    }

}
