package io.kmaker.sharing.collections.service;

import io.kmaker.sharing.collections.entity.Course;
import io.kmaker.sharing.collections.entity.Student;
import io.kmaker.sharing.collections.repo.Db;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {

    private final Db db;

    private List<Student> getAllStudents(int page,
                                         int limit,
                                         String sort) {
        final var students = db.findAllStudents();
        final var skip = (page - 1) * limit;
        return students.stream()
                .sorted((s1, s2) -> {
                    if ("ASC".equals(sort)) {
                        return s1.getId() - s2.getId();
                    } else {
                        return s2.getId() - s1.getId();
                    }
                })
                .skip(skip)
                .limit(limit)
                .toList();
    }

    public List<Student> getAllStudentsV1(int page,
                                          int limit,
                                          String sort) {
        final var map = new HashMap<String, Student>();

        // Simulate get join record from database
        final var rawData = db.findAllStudentCourse();
        final var skip = (page - 1) * limit;
        final var sorted = rawData.stream()
                .sorted((m1, m2) -> {
                    if ("ASC".equals(sort)) {
                        return m1.get("studentId").compareTo(m2.get("studentId"));
                    } else {
                        return m2.get("studentId").compareTo(m1.get("studentId"));
                    }
                })
                .skip(skip)
                .limit(limit)
                .toList();

        for (final var item : sorted) {
            final var student = map.computeIfAbsent(item.get("studentId"), k ->
                    new Student(Integer.parseInt(item.get("studentId")), item.get("studentName")));
            final var courses = student.getCourses();
            courses.add(new Course(Integer.parseInt(item.get("courseId")), item.get("courseName")));
        }

        return new ArrayList<>(map.values());
    }

    public List<Student> getAllStudentsV2(int page,
                                          int limit,
                                          String sort) {
        final var map = new HashMap<String, Student>();

        // resolve filter main table first
        final var students = getAllStudents(page, limit, sort);
        final var studentIds = students.stream()
                .map(Student::getId)
                .toList();

        // Simulate get join record from database
        final var rawData = db.findAllStudentCourse();
        final var sorted = rawData.stream()
                .filter(f -> studentIds.contains(Integer.parseInt(f.get("studentId"))))
                .sorted((m1, m2) -> {
                    if ("ASC".equals(sort)) {
                        return Integer.parseInt(m1.get("studentId")) - Integer.parseInt(m2.get("studentId"));
                    } else {
                        return Integer.parseInt(m2.get("studentId")) - Integer.parseInt(m1.get("studentId"));
                    }
                })
                .toList();

        for (final var item : sorted) {
            final var student = map.computeIfAbsent(item.get("studentId"), k ->
                    new Student(Integer.parseInt(item.get("studentId")), item.get("studentName")));
            final var courses = student.getCourses();
            courses.add(new Course(Integer.parseInt(item.get("courseId")), item.get("courseName")));
        }

        return new ArrayList<>(map.values());
    }

    public List<Student> getAllStudentsV3(int page,
                                          int limit,
                                          String sort) {
        final var map = new LinkedHashMap<String, Student>();

        // resolve filter main table first
        final var students = getAllStudents(page, limit, sort);
        final var studentIds = students.stream()
                .map(Student::getId)
                .toList();

        // Simulate get join record from database
        final var rawData = db.findAllStudentCourse();
        final var sorted = rawData.stream()
                .filter(f -> studentIds.contains(Integer.parseInt(f.get("studentId"))))
                .sorted((m1, m2) -> {
                    if ("ASC".equals(sort)) {
                        return Integer.parseInt(m1.get("studentId")) - Integer.parseInt(m2.get("studentId"));
                    } else {
                        return Integer.parseInt(m2.get("studentId")) - Integer.parseInt(m1.get("studentId"));
                    }
                })
                .toList();

        for (final var item : sorted) {
            final var student = map.computeIfAbsent(item.get("studentId"), k ->
                    new Student(Integer.parseInt(item.get("studentId")), item.get("studentName")));
            final var courses = student.getCourses();
            courses.add(new Course(Integer.parseInt(item.get("courseId")), item.get("courseName")));
        }

        return new ArrayList<>(map.values());
    }
}
