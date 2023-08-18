package com.xu.graph;

import com.xu.list.ArrayList;
import com.xu.list.List;

import java.util.Arrays;
import java.util.Random;

public  class DenseGraph {
    private int n;//点数
    private int m;//边数
    private boolean directed;//是否是有向图
    private boolean [][]g;

    public DenseGraph(int n, boolean directed) {
        this.n = n;
        this.m = 0;
        this.directed = directed;
        g = new boolean[n][];

        for (int i = 0; i < n; i++) {
            boolean []r = new boolean[n];
            Arrays.fill(r, false);
            g[i] = r;
        }
    }

    //返回点个数
    public int V() {
        return n;
    }
    //返回边个数
    public int E(){
        return m;
    }

    public void addEdge(int v, int w) {
        if (v < 0 && v >= n) {
            throw new IllegalArgumentException();
        }
        if (w < 0 && w >= n) {
            throw new IllegalArgumentException();
        }

        if (hasEdge(v, w)) {
            return;
        }

        g[v][w] = true;
        if (!directed) {//无向图可以看做两个方向都有边的有向图
            g[v][w] = true;
        }
        m++;
    }

    boolean hasEdge(int v, int w) {
        if (v < 0 && v >= n) {
            throw new IllegalArgumentException();
        }
        if (w < 0 && w >= n) {
            throw new IllegalArgumentException();
        }
        return g[v][w];
    }

   public static class AdjIterator{
        private DenseGraph G;//当前迭代的稠密图
        private int v;  //遍历的点
        private int index;//迭代位置

        public AdjIterator(DenseGraph g, int v) {
            G = g;
            this.v = v;
            this.index = -1;
        }

        public int begin() {
            index = -1;
            return next();
        }

        public int next() {
            for (index += 1; index < G.V(); index++) {
                if (G.g[v][index]) {
                    return index;
                }
            }
            return -1;
        }

        public boolean end() {
            return index >= G.V();
        }
    }

    public static void main(String[] args) {
        int N = 20;//点个数
        int M = 100;//边个数
        DenseGraph denseGraph = new DenseGraph(N, false);
        for (int i = 0; i < M; i++) {
            int a = new Random().nextInt(20);
            int b = new Random().nextInt(20);
            denseGraph.addEdge(a, b);
        }

        for (int v = 0; v < N; v++) {
            System.out.print(v + ":");
            AdjIterator iterator = new AdjIterator(denseGraph, v);
            for (int w = iterator.begin(); !iterator.end(); w = iterator.next()) {
                System.out.print(w + " ");
            }
            System.out.println();
        }
    }
}
