package io.kmaker.sharing.specification.generic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface BasedProjectionRepo<ET> {

    <DT> Page<DT> findAllWithGenericProjection(final Specification<ET> spec,
                                               final Pageable pageable,
                                               final Class<DT> clzDto,
                                               final Class<ET> clzEntity);
}
