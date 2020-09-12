package com.xu.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author xuwei
 * @Date 2020/9/12 0012
 * @Version V1.0
 **/
public class SortColorsTest {
    @Test
    public void test() {
        int[] numx = {2, 0, 2, 1, 1, 0};
        SortColors sortColors = new SortColors();
        sortColors.sortColors(numx);
        List<Integer> list = Arrays.stream(numx).boxed().collect(Collectors.toList());
        Assert.assertEquals("[0, 0, 1, 1, 2, 2]", list.toString());
    }

    @Test
    public void test2() {
        int[] numx = {2, 0, 2, 1, 1, 0};
        SortColors2 sortColors = new SortColors2();
        sortColors.sortColors(numx);
        List<Integer> list = Arrays.stream(numx).boxed().collect(Collectors.toList());
        Assert.assertEquals("[0, 0, 1, 1, 2, 2]", list.toString());
    }
}
