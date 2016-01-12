package com.github.sduc.view;

import com.github.sduc.model.Pair;

/**
 * Created by sduc on 12/01/16.
 */
public class JGridComponentsView implements GridView {

    private CellView[][] cells;

    public JGridComponentsView(int width, int height) {
        cells = new CellView[width][height];
    }

    @Override
    public void toggleCell(Pair p) {
        cells[p.left][p.right].toggle();
    }
}
