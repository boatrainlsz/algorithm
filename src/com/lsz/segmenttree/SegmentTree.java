package com.lsz.segmenttree;

public class SegmentTree<E> {
    /**
     * arr的副本
     */
    public E[] data;
    /**
     * 区间树
     */
    public E[] tree;
    public Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger) {
        this.merger = merger;
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        //4倍空间
        tree = (E[]) new Object[arr.length * 4];
        //开始组织线段树
        buildTree(0, 0, data.length - 1);
    }

    /**
     * 在tree[treeIndex]创建表示data[l,r]的线段树
     *
     * @param treeIndex
     * @param l
     * @param r
     */
    private void buildTree(int treeIndex, int l, int r) {
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        int mid = l + (r - l) / 2;
        buildTree(leftTreeIndex, l, mid);
        buildTree(rightTreeIndex, mid + 1, r);
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    public E get(int index) {
        return data[index];
    }

    /**
     * 查询data[queryR,queryR]的区间结果
     *
     * @param queryL
     * @param queryR
     * @return
     */
    public E query(int queryL, int queryR) {
        return query(0, 0, data.length - 1, queryL, queryR);
    }

    /**
     * 在tree[treeIndex]为根的线段树中，它存储了data[l,r]的数据，查找区间[queryL,queryR]的值
     *
     * @param treeIndex
     * @param l
     * @param r
     * @param queryL
     * @param queryR
     * @return
     */
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        if (l == queryL && r == queryR) {
            return tree[treeIndex];
        }
        int mid = l + (r - l) / 2;
        int leftChildIndex = leftChild(treeIndex);
        int rightChildIndex = rightChild(treeIndex);
        if (queryL >= mid + 1) {
            return query(rightChildIndex, mid + 1, r, queryL, queryR);
        } else if (queryR <= mid) {
            return query(leftChildIndex, l, mid, queryL, queryR);
        }
        //左子树存储了data[l,mid]
        E left = query(leftChildIndex, l, mid, queryL, mid);
        //右子树存储了data[mid+1,r]
        E right = query(rightChildIndex, mid + 1, r, mid + 1, queryR);
        return merger.merge(left, right);
    }

    public int getSize() {
        return data.length;
    }

    /**
     * 返回完全二叉树的数组表示中，左孩子的索引
     *
     * @param index
     * @return
     */
    private int leftChild(int index) {
        return 2 * index + 1;
    }


    /**
     * 返回完全二叉树的数组表示中，右孩子的索引
     *
     * @param index
     * @return
     */
    private int rightChild(int index) {
        return 2 * index + 2;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null) {
                res.append(tree[i]);
            } else {
                res.append("null");
            }
            if (i != tree.length - 1) {
                res.append("->");
            }
        }
        return res.toString();
    }
}
