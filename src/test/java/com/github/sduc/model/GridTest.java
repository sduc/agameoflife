package com.github.sduc.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by sduc on 03/01/16.
 */
public class GridTest {

    private Grid grid = null;

    private Grid.Cell getAnyCell() {
        return grid.new Cell(1,1);
    }

    private Grid.Cell getAnotherCell() {
        return grid.new Cell(3, 3);
    }

    @Before
    public void setUp() throws Exception {
        grid = new Grid(20,20);
    }

    @Test
    public void testDefaultCellStatusShouldBeDead() throws Exception {
        Pair cell = getAnyCell();
        assertFalse(grid.isCellAlive(cell));
    }

    @Test
    public void testSetCellAliveShouldChangeCellStatus() throws Exception {
        Pair cell = getAnyCell();
        grid.setCellAlive(cell);
        assertTrue(grid.isCellAlive(cell));
    }

    @Test
    public void testSetCellAliveShouldNotChangeOtherCellStatus() throws Exception {
        Pair cell = getAnyCell();
        Pair otherCell = getAnotherCell();
        grid.setCellAlive(cell);
        assertFalse(grid.isCellAlive(otherCell));
    }

    @Test
    public void testSetCellAliveThenSetCellDeadCellStatusShouldBeDead() throws Exception {
        Grid.Cell cell = getAnyCell();
        grid.setCellAlive(cell);
        grid.setCellDead(cell);
        assertFalse(grid.isCellAlive(cell));
    }

    @Test
    public void testNextCellStateWhenAllDeadShouldBeDead() throws Exception {
        Grid.Cell cell = getAnyCell();
        assertFalse(grid.isNextCellStateAlive(cell));
    }

    private void testCellNeighboursInternal(
            Grid.Cell cell,
            List<Grid.Cell> expectedNeighbours) {
        List<Pair> neighbours = cell.neighbours();
        assertEquals(expectedNeighbours.size(), neighbours.size());
        assertTrue(neighbours.containsAll(expectedNeighbours));
    }

    @Test
    public void testCellNeighboursShouldNotContainItself() throws Exception {
        Grid.Cell cell = getAnyCell();
        assertFalse(cell.neighbours().contains(cell));
    }

    @Test
    public void testCellNeighboursCorner() throws Exception {
        Grid.Cell cell = grid.new Cell(0,0);
        List<Grid.Cell> expectNeighbours= Arrays.asList(
                grid.new Cell(1, grid.width()-1),
                grid.new Cell(1, 0),
                grid.new Cell(1, 1),
                grid.new Cell(0, grid.width()-1),
                grid.new Cell(0, 1),
                grid.new Cell(grid.height()-1, grid.width()-1),
                grid.new Cell(grid.height()-1, 0),
                grid.new Cell(grid.height()-1, 1)
        );

        testCellNeighboursInternal(cell, expectNeighbours);
    }

    @Test
    public void testCellNeighboursMiddle() throws Exception {
        Grid.Cell cell = grid.new Cell(1,1);
        List<Grid.Cell> expectNeighbours= Arrays.asList(
                grid.new Cell(2, 0),
                grid.new Cell(2, 1),
                grid.new Cell(2, 2),
                grid.new Cell(1, 0),
                grid.new Cell(1, 2),
                grid.new Cell(0, 0),
                grid.new Cell(0, 1),
                grid.new Cell(0, 2)
        );

        testCellNeighboursInternal(cell, expectNeighbours);
    }

    @Test
    public void tesLiveCellSurroundedByAllDeadCellShouldDie() throws Exception {
        Grid.Cell cell = getAnyCell();
        grid.setCellAlive(cell);
        assertFalse(grid.isNextCellStateAlive(cell));
    }

    @Test
    public void testLiveCellSurroundedByAllAliveCellsShouldDie() throws Exception {
        Grid.Cell cell = getAnyCell();
        grid.setCellAlive(cell);
        for (Pair n : cell.neighbours()) {
            grid.setCellAlive(n);
        }
        assertFalse(grid.isNextCellStateAlive(cell));
    }

    @Test
    public void testCellSurroundedByTwoLivingCellShouldLive() throws Exception {
        Grid.Cell cell = getAnyCell();
        List<Pair> neighbours = cell.neighbours();
        grid.setCellAlive(cell.left, cell.right);

        Pair firstNeighbour = neighbours.get(0);
        grid.setCellAlive(firstNeighbour.left, firstNeighbour.right);
        Pair secondNeighbour = neighbours.get(1);
        grid.setCellAlive(secondNeighbour.left, secondNeighbour.right);

        assertTrue(grid.isNextCellStateAlive(cell.left, cell.right));
    }

    @Test
    public void testCellDeadSurroundedByThreeAliveCellsShouldLive() throws Exception {
        Grid.Cell cell = getAnyCell();
        List<Pair> nbs = cell.neighbours();

        grid.setCellAlive(nbs.get(0).left, nbs.get(0).right);
        grid.setCellAlive(nbs.get(1).left, nbs.get(1).right);
        grid.setCellAlive(nbs.get(2).left, nbs.get(2).right);

        assertTrue(grid.isNextCellStateAlive(cell));
    }

    @Test
    public void testConstructedNextGridShouldMatchIsNextCellStateAliveForAllCells() throws Exception {
        addManyLivingCells(grid);

        Grid next = grid.buildNextStateGrid();

        for (int i = 0; i < grid.width(); i++) {
            for (int j = 0; j < grid.height(); j++) {
                assertEquals(next.isCellAlive(i,j), grid.isNextCellStateAlive(i,j));
            }
        }
    }

    private void addManyLivingCells(Grid grid) {
        grid.setCellAlive(0, 0);
        grid.setCellAlive(1, 1);
        grid.setCellAlive(2, 2);
        grid.setCellAlive(3, 3);
        grid.setCellAlive(4, 3);
    }

    @Test
    public void testDiffGridSize() throws Exception, NoGridDiffException {
        Pair cell = getAnyCell();
        grid.setCellAlive(cell);
        assertEquals(
                1,
                grid.buildNextStateGrid().getGridDiff().size());
    }

    @Test
    public void testDiffGridConstructedNextGridShouldMatchGridDiff() throws Exception, NoGridDiffException {
        addManyLivingCells(grid);
        Grid next = grid.buildNextStateGrid();

        for (int i = 0; i < grid.width(); i++) {
            for (int j = 0; j <grid.height(); j++) {
                assertEquals(
                        grid.isCellAlive(i, j) ^ grid.isNextCellStateAlive(i, j),
                        next.getGridDiff().contains(new Pair(i,j)));
            }
        }
    }
}