package com.lsz.quicksort;

import java.util.Random;

/**
 * 三路快排
 * 对于常数数组的排序提升巨大：
 * QuickSort took 1.6035787s, size=50000000
 * QuickSort3Way took 0.0216437s, size=50000000
 * based on
 *
 * @see QuickSort2Way
 */
public class QuickSort3Way {

    public static void sort(int[] arr) {
        Random random = new Random();
        quickSort(arr, 0, arr.length - 1, random);
    }

    private static void quickSort(int[] arr, int l, int r, Random random) {
        if (l >= r) return;
        //循环不变量:arr[l+1,lt]<v,arr[lt+1,i-1]==v,arr[gt,r]>v
        int p = random.nextInt(r - l + 1) + l;
        swap(arr, l, p);
        int lt = l, gt = r + 1, i = l + 1;
        int pivot = arr[l];
        while (true) {
            if (arr[i] == pivot) {
                i++;
            } else if (arr[i] > pivot) {
                gt--;
                swap(arr, i, gt);
            } else if (arr[i] < pivot) {
                lt++;
                swap(arr, i, lt);
                i++;
            }
            //结束循环
            if (i >= gt) {
                break;
            }
        }
        swap(arr, l, lt);
        //swap后，循环不变量变为：arr[l,lt-1]<v,arr[lt,gt-1]==v,arr[gt,r]>v

        quickSort(arr, l, lt - 1, random);
        quickSort(arr, gt, r, random);
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
