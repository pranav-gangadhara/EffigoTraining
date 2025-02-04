package com.spring_boot.RestApi_project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring_boot.RestApi_project.entities.Course;

@Repository
public interface CourseRepo extends JpaRepository<Course,Long>{
	
}
