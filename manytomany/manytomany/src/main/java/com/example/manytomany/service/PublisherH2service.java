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

import jakarta.transaction.Transactional;

@Service
public class PublisherH2service implements PublisherRepository {

    @Autowired
    private PublisherJpaRepository publisherJpaRepository;

    @Autowired
    private BookJpaRepository bookJpaRepository;

    @Override
    public ArrayList<Publisher> getPublishers() {
        List<Publisher> publisherList = publisherJpaRepository.findAll();
        // Remove the default "No publisher assigned" publisher if present
        publisherList.removeIf(publisher -> "No publisher assigned".equals(publisher.getPublisherName()));
        return new ArrayList<>(publisherList);
    }

    @Override
    public Publisher getPublisherById(int id) {
        return publisherJpaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Publisher not found"));
    }

    @Override
    public Publisher addPublisher(Publisher publisher) {
        // Check if a publisher with the same name already exists
        Optional<Publisher> existingPublisher = publisherJpaRepository.findByPublisherName(publisher.getPublisherName());
        if (existingPublisher.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Publisher already exists");
        }
        return publisherJpaRepository.save(publisher);
    }

    @Override
    public Publisher updatePublisher(int id, Publisher publisher) {
        Publisher original = publisherJpaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Publisher not found"));

        if (publisher.getPublisherName() != null) {
            original.setPublisherName(publisher.getPublisherName());
        }

        return publisherJpaRepository.save(original);
    }

    @Transactional
    @Override
    public void deletePublisher(int id) {
        try {
            // Fetch publisher
            Publisher publisher = publisherJpaRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Publisher not found"));
            
            // Get all books associated with this publisher
            List<Book> books = bookJpaRepository.findByPublisher(publisher);
           
            // Check if "No publisher assigned" already exists, else create it
            Publisher defaultPublisher = publisherJpaRepository
                    .findByPublisherName("No publisher assigned")
                    .orElseGet(() -> {
                        Publisher newPublisher = new Publisher();
                        newPublisher.setPublisherName("No publisher assigned");
                        return publisherJpaRepository.save(newPublisher); // Save first
                    });
            
            // Assign the default publisher to all books
            for (Book book : books) {
                book.setPublisher(defaultPublisher);
            }
            
            // Save updated books
            bookJpaRepository.saveAll(books);
            
            // Delete the original publisher after updating books
            publisherJpaRepository.delete(publisher);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
