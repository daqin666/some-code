package com.example.somecode.clone;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Student implements Cloneable {
    private int age;
    private String name;

    @Override
    public Student clone() {
        try {
            Student clone = (Student) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
