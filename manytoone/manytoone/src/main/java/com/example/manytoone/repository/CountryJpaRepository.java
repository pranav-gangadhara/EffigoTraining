package com.example.manytoone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.manytoone.model.Country;


@Repository
public interface CountryJpaRepository extends JpaRepository<Country, Integer> {

}