package com.example.manytoone.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.manytoone.model.City;
import com.example.manytoone.model.Country;
import com.example.manytoone.repository.CityJpaRepository;
import com.example.manytoone.repository.CityRepository;
import com.example.manytoone.repository.CountryJpaRepository;



@Service
public class CityService implements CityRepository{
    @Autowired
    private CityJpaRepository cityJpaRepository;

    @Autowired
    private CountryJpaRepository countryJpaRepository;
    
    @Override
    public ArrayList<City> getCities(){

        List<City> cities= cityJpaRepository.findAll();
        ArrayList<City>  citylist= new ArrayList<>(cities);
        return citylist;
    } 
    @Override
    public City addCity(City city){
        try{
          
            int id=city.getCountry().getCountryId();
            
            Country country=countryJpaRepository.findById(id).get();
            System.out.println(country);
            city.setCountry(country);
            
            cityJpaRepository.save(city);
           return city;
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @Override
    public City getCityById(int id){
        try{
            City city=cityJpaRepository.findById(id).get();
           return city;
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @Override
    public City updateCityById(int id, City city) {
        try {
            City original = cityJpaRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "City not found"));

            if (city.getCityName() != null) {
                original.setCityName(city.getCityName());
            }

            if (city.getLatitude() != null) {
                original.setLatitude(city.getLatitude());
            }

            if (city.getLongitude() != null) {
                original.setLongitude(city.getLongitude());
            }

            if (city.getPopulation() != 0) {
                original.setPopulation(city.getPopulation());
            }

            // Handle country update
            if (city.getCountry() != null) {
                Country existingCountry = null;
                Integer countryId = city.getCountry().getCountryId();
                String newCountryName = city.getCountry().getCountryName();

                if (countryId != null) {
                    existingCountry = countryJpaRepository.findById(countryId).orElse(null);
                    if (existingCountry == null) {
                        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found with ID: " + countryId);
                    }
                }

                // If a new country name is provided, update existing country or create a new one
                if (newCountryName != null && !newCountryName.isEmpty()) {
                    if (existingCountry != null) {
                        existingCountry.setCountryName(newCountryName);
                        countryJpaRepository.save(existingCountry);
                    } else {
                        Country newCountry = new Country();
                        newCountry.setCountryName(newCountryName);
                        existingCountry = countryJpaRepository.save(newCountry);
                    }
                }

                // Assign the updated or newly created country
                original.setCountry(existingCountry);
            } else {
                // If no country info is provided, remove association (if allowed by DB constraints)
                original.setCountry(null);
            }

            return cityJpaRepository.save(original);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating city", e);
        }
    }

  @Override
  public Country getCountryByCityId(int id){
    try{
          City city=cityJpaRepository.findById(id).get();
          int countryId=city.getCountry().getCountryId();
          Country country=countryJpaRepository.findById(countryId).get();
          return country;
    }
    catch(Exception e){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }

  @Override 
  public void  deleteCity(int id){
      try{
         City city=cityJpaRepository.findById(id).get();
         cityJpaRepository.deleteById(id);
      }
      catch(Exception e){
          throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }
  }

}