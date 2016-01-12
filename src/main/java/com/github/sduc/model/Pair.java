package com.github.sduc.model;

/**
 * Created by sduc on 12/01/16.
 */
public class Pair {

    public final int left;
    public final int right;

    public Pair(int left, int right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;

        Pair pair = (Pair) o;

        if (left != pair.left) return false;
        return right == pair.right;

    }

    @Override
    public int hashCode() {
        int result = left;
        result = 31 * result + right;
        return result;
    }

    @Override
    public String toString() {
        return "{" + left + "," + right + '}';
    }
}
