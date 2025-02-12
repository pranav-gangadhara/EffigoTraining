package com.example.manytoone.repository;

import java.util.List;
import com.example.manytoone.model.Country;

public interface CountryRepository {
    List<Country> getCountries();
    Country addCountry(Country country);
    Country getCountryById(int id);
    Country updateCountry(Country country, int id);
    void deleteCountry(int id);
    
}
