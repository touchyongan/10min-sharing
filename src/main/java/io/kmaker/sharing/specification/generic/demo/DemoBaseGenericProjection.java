package io.kmaker.sharing.specification.generic.demo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface DemoBaseGenericProjection<ET> {

    <DT> Page<DT> findAllWithGenProj(final Specification<ET> spec,
                                     final Pageable pageable,
                                     final Class<DT> clsDto,
                                     final Class<ET> clsEntity);
}
