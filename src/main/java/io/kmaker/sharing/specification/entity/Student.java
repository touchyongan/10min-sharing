package io.kmaker.sharing.specification.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Generated;

import java.util.List;


@Table(name = "students")
@Entity
@Getter
@Setter
public class Student {

    @Id
    @Generated
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @OneToMany(mappedBy = "student")
    private List<StudentCourse> studentCourses;
}
