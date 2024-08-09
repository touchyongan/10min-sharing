package io.kmaker.sharing.specification.generic.demo;

import io.kmaker.sharing.specification.dto.CourseData;
import io.kmaker.sharing.specification.entity.Course;
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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class DemoBaseGenericProjectionImpl<ET> implements DemoBaseGenericProjection<ET> {

    @Autowired
    private EntityManager entityManager;

    public <DT> Page<DT> findAllWithGenProj(final Specification<ET> spec,
                                            final Pageable pageable,
                                            final Class<DT> clsDto,
                                            final Class<ET> clsEntity) {
        final var cb = entityManager.getCriteriaBuilder();
        final var query = cb.createQuery(clsDto);
        final var root = query.from(clsEntity);
        final var selections = getSelectFields(root, clsDto);
        query.select(cb.construct(clsDto, selections.toArray(new Selection[0])));
        if (spec != null) {
            query.where(spec.toPredicate(root, query, cb));
        }
        if (Objects.equals(pageable.getSort(), Sort.unsorted())) {
            query.orderBy(cb.asc(root.get("id")));
        } else {
            query.orderBy(getSort(root, cb, pageable));
        }

        final var typeQuery = entityManager.createQuery(query);
        final var result = typeQuery.setFirstResult((int) pageable.getOffset())
                .setMaxResults((int) pageable.getPageSize())
                .getResultList();

        // Get the total count
        final var countQuery = cb.createQuery(Long.class);
        final var countRoot = countQuery.from(clsEntity);
        countQuery.select(cb.count(countRoot));
        if (spec != null) {
            countQuery.where(spec.toPredicate(countRoot, countQuery, cb));
        }
        final var total = entityManager.createQuery(countQuery).getSingleResult();

        return new PageImpl<>(result, pageable, total);
    }

    private <DT> List<Selection<Object>> getSelectFields(final Root<ET> root,
                                                         final Class<DT> clsDto) {
        final var fields = clsDto.getDeclaredFields();
        final var selections = new ArrayList<Selection<Object>>();
        for (final var field : fields) {
            selections.add(root.get(field.getName()).alias(field.getName()));
        }
        return selections;
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
