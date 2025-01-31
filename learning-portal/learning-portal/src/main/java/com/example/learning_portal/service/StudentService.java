package com.example.learning_portal.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.server.ResponseStatusException;

import com.example.learning_portal.model.Course;
import com.example.learning_portal.model.Student;
import com.example.learning_portal.repository.CourseRepository;
import com.example.learning_portal.repository.StudentRepository;

import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student enrollStudentInCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
        
        Course course = courseRepository.findById(courseId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));

        student.getCourses().add(course);
        return studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));

        // Remove student from all enrolled courses to maintain referential integrity
        for (Course course : student.getCourses()) {
            course.getStudents().remove(student);
        }
        courseRepository.saveAll(student.getCourses()); // Save updated course enrollments

        // Now delete the student
        studentRepository.delete(student);
    }
}

