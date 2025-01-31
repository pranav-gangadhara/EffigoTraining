package com.example.learning_portal.service;

import com.example.learning_portal.model.Course;
import com.example.learning_portal.model.Student;
import com.example.learning_portal.model.Instructor;
import com.example.learning_portal.model.Lesson;
import com.example.learning_portal.repository.CourseRepository;
import com.example.learning_portal.repository.StudentRepository;
import com.example.learning_portal.repository.InstructorRepository;
import com.example.learning_portal.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private LessonRepository lessonRepository;

    
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // Get a course by ID
    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));
    }

    // Add a new course
    public Course addCourse(Course course) {
        // Saving course with instructor and lessons included
        if (course.getInstructor() != null) {
            Instructor instructor = instructorRepository.findById(course.getInstructor().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Instructor not found"));
            course.setInstructor(instructor);
        }

        if (course.getLessons() != null) {
            for (Lesson lesson : course.getLessons()) {
                lessonRepository.save(lesson);  // Saving each lesson before associating with the course
            }
        }
        
        return courseRepository.save(course);
    }

    // Delete a course
    public void deleteCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));

        // Remove course reference from all enrolled students
        for (Student student : course.getStudents()) {
            student.getCourses().remove(course); // Removing course reference from students
            studentRepository.save(student); // Save the updated student
        }

        courseRepository.delete(course); // Delete the course
    }

    // Enroll a student in a course (Many-to-many relationship)
    public Course enrollStudentInCourse(Long courseId, Long studentId) {
        Course course = getCourseById(courseId);
        Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));

        // Adding the student to the course
        course.getStudents().add(student);
        student.getCourses().add(course); // Adding course to student

        courseRepository.save(course); // Saving course after adding the student
        studentRepository.save(student); // Saving student after adding the course
        
        return course;
    }

    // Add a lesson to a course (One-to-many relationship)
    public Course addLessonToCourse(Long courseId, Lesson lesson) {
        Course course = getCourseById(courseId);
        
        // Adding the lesson to the course
        course.getLessons().add(lesson);
        
        lesson.setCourse(course); // Associating the lesson with the course
        
        lessonRepository.save(lesson); // Saving the lesson
        courseRepository.save(course); // Saving the updated course
        
        return course;
    }

    // Assign an instructor to a course (One-to-one relationship)
    public Course assignInstructorToCourse(Long courseId, Long instructorId) {
        Course course = getCourseById(courseId);
        Instructor instructor = instructorRepository.findById(instructorId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Instructor not found"));

        // Assigning the instructor to the course
        course.setInstructor(instructor);
        courseRepository.save(course); // Saving the updated course
        
        return course;
    }
}

