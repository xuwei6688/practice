package com.xu;

import com.xu.stack.ArrayStack;
import com.xu.stack.Stack;
import org.junit.Test;

import static org.junit.Assert.*;

public class StackTest {
    @Test
    public void test1() {
        Stack<String> stack = new ArrayStack<>(2);
        stack.push("a");
        stack.push("b");
        stack.push("c");

        assertEquals("c", stack.peek());
        assertEquals("c", stack.pop());

        assertEquals(2, stack.size());
        assertEquals("b", stack.pop());
        assertFalse(stack.isEmpty());
        assertEquals("a", stack.pop());
        assertTrue(stack.isEmpty());
    }
}
