package com.lsz.graph;

import java.io.File;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * 使用treeset实现邻接表
 */
public class AdjSet {
    private int V;
    private int E;
    private TreeSet<Integer>[] adj;

    public AdjSet(String filename) {
        File file = new File(filename);
        try (Scanner scanner = new Scanner(file)) {
            V = scanner.nextInt();
            if (V < 0) throw new Exception("wrong V");
            adj = new TreeSet[V];
            E = scanner.nextInt();
            if (E < 0) throw new Exception("wrong E");
            for (int i = 0; i < V; i++) {
                adj[i] = new TreeSet<Integer>();
            }
            for (int i = 0; i < E; i++) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                adj[a].add(b);
                adj[b].add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        AdjSet adjList = new AdjSet("C:\\Users\\Amy\\Desktop\\lsz\\algorithm\\src\\com\\lsz\\graph\\a.txt");
        System.out.println(adjList);
    }

    public int getV() {
        return V;
    }

    public int getE() {
        return E;
    }

    public boolean hasEdge(int p, int q) {
        validateVertex(p);
        validateVertex(q);
        return adj[p].contains(q);
    }

    public Iterable<Integer> adj(int p) {
        validateVertex(p);
        return adj[p];
    }

    public int degree(int p) {
        return adj[p].size();
    }

    private void validateVertex(int p) {
        if (p < 0 || p > V) {
            throw new RuntimeException("wrong vertex:" + p);
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V=%d,E=%d\n", V, E));
        for (int v = 0; v < V; v++) {
            sb.append(String.format("%d : ", v));
            for (int w : adj[v]) {
                sb.append(String.format("%d ", w));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
