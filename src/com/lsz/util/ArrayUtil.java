package com.lsz.util;

import java.util.concurrent.ThreadLocalRandom;

public class ArrayUtil {
    public static int[] generateRandomArray(int min, int max, int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = ThreadLocalRandom.current().nextInt(min, max + 1);
        }
        return arr;
    }

    public static int[] generateDescendArray(int min, int max) throws Exception {
        if (min < 0 || max < 0) {
            throw new Exception("只支持非负数！");
        }
        int[] arr = new int[max - min + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = max - i;
        }
        return arr;
    }

    public static boolean isSorted(int[] arr) throws Exception {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
