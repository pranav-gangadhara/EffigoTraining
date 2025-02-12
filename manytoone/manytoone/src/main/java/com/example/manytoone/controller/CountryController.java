package com.example.manytoone.controller;

import org.springframework.web.bind.annotation.*;
import com.example.manytoone.model.Country;
import com.example.manytoone.service.CountryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping
    public List<Country> getAllCountries() {
       return countryService.getCountries();
    }

    @PostMapping
    public Country addCountry(@RequestBody Country country) {
        return countryService.addCountry(country);
    }

    @GetMapping("/{countryId}")
    public Country getCountryById(@PathVariable("countryId") int id) {
        return countryService.getCountryById(id);
    }

    @PutMapping("/{countryId}")
    public Country updateCountry(@RequestBody Country country, @PathVariable("countryId") int id) {
        return countryService.updateCountry(country, id);
    }

    @DeleteMapping("/{countryId}")
    public void deleteCountry(@PathVariable("countryId") int id) {
        countryService.deleteCountry(id);
    }
}
