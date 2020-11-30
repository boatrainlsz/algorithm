package com.lsz.segmenttree;

public class SumMerger implements Merger<Integer> {
    @Override
    public Integer merge(Integer a, Integer b) {
        return a + b;
    }
}
