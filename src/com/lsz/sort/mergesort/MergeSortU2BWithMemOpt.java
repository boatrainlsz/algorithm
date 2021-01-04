package com.lsz.sort.mergesort;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 内存优化
 */
public class MergeSortU2BWithMemOpt {
    public static void main(String[] args) {
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = ThreadLocalRandom.current().nextInt(0, 100 + 1);
        }
        System.out.println(Arrays.toString(array));
        MergeSortU2BWithMemOpt mergeSort = new MergeSortU2BWithMemOpt();
        mergeSort.mergeSort(array);
        System.out.println(Arrays.toString(array));
    }

    public void mergeSort(int[] arr) {
        //优化3：调用前先拷贝一次
        int[] copy = Arrays.copyOf(arr, arr.length);
        mergeSort(arr, 0, arr.length - 1, copy);
    }

    /**
     * 对arr[l,r]进行排序
     *
     * @param arr
     * @param l
     * @param r
     */
    public void mergeSort(int[] arr, int l, int r, int[] copy) {
        if (l == r) {
            return;
        }
        int mid = (l + r) / 2;
        mergeSort(arr, l, mid, copy);
        mergeSort(arr, mid + 1, r, copy);
        merge(arr, l, mid, r, copy);
    }

    /**
     * 对已经有序的两个数组：arr[l,mid]和arr[mid+1,r]合并
     *
     * @param arr
     * @param l
     * @param mid
     * @param r
     */
    private void merge(int[] arr, int l, int mid, int r, int[] copy) {
        if (arr[mid] <= arr[mid + 1]) {
            //优化1：arr[l,r]已经有序了，不用再排了
            return;
        }

        //优化2，如果数组长度小于某个阈值，就采用插入排序，性能更佳
        if (r - l <= 15) {
            InsertionSort.sort(arr, l, r);
            return;
        }
        //优化3：copy数组全局只有一份
        System.arraycopy(arr, l, copy, l, r - l + 1);
        int i = l;
        int j = mid + 1;
        //每轮循环为arr[k]赋值。
        for (int k = l; k <= r; k++) {
            if (i > mid) {
                //如果i越界了
                arr[k] = copy[j];
                j++;
            } else if (j > r) {
                //如果j越界了
                arr[k] = copy[i];
                i++;
            } else if (copy[i] <= copy[j]) {
                //都没越界，比较大小
                arr[k] = copy[i];
                i++;
            } else {
                arr[k] = copy[j];
                j++;
            }
        }
    }

}
