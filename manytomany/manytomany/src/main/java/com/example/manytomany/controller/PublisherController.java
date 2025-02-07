package com.example.manytomany.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.manytomany.model.Publisher;
import com.example.manytomany.service.PublisherH2service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@CrossOrigin(origins="*")
public class PublisherController {
    @Autowired
    PublisherH2service b;
     @GetMapping("/publishers")
    public ArrayList<Publisher>  getPublishers(){
      
        return b.getPublishers();
    }
    @GetMapping("/publishers/{pubId}")
    public Publisher getMethodName(@PathVariable("pubId") int id) {
        return b.getPublisherById(id);
    }
    
    @PostMapping("/publishers")
    public Publisher postMethodName(@RequestBody Publisher book) {
       return b.addPublisher(book);
    }
    @PutMapping("/publishers/{bookId}")
    public Publisher putMethodName(@PathVariable("bookId") int id, @RequestBody Publisher publisher) {
        System.out.println("sonegthing");
        return b.updatePublisher(id,publisher);
    }
    @DeleteMapping("/publishers/{pubId}")
    public String delete(@PathVariable("pubId") int id) {
        b.deletePublisher(id);
        return "book deleted successfully";
    }
}