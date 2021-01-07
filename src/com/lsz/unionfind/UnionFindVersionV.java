package com.lsz.unionfind;

/**
 * Quick Union
 * similar to {@link UnionFindVersionIV}
 * 路径压缩，提高效率 better than UnionFindVersionIV
 * 在查找的时候进行路径压缩
 */
public class UnionFindVersionV implements UF {

    /**
     * i是元素，parent[i]是元素的父节点
     * i:        0,1,2,3,4,5,6,7,8,9
     * parent[i]:1,1,1,8,3,0,5,1,8,8
     */
    private int[] parent;

    /**
     * rank[i]：以i为根节点的树的层数
     */
    private int[] rank;

    public UnionFindVersionV(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;
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
            //优化，要考虑pRoot和qRoot这两棵树谁的层数大，小的加到大的上，避免树瘸腿
            if (rank[pRoot] < rank[qRoot]) {
                //q树更深，注意这里qRoot的层数无需更新！
                parent[pRoot] = qRoot;
            } else if (rank[pRoot] > rank[qRoot]) {
                //p树更深
                parent[qRoot] = pRoot;
            } else {
                //层数相等
                parent[qRoot] = pRoot;
                //注意这里pRoot的层数需要加1
                rank[pRoot] += 1;
            }
        }
    }

    //查找元素p所在树的顶层根节点，也就是元素p的的集合编号
    //O(H),H为树的高度
    private int find(int p) {
        while (p != parent[p]) {
            //优化：路径压缩,p和parent[p]成为同级元素，减小树的深度
            //注意！路径压缩后，树的深度变了，但是不去变更rank值，一是因为得不偿失，性能损耗比较大，二是一个粗略的rank值在合并时也够用了

            //路径压缩算法1：孩子变兄弟
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;

        //路径压缩算法2：孩子变祖先，这个更狠
        //https://www.runoob.com/data-structures/union-find-compress.html
//        if (parent[p] != p) {
//            parent[p] = find(parent[p]);
//        }
//        return parent[p];
    }
}
