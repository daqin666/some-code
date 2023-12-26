package com.example.somecode.io;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
public class Person implements Serializable {
    private String name;
    private int age;
}
