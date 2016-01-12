package com.github.sduc.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by sduc on 10/01/16.
 */
public class GridDiff implements Iterable<Pair> {

    private List<Pair> diffs = new ArrayList<>();

    public void addDiff(int i, int j) {
        diffs.add(new Pair(i,j));
    }

    public boolean contains(Pair pair) {
        return diffs.contains(pair);
    }

    @Override
    public Iterator<Pair> iterator() {
        return diffs.iterator();
    }

    public int size() {
        return diffs.size();
    }
}
