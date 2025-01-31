package com.example.learning_portal.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.learning_portal.model.Course;
import com.example.learning_portal.model.Instructor;


public interface CourseRepository extends JpaRepository<Course, Long> {
	List<Course> findByInstructor(Instructor instructor);
}
