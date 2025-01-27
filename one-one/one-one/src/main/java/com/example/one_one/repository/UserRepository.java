package com.example.one_one.repository;

import com.example.one_one.model.User;  // Correct import

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
