package com.github.sduc.view;

import com.github.sduc.controller.Controller;
import com.github.sduc.model.Pair;

import javax.swing.*;
import java.awt.*;

/**
 * Created by sduc on 12/01/16.
 */
public class JGridComponentsView extends JComponent implements GridView {

    private CellView[][] cells;
    private Controller controler;

    public JGridComponentsView(int width, int height) {
        cells = new CellView[width][height];
    }

    @Override
    public void toggleCell(Pair p) {
        cells[p.left][p.right].toggle();
    }

    @Override
    public Component getComponent() {
        return this;
    }

    @Override
    public void setController(Controller controller) {
        this.controler = controller;
    }
}
