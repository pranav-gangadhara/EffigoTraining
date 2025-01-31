package com.example.learning_portal.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.learning_portal.model.Course;
import com.example.learning_portal.model.Lesson;
import com.example.learning_portal.repository.CourseRepository;
import com.example.learning_portal.repository.LessonRepository;

import org.springframework.http.HttpStatus;
import java.util.List;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private CourseRepository courseRepository;

    // Fetch all lessons
    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    // Get lesson by ID
    public Lesson getLessonById(Long id) {
        return lessonRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lesson not found"));
    }

    // Add a new lesson to a course
    public Lesson addLesson(Long courseId, Lesson lesson) {
        Course course = courseRepository.findById(courseId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));

        lesson.setCourse(course); // Assign the lesson to the course
        return lessonRepository.save(lesson);
    }

    // Update a lesson
    public Lesson updateLesson(Long id, Lesson lessonDetails) {
        Lesson lesson = lessonRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lesson not found"));

        if (lessonDetails.getTitle() != null) {
            lesson.setTitle(lessonDetails.getTitle());
        }

        if (lessonDetails.getContent() != null) {
            lesson.setContent(lessonDetails.getContent());
        }

        return lessonRepository.save(lesson);
    }

    // Delete a lesson safely
    public void deleteLesson(Long id) {
        Lesson lesson = lessonRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lesson not found"));

        lessonRepository.delete(lesson);
    }
}
