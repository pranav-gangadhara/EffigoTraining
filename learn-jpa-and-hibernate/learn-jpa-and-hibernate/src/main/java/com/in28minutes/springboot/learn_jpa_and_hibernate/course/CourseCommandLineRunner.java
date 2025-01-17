package com.in28minutes.springboot.learn_jpa_and_hibernate.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.in28minutes.springboot.learn_jpa_and_hibernate.course.Course;
import com.in28minutes.springboot.learn_jpa_and_hibernate.course.jdbc.CourseJdbcRepository;
import com.in28minutes.springboot.learn_jpa_and_hibernate.course.jpa.CourseRepositoryJpa;
import com.in28minutes.springboot.learn_jpa_and_hibernate.course.springdatajpa.CourseSpringDataJpaRepository;

@Component
public class CourseCommandLineRunner implements CommandLineRunner {

	
	//@Autowired
	//private CourseJdbcRepository repository;
   // @Autowired
    //private CourseRepositoryJpa repository;
	@Autowired
     private CourseSpringDataJpaRepository repository;
    @Override
    public void run(String... args) throws Exception {
        // Insert a new course
        repository.save(new Course(1, "aws", "Pranav"));
        repository.save(new Course(2, "c++", "Likhith"));
        repository.save(new Course(3,"Java","Kohler"));
        repository.save(new Course(4,"Python","Buttler"));
        repository.deleteById(1l);
        System.out.println(repository.findById(2l));
        System.out.println(repository.findById(3l));
        System.out.println(repository.findById(4l));
        System.out.println(repository.findAll());
        System.out.println(repository.findByAuthor("Pranav"));
    }
}

