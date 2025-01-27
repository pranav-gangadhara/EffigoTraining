package com.example.one_one.controller;

import com.example.one_one.model.User;
import com.example.one_one.model.Profile;
import com.example.one_one.repository.UserRepository;
import com.example.one_one.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
//@RequestMapping("/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    // Create a new user with a profile
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    // Get user by ID
    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable Long id) {
        return userRepository.findById(id);
    }

    // Create a new profile for a user
    @PostMapping("/profile")
    public Profile createProfile(@RequestBody Profile profile) {
        return profileRepository.save(profile);
    }

}
