package com.xu.graph;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ShortestPath {
    private Graph G;
    private boolean []visited;
    private int source;//起点
    private int []from;//记录每个节点是从哪个节点遍历到的
    private int []ord;//记录每个节点到源节点的路径长度

    public ShortestPath(Graph g, int source) {
        G = g;
        this.visited = new boolean[G.V()];
        this.source = source;
        from = new int[G.V()];
        ord = new int[G.V()];

        for (int i = 0; i < G.V(); i++) {
            from[i] = -1;
            ord[i] = -1;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;
        ord[source] = 0;

        while (!queue.isEmpty()) {
            Integer v = queue.poll();

            Iterator iterator = g.iterator(v);
            for (int i = iterator.begin(); !iterator.end(); i =iterator.next()) {
                if (!visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                    from[i] = v;
                    ord[i] = ord[v] + 1;
                }
            }
        }
    }

    /**
     * 返回 source到w是否有一条路径
     */
    public boolean hasPath(int w) {
        if (w < 0 || w >= G.V()) {
            throw new IllegalArgumentException();
        }

        return visited[w];
    }

    public void showPath(int w) {
        Stack<Integer> stack = new Stack<>();
        int p = w;
        while (p != -1) {
            stack.push(p);
            p = from[p];
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    /**
     * source到w的最短路径长度
     */
    public int length(int w) {
        if (w < 0 || w >= G.V()) {
            throw new IllegalArgumentException();
        }
        return ord[w];
    }


    public static void main(String[] args) throws IOException {
        java.nio.file.Path path = Paths.get("/Users/xuwei/IdeaProjects/practice/dataStructure/src/main/java/com/xu/graph/graph2.txt");
        List<String> lines = Files.readAllLines(path);


        String[] split = lines.get(0).split("\\s+");
        int v = Integer.parseInt(split[0]);
        Graph graph = new SparseGraph(v, false);

        for (int i = 1; i < lines.size(); i++) {
            String[] line = lines.get(i).split("\\s+");
            graph.addEdge(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
        }

        ShortestPath p = new ShortestPath(graph, 0);
        p.showPath(6);
        System.out.println(p.length(6));
        System.out.println(p.length(4));
    }
}
