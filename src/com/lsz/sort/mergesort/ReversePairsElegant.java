package com.lsz.sort.mergesort;

import java.util.Arrays;

/**
 * based on ReversePairs
 * 去掉状态变量，更优雅
 */
public class ReversePairsElegant {


    public static void main(String[] args) {
        ReversePairsElegant reversePairs = new ReversePairsElegant();
        System.out.println(reversePairs.reversePairs(new int[]{10, 6, 7, 5, 4, 8, 3, 9, 2, 1}));
    }

    public int reversePairs(int[] nums) {
        return mergeSort(nums);
    }

    public int mergeSort(int[] arr) {
        int res = 0;
        int[] copy = Arrays.copyOf(arr, arr.length);
        int n = arr.length;
        //待合并的区间的长度，1，2，4，8....
        for (int size = 1; size < n; size += size) {
            //合并两个有序数组[i,i+size-1]和[i+size,Math.min(i + size + size - 1,n-1)]
//            System.out.println("size="+size+"，arr="+ Arrays.toString(arr));
            for (int i = 0; i + size < n; i += size + size) {
                //如果已经有序，无需合并
                if (arr[i + size - 1] > arr[i + size]) {
                    //i + size + size - 1可能越界
                    res += merge(arr, i, i + size - 1, Math.min(i + size + size - 1, n - 1), copy);
                }
            }
        }
        return res;
    }

    /**
     * 对已经有序的两个数组：arr[l,mid]和arr[mid+1,r]合并
     *
     * @param arr
     * @param l
     * @param mid
     * @param r
     */
    private int merge(int[] arr, int l, int mid, int r, int[] copy) {
        int res = 0;

        if (arr[mid] <= arr[mid + 1]) {
            //优化1：arr[l,r]已经有序了，不用再排了
            return 0;
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
                res += mid - i + 1;
                j++;
            }
        }
        return res;
    }
}
