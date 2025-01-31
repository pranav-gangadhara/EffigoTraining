package com.example.learning_portal.controller;

import com.example.learning_portal.model.Course;
import com.example.learning_portal.model.Student;
import com.example.learning_portal.model.Instructor;
import com.example.learning_portal.model.Lesson;
import com.example.learning_portal.service.CourseService;
import com.example.learning_portal.service.StudentService;
import com.example.learning_portal.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private InstructorService instructorService;

    // Get all courses
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    // Get a course by ID
    @GetMapping("/{courseId}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long courseId) {
        Course course = courseService.getCourseById(courseId);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    // Add a new course
    @PostMapping
    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
        Course savedCourse = courseService.addCourse(course);
        return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
    }

    // Delete a course
    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long courseId) {
        courseService.deleteCourse(courseId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Enroll a student in a course
    @PostMapping("/{courseId}/students/{studentId}")
    public ResponseEntity<Course> enrollStudentInCourse(@PathVariable Long courseId, @PathVariable Long studentId) {
        Course course = courseService.enrollStudentInCourse(courseId, studentId);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    // Add a lesson to a course
    @PostMapping("/{courseId}/lessons")
    public ResponseEntity<Course> addLessonToCourse(@PathVariable Long courseId, @RequestBody Lesson lesson) {
        Course course = courseService.addLessonToCourse(courseId, lesson);
        return new ResponseEntity<>(course, HttpStatus.CREATED);
    }

    // Assign an instructor to a course
    @PutMapping("/{courseId}/instructors/{instructorId}")
    public ResponseEntity<Course> assignInstructorToCourse(@PathVariable Long courseId, @PathVariable Long instructorId) {
        Course course = courseService.assignInstructorToCourse(courseId, instructorId);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }
}


