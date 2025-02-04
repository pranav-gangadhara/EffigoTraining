package com.example.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.practice.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
