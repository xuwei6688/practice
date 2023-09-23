package com.alg.backtrack;

import java.util.ArrayList;
import java.util.List;

class GenerateParenthesis {
    private List<String> list = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        generate(0, 0, n, "");
        return list;
    }

    private void generate(int left, int right, int n, String s){
        if (left == n && right == n) {
            list.add(s);
            return;
        }

        if (left < n) {
            generate(++left, right, n, s + "(");
        }
        if (right < n && right < left) {
            generate(left, ++right, n, s + ")");
        }
    }

    private void print(int x) {
        System.out.println(x);
    }

    public static void main(String[] args) {
        GenerateParenthesis solution = new GenerateParenthesis();
        List<String> strings = solution.generateParenthesis(3);
        System.out.println(strings);
        int x = 1;
        solution.print(++x);
    }
}