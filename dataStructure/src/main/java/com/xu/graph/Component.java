package com.xu.graph;

public class Component {
    private Graph G;//图
    private boolean []visited;//记录节点是否被访问过
    private int cCount; //联通分量
    private int []id;//记录每个顶点对应的联通分量

    public Component(Graph g) {
        G = g;
        this.visited = new boolean[G.V()];
        this.cCount = 0;
        this.id = new int[G.V()];

        for (int i = 0; i < G.V(); i++) {
            id[i] = -1;
        }

        for (int i = 0; i < G.V(); i++) {
            if (!visited[i]) {
                dfs(i);
                //每次深度优先遍历后，相连着的节点都会被遍历到，联通分量+1
                cCount++;
            }
        }
    }

    public int count() {
        return cCount;
    }

    public boolean isConnected(int v, int w) {
        if (v < 0 && v >= G.V()) {
            throw new IllegalArgumentException();
        }
        if (w < 0 && w >= G.V()) {
            throw new IllegalArgumentException();
        }
        return id[v] == id[w];
    }

    private void dfs(int v) {
        visited[v] = true;
        System.out.print(v + " ");
        id[v] = cCount;

        Iterator iterator = G.iterator(v);
        for (int i = iterator.begin(); !iterator.end(); i = iterator.next()) {
            if (!visited[i]) {
                dfs(i);
            }
        }
    }
}
