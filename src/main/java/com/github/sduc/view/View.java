package com.github.sduc.view;

import com.github.sduc.controller.Controller;
import com.github.sduc.model.GridDiff;
import com.github.sduc.model.Pair;

/**
 * Created by sduc on 10/01/16.
 */
public class View {

    private GridView gridView;
    private ControllerView controllerView;

    private Controller controller;

    public void notifyGridChanges(GridDiff change) {
        for(Pair p: change) {
            gridView.toggleCell(p);
        }
    }

}
