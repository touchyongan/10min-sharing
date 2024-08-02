package io.kmaker.sharing.collections.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Student {
    private int id;
    private String name;
    private List<Course> courses = new ArrayList<>();

    public Student() {
    }

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
