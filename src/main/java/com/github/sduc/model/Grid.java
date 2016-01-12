package com.github.sduc.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sduc on 03/01/16.
 */
public class Grid {

    private boolean [][] cellGrid;
    private GridDiff gridDiff = null;

    public Grid(int sizeX, int sizeY) {
        cellGrid = new boolean[sizeX][sizeY];
    }

    public int width() {
        return cellGrid.length;
    }

    public int height() {
        return cellGrid[0].length;
    }

    @Override
    public String toString() {
        String ret = "";
        for (int i = 0; i < width(); i++) {
            if (i != 0)
                ret += "\n";
            for (int j = 0; j < height(); j++)
                ret += (cellGrid[i][j]) ? "#" : ".";
        }
        return ret;
    }

    public boolean isCellAlive(Pair p) {
        return isCellAlive(p.left, p.right);
    }

    public boolean isCellAlive(int x, int y) {
        return cellGrid[x][y];
    }

    public void toggleCellState(Pair p) {
        toggleCellState(p.left, p.right);
    }

    public void toggleCellState(int x, int y) {
        if (isCellAlive(x, y))
            setCellDead(x, y);
        else
            setCellAlive(x, y);
    }

    public void setCellAlive(Pair p) {
        setCellAlive(p.left, p.right);
    }

    public void setCellDead(Pair p) {
        setCellDead(p.left, p.right);
    }

    public void setCellAlive(int x, int y) {
        cellGrid[x][y] = true;
    }

    public void setCellDead(int x, int y) {
        cellGrid[x][y] = false;
    }

    public boolean isNextCellStateAlive(Pair p) {
        int nAliveNeighbours = numberOfAliveNeighbours(p);
        if (isCellAlive(p)) {
            if (nAliveNeighbours < 2 || nAliveNeighbours > 3)
                return false;
            else
                return true;
        } else if (nAliveNeighbours == 3)
            return true;

        return false;
    }

    public boolean isNextCellStateAlive(int x, int y) {
        return isNextCellStateAlive(new Cell(x, y));
    }

    private int numberOfAliveNeighbours(Pair p) {
        Cell cell = new Cell(p);
        int nAlive = 0;
        for(Pair n : cell.neighbours())
            nAlive += (isCellAlive(n)) ? 1 : 0;

        return nAlive;
    }

    public Grid buildNextStateGrid() {
        Grid next = new Grid(width(), height());
        GridDiff diff = new GridDiff();
        for (int i = 0; i < width(); i++) {
            for (int j = 0; j < height(); j++) {
                if (this.isNextCellStateAlive(i,j))
                    next.setCellAlive(i,j);
                else
                    next.setCellDead(i,j);

                addInGridDiffIfDifferent(diff, next, i, j);
            }
        }
        next.setGridDiff(diff);
        return next;
    }

    private void addInGridDiffIfDifferent(
            GridDiff diff,
            Grid next,
            int i, int j) {
        if (next.isCellAlive(i,j) ^ this.isCellAlive(i,j)) {
            diff.addDiff(i,j);
        }
    }

    public GridDiff getGridDiff() throws NoGridDiffException {
        if (gridDiff == null)
            throw new NoGridDiffException();
        return gridDiff;
    }

    private void setGridDiff(GridDiff diff) {
        this.gridDiff = diff;
    }

    private static int mod(int x, int n) {
        int res = x % n;
        return (res < 0) ? res + n: res;
    }

    protected class Cell extends Pair {

        public Cell(int x, int y) {
            super(Grid.mod(x, width()), Grid.mod(y, height()));
        }

        public Cell(Pair p) {
            super(Grid.mod(p.left, width()), Grid.mod(p.right, height()));
        }

        public List<Pair> neighbours() {
            List<Pair> neighbours = new ArrayList<>();
            neighbours.add(new Cell(super.left+1, super.right-1));
            neighbours.add(new Cell(super.left+1, super.right));
            neighbours.add(new Cell(super.left+1, super.right+1));
            neighbours.add(new Cell(super.left, super.right-1));
            neighbours.add(new Cell(super.left, super.right+1));
            neighbours.add(new Cell(super.left-1, super.right-1));
            neighbours.add(new Cell(super.left-1, super.right));
            neighbours.add(new Cell(super.left-1, super.right+1));
            return neighbours;
        }
    }
}
