package io.kmaker.sharing.specification.repo;

import io.kmaker.sharing.specification.dto.StudentProjectionData;
import io.kmaker.sharing.specification.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface StudentProjectionRepository {

    Page<StudentProjectionData> findAllWithProjection(Specification<Student> spec,
                                                      Pageable pageable);
}
