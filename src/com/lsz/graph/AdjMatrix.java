package com.lsz.graph;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 邻接矩阵
 */
public class AdjMatrix {

    private int V;
    private int E;
    private int[][] adj;

    public AdjMatrix(String filename) {
        File file = new File(filename);
        try (Scanner scanner = new Scanner(file)) {
            V = scanner.nextInt();
            if (V < 0) throw new Exception("wrong V");
            adj = new int[V][V];
            E = scanner.nextInt();
            if (E < 0) throw new Exception("wrong E");
            for (int i = 0; i < V; i++) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                adj[a][b] = 1;
                adj[b][a] = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        AdjMatrix adjMatrix = new AdjMatrix("C:\\Users\\Amy\\Desktop\\lsz\\algorithm\\src\\com\\lsz\\graph\\a.txt");
        System.out.println(adjMatrix);
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
        return adj[p][q] == 1;
    }

    public ArrayList<Integer> adj(int p) {
        validateVertex(p);
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (adj[p][i] == 1) {
                res.add(i);
            }
        }
        return res;
    }

    public int degree(int p) {
        return adj(p).size();
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
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                sb.append(String.format("%d ", adj[i][j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
