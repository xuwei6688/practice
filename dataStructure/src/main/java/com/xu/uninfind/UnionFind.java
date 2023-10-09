package com.xu.uninfind;

public class UnionFind {
    private int[] roots;


    public UnionFind(int n) {
        roots = new int[n];
        for (int i = 0; i < n; i++) {
            roots[i] = i;
        }
    }

    private int findRoot(int i) {
        int root = i;
        //找到root
        while (root != roots[root]) {
            root = roots[root];
        }
        //一直向上，将所有节点都直接指向root
        while (i != roots[i]) {
            int tmp = roots[i];
            roots[i] = root;
            i = tmp;
        }
        return root;
    }

    public boolean connected(int p, int q) {
        return findRoot(p) == findRoot(q);
    }

    public void union(int p, int q) {
        int pRoot = findRoot(p);
        int qRoot = findRoot(q);
        if (pRoot == qRoot) {
            return;
        }
        roots[pRoot] = qRoot;
    }
}
