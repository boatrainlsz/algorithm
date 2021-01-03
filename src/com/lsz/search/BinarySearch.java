package com.lsz.search;

public class BinarySearch {
    private BinarySearch() {
    }

    /**
     * 递归
     *
     * @param data
     * @param target
     * @param <E>
     * @return
     */
    public static <E extends Comparable<E>> int searchR(E[] data, E target) {
        return searchR(data, 0, data.length - 1, target);
    }

    /**
     * 非递归
     *
     * @param data
     * @param target
     * @param <E>
     * @return
     */
    public static <E extends Comparable<E>> int search(E[] data, E target) {
        int l = 0, r = data.length - 1;
        //在data[l,r]中查找
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (data[mid].compareTo(target) == 0) {
                return mid;
            } else if (data[mid].compareTo(target) < 0) {
                l = mid + 1;
            } else if (data[mid].compareTo(target) > 0) {
                r = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 非递归
     * 返回大于target的最小值的位置
     *
     * @param data
     * @param target
     * @param <E>
     * @return
     */
    public static <E extends Comparable<E>> int upper(E[] data, E target) {
        /**
         *1.r的初值是data.length，这样，对于[1,2,3,4,5] target=6,这种情况，最后返回的是data.length，代表不存在这样的数
         * 2.注意while(l<r),这样循环结束时l==r
         */
        int l = 0, r = data.length;
        //在data[l,r]中查找
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (data[mid].compareTo(target) <= 0) {
                l = mid + 1;
            } else if (data[mid].compareTo(target) > 0) {
                //arr[mid]也有可能是答案，所以不能更新为r=mid-1
                r = mid;
            }
        }
        return l;
    }

    private static <E extends Comparable<E>> int searchR(E[] data, int l, int r, E target) {
        if (l > r) return -1;
        int mid = l + (r - l) / 2;
        if (data[mid].compareTo(target) == 0) {
            return mid;
        }
        if (data[mid].compareTo(target) < 0) {
            return searchR(data, mid + 1, r, target);
        }
        return searchR(data, l, mid - 1, target);
    }


}
