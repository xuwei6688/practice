package com.xu.graph2.path;

import com.xu.graph2.*;
import com.xu.list.ArrayList;
import com.xu.list.List;
import org.omg.CORBA.FREE_MEM;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Stack;


public class BellmanFord {
     private Graph G;
     private int s;
     private Weight[] distTo;
     private Edge[] from;
     private boolean hasNegativeCycle;

    public BellmanFord(Graph g, int s) {
        G = g;
        this.s = s;

        distTo = new Weight[G.V()];
        from = new Edge[G.V()];

        distTo[s] = new Weight();
        for (int pass = 1; pass < G.V(); pass++) {
            for (int i = 0; i < G.V(); i++) {
                Iterator iterator = G.iterator(i);
                for (Edge e = iterator.begin(); !iterator.end(); e = iterator.next()) {
                    if (from[e.w()] == null || distTo[e.v()].getWt() + e.wt().getWt() < distTo[e.w()].getWt()) {
                        distTo[e.w()] = new Weight(distTo[e.v()].getWt() + e.wt().getWt());
                        from[e.w()] = e;
                    }
                }
            }
        }

        hasNegativeCycle = detectNegativeCycle();
    }

    private boolean detectNegativeCycle() {
        for (int i = 0; i < G.V(); i++) {
            Iterator iterator = G.iterator(i);
            for (Edge e = iterator.begin(); !iterator.end(); e = iterator.next()) {
                if (from[e.w()] == null || distTo[e.v()].getWt() + e.wt().getWt() < distTo[e.w()].getWt()) {
                    return true;
                }
            }
        }

        return false;
    }

    public Weight shortestPathTo(int w) {
        if (hasNegativeCycle) {
            throw new IllegalStateException();
        }
        return distTo[w];
    }

    public boolean hasPathTo(int w) {
        return from[w] != null;
    }

    public com.xu.list.List<Edge> shortestPath(int w) {
        if (hasNegativeCycle) {
            throw new IllegalStateException();
        }
        com.xu.list.List<Edge> result = new ArrayList<>();

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
        Path path = Paths.get("/Users/xuwei/IdeaProjects/practice/dataStructure/src/main/java/com/xu/graph2/path/graph2.txt");
        java.util.List<String> lines = Files.readAllLines(path);


        String[] split = lines.get(0).split("\\s+");
        int v = Integer.parseInt(split[0]);
        Graph graph =  new DenseGraph(v, true);

        for (int i = 1; i < lines.size(); i++) {
            String[] line = lines.get(i).split("\\s+");
            graph.addEdge(Integer.parseInt(line[0]), Integer.parseInt(line[1]), new Weight(Double.parseDouble(line[2])));
        }

        BellmanFord bellmanFord = new BellmanFord(graph, 0);
        for (int i = 1; i < graph.V(); i++) {
            System.out.println(String.format("Shortest Patho to %s : %s", i, bellmanFord.shortestPathTo(i)));
            System.out.println(bellmanFord.showShortestPath(i));
        }
    }
}
