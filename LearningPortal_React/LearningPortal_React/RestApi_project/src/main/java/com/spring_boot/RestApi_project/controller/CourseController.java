package com.spring_boot.RestApi_project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring_boot.RestApi_project.entities.Course;
import com.spring_boot.RestApi_project.service.CourseService;

@RestController
@CrossOrigin(origins="*")
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@GetMapping("/courses")
	public List<Course> getCourses(){
		return this.courseService.getCourses();		
	}
	
	@GetMapping("/courses/{courseId}")
	public Optional<Course> getCourse(@PathVariable String courseId){
		return this.courseService.getCourse(Long.parseLong(courseId));		
	}
	
	@PostMapping("/courses")
	public Course addCourse(@RequestBody Course course){
		return this.courseService.addCourse(course);		
	}
	
	@DeleteMapping("/courses/{courseId}")
	public void deleteCourse(@PathVariable String courseId){
		courseService.deleteCourse(Long.parseLong(courseId));
	}
	
	@PutMapping("/courses")
	public Course updateCourse(@RequestBody Course course){
		return this.courseService.updateCourse(course);
	}
	
}
