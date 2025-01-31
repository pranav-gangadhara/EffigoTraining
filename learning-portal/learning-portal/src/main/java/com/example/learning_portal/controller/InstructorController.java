package com.example.learning_portal.controller;

import com.example.learning_portal.model.Instructor;
import com.example.learning_portal.model.Course;
import com.example.learning_portal.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {

    private final InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    // Fetch all instructors
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Instructor> getAllInstructors() {
        return instructorService.getAllInstructors();
    }

    // Get instructor by ID
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Instructor getInstructorById(@PathVariable Long id) {
        return instructorService.getInstructorById(id);
    }

    // Add a new instructor (Changed endpoint to RESTful convention)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Instructor addInstructor(@RequestBody Instructor instructor) {
        return instructorService.addInstructor(instructor);
    }

    // Assign an instructor to a course
    @PutMapping("/{instructorId}/assign/{courseId}")
    @ResponseStatus(HttpStatus.OK)
    public void assignInstructorToCourse(@PathVariable Long instructorId, @PathVariable Long courseId) {
        instructorService.assignInstructorToCourse(instructorId, courseId);
    }

    // Delete an instructor
    @DeleteMapping("/{instructorId}")
    public ResponseEntity<String> deleteInstructor(@PathVariable Long instructorId) {
        try {
            instructorService.deleteInstructor(instructorId);
            return ResponseEntity.ok("Instructor and related courses deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
}
