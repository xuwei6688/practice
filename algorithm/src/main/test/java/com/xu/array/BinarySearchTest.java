package com.xu.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author xuwei
 * @Date 2020/9/12 0012
 * @Version V1.0
 **/
public class BinarySearchTest {
    @Test
    public void test() {
        BinarySearch binarySearch = new BinarySearch();
        int n = 1000;
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = i;
        }

        for (int i = 0; i < n; i++) {
            Assert.assertTrue(i == binarySearch.search(array, i));
        }
    }
}
