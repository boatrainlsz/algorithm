package com.lsz.unionfind;

/**
 * Quick Union
 * based on{@link com.lsz.unionfind.UnionFindVersionII}
 * 修复了合并时出现瘸腿的问题
 */
public class UnionFindVersionIII implements UF {

    /**
     * i是元素，parent[i]是元素的父节点
     * i:        0,1,2,3,4,5,6,7,8,9
     * parent[i]:1,1,1,8,3,0,5,1,8,8
     */
    private int[] parent;

    /**
     * sz[i]：以i为根节点的集合中的元素个数
     */
    private int[] sz;

    public UnionFindVersionIII(int size) {
        parent = new int[size];
        sz = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            sz[i] = 1;
        }
    }

    @Override
    public boolean isConnected(int p, int q) {
        //O(H),H为树的高度
        return find(p) == find(q);
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    @Override
    public void unionElements(int p, int q) {
        //O(H)
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot != qRoot) {
            //优化，要考虑pRoot和qRoot这两棵树谁的元素多，小的加到大的上，避免树瘸腿
            if (sz[pRoot] < sz[qRoot]) {
                //q树大
                parent[pRoot] = qRoot;
                sz[qRoot] += sz[pRoot];
            } else {
                //p树大
                parent[qRoot] = pRoot;
                sz[pRoot] += sz[qRoot];
            }
        }
    }

    //查找元素p所在树的顶层根节点，也就是元素p的的集合编号
    //O(H),H为树的高度
    private int find(int p) {
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }
}
