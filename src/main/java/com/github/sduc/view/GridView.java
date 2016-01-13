package com.github.sduc.view;

import com.github.sduc.controller.Controller;
import com.github.sduc.model.Pair;

import java.awt.*;

/**
 * Created by sduc on 10/01/16.
 */
public interface GridView {

    void toggleCell(Pair p);

    Component getComponent();

    void setController(Controller controller);

    void repaint();

}
