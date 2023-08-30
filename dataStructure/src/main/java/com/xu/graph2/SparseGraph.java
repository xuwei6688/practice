package com.xu.graph2;

import com.xu.graph2.Graph;
import com.xu.graph2.Iterator;
import com.xu.list.ArrayList;
import com.xu.list.List;

import java.util.Random;

public class SparseGraph implements Graph {
    private int n;//顶点个数
    private int m;//边个数
    private boolean directed;//是否是有向图
    private List<List<Edge>> g;

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
        return n;
    }
    //返回边个数
    public int E(){
        return m;
    }

    public void addEdge(int v, int w, Weight weight) {
        if (v < 0 && v >= m) {
            throw new IllegalArgumentException();
        }
        if (w < 0 && w >= m) {
            throw new IllegalArgumentException();
        }

        g.get(v).add(new Edge(v, w, weight));
        if (v != w && !directed) {
            g.get(w).add(new Edge(w, v, weight));
        }
        m++;
    }

    public boolean hasEdge(int v, int w) {
        if (v < 0 && v >= n) {
            throw new IllegalArgumentException();
        }
        if (w < 0 && w >= n) {
            throw new IllegalArgumentException();
        }
        //稀疏图，遍历顶点v上所有的边，如果存在另一顶点是w的边，返回true
        for (int i = 0; i < g.get(v).size(); i++) {
            if (g.get(v).get(i).other(v) == w) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator iterator(int v) {
        return new SpaIterator(this, v);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < g.size(); i++) {
            sb.append("vertex ").append(i).append("  ");
            List<Edge> list = g.get(i);
            for (int j = 0; j < list.size(); j++) {
                Edge edge = list.get(j);
                sb.append(String.format("(to:%s,wt:%s) ", edge.other(i),  edge.wt().getWt()));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public class SpaIterator implements Iterator {
        private SparseGraph G;
        private int v;
        private int index;

        public SpaIterator(SparseGraph G, int v) {
            this.G = G;
            this.v = v;
            this.index = 0;
        }

        public Edge begin() {
            index = 0;
            if (!G.g.get(v).isEmpty()) {
                return G.g.get(v).get(index);
            }
            return null;
        }

        public Edge next() {
            index++;
            if (index < G.g.get(v).size()) {
                return G.g.get(v).get(index);
            }
            return null;
        }

        public boolean end() {
            return index >= G.g.get(v).size();
        }
    }
}
