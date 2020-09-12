package com.xu.array;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author xuwei
 * @Date 2020/9/12 0012
 * @Version V1.0
 **/
public class MoveZerosTest {
    @Test
    public void test1() {
        MoveZeroes moveZeroes = new MoveZeroes();
        int[] nums = {0};
        moveZeroes.moveZeroes(nums);
        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
        System.out.println(list);
    }

    @Test
    public void test2() {
        MoveZeroes2 moveZeroes = new MoveZeroes2();
        int[] nums = {0,2,8,0,9};
        moveZeroes.moveZeroes(nums);
        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
        System.out.println(list);
    }
}
