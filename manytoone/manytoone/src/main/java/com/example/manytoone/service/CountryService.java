package com.example.manytoone.service;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.manytoone.model.City;
import com.example.manytoone.model.Country;
import com.example.manytoone.repository.CityJpaRepository;
import com.example.manytoone.repository.CountryJpaRepository;
import com.example.manytoone.repository.CountryRepository;

@Service
public class CountryService implements CountryRepository {

    @Autowired
    private CountryJpaRepository countryJpaRepository;

    @Autowired
    private CityJpaRepository cityJpaRepository;

    @Override
    public List<Country> getCountries() {
        return new ArrayList<>(countryJpaRepository.findAll()); // Ensure List is explicitly returned as ArrayList
    }

    @Override
    public Country addCountry(Country country) {
        return countryJpaRepository.save(country);
    }

    @Override
    public Country getCountryById(int id) {
        return countryJpaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found"));
    }

    @Override
    public Country updateCountry(Country country, int id) {
        Country original = countryJpaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found"));

        if (country.getCountryName() != null) {
            original.setCountryName(country.getCountryName());
        }
        if (country.getCurrency() != null) {
            original.setCurrency(country.getCurrency());
        }
        if (country.getLatitude() != null) {
            original.setLatitude(country.getLatitude());
        }
        if (country.getLongitude() != null) {
            original.setLongitude(country.getLongitude());
        }
        if (country.getPopulation() > 0) { // Ensure valid population values
            original.setPopulation(country.getPopulation());
        }

        return countryJpaRepository.save(original);
    }

    @Override
    public void deleteCountry(int id) {
        Country country = countryJpaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found"));

        // Fetch and delete all cities linked to this country
        List<City> cities = cityJpaRepository.findByCountry(country);
        if (!cities.isEmpty()) {
            cityJpaRepository.deleteAll(cities);
        }

        // Delete the country
        countryJpaRepository.delete(country);
    }
}
