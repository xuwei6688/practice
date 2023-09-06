package com.xu;

import com.xu.graph2.Edge;
import com.xu.graph2.Weight;
import com.xu.heap.MaxHeap;
import com.xu.heap.MaxIndexHeap;
import com.xu.heap.MinHeap;
import com.xu.heap.MinIndexHeap;
import org.junit.Test;

import java.util.Random;

/**
 * @Author xuwei
 * @Date 2020/8/23
 * @Version V1.0
 **/
public class HeapTest {

    /**
     * 验证 add 和 extractMax
     */
    @Test
    public void test() {
        int n = 1000000;
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        Random random = new Random();
        for(int i = 0 ; i < n ; i ++)
            maxHeap.add(random.nextInt(Integer.MAX_VALUE));

        int[] arr = new int[n];
        for(int i = 0 ; i < n ; i ++)
            arr[i] = maxHeap.extractMax();

        for(int i = 1 ; i < n ; i ++)
            if(arr[i-1] < arr[i])
                throw new IllegalArgumentException("Error");

    }

    /**
     * 验证 replace
     */
    @Test
    public void test2() {
        int n = 1000000;
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        Random random = new Random();
        for(int i = 0 ; i < n ; i ++)
            maxHeap.add(random.nextInt(Integer.MAX_VALUE));

        maxHeap.replace(10);
        int[] arr = new int[n];
        for(int i = 0 ; i < n ; i ++)
            arr[i] = maxHeap.extractMax();

        for(int i = 1 ; i < n ; i ++)
            if(arr[i-1] < arr[i])
                throw new IllegalArgumentException("Error");
    }

    /**
     * 测试heapify
     */
    @Test
    public void test3() {
        int n = 2;
        Random random = new Random();
        Integer[] array = new Integer[n];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(Integer.MAX_VALUE);
        }

        Integer[] arr = new Integer[n];
        MaxHeap<Integer> maxHeap = new MaxHeap<>(array);
        for(int i = 0 ; i < n ; i ++)
            arr[i] = maxHeap.extractMax();

        for(int i = 1 ; i < n ; i ++)
            if(arr[i-1] < arr[i])
                throw new IllegalArgumentException("Error");
     }

    @Test
    public void testMinHeap() {
        int n = 1000000;
        MinHeap<Integer> minHeap = new MinHeap<>(1000000);
        Random random = new Random();
        for(int i = 0 ; i < n ; i ++)
            minHeap.add(random.nextInt(Integer.MAX_VALUE));

        int[] arr = new int[n];
        for(int i = 0 ; i < n ; i ++)
            arr[i] = minHeap.extractMin();

        for(int i = 1 ; i < n ; i ++)
            if(arr[i-1] > arr[i])
                throw new IllegalArgumentException("Error");
    }

    @Test
    public void testMinHeap2() {
        int n = 10000;
        MinHeap<Edge> minHeap = new MinHeap<>(n);
        Random random = new Random();
        for(int i = 0 ; i < n ; i ++)
            minHeap.add(new Edge(1,2, new Weight(random.nextDouble())));

        double[] arr = new double[n];
        for(int i = 0 ; i < n ; i ++)
            arr[i] = minHeap.extractMin().wt().getWt();

        for(int i = 1 ; i < n ; i ++)
            if(arr[i-1] > arr[i])
                throw new IllegalArgumentException("Error");
    }

    @Test
    public void testMaxIndexHeap() {
        int n = 1000000;
        MaxIndexHeap<Integer> maxIndexHeap = new MaxIndexHeap<>(n);
        Random random = new Random();
        for(int i = 0 ; i < n ; i ++)
            maxIndexHeap.add(random.nextInt(Integer.MAX_VALUE));

        int[] arr = new int[n];
        for(int i = 0 ; i < n ; i ++)
            arr[i] = maxIndexHeap.extractMax();

        for(int i = 1 ; i < n ; i ++)
            if(arr[i-1] < arr[i])
                throw new IllegalArgumentException("Error");
    }

    @Test
    public void testMaxIndexHeapChange() {
        int n = 100000;
        MaxIndexHeap<Integer> maxIndexHeap = new MaxIndexHeap<>(n);
        Random random = new Random();
        for(int i = 0 ; i < n ; i ++)
            maxIndexHeap.add(random.nextInt(Integer.MAX_VALUE));

        maxIndexHeap.change(100, 1);

        int[] arr = new int[n];
        for(int i = 0 ; i < n ; i ++)
            arr[i] = maxIndexHeap.extractMax();

        for(int i = 1 ; i < n ; i ++)
            if(arr[i-1] < arr[i])
                throw new IllegalArgumentException("Error");

    }

    @Test
    public void testMinIndexHeapChange() {
        int n = 100000;
        MinIndexHeap<Integer> minIndexHeap = new MinIndexHeap<>(n);
        Random random = new Random();
        for(int i = 0 ; i < n ; i ++)
            minIndexHeap.add(random.nextInt(Integer.MAX_VALUE));

        minIndexHeap.change(100, 1);

        int[] arr = new int[n];
        for(int i = 0 ; i < n ; i ++)
            arr[i] = minIndexHeap.extractMin();

        for(int i = 1 ; i < n ; i ++)
            if(arr[i-1] > arr[i])
                throw new IllegalArgumentException("Error");
    }


    @Test
    public void testMinIndexHeap2() {
        int n = 10;
        MinIndexHeap<Integer> minIndexHeap = new MinIndexHeap<>(n);
        Random random = new Random();
        for(int i = 0 ; i < n ; i ++)
            minIndexHeap.insert(9-i, random.nextInt(100));

        int[] arr = new int[n];
        for(int i = 0 ; i < n ; i ++)
            arr[i] = minIndexHeap.extractMin();

        for(int i = 1 ; i < n ; i ++)
            if(arr[i-1] > arr[i])
                throw new IllegalArgumentException("Error");
    }
}
