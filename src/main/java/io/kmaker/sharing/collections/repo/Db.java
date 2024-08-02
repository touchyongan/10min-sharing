package io.kmaker.sharing.collections.repo;

import io.kmaker.sharing.collections.entity.Course;
import io.kmaker.sharing.collections.entity.Student;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Db {

    private static List<Map<String, String>> rawData;
    private static List<Student> students;
    private static List<Course> courses;

    static {
        seedRawData();
        seedStudent();
        seedCourses();
    }

    static void seedRawData() {
        rawData = new ArrayList<>();
        rawData.add(createRawData(List.of("1", "Mr A", "1", "Java")));
        rawData.add(createRawData(List.of("1", "Mr A", "2", "PHP")));
        rawData.add(createRawData(List.of("2", "Mr B", "1", "Java")));
        rawData.add(createRawData(List.of("2", "Mr B", "3", "React")));
        rawData.add(createRawData(List.of("3", "Mr C", "1", "Java")));
        rawData.add(createRawData(List.of("4", "Mr D", "1", "Java")));
        rawData.add(createRawData(List.of("5", "Mr E", "1", "Java")));
        rawData.add(createRawData(List.of("6", "Mr F", "1", "Java")));
        rawData.add(createRawData(List.of("7", "Mr G", "1", "Java")));
        rawData.add(createRawData(List.of("8", "Mr H", "1", "Java")));
        rawData.add(createRawData(List.of("9", "Mr I", "1", "Java")));
        rawData.add(createRawData(List.of("10", "Mr J", "1", "Java")));
    }

    static Map<String, String> createRawData(List<String> raw) {
        final var map = new HashMap<String, String>();
        map.put("studentId", raw.get(0));
        map.put("studentName", raw.get(1));
        map.put("courseId", raw.get(2));
        map.put("courseName", raw.get(3));
        return map;
    }

    static void seedStudent() {
        students = new ArrayList<>();
        students.add(new Student(1, "Mr A"));
        students.add(new Student(2, "Mr B"));
        students.add(new Student(3, "Mr C"));
        students.add(new Student(4, "Mr D"));
        students.add(new Student(5, "Mr E"));
        students.add(new Student(6, "Mr F"));
        students.add(new Student(7, "Mr G"));
        students.add(new Student(8, "Mr H"));
        students.add(new Student(9, "Mr I"));
        students.add(new Student(10, "Mr J"));
    }

    static void seedCourses() {
        courses = new ArrayList<>();
        courses.add(new Course(1, "Java"));
        courses.add(new Course(2, "PHP"));
        courses.add(new Course(3, "React"));
        courses.add(new Course(4, "Flutter"));
    }

    public List<Student> findAllStudents() {
        return students;
    }

    public List<Course> findAllCourses() {
        return courses;
    }

    public List<Map<String, String>> findAllStudentCourse() {
        return rawData;
    }
}
