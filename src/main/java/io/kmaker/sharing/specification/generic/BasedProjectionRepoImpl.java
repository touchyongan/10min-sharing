package io.kmaker.sharing.specification.generic;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Selection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class BasedProjectionRepoImpl<ET> implements BasedProjectionRepo<ET> {

    @Autowired
    private EntityManager entityManager;

    public <DT> Page<DT> findAllWithGenericProjection(final Specification<ET> spec,
                                                      final Pageable pageable,
                                                      final Class<DT> clzDto,
                                                      final Class<ET> clzEntity) {
        final var cb = entityManager.getCriteriaBuilder();
        final var query = cb.createQuery(clzDto);
        final var root = query.from(clzEntity);
        query.select(cb.construct(clzDto, getSelectFields(root, clzDto)));

        if (Objects.nonNull(spec)) {
            query.where(spec.toPredicate(root, query, cb));
        }

        if (Objects.equals(pageable.getSort(), Sort.unsorted())) {
            query.orderBy(cb.asc(root.get("id")));
        } else {
            query.orderBy(getSort(root, cb, pageable));
        }

        final var typeQuery = entityManager.createQuery(query);
        final var result = typeQuery.getResultList();

        // Get the total count
        final var countQuery = cb.createQuery(Long.class);
        final var countRoot = countQuery.from(clzEntity);
        countQuery.select(cb.count(countRoot));
        if (spec != null) {
            countQuery.where(spec.toPredicate(countRoot, countQuery, cb));
        }
        final var total = entityManager.createQuery(countQuery).getSingleResult();

        return new PageImpl<>(result, pageable, total);
    }

    @SuppressWarnings("rawtypes")
    public <DT> Selection[] getSelectFields(final Root<ET> root,
                                            final Class<DT> clsDto) {
        final var selections = new ArrayList<Selection<Object>>();

        final var fields = clsDto.getDeclaredFields();
        for (final var field : fields) {
            selections.add(root.get(field.getName()).alias(field.getName()));
        }

        return selections.toArray(new Selection[0]);
    }

    private List<Order> getSort(final Root<?> root,
                                final CriteriaBuilder cb,
                                final Pageable pageable) {
        final var orders = new ArrayList<Order>();
        final var sort = pageable.getSort();
        for (final var order : sort) {
            Order jpaOrder;
            if (order.isAscending()) {
                jpaOrder = cb.asc(root.get(order.getProperty()));
            } else {
                jpaOrder = cb.desc(root.get(order.getProperty()));
            }
            orders.add(jpaOrder);
        }
        return orders;
    }

}
