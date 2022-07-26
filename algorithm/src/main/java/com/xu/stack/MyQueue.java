package com.xu.stack;

import java.util.Stack;

/**
 * @Author xuwei
 * @Date 2021/2/22
 * @Version V1.0
 **/
class MyQueue {
    private Stack<Integer> inputStack;
    private Stack<Integer> outputStack;

    /** Initialize your data structure here. */
    public MyQueue() {
        inputStack = new Stack<>();
        outputStack = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        inputStack.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        transfer();
        return outputStack.pop();
    }

    /** Get the front element. */
    public int peek() {
        transfer();
        return outputStack.peek();
    }

    public void transfer() {
        if (outputStack.isEmpty()) {
            while (!inputStack.isEmpty()) {
                outputStack.push(inputStack.pop());
            }
        }
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return inputStack.isEmpty() && outputStack.isEmpty();
    }
}