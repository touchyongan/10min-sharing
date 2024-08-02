package io.kmaker.sharing.collections.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Course {
    private int id;
    private String name;

    public Course() {
    }

    public Course(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
