package com.example.manytoone.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.manytoone.model.City;
import com.example.manytoone.model.Country;


@Repository
public interface CityJpaRepository extends JpaRepository<City, Integer> {
    ArrayList<City> findByCountry(Country country);
}

