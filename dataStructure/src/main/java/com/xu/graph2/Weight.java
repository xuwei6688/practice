package com.xu.graph2;

import java.util.Objects;

public class Weight implements Comparable<Weight>{
    private double wt;

    public Weight() {
        this.wt = 0d;
    }

    public Weight(double wt) {
        this.wt = wt;
    }

    public double getWt() {
        return wt;
    }

    public void setWt(double wt) {
        this.wt = wt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weight weight = (Weight) o;
        return Double.compare(wt, weight.wt) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(wt);
    }

    @Override
    public String toString() {
       return String.valueOf(wt);
    }

    @Override
    public int compareTo(Weight other) {
        if (wt == other.getWt()) {
            return 0;
        }

        return wt - other.getWt() > 0 ? 1 : -1;
    }
}
