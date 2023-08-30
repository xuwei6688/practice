package com.xu.graph2;

public interface Graph {
    /**
     * 返回节点个数
     */
    int V();

    /**
     * 返回边个数
     */
    int E();

    /**
     * 在顶点v->w 增加边
     */
    void addEdge(int v, int w, Weight weight);

    /**
     * 返回顶点 v 的迭代器
     */
    Iterator iterator(int v);
}
