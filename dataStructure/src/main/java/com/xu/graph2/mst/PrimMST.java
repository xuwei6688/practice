package com.xu.graph2.mst;

import com.xu.graph2.*;
import com.xu.heap.MinIndexHeap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PrimMST {
    private Graph G;
    //记录阵营中到达节点最短的边的权重,例如ipq[2]表示该阵营到达2这个节点权重最短的边的权重
    private MinIndexHeap<Weight> ipq;
    //记录阵营中到达节点最短的边，和ipq相对应
    private Edge[] edgeTo;
    private boolean []marked;//标记同一阵营
    private List<Edge> mst;//最小生成树
    private Weight mstWeight;


    private void visit(int v) {
        if (marked[v]) {
            throw new IllegalArgumentException();
        }

        marked[v] = true;


        Iterator iterator = G.iterator(v);
        for (Edge edge = iterator.begin(); !iterator.end(); edge =iterator.next()) {
            int w = edge.other(v);
            if (!marked[w]) {
                //到达该节点的边还没记录过，直接记录下来；否则比较边和记录的边的权重，如果小于记录的边的权重，将记录的边替换为这次的这个边。
                if (edgeTo[w] == null) {
                    edgeTo[w] = edge;
                    ipq.insert(w, edge.wt());
                } else if (edge.wt().compareTo(edgeTo[w].wt()) < 0) {
                    edgeTo[w] = edge;
                    ipq.change(w, edge.wt());
                }
            }
        }
    }

    public PrimMST(Graph g) {
        G = g;

        marked = new boolean[G.V()];
        ipq = new MinIndexHeap<>(G.V());
        mst = new ArrayList<>();
        edgeTo = new Edge[G.V()];

        visit(0);


        while (!ipq.isEmpty()) {
            int v = ipq.extractMinIndex();
            if (edgeTo[v] == null) {
                throw new IllegalStateException();
            }

            mst.add(edgeTo[v]);
            visit(v);
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
        List<String> lines = Files.readAllLines(path);

        String[] split = lines.get(0).split("\\s+");
        int v = Integer.parseInt(split[0]);
//        Graph graph = new DenseGraph(v, false);
        Graph graph = new DenseGraph(v, false);

        for (int i = 1; i < lines.size(); i++) {
            String[] line = lines.get(i).split("\\s+");
            graph.addEdge(Integer.parseInt(line[0]), Integer.parseInt(line[1]), new Weight(Double.parseDouble(line[2])));
        }

        PrimMST lazyPrimMST = new PrimMST(graph);

        for (Edge edge : lazyPrimMST.getMst()) {
            System.out.println(edge.w() + "-" + edge.v() + ":" + edge.wt().getWt());
        }

        System.out.println(lazyPrimMST.getMstWeight().getWt());
    }
}
