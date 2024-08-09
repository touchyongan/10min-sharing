package io.kmaker.sharing.specification.repo;

import io.kmaker.sharing.specification.entity.Course;
import io.kmaker.sharing.specification.generic.demo.DemoBaseGenericProjection;

public interface CourseRepo extends BaseJpaRepository<Course>, DemoBaseGenericProjection<Course> {
}
