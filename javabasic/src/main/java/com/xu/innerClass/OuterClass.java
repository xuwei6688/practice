package com.xu.innerClass;

/**
 * @Author xuwei
 * @Date 2020/11/7
 * @Version V1.0
 **/
public class OuterClass {
    static class Pair {
      private int first;
      private int second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        public int getFirst() {
            return first;
        }

        public void setFirst(int first) {
            this.first = first;
        }

        public int getSecond() {
            return second;
        }

        public void setSecond(int second) {
            this.second = second;
        }
    }

    public Pair minmax(int[] array) {
        Pair pair = new Pair(array[0], array[1]);
        return pair;
    }
}
