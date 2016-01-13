package com.github.sduc.model;

import com.github.sduc.view.View;

/**
 * Created by sduc on 10/01/16.
 */
public class Simulator {

    private View view;

    private Grid current;

    private Thread simulationThread;

    long waitTime = 100;
    public static long waitIncr = 10;
    public static long MIN_WAIT_TIME = 10;
    public static long MAX_WAIT_TIME = 200;


    public Simulator(Grid grid) {
        this.current = grid;
    }

    public void setView(View view) {
        this.view = view;
    }

    private Thread createSimulationThread() {
        return new Thread() {
            @Override
            public void run() {
                while(true) {
                    step();
                    try {
                        this.sleep(waitTime);
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
                change = next.getGridDiff();
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

        GridDiff diff = new GridDiff();
        diff.addDiff(x,y);
        view.notifyGridChanges(diff);
    }

    public void start() {
        simulationThread = createSimulationThread();
        simulationThread.start();
    }

    public void pause() {
        try {
            //synchronized (current) {
                simulationThread.wait();
            //}
        } catch (InterruptedException e) {
            view.notifyError(e);
        }
    }

    public void play() {
        simulationThread.notify();
    }

    public void accelerate() {
        if (waitTime > MIN_WAIT_TIME)
            waitTime -= waitIncr;
    }

    public void deccelerate() {
        if (waitTime < MAX_WAIT_TIME)
            waitTime += waitIncr;
    }

}
