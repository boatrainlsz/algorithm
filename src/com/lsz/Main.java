package com.lsz;

import com.lsz.segmenttree.SegmentTree;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Integer[] nums = new Integer[]{1, 2, 3, 4, 5, 6};
        SegmentTree<Integer> tree = new SegmentTree<>(nums, Integer::sum);
        Integer query = tree.query(0, 3);
        System.out.println(query);
    }
}
