package com.spring_boot.RestApi_project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.spring_boot.RestApi_project.entities.Course;
@Service
public interface CourseService {

	public List<Course> getCourses();

	public Optional<Course> getCourse(long courseId);

	public Course addCourse(Course course);

	public void deleteCourse(long courseId);

	public Course updateCourse(Course course);
}
