package com.example.learning_portal.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.learning_portal.model.Student;


public interface StudentRepository extends JpaRepository<Student, Long> {
	
	
}
