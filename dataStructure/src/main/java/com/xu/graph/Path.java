package com.xu.graph;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Stack;

public class Path {
    private Graph G;
    private boolean []visited;
    private int source;//起点
    private int []from;//记录每个节点是从哪个节点遍历到的

    public Path(Graph g, int source) {
        G = g;
        this.visited = new boolean[G.V()];
        this.source = source;
        from = new int[G.V()];

        for (int i = 0; i < G.V(); i++) {
            from[i] = -1;
        }

        for (int i = source; i < G.V(); i++) {
            if (!visited[i]) {
                dfs(i);
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

    private void dfs(int v) {
        visited[v] = true;

        Iterator iterator = G.iterator(v);
        try {
            for (int i = iterator.begin(); !iterator.end(); i = iterator.next()) {
                if (!visited[i]) {
                    from[i] = v;
                    dfs(i);
                }
            }
        } catch (Exception e) {
            System.out.println("-----");
            System.out.println(v);
            System.out.println("-----");
        }
    }

    public static void main(String[] args) throws IOException {
        java.nio.file.Path path = Paths.get("/Users/xuwei/IdeaProjects/practice/dataStructure/src/main/java/com/xu/graph/graph2.txt");
        List<String> lines = Files.readAllLines(path);


        String[] split = lines.get(0).split("\\s+");
        int v = Integer.parseInt(split[0]);
        Graph graph = new DenseGraph(v, false);

        for (int i = 1; i < lines.size(); i++) {
            String[] line = lines.get(i).split("\\s+");
            graph.addEdge(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
        }

        Path p = new Path(graph, 0);
        p.showPath(6);
    }
}
