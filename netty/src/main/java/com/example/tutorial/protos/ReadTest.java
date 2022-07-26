package com.example.tutorial.protos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadTest {
    public static void main(String[] args) throws IOException {
        AddressBook addressBook =
                AddressBook.parseFrom(new FileInputStream("addressBook.bak"));
        System.out.println(addressBook.getPeople(0).getName());
        //直接toString汉字是乱码的，使用get方法就不会出现这个问题
        System.out.println(addressBook.toString());
    }
}
