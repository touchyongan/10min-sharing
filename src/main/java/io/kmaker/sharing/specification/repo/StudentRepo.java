package io.kmaker.sharing.specification.repo;

import io.kmaker.sharing.specification.entity.Student;
import io.kmaker.sharing.specification.generic.BasedProjectionRepo;

public interface StudentRepo extends BaseJpaRepository<Student>, StudentProjectionRepository, BasedProjectionRepo<Student> {
    
}
