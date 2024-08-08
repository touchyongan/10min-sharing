package io.kmaker.sharing.specification.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Generated;

import java.util.List;

@Table(name = "courses")
@Entity
@Getter
@Setter
public class Course {
    @Id
    @Generated
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "course")
    private List<StudentCourse> studentCourses;
}
