package com.github.sduc.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by sduc on 14/01/16.
 */
class IconButton extends JButton {

    public IconButton(String iconResource) {
        initIcon(iconResource);
    }

    private void initIcon(String iconResource) {
        try {
            Image img = ImageIO.read(
                    getClass().getClassLoader().getResource(iconResource));
            ImageIcon icon = new ImageIcon(
                    img.getScaledInstance(35, 15, java.awt.Image.SCALE_SMOOTH)
            );
            setIcon(icon);
        } catch (IOException e) {
            assert(false);
        }
    }

    public static IconButton buildFastFowardButton() {
        return new IconButton("fast_forward_icon.png");
    }

    public static IconButton buildFastRewindButton() {
        return new IconButton("fast_rewind_icon.png");
    }

}
