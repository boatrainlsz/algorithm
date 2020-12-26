package com.lsz.quicksort;

import java.util.Random;

public class QuickSort {

    public static void sort(int[] arr) {
        Random random = new Random();
        quickSort(arr, 0, arr.length - 1, random);
    }

    private static void quickSort(int[] arr, int l, int r, Random random) {
        if (l >= r) return;
        int p = partition(arr, l, r, random);
        quickSort(arr, l, p - 1, random);
        quickSort(arr, p + 1, r, random);
    }

    private static int partition(int[] arr, int l, int r, Random random) {
        //问题：对于已经有序的数组，总是选取arr[l]作为pivot会导致快速排序退化为O(N^2)的算法，数据量大的的时候甚至会栈溢出。
        //解决办法：随机选取一个arr[l,r]的一个数作为pivot
        int p = random.nextInt(r - l + 1) + l;
        //交换一下，再开始分区
        swap(arr, l, p);
        int i = l + 1, j = r;
        //arr[l+1,i-1] <=arr[l]，arr[j+1,r] >=arr[l]
        while (true) {
            while (i <= j && arr[i] < arr[l]) {
                i++;
            }
            while (i <= j && arr[j] > arr[l]) {
                j--;
            }
            if (i >= j) break;
            swap(arr, i, j);
            i++;
            j--;
        }
        swap(arr, l, j);
        return j;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
