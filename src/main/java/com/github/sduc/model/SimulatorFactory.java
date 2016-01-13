package com.github.sduc.model;

/**
 * Created by sduc on 13/01/16.
 */
public class SimulatorFactory {

    public static Simulator createSimulator(int width, int height) {
        Grid grid = new Grid(width, height);
        return new Simulator(grid);
    }

}
