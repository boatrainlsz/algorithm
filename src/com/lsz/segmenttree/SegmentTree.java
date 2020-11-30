package com.lsz.segmenttree;

import java.security.interfaces.RSAKey;

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
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        //4倍空间
        tree = (E[]) new Object[arr.length * 4];
        //开始组织线段树
        buildTree(0, 0, data.length - 1);
        this.merger = merger;
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
