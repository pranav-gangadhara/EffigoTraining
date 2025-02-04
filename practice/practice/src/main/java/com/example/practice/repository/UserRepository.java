package com.example.practice.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.practice.model.Address;
import com.example.practice.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByAddress(Address address);

}
