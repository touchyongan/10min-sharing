package io.kmaker.sharing.collections.controller;

import io.kmaker.sharing.collections.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
@AllArgsConstructor
public class StudentController {
    private final StudentService service;

    @GetMapping("/v1")
    public ResponseEntity<Object> getAllStudentV1(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "4") int limit,
            @RequestParam(value = "sort", defaultValue = "ASC") String sort
    ) {
        final var result = service.getAllStudentsV1(page, limit, sort);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/v2")
    public ResponseEntity<Object> getAllStudentV2(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "4") int limit,
            @RequestParam(value = "sort", defaultValue = "ASC") String sort
    ) {
        final var result = service.getAllStudentsV2(page, limit, sort);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/v3")
    public ResponseEntity<Object> getAllStudentV3(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "4") int limit,
            @RequestParam(value = "sort", defaultValue = "ASC") String sort
    ) {
        final var result = service.getAllStudentsV3(page, limit, sort);
        return ResponseEntity.ok(result);
    }
}
