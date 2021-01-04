package com.lsz.util;

import com.lsz.sort.quicksort.QuickSort2Way;
import com.lsz.sort.quicksort.QuickSort3Way;

import java.util.Arrays;

public class SortHelper {
    public static void main(String[] args) throws Exception {
//        int[] arr = ArrayUtil.generateDescendArray(1, 50000000);
        int[] arr = ArrayUtil.generateRandomArray(0, 10, 10);
//        sort(arr, "QuickSort2Way");
        sort(arr, "QuickSort3Way");
//        sort(arr, "JDKSort");
    }

    public static void sort(int[] arr, String method) throws Exception {
        long start = System.nanoTime();
        if ("QuickSort2Way".equals(method)) {
            QuickSort2Way.sort(arr);
        } else if ("QuickSort3Way".equals(method)) {
            QuickSort3Way.sort(arr);
        } else if ("JDKSort".equals(method)) {
            Arrays.sort(arr);
        }
        if (!ArrayUtil.isSorted(arr)) {
            throw new Exception(method + " failed, " + "size=" + arr.length);
        }
        long elapsedTime = System.nanoTime() - start;
        System.out.println(method + " took " + elapsedTime / 1000000000.0 + "s, " + "size=" + arr.length);
    }
}
