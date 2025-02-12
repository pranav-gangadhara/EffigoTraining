package com.example.manytoone.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.manytoone.model.City;
import com.example.manytoone.model.Country;
import com.example.manytoone.repository.CityJpaRepository;
import com.example.manytoone.repository.CountryJpaRepository;

@Service
public class CityService {

    @Autowired
    private CityJpaRepository cityJpaRepository;

    @Autowired
    private CountryJpaRepository countryJpaRepository;

    public List<City> getCities() {
        return cityJpaRepository.findAll();
    }

    public City addCity(City city) {
//        if (city == null || city.getCountry() == null || city.getCountry().getCountryId() == null) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Valid country information is required");
//        }

        Country country = countryJpaRepository.findById(city.getCountry().getCountryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found"));

        // Check if the city already exists under the same country
        boolean cityExists = cityJpaRepository.existsByCityNameIgnoreCaseAndCountry(city.getCityName(), country);
        if (cityExists) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "City already exists in this country");
        }

        city.setCountry(country);
        return cityJpaRepository.save(city);
    }



    public City getCityById(int id) {
        return cityJpaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "City not found"));
    }

    public City updateCityById(int id, City city) {
        City original = cityJpaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "City not found"));

        if (city.getCityName() != null) {
            boolean exists = cityJpaRepository.existsByCityNameIgnoreCaseAndCountry(city.getCityName(), original.getCountry());
            if (exists) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "City already exists in this country");
            }
            original.setCityName(city.getCityName());
        }
        if (city.getLatitude() != null) {
            original.setLatitude(city.getLatitude());
        }
        if (city.getLongitude() != null) {
            original.setLongitude(city.getLongitude());
        }
        if (city.getPopulation() > 0) {
            original.setPopulation(city.getPopulation());
        }

        return cityJpaRepository.save(original);
    }

    public Country getCountryByCityId(int id) {
        City city = cityJpaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "City not found"));

        return city.getCountry();
    }

    public void deleteCity(int id) {
        City city = cityJpaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "City not found"));

        cityJpaRepository.delete(city);
    }
}
