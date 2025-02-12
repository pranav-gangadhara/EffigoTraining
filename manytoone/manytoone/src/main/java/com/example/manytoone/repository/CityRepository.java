package com.example.manytoone.repository;

import java.util.ArrayList;
import java.util.List;

import com.example.manytoone.model.City;
import com.example.manytoone.model.Country;

public interface CityRepository {


    List<City> getCities();
    City addCity(City city);
    City getCityById(int id);
    City updateCityById(int id,City city);
    Country getCountryByCityId(int id);
    void  deleteCity(int id);
    
}
