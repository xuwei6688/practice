package com.xu.graph;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("/Users/xuwei/IdeaProjects/practice/dataStructure/src/main/java/com/xu/graph/graph1.txt");
        List<String> lines = Files.readAllLines(path);


        String[] split = lines.get(0).split("\\s+");
        int v = Integer.parseInt(split[0]);
        Graph graph = new DenseGraph(v, false);

        for (int i = 1; i < lines.size(); i++) {
            String[] line = lines.get(i).split("\\s+");
            graph.addEdge(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
        }

        Component component = new Component(graph);

        System.out.println(component.count());
        System.out.println(component.isConnected(0,2));
        System.out.println(component.isConnected(0,8));
        System.out.println(component.isConnected(0,9));
        System.out.println(component.isConnected(8,7));
        System.out.println(component.isConnected(12,9));
    }
}
