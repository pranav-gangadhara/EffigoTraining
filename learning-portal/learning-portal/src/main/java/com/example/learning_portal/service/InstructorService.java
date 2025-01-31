package com.example.learning_portal.service;

import com.example.learning_portal.model.Instructor;
import com.example.learning_portal.model.Course;
import com.example.learning_portal.repository.InstructorRepository;

import jakarta.transaction.Transactional;

import com.example.learning_portal.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorService {

    private final InstructorRepository instructorRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public InstructorService(InstructorRepository instructorRepository, CourseRepository courseRepository) {
        this.instructorRepository = instructorRepository;
        this.courseRepository = courseRepository;
    }

    // Fetch all instructors
    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    // Get instructor by ID
    public Instructor getInstructorById(Long id) {
        Optional<Instructor> instructor = instructorRepository.findById(id);
        if (instructor.isPresent()) {
            return instructor.get();
        } else {
            throw new IllegalArgumentException("Instructor not found with ID: " + id);
        }
    }

    // Add a new instructor
    @Transactional
    public Instructor addInstructor(Instructor instructor) {
        return instructorRepository.save(instructor);
    }
    

    // Assign an instructor to a course
    
    public void assignInstructorToCourse(Long instructorId, Long courseId) {
        Optional<Instructor> instructor = instructorRepository.findById(instructorId);
        Optional<Course> course = courseRepository.findById(courseId);

        if (instructor.isPresent() && course.isPresent()) {
            Course courseEntity = course.get();
            Instructor instructorEntity = instructor.get();

            courseEntity.setInstructor(instructorEntity);
            //Assuming a 'setInstructor' method exists in Course
            courseRepository.save(courseEntity);
            // Save the course with the assigned instructor
        } else {
            throw new IllegalArgumentException("Instructor or Course not found with given IDs");
        }
    }

    // Delete an instructor (and handle related courses)
   
    public void deleteInstructor(Long instructorId) {
        Optional<Instructor> instructor = instructorRepository.findById(instructorId);

        if (instructor.isPresent()) {
            Instructor instructorEntity = instructor.get();
            // Set instructor reference to null for all courses they are assigned to
            List<Course> courses = courseRepository.findByInstructor(instructorEntity);
            System.out.println(courses);
            for (Course course : courses) {
                course.setInstructor(null); // Unassign the instructor from courses
                courseRepository.save(course); // Save updated course
            }
            instructorRepository.delete(instructorEntity); // Delete the instructor
        } else {
            throw new IllegalArgumentException("Instructor not found with ID: " + instructorId);
        }
    }
}

