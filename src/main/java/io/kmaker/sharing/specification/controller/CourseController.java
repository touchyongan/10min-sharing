package io.kmaker.sharing.specification.controller;

import io.kmaker.sharing.specification.dto.CourseData;
import io.kmaker.sharing.specification.entity.Course;
import io.kmaker.sharing.specification.repo.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("courses")
public class CourseController {

    @Autowired
    private CourseRepo courseRepo;

    @GetMapping
    public ResponseEntity<Object> getAll(@RequestParam(value = "limit", defaultValue = "3") final int limit,
                                         @RequestParam(value = "page", defaultValue = "0") final int page) {

        final var pageRequest = PageRequest.of(page, limit);
        final var result = courseRepo.findAllWithGenProj(null, pageRequest, CourseData.class, Course.class);

        return ResponseEntity.ok(result);
    }
}
