package com.xu.graph2.path;

import com.xu.graph2.DenseGraph;
import com.xu.graph2.Edge;
import com.xu.graph2.Graph;
import com.xu.graph2.Iterator;
import com.xu.graph2.Weight;
import com.xu.heap.MinIndexHeap;
import com.xu.list.ArrayList;
import com.xu.list.List;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Stack;

public class Dikstra {
    private Graph G;
    private int s;
    private Weight[] distTo;
    private Edge[] from;
    private boolean[] marked;


    public Dikstra(Graph g, int s) {
       this.G = g;
       this.s = s;

        distTo = new Weight[G.V()];
        from = new Edge[G.V()];
        marked = new boolean[G.V()];

        MinIndexHeap<Weight> ipq = new MinIndexHeap<>(G.V());
        ipq.insert(0, new Weight());
        marked[0] = true;
        distTo[0] = new Weight();

        while (!ipq.isEmpty()) {
            int v = ipq.extractMinIndex();
            marked[v] = true;

            Iterator iterator = G.iterator(v);
            for (Edge e = iterator.begin(); !iterator.end(); e = iterator.next()) {
                int w = e.other(v);
                if (!marked[w]) {
                    if (from[w] == null || distTo[v].getWt() + e.wt().getWt() < distTo[w].getWt()) {
                        from[w] = e;
                        distTo[w] = new Weight(distTo[v].getWt() + e.wt().getWt());

                        if (ipq.contains(w)) {
                            ipq.change(w, distTo[w]);
                        }else {
                            ipq.insert(w, distTo[w]);
                        }
                    }
                }
            }
        }
    }

    public Weight shortestPathTo(int w) {
        return distTo[w];
    }

    public boolean hasPathTo(int w) {
        return marked[w];
    }

    public List<Edge> shortestPath(int w) {
        List<Edge> result = new ArrayList<>();

        Stack<Edge> stack = new Stack<>();
        Edge edge = from[w];
        while (edge != null) {
            stack.push(edge);
            edge = from[edge.v()];
        }

        while (!stack.isEmpty()) {
            edge = stack.pop();
            result.add(edge);
        }
        return result;
    }

    public String showShortestPath(int w) {
        StringBuilder sb = new StringBuilder();

        List<Edge> list = shortestPath(w);
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i).v());
            sb.append(" -> ");
        }
        return sb.toString() + w;
    }

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("/Users/xuwei/IdeaProjects/practice/dataStructure/src/main/java/com/xu/graph2/path/graph.txt");
        java.util.List<String> lines = Files.readAllLines(path);


        String[] split = lines.get(0).split("\\s+");
        int v = Integer.parseInt(split[0]);
        Graph graph =  new DenseGraph(v, false);

        for (int i = 1; i < lines.size(); i++) {
            String[] line = lines.get(i).split("\\s+");
            graph.addEdge(Integer.parseInt(line[0]), Integer.parseInt(line[1]), new Weight(Double.parseDouble(line[2])));
        }

        Dikstra dikstra = new Dikstra(graph, 0);
        for (int i = 1; i < graph.V(); i++) {
            System.out.println(String.format("Shortest Patho to %s : %s", i, dikstra.shortestPathTo(i)));
            System.out.println(dikstra.showShortestPath(i));
        }
    }
}
