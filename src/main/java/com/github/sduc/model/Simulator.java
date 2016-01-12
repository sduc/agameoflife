package com.github.sduc.model;

import com.github.sduc.view.View;

/**
 * Created by sduc on 10/01/16.
 */
public class Simulator {

    private View view;

    private Grid current;

    private Thread simulationThread;

    public static long waitTime = 100000;

    Simulator(Grid grid) {
        this.current = grid;
    }

    private Thread createSimulationThread() {
        return new Thread() {
            @Override
            public void run() {
                while(true) {
                    step();
                    try {
                        wait(Simulator.waitTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();//TODO: remove this
                    }
                }
            }
        };
    }

    private void step() {
        synchronized (current) {
            Grid next = current.buildNextStateGrid();
            GridDiff change = null;
            try {
                change = current.getGridDiff();
            } catch (NoGridDiffException e) {
                assert(false);
            }
            current = next;
            view.notifyGridChanges(change);
        }
    }

    public void toggleCellState(int x, int y) {
        synchronized (current) {
            current.toggleCellState(x, y);
        }
    }

    public void start() {
        simulationThread = createSimulationThread();
        simulationThread.start();
    }

    public void pause() throws InterruptedException {
        simulationThread.wait();
    }

    public void play() {
        simulationThread.notify();
    }

}
