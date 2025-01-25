package com.example.demo.repository;

import java.util.ArrayList;

import com.example.demo.model.Publisher;

public interface PublisherRepository {

     ArrayList<Publisher> getPublishers();
    Publisher getPublisherById(int id);
    Publisher addPublisher(Publisher publisher);
    Publisher updatePublisher(int id,Publisher book);
    void deletePublisher(int id);
} 