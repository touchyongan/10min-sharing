package io.kmaker.sharing.specification.repo;

import io.kmaker.sharing.specification.entity.Student;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Setter
public class StudentSpecification implements Specification<Student> {
    private String name;
    private List<Long> ids;
    private List<Integer> ages;

    public Specification<Student> filterName(final String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    public Specification<Student> filterIds(final List<Long> ids) {
        return (root, query, criteriaBuilder) -> root.get("id").in(ids);
    }

    public Specification<Student> filterAge(final List<Integer> ages) {
        return (root, query, criteriaBuilder) -> root.get("id").in(ages);
    }

    @Override
    public Predicate toPredicate(final Root<Student> root,
                                 final CriteriaQuery<?> query,
                                 final CriteriaBuilder criteriaBuilder) {
        final var predicates = new ArrayList<Predicate>();
        if (StringUtils.hasText(name)) {
            predicates.add(filterName(name).toPredicate(root, query, criteriaBuilder));
        }
        if (Objects.nonNull(ids) && !ids.isEmpty()) {
            predicates.add(filterIds(ids).toPredicate(root, query, criteriaBuilder));
        }
        if (Objects.nonNull(ages) && !ages.isEmpty()) {
            predicates.add(filterAge(ages).toPredicate(root, query, criteriaBuilder));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
