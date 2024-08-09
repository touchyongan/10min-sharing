package io.kmaker.sharing.specification.controller;

import io.kmaker.sharing.specification.dto.StudentData;
import io.kmaker.sharing.specification.dto.StudentProjectionData;
import io.kmaker.sharing.specification.repo.StudentSpecification;
import io.kmaker.sharing.specification.service.StudentJpaService;
import lombok.AllArgsConstructor;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/jpa/students")
@AllArgsConstructor
public class StudentJpaController {
    private final StudentJpaService service;

    @GetMapping("/v1")
    public ResponseEntity<Object> getAllStudentV1(
            @RequestParam(value = "page", defaultValue = "0") final int page,
            @RequestParam(value = "limit", defaultValue = "3") final int limit,
            @RequestParam(value = "sort", defaultValue = "asc") final String sort,
            final PagedResourcesAssembler<StudentData> assembler
    ) {
        final var s = Set.of("ASC", "asc", "DESC", "desc").contains(sort) ? sort : "ASC";
        final var result = service.getAllStudentsV1(page, limit, s);
        return ResponseEntity.ok(assembler.toModel(result));
    }

    @GetMapping("/v2")
    public ResponseEntity<Object> getAllStudentV2(
            @RequestParam(value = "page", defaultValue = "0") final int page,
            @RequestParam(value = "limit", defaultValue = "3") final int limit,
            @RequestParam(value = "sort", defaultValue = "asc") final String sort,
            @RequestParam(value = "ids", required = false) final List<Long> ids,
            @RequestParam(value = "ages", required = false) final List<Integer> ages,
            @RequestParam(value = "name", required = false) final String name,
            final PagedResourcesAssembler<StudentProjectionData> assembler
    ) {
        final var s = Set.of("ASC", "asc", "DESC", "desc").contains(sort) ? sort : "ASC";
        final var spec = new StudentSpecification();
        spec.setName(name);
        spec.setIds(ids);
        spec.setAges(ages);
        final var result = service.getAllStudentsV2(page, limit, s, spec);
        return ResponseEntity.ok(assembler.toModel(result));
    }

    @GetMapping("/v3")
    public ResponseEntity<Object> getAllStudentV3(
            @RequestParam(value = "page", defaultValue = "0") final int page,
            @RequestParam(value = "limit", defaultValue = "3") final int limit,
            @RequestParam(value = "sort", defaultValue = "asc") final String sort,
            @RequestParam(value = "ids", required = false) final List<Long> ids,
            @RequestParam(value = "ages", required = false) final List<Integer> ages,
            @RequestParam(value = "name", required = false) final String name,
            final PagedResourcesAssembler<StudentProjectionData> assembler
    ) {
        final var s = Set.of("ASC", "asc", "DESC", "desc").contains(sort) ? sort : "ASC";
        final var spec = new StudentSpecification();
        spec.setName(name);
        spec.setIds(ids);
        spec.setAges(ages);
        final var result = service.getAllStudentsV3(page, limit, s, spec);
        return ResponseEntity.ok(assembler.toModel(result));
    }
}
