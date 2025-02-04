package com.spring_boot.RestApi_project.service;

//import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
//import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring_boot.RestApi_project.entities.Course;
import com.spring_boot.RestApi_project.repo.CourseRepo;

@Service
public class CourseServiceImp implements CourseService {
	
//	private List<Course> list;
	@Autowired
	private CourseRepo courseRepo;
	
//	public CourseServiceImp() {
//		list=new ArrayList<>();
//		list.add(new Course(123,"Java Course","Complete Java course"));
//		list.add(new Course(132,"Spring Course","Complete Spring course"));
//	}

	@Override
	public List<Course> getCourses() {
//		return list;
		return courseRepo.findAll();
	}

	@Override
	public Optional<Course> getCourse(long courseId) {
		return courseRepo.findById(courseId);
//		Course a =null;
//		for(Course course:list) {
//			if(course.getId()==courseId) {
//				a=course;
//				break;
//			}
//		}
//		return a;
	}
	
	@Override
	public Course addCourse(Course course) {
//		list.add(course);
//		return course;	
		courseRepo.save(course);
		return course;
	}
	
	@Override
	public void deleteCourse(long courseId) {
//	    Course d = null;    
//	    for (Course course : list) {
//	        if (course.getId()==courseId) {
//	            d = course;
//	            break;
//	        }
//	    }   
//	    if (d != null) {
//	        list.remove(d);
//	    }
//		list=this.list.stream().filter(e->
//		   e.getId()!=courseId).collect(Collectors.toList());
		courseRepo.deleteById(courseId);
		
	}
	
	@Override
	public Course updateCourse(Course course) {
//		for (int i = 0; i < list.size(); i++) {
//			if (list.get(i).getId()==updatedCourse.getId()) {
//				list.set(i,updatedCourse);
//				return updatedCourse;
//			}
//		}
//		return null;
//		list.forEach(e ->{
//			if(e.getId()==course.getId()) {
//				e.setTitle(course.getTitle());
//				e.setDescription(course.getDescription());
//			}
//		});
//		return course;
		courseRepo.save(course);
		return course;
	}

}
