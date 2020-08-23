package com.xu;

import com.xu.heap.MaxHeap;
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
}
