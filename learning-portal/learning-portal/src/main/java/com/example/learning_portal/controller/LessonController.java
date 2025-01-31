package com.example.learning_portal.controller;

import com.example.learning_portal.model.Lesson;
import com.example.learning_portal.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    @Autowired
    private LessonService lessonService;

    // Get all lessons
    @GetMapping
    public ResponseEntity<List<Lesson>> getAllLessons() {
        return ResponseEntity.ok(lessonService.getAllLessons());
    }

    // ✅ Get lesson by ID
    @GetMapping("/{id}")
    public ResponseEntity<Lesson> getLessonById(@PathVariable Long id) {
        return ResponseEntity.ok(lessonService.getLessonById(id));
    }

    // ✅ Add a new lesson to a specific course
    @PostMapping("/course/{courseId}")
    public ResponseEntity<Lesson> addLesson(@PathVariable Long courseId, @RequestBody Lesson lesson) {
        Lesson savedLesson = lessonService.addLesson(courseId, lesson);
        return new ResponseEntity<>(savedLesson, HttpStatus.CREATED);
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Lesson> updateLesson(@PathVariable Long id, @RequestBody Lesson lessonDetails) {
        Lesson updatedLesson = lessonService.updateLesson(id, lessonDetails);
        return ResponseEntity.ok(updatedLesson);
    }

    // Delete a lesson
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLesson(@PathVariable Long id) {
        lessonService.deleteLesson(id);
        return ResponseEntity.noContent().build();
    }
}
