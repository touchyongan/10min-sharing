package io.kmaker.sharing.specification.repo;

import io.kmaker.sharing.specification.dto.CourseData;
import io.kmaker.sharing.specification.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface CourseProjectionRepo {

    Page<CourseData> findAllCourseProjection(final Specification<Course> spec,
                                             final Pageable pageable);
}
