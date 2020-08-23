package com.xu;


import com.xu.queue.PriorityQueue;
import com.xu.queue.Queue;
import org.junit.Test;
import static org.junit.Assert.*;

public class QueueTest {
    @Test
    public void test() {
        Queue<Integer> queue = new PriorityQueue<>();
        queue.offer(6);
        queue.offer(5);
        queue.offer(4);
        queue.offer(10);
        queue.offer(8);
        assertEquals(new Integer(10), queue.peek());
        queue.poll();
        assertEquals(new Integer(8), queue.peek());
        queue.poll();
        queue.poll();
        assertEquals(new Integer(5), queue.peek());
        queue.poll();
        queue.poll();
        assertNull(queue.peek());
        assertNull(queue.poll());
    }
}
