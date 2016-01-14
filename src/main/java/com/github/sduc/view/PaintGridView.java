package com.github.sduc.view;

import com.github.sduc.controller.Controller;
import com.github.sduc.model.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.Transient;

/**
 * Created by sduc on 13/01/16.
 */
public class PaintGridView extends JPanel implements GridView, MouseListener {

    private Controller controller;
    private boolean[][] grid;
    private int cellSize = 5;

    public PaintGridView(int width, int height, Controller controller) {
        this.controller = controller;
        this.grid = new boolean[width][height];
        this.addMouseListener(this);
        this.setBackground(Color.WHITE);
    }

    @Override
    public void toggleCell(Pair p) {
        grid[p.left][p.right] = !grid[p.left][p.right];
    }

    @Override
    public Component getComponent() {
        return this;
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Color gColor = g.getColor();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j]) {
                    g.setColor(Color.red);
                    g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                }
            }
        }
        g.setColor(gColor);
    }

    @Override
    @Transient
    public Dimension getPreferredSize() {
        return new Dimension(grid.length * cellSize, grid[0].length * cellSize);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if ((mouseEvent.getButton() == 1)) {
            int i = mouseEvent.getY()/cellSize;
            int j = mouseEvent.getX()/cellSize;
            controller.toggleCellState(i,j);
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
