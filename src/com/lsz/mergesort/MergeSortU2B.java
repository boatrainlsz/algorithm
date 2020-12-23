package com.lsz.mergesort;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 自底向上
 */
public class MergeSortU2B {
    public static void main(String[] args) {
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = ThreadLocalRandom.current().nextInt(0, 100 + 1);
        }
        System.out.println(Arrays.toString(array));
        MergeSortU2B mergeSortUp2Down = new MergeSortU2B();
        mergeSortUp2Down.mergeSort(array);
        System.out.println(Arrays.toString(array));
    }

    public void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }


    /**
     * 对arr[l,r]进行排序
     *
     * @param arr
     * @param l
     * @param r
     */
    public void mergeSort(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }
        int mid = (l + r) / 2;
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    /**
     * 对已经有序的两个数组：arr[l,mid]和arr[mid+1,r]合并
     *
     * @param arr
     * @param l
     * @param mid
     * @param r
     */
    private void merge(int[] arr, int l, int mid, int r) {
        if (arr[mid] <= arr[mid + 1]) {
            //优化1：arr[l,r]已经有序了，不用再排了
            return;
        }

        //优化2，如果数组长度小于某个阈值，就采用插入排序，性能更佳
        if (r - l <= 15) {
            InsertionSort.sort(arr, l, r);
            return;
        }

        //复制一份
        int[] copy = Arrays.copyOfRange(arr, l, r + 1);
        int i = l;
        int j = mid + 1;
        //每轮循环为arr[k]赋值。注意arr[i]在copy中的下标变为了i-l;
        for (int k = l; k <= r; k++) {
            if (i > mid) {
                //如果i越界了
                arr[k] = copy[j - l];
                j++;
            } else if (j > r) {
                //如果j越界了
                arr[k] = copy[i - l];
                i++;
            } else if (copy[i - l] <= copy[j - l]) {
                //都没越界，比较大小
                arr[k] = copy[i - l];
                i++;
            } else {
                arr[k] = copy[j - l];
                j++;
            }
        }
    }

}
