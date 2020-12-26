package com.lsz.quicksort;

import com.lsz.util.ArrayGenerator;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) throws Exception {
        int[] arr = ArrayGenerator.generateDescendArray(1, 9);
//        int[] arr = ArrayGenerator.generateRandomArray(0, 1000, 100);
        System.out.println("排序前："+Arrays.toString(arr));
        QuickSort.quickSort(arr);
        System.out.println("排序后："+Arrays.toString(arr));
    }

    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int l, int r) {
        if (l >= r) return;
        int p = partition(arr, l, r);
        quickSort(arr, l, p - 1);
        quickSort(arr, p + 1, r);
    }

    private static int partition(int[] arr, int l, int r) {
        int i = l, j = r;
        while (true) {
            while (arr[i] <= arr[l]) {
                i++;
                if (i == r) {
                    break;
                }
            }
            while (arr[j] >= arr[l]) {
                j--;
                if (j == l) {
                    break;
                }
            }
            if (i >= j) break;
            swap(arr, i, j);
            System.out.println(Arrays.toString(arr));

        }
        System.out.println("i=" + i + ",j=" + j);
        System.out.println(Arrays.toString(arr));

        swap(arr, l, j);
        return j;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
