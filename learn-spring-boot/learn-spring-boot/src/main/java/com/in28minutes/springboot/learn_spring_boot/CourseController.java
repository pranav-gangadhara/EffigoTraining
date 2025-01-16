package com.in28minutes.springboot.learn_spring_boot;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class CourseController{
	
    @RequestMapping("/courses")
	public List<Course> retrieveAllCourses(){
		return Arrays.asList(new Course(1,"Java","28minutes"),
				              new Course(2,"WebDev","in28 minutes"),
				              new Course(2,"React","45minutes"));
	}
}
