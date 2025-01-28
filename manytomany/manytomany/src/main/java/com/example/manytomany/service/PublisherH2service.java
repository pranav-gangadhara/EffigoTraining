package com.example.manytomany.service;

import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.manytomany.model.Book;
import com.example.manytomany.model.Publisher;
import com.example.manytomany.repository.BookJpaRepository;
import com.example.manytomany.repository.PublisherJpaRepository;
import com.example.manytomany.repository.PublisherRepository;

@Service
public class PublisherH2service  implements PublisherRepository{
    @Autowired
    private PublisherJpaRepository publisherJpaRepository;
    @Autowired
    private BookJpaRepository bookJpaRepository;
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

        return   publisherJpaRepository.save(publisher);
    }
    @Override
    public Publisher updatePublisher(int id, Publisher publisher) {
        try{
        Publisher orginal=publisherJpaRepository.findById(id).get();
       

        if(publisher.getPublisherName()!=null){
            orginal.setPublisherName(publisher.getPublisherName());
        }
        
        return  publisherJpaRepository.save(orginal);
    } 
    catch(Exception e){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    }
    @Override
    public void deletePublisher(int id) {
        try{
        Publisher publisher=publisherJpaRepository.findById(id).get();
        ArrayList<Book> books=bookJpaRepository.findByPublisher(publisher);
             for(Book book:books)
             {
                book.setPublisher(null);
             }
             bookJpaRepository.saveAll(books);
            publisherJpaRepository.deleteById(id);
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    
}