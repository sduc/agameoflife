package com.github.sduc.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by sduc on 14/01/16.
 */
class PlayPauseButton extends RoundButton {

    private ImageIcon playIcon;
    private ImageIcon pauseIcon;

    private boolean isPlay = true;

    public PlayPauseButton() {
        initImageResources();

        setIcon(playIcon);
    }

    private void initImageResources() {
        try {
            Image playImg = ImageIO.read(
                    getClass().getClassLoader().getResource("play_icon.png"));
            playIcon = new ImageIcon(playImg.getScaledInstance(
                    29, 29, java.awt.Image.SCALE_SMOOTH));

            Image pauseImg = ImageIO.read(
                    getClass().getClassLoader().getResource("pause_icon.png"));
            pauseIcon = new ImageIcon(pauseImg.getScaledInstance(
                    29, 29, java.awt.Image.SCALE_SMOOTH));
        } catch (IOException e) {
            assert(false);
        }
    }

    public void toggleIcon() {
        if (isPlay) {
            setIcon(pauseIcon);
            isPlay = false;
        } else {
            setIcon(playIcon);
            isPlay = true;
        }
        invalidate();
    }

}
