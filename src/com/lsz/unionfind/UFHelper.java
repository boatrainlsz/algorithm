package com.lsz.unionfind;

import java.util.Random;

public class UFHelper {
    public static void main(String[] args) {
        int size = 100000;
        int m = 100000;
        UnionFindVersionI uf1 = new UnionFindVersionI(size);
        UnionFindVersionII uf2 = new UnionFindVersionII(size);
        UnionFindVersionIII uf3 = new UnionFindVersionIII(size);
        System.out.println("uf1：" + testUF(uf1, m) + " s");
        System.out.println("uf2：" + testUF(uf2, m) + " s");
        System.out.println("uf3：" + testUF(uf3, m) + " s");
    }

    private static double testUF(UF uf, int m) {
        int size = uf.getSize();
        Random random = new Random();
        long startTime = System.nanoTime();
        for (int i = 0; i < m; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.unionElements(a, b);
        }
        for (int i = 0; i < m; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.isConnected(a, b);
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1e9;
    }
}
