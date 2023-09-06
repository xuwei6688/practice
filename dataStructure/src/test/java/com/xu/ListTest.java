package com.xu;

import com.xu.list.ArrayList;
import com.xu.list.LinkedList;
import com.xu.list.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;


public class ListTest {
    public List<String> list;
    @Test
    public void test1() {
        list = new ArrayList<>();
    }

    @Test
    public void test2() {
        list = new LinkedList<>();
    }

//    @After
    public void after() {
        Assert.assertEquals(0, list.size());
        list.add(0, "A");
        list.add(1, "B");
        list.add(2, "C");
        list.add("D");
        list.add("E");
        list.add("F");
        Assert.assertEquals("[A,B,C,D,E,F]", list.toString());
        Assert.assertEquals(6, list.size());

        list.remove(4);
        Assert.assertEquals("[A,B,C,D,F]", list.toString());

        String element = list.remove(3);
        Assert.assertEquals("[A,B,C,F]", list.toString());
        Assert.assertEquals("D", element);

        list.remove(2);
        Assert.assertEquals("[A,B,F]", list.toString());

        list.remove(2);
        Assert.assertEquals("[A,B]", list.toString());
        Assert.assertEquals(2, list.size());

        Assert.assertTrue(list.contains("A"));

        list.remove("B");
        Assert.assertEquals("[A]", list.toString());


        list.add(0, "C");
        Assert.assertEquals("[C,A]", list.toString());
    }


    @After
    public void after2() {
        Assert.assertEquals(0, list.size());
        list.add(6, "A");
        list.add(2, "B");

        Assert.assertEquals("[B,A]", list.toString());
        Assert.assertEquals(2, list.size());
    }
}
