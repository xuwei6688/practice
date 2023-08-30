package com.xu.graph2;

import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

public  class DenseGraph implements Graph {
    private int n;//点数
    private int m;//边数
    private boolean directed;//是否是有向图
    private Edge [][]g;

    public DenseGraph(int n, boolean directed) {
        this.n = n;
        this.m = 0;
        this.directed = directed;
        g = new Edge[n][];

        for (int i = 0; i < n; i++) {
            Edge[]r = new Edge[n];
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

    public void addEdge(int v, int w, Weight weight) {
        if (v < 0 && v >= n) {
            throw new IllegalArgumentException();
        }
        if (w < 0 && w >= n) {
            throw new IllegalArgumentException();
        }

        if (hasEdge(v, w)) {
            g[v][w] = null;
            if (!directed) {
                g[w][v] = null;
            }
            m--;
        }

        g[v][w] = new Edge(v, w, weight);
        if (!directed) {//无向图可以看做两个方向都有边的有向图
            g[w][v] = new Edge(w, v, weight);;
        }
        m++;
    }

    @Override
    public Iterator iterator(int v) {
        return new AdjIterator(this, v);
    }

    boolean hasEdge(int v, int w) {
        if (v < 0 && v >= n) {
            throw new IllegalArgumentException();
        }
        if (w < 0 && w >= n) {
            throw new IllegalArgumentException();
        }
        return g[v][w] != null;
    }

   public class AdjIterator implements Iterator {
        private DenseGraph G;//当前迭代的稠密图
        private int v;  //遍历的点
        private int index;//迭代位置

        public AdjIterator(DenseGraph g, int v) {
            G = g;
            this.v = v;
            this.index = -1;
        }

        public Edge begin() {
            index = -1;
            return next();
        }

        public Edge next() {
            for (index += 1; index < G.V(); index++) {
                if (G.g[v][index] != null) {
                    return G.g[v][index];
                }
            }
            return null;
        }

        public boolean end() {
            return index >= G.V();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(Optional.ofNullable(g[i][j]).map(Edge::wt).map(Weight::getWt).orElse(null) + "  ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
