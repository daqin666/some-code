package com.example.somecode.clone;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Classroom implements Cloneable {
    private String roomNum;
    private Student student;

    @Override
    public Classroom clone() {
        try {
            Classroom clone = (Classroom) super.clone();
            clone.student = student.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
