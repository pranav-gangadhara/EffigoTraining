package com.example.learning_portal.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.learning_portal.model.Lesson;


public interface LessonRepository extends JpaRepository<Lesson, Long> {
	
	
}
