package com.xu.nio;


import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * ByteBuffer 维护了四个属性
 * capacity：容量，底层数组大小
 * position：当前位置，当前读写的索引
 * limit：限制位置，当前
 * mark：标记位，这个编程过程中比较少用到
 */
public class ByteBufferTest {
    @Test
    public void test1() {
        String str = "abcde";
        System.out.println("---------------分配缓冲区---------------");
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

        System.out.println("--------------写---------------");
        buffer.put(str.getBytes());
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

        System.out.println("---------------flip()---------------");
        buffer.flip();
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

        System.out.println("---------------get()---------------");
        byte[] bytes = new byte[str.length()];
        buffer.get(bytes, 0, str.length());
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());
        System.out.println(new String(bytes));

        System.out.println("---------------rewind()---------------");
        buffer.rewind();
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());
        System.out.println(new String(bytes));

        System.out.println("-----------------clear()----------------");
        buffer.clear();
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

        System.out.println((char)buffer.get());
    }
}
