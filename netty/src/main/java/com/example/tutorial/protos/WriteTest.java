package com.example.tutorial.protos;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteTest {
    public static void main(String[] args) throws IOException {

        Person person =  Person.newBuilder()
                .setName("令狐冲").setId(1).setEmail("linghu@gmail.com").build();

        AddressBook.Builder builder = AddressBook.newBuilder();
        AddressBook addressBook = builder.addPeople(person).build();
        addressBook.writeTo(new FileOutputStream("addressBook.bak"));

    }
}
