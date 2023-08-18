package com.xu.graph;

import com.xu.list.ArrayList;
import com.xu.list.List;

import java.util.Random;

public class SparseGraph {
    private int n;//顶点个数
    private int m;//边个数
    private boolean directed;//是否是有向图
    private List<List<Integer>> g;

    public SparseGraph(int n, boolean directed) {
        this.n = n;
        this.m = 0;
        this.directed = directed;
        g = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            g.add(new ArrayList<>());
        }
    }

    //返回点个数
    public int V() {
        return m;
    }
    //返回边个数
    public int E(){
        return n;
    }

    public void addEdge(int v, int w) {
        if (v < 0 && v >= m) {
            throw new IllegalArgumentException();
        }
        if (w < 0 && w >= m) {
            throw new IllegalArgumentException();
        }

        g.get(v).add(w);
        if (v != w && !directed) {
            g.get(w).add(v);
        }
        m++;
    }

    public static class SpaIterator{
        private SparseGraph G;
        private int v;
        private int index;

        public SpaIterator(SparseGraph G, int v) {
            this.G = G;
            this.v = v;
            this.index = 0;
        }

        public int begin() {
            index = 0;
            if (!G.g.get(v).isEmpty()) {
                return G.g.get(v).get(index);
            }
            return -1;
        }

        public int next() {
            index++;
            if (index < G.g.get(v).size()) {
                return G.g.get(v).get(index);
            }
            return -1;
        }

        public boolean end() {
            return index >= G.g.get(v).size();
        }
    }

    public static void main(String[] args) {
        int N = 20;//点个数
        int M = 100;//边个数
        SparseGraph graph = new SparseGraph(N, false);
        for (int i = 0; i < M; i++) {
            int a = new Random().nextInt(20);
            int b = new Random().nextInt(20);
            graph.addEdge(a, b);
        }

        for (int v = 0; v < N; v++) {
            System.out.print(v + ":");
            SparseGraph.SpaIterator iterator = new SparseGraph.SpaIterator(graph, v);
            for (int w = iterator.begin(); !iterator.end(); w = iterator.next()) {
                System.out.print(w + " ");
            }
            System.out.println();
        }
        System.out.println("hhh");
    }
}
