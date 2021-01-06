package com.lsz.unionfind;

/**
 * Quick Union
 * 基于数组，但是形成了树结构,孩子指向父亲
 */
public class UnionFindVersionII implements UF {

    /**
     * i是元素，parent[i]是元素的父节点
     * i:        0,1,2,3,4,5,6,7,8,9
     * parent[i]:1,1,1,8,3,0,5,1,8,8
     */
    private int[] parent;

    public UnionFindVersionII(int size) {
        parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
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
            parent[pRoot] = qRoot;
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
