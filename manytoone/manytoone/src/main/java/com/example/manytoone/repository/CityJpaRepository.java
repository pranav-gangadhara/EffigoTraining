package com.example.manytoone.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.manytoone.model.City;
import com.example.manytoone.model.Country;


@Repository
public interface CityJpaRepository extends JpaRepository<City, Integer> {
    boolean existsByCityNameIgnoreCaseAndCountry(String cityName, Country country);

	List<City> findByCountry(Country country);
    
}

