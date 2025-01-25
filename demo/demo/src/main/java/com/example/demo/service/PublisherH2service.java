package com.example.demo.service;

import com.example.demo.model.Publisher;
import com.example.demo.repository.PublisherJpaRepository;
import com.example.demo.repository.PublisherRepository;
import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PublisherH2service  implements PublisherRepository{
    @Autowired
    private PublisherJpaRepository publisherJpaRepository;
    
    @Override
    public ArrayList<Publisher> getPublishers(){
        List<Publisher> publisherList = publisherJpaRepository.findAll();
        ArrayList<Publisher> publishers = new ArrayList<>(publisherList);
        return publishers;
    }
    @Override
    public Publisher getPublisherById(int id){
        try{
        Publisher publisher=publisherJpaRepository.findById(id).get();
        return publisher;
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @Override
    public Publisher addPublisher(Publisher publisher) {
         System.out.println(publisher.getPublisherName());
        return   publisherJpaRepository.save(publisher);
    }
    @Override
    public Publisher updatePublisher(int id, Publisher book) {
        Publisher orginal=publisherJpaRepository.findById(id).get();
        if(orginal == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if(book.getPublisherName()!=null){
            orginal.setPublisherName(book.getPublisherName());
        }
        
        return  publisherJpaRepository.save(orginal);
    }
    @Override
    public void deletePublisher(int id) {
        Publisher orginal=publisherJpaRepository.findById(id).get();
        if(orginal == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        publisherJpaRepository.deleteById(id);
    }
}