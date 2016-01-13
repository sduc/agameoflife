package com.github.sduc.view;

import com.github.sduc.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by sduc on 10/01/16.
 */
public class ControllerView extends JComponent {

    private Controller controller;

    private JButton deccelerate;
    private JButton accelerate;
    private JButton playPause;

    public ControllerView(Controller controller) {
        this.controller = controller;
        init();
    }

    private void init() {
        initButtons();
        this.setLayout(new FlowLayout());
        JComponent compact = new JPanel();
        int nButtons = 3;
        int vGap = 1;
        int hGap = 20;
        compact.setLayout(new GridLayout(1, nButtons, hGap, vGap));
        compact.add(deccelerate);
        compact.add(playPause);
        compact.add(accelerate);
        compact.setPreferredSize(new Dimension(200,30));
        this.add(compact);
    }

    private void initButtons() {
        initAccelerateButton();
        initDeccelerateButton();
        initPlayPauseButton();
    }

    private void initPlayPauseButton() {
        playPause = new JButton();
        playPause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                controller.togglePlayPause();
            }
        });
    }

    private void initDeccelerateButton() {
        deccelerate = new JButton();
        deccelerate.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                controller.deccelerate();
            }

        });
    }

    private void initAccelerateButton() {
        accelerate = new JButton();
        accelerate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                controller.accelerate();
            }
        });
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

}
