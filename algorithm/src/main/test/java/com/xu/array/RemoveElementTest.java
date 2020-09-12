package com.xu.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author xuwei
 * @Date 2020/9/12 0012
 * @Version V1.0
 **/
public class RemoveElementTest {
    @Test
    public void test() {
        int[] nums = {3, 2, 2, 3};
        RemoveElement removeElement = new RemoveElement();
        int lenght1 = removeElement.removeElement(nums, 3);
        List<Integer> list1 = Arrays.stream(nums).boxed().collect(Collectors.toList());
        System.out.println(list1);
        System.out.println(lenght1);


    }

    @Test
    public void test2() {
        int[] nums = {0,1,2,2,3,0,4,2};
        RemoveElement removeElement = new RemoveElement();
        int lenght1 = removeElement.removeElement(nums, 2);
        List<Integer> list1 = Arrays.stream(nums).boxed().collect(Collectors.toList());
        System.out.println(list1);
        System.out.println(lenght1);
    }
}
