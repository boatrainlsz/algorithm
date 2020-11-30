package com.lsz.segmenttree;

public class Main {
    public static void main(String[] args) {
        Integer[] array = new Integer[]{1, 2, 3, 4, 5, 6};
        SegmentTree<Integer> tree = new SegmentTree<>(array, Integer::sum);
    }
}
