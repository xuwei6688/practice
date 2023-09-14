package com.alg.stack;

import java.util.Stack;

class MyQueue {
    private final Stack<Integer> input;
    private final Stack<Integer> output;

    public MyQueue() {
        input = new Stack<>();
        output = new Stack<>();
    }

    public void push(int x) {


        input.push(x);
    }

    public int pop() {
        transferIfNeed();

        return output.pop();
    }

    public int peek() {
        transferIfNeed();

        return output.peek();
    }

    private void transferIfNeed() {
        if (output.empty()) {
            while (!input.isEmpty()) {
                output.push(input.pop());
            }
        }
    }

    public boolean empty() {
       return input.isEmpty() && output.isEmpty();
    }

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.push(1);
        myQueue.push(2);
        System.out.println(myQueue.peek());
        System.out.println(myQueue.pop());
        System.out.println(myQueue.empty());
    }
}
