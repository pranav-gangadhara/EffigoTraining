package com.example.one_one.model;



import jakarta.persistence.*;

@Entity(name="user_details")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private Profile profile;

    // Constructors, Getters, Setters, toString

    public User() {}

    public User(String name, Profile profile) {
        this.name = name;
        this.profile = profile;
    }

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

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "', profile=" + profile + "}";
    }
}
