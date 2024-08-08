package io.kmaker.sharing.specification.mapper;

import io.kmaker.sharing.specification.dto.CourseData;
import io.kmaker.sharing.specification.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CourseMapper {

    CourseData mapToData(Course course);
}
