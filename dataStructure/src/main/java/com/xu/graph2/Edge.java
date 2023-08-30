package com.xu.graph2;

import java.util.Objects;

public class Edge implements Comparable<Edge>{
    private int a;
    private int b;
    private Weight weight;

    public Edge(int a, int b, Weight weight) {
        this.a = a;
        this.b = b;
        this.weight = weight;
    }

    public int v() {
        return a;
    }

    public int w() {
        return b;
    }

    public Weight wt() {
        return weight;
    }

    public int other(int x) {
        if (x != a && x != b) {
            throw new IllegalArgumentException();
        }

        return x == a ? b : a;
    }

    @Override
    public int compareTo(Edge other) {
        if (weight.getWt() - other.wt().getWt() < 0) {
            return -1;
        }
        if (weight.getWt() - other.wt().getWt() > 0) {
            return 1;
        }
        return 0;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return a == edge.a && b == edge.b && Objects.equals(weight, edge.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, weight);
    }
}
