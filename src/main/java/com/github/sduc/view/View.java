package com.github.sduc.view;

import com.github.sduc.controller.Controller;
import com.github.sduc.model.GridDiff;
import com.github.sduc.model.Pair;

import javax.swing.*;
import java.awt.*;

/**
 * Created by sduc on 10/01/16.
 */
public class View {

    private GridView gridView;
    private ControllerView controllerView;

    private Controller controller;

    private int width;
    private int height;

    public View(int width, int height, Controller controller) {
        this.controller = controller;
        this.gridView = new PaintGridView(width, height, controller);
        this.controllerView = new ControllerView(controller);
        this.width = width;
        this.height = height;
    }

    public void setController(Controller controller) {
        this.controller = controller;
        this.gridView.setController(controller);
        this.controllerView.setController(controller);
    }

    public JFrame getJFrame() {
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());

        JPanel centralPanel = new JPanel();
        centralPanel.setLayout(new FlowLayout());
        centralPanel.add(gridView.getComponent());
        frame.add(centralPanel, BorderLayout.CENTER);
        frame.add(controllerView, BorderLayout.SOUTH);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        return frame;
    }

    public void notifyGridChanges(GridDiff change) {
        for(Pair p: change) {
            gridView.toggleCell(p);
        }
        gridView.repaint();
    }

    public void notifyError(Exception e) {
        e.printStackTrace();
    }
}
