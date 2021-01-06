package com.lsz.unionfind;

/**
 * Quick Find
 * 基于数组，了解就行，没啥用，因为虽然Find很快，但是Union太慢
 */
@Deprecated
public class UnionFindVersionI implements UF {

    /**
     * i是元素，id[i]是元素所属的集合编号
     * i:    0,1,2,3,4,5,6,7,8,9
     * id[i]:0,1,0,1,0,1,0,1,0,1
     */
    private int[] id;

    public UnionFindVersionI(int size) {
        id = new int[size];
        for (int i = 0; i < size; i++) {
            id[i] = i;
        }
    }

    @Override
    public boolean isConnected(int p, int q) {
        //O(1)
        return find(p) == find(q);
    }

    @Override
    public int getSize() {
        return id.length;
    }

    @Override
    public void unionElements(int p, int q) {
        //O(N)
        int pID = find(p);
        int qID = find(q);
        if (pID != qID) {
            for (int i = 0; i < id.length; i++) {
                if (id[i] == pID) {
                    id[i] = qID;
                }
            }
        }
    }

    //查找元素p对应的集合编号
    private int find(int p) {
        return id[p];
    }
}
