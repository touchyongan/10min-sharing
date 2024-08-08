package io.kmaker.sharing.specification.mapper;

import io.kmaker.sharing.specification.dto.CourseData;
import io.kmaker.sharing.specification.dto.StudentData;
import io.kmaker.sharing.specification.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class StudentMapper {
    @Autowired
    private CourseMapper courseMapper;

    @Mapping(target = "courses", source = "student", qualifiedByName = "mapCourse")
    public abstract StudentData mapToData(Student student);

    @Named("mapCourse")
    public List<CourseData> mapCourse(final Student student) {
        final var list = new ArrayList<CourseData>();
        if (Objects.isNull(student.getStudentCourses())) {
            return List.of();
        }
        for (final var course : student.getStudentCourses()) {
            final var courseData = courseMapper.mapToData(course.getCourse());
            if (Objects.nonNull(courseData)) {
                list.add(courseData);
            }
        }
        return list;
    }

}
