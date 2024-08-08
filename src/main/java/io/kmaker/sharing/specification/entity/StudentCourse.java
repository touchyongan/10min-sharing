package io.kmaker.sharing.specification.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "student_course")
@Entity
@Getter
@Setter
public class StudentCourse {

    @EmbeddedId
    private StudentCourseId studentCourseId;

    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false, referencedColumnName = "id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false, referencedColumnName = "id")
    private Course course;

}
