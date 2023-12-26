package com.example.somecode.reflect;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Person {
    private String id;
    private int age;

    public void eat() {
        System.out.println("person eat...");
    }
}
