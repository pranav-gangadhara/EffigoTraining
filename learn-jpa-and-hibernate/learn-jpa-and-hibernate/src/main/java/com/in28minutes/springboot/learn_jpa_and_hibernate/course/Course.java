package com.in28minutes.springboot.learn_jpa_and_hibernate.course;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Course {
	@Id
    private int id;
    private String name;
    private String author;

    // Default constructor
    public Course() {
    }

    // Parameterized constructor
    public Course(int id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", author=" + author + "]";
	}
    
}
