package com.example.learning_portal.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String expertise;

    @OneToOne(mappedBy = "instructor", cascade = CascadeType.ALL)
    @JsonIgnore
    private Course course;  // Mapping to Course

    // Default Constructor
    public Instructor() {}

    // Constructor
    public Instructor(String name, String expertise) {
        this.name = name;
        this.expertise = expertise;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}

