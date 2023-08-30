package com.xu.graph2.mst;

import com.xu.graph2.*;
import com.xu.heap.MinHeap;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LazyPrimMST {
    private Graph G;
    private MinHeap<Edge> pq;
    private boolean []marked;//标记同一阵营
    private List<Edge> mst;//最小生成树
    private Weight mstWeight;


    private void visited(int v) {
        if (marked[v]) {
            throw new IllegalArgumentException();
        }

        marked[v] = true;

        //遍历节点v的所有临边，如果临边另一个顶点没有访问过，将临边加入到小顶堆中
        Iterator iterator = G.iterator(v);
        for (Edge edge = iterator.begin(); !iterator.end(); edge =iterator.next()) {
            if (!marked[edge.other(v)]) {
                pq.add(edge);
            }
        }
    }

    public LazyPrimMST(Graph g) {
        G = g;

        marked = new boolean[G.V()];
        pq = new MinHeap<>(G.V() - 1);
        mst = new ArrayList<>();

        visited(0);

        //每次从最小堆中取出权重最小的边，如果边的两个顶点不在同一阵营，将边加入到最小生成树中
        //然后访问下一个节点
        while (!pq.isEmpty()) {
            Edge edge = pq.extractMin();
            if (marked[edge.v()] == marked[edge.w()]) {
                continue;
            }

            mst.add(edge);
            //访问另一个边
            if (!marked[edge.w()]) {
                visited(edge.w());
            }else {
                visited(edge.v());
            }
        }

        double sum = mst.stream().mapToDouble(edge -> edge.wt().getWt()).sum();
        mstWeight = new Weight(sum);
    }

    public Weight getMstWeight() {
        return mstWeight;
    }

    public List<Edge> getMst() {
        return mst;
    }

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("/Users/xuwei/IdeaProjects/practice/dataStructure/src/main/java/com/xu/graph2/graph1.txt");
        java.util.List<String> lines = Files.readAllLines(path);

        String[] split = lines.get(0).split("\\s+");
        int v = Integer.parseInt(split[0]);
//        Graph graph = new DenseGraph(v, false);
        Graph graph = new DenseGraph(v, false);

        for (int i = 1; i < lines.size(); i++) {
            String[] line = lines.get(i).split("\\s+");
            graph.addEdge(Integer.parseInt(line[0]), Integer.parseInt(line[1]), new Weight(Double.parseDouble(line[2])));
        }

        LazyPrimMST lazyPrimMST = new LazyPrimMST(graph);

        for (Edge edge : lazyPrimMST.getMst()) {
            System.out.println(edge.w() + "-" + edge.v() + ":" + edge.wt().getWt());
        }

        System.out.println(lazyPrimMST.getMstWeight().getWt());
    }
}
