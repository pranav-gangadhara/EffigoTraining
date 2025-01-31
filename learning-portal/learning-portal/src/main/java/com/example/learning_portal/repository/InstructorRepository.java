package com.example.learning_portal.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.learning_portal.model.Instructor;


public interface InstructorRepository extends JpaRepository<Instructor, Long> {
	
}
