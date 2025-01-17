package com.in28minutes.springboot.learn_jpa_and_hibernate.course.jpa;

import org.springframework.stereotype.Component;

import com.in28minutes.springboot.learn_jpa_and_hibernate.course.Course;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
@Component
@Transactional
public class CourseRepositoryJpa {
	@PersistenceContext
	private EntityManager entityManager;
	
	public void insert(Course course) {
		entityManager.merge(course);
	}
	public Course findById(long id) {
		return entityManager.find(Course.class,id);
	}
	public void deleteById(long id) {
		Course course= entityManager.find(Course.class,id);
		entityManager.remove(course);
	}
	

}
