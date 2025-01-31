package com.example.learning_portal.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private String description;

    @ManyToMany(mappedBy = "courses")
    @JsonIgnore
    private List<Student> students;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lesson> lessons;

    @OneToOne
    @JoinColumn(name = "instructor_id", unique = true)  // One-to-One mapping
    private Instructor instructor;

    
    public Course() {}

    // Constructor
    public Course(String title, String description, List<Student> students, List<Lesson> lessons, Instructor instructor) {
        this.title = title;
        this.description = description;
        this.students = students;
        this.lessons = lessons;
        this.instructor = instructor;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

	@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + ", description=" + description + ", students=" + students
				+ ", lessons=" + lessons + ", instructor=" + instructor + ", getId()=" + getId() + ", getTitle()="
				+ getTitle() + ", getDescription()=" + getDescription() + ", getStudents()=" + getStudents()
				+ ", getLessons()=" + getLessons() + ", getInstructor()=" + getInstructor() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
}

