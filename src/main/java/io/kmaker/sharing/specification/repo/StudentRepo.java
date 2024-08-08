package io.kmaker.sharing.specification.repo;

import io.kmaker.sharing.specification.entity.Student;

public interface StudentRepo extends BaseJpaRepository<Student>, StudentProjectionRepository {
    
}
