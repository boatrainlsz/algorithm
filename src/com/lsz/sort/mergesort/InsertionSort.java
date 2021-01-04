package com.lsz.sort.mergesort;

import java.util.Arrays;

public class InsertionSort {
    public static void main(String[] args) {
        InsertionSort insertionSort = new InsertionSort();
        int[] arr = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1};
        insertionSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    public static void sort(int[] arr, int from, int to) {
        for (int i = from; i <= to; i++) {
            int cur = arr[i];
            //将cur放到合适的位置
            int j;
            for (j = i; j >= 1 && cur < arr[j - 1]; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = cur;
        }
    }
}
