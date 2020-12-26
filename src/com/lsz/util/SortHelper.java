package com.lsz.util;

import com.lsz.quicksort.QuickSort;

public class SortHelper {
    public static void main(String[] args) throws Exception {
//        int[] arr = ArrayGenerator.generateDescendArray(1, 9);
        int[] arr = ArrayUtil.generateRandomArray(0, 50000000, 50000000);
        sort(arr, "QuickSort");
    }

    public static void sort(int[] arr, String method) throws Exception {
        long start = System.nanoTime();
        if ("QuickSort".equals(method)) {
            QuickSort.sort(arr);
        }
        if (!ArrayUtil.isSorted(arr)) {
            throw new Exception(method + " failed, " + "size=" + arr.length);
        }
        long elapsedTime = System.nanoTime() - start;
        System.out.println(method + " took " + elapsedTime / 1000000000.0 + "s, " + "size=" + arr.length);
    }
}