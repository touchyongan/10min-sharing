package io.kmaker.sharing.specification.dto;

import lombok.Data;

import java.util.List;

@Data
public class StudentData {
    private Long id;
    private String name;
    private int age;
    private List<CourseData> courses;
}
