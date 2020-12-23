package com.lsz.mergesort;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 自底向上的归并排序，使用插入进行优化
 */
public class MergeSortB2UWithInsertOpt {
    public static void main(String[] args) {
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = ThreadLocalRandom.current().nextInt(0, 100 + 1);
        }
        int[] ten = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(array));
        MergeSortB2UWithInsertOpt mergeSort = new MergeSortB2UWithInsertOpt();
        mergeSort.mergeSort(array);
        System.out.println(Arrays.toString(array));
    }

    public void mergeSort(int[] arr) {
        //优化3：调用前先拷贝一次
        int[] copy = Arrays.copyOf(arr, arr.length);
        int n = arr.length;

        //对所有 arr[i, i + 15] 的区间，使用插入排序法
        for (int i = 0; i < n; i += 16) {
            InsertionSort.sort(arr, i, Math.min(i + 15, n - 1));
        }

        //size的起点自然也就不是1了，而是16。
        for (int size = 16; size < n; size += size) {
            //合并两个有序数组[i,i+size-1]和[i+size,Math.min(i + size + size - 1,n-1)]
//            System.out.println("size="+size+"，arr="+ Arrays.toString(arr));
            for (int i = 0; i + size < n; i += size + size) {
                //如果已经有序，无需合并
                if (arr[i + size - 1] > arr[i + size]) {
                    //i + size + size - 1可能越界
                    merge(arr, i, i + size - 1, Math.min(i + size + size - 1, n - 1), copy);
                }
            }
        }
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

        /*//优化2，如果数组长度小于某个阈值，就采用插入排序，性能更佳
        if (r - l <= 15) {
            InsertionSort.sort(arr, l, r);
            return;
        }*/
        //优化3：copy数组全局只有一份
        System.arraycopy(arr, l, copy, l, r - l + 1);
        int i = l;
        int j = mid + 1;
        //每轮循环为arr[k]赋值
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
