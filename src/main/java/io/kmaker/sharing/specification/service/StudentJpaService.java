package io.kmaker.sharing.specification.service;

import io.kmaker.sharing.specification.dto.StudentData;
import io.kmaker.sharing.specification.dto.StudentProjectionData;
import io.kmaker.sharing.specification.entity.Student;
import io.kmaker.sharing.specification.mapper.StudentMapper;
import io.kmaker.sharing.specification.repo.StudentRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudentJpaService {
    private final StudentRepo studentRepo;
    private final StudentMapper mapper;

    public Page<StudentData> getAllStudentsV1(final int page,
                                              final int limit,
                                              final String sort) {
        final var direction = "ASC".equalsIgnoreCase(sort) ? Sort.Direction.ASC : Sort.Direction.DESC;
        final var sortObj = Sort.by(direction, "id");
        final var pageRequest = PageRequest.of(page, limit, sortObj);
        final var result = studentRepo.findAll(pageRequest);
        final var content = result.getContent()
                .stream()
                .map(mapper::mapToData)
                .toList();
        return new PageImpl<>(content, pageRequest, result.getTotalPages());
    }

    public Page<StudentProjectionData> getAllStudentsV2(final int page,
                                                        final int limit,
                                                        final String sort,
                                                        final Specification<Student> spec) {
        final var direction = "ASC".equalsIgnoreCase(sort) ? Sort.Direction.ASC : Sort.Direction.DESC;
        final var sortObj = Sort.by(direction, "id");
        final var pageRequest = PageRequest.of(page, limit, sortObj);
        final var result = studentRepo.findAllWithProjection(spec, pageRequest);
        return result;
    }

    public Page<StudentProjectionData> getAllStudentsV3(final int page,
                                                        final int limit,
                                                        final String sort,
                                                        final Specification<Student> spec) {
        final var direction = "ASC".equalsIgnoreCase(sort) ? Sort.Direction.ASC : Sort.Direction.DESC;
        final var sortObj = Sort.by(direction, "id");
        final var pageRequest = PageRequest.of(page, limit, sortObj);
        final var result = studentRepo.findAllWithGenericProjection(spec, pageRequest,
                StudentProjectionData.class, Student.class);
        return result;
    }
}
