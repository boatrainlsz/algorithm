package com.lsz.util;

import com.lsz.sort.mergesort.MergeSortU2BWithMemOpt;
import com.lsz.sort.quicksort.QuickSort2Way;
import com.lsz.sort.quicksort.QuickSort3Way;

import java.util.Arrays;

public class SortHelper {
    public static void main(String[] args) throws Exception {
//        int[] arr = ArrayUtil.generateDescendArray(1, 50000000);
//        int[] arr = ArrayUtil.generateRandomArray(0, 10, 10);
//        int[] arr = new int[]{6, 7, 8, 9, 10, 1, 2, 3, 4, 5};
        int[] arr = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        sort(arr, "QuickSort2Way");
//        sort(arr, "MergeSortU2BWithMemOpt");
//        sort(arr, "JDKSort");
    }

    public static void sort(int[] arr, String method) throws Exception {
        long start = System.nanoTime();
        if ("QuickSort2Way".equals(method)) {
            QuickSort2Way.sort(arr);
        } else if ("QuickSort3Way".equals(method)) {
            QuickSort3Way.sort(arr);
        } else if ("MergeSortU2BWithMemOpt".equals(method)) {
            MergeSortU2BWithMemOpt.sort(arr);
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
