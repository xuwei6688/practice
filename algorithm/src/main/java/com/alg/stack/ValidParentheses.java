package com.alg.stack;

import java.util.Stack;

public class ValidParentheses {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '}') {
                if (stack.size() == 0 || stack.pop() != '{') return false;
            } else if (c == ']') {
                if (stack.size() == 0 || stack.pop() != '[') return false;
            }else if (c == ')') {
                if (stack.size() == 0 || stack.pop() != '(') return false;
            }else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
}
