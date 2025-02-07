package com.example.manytomany.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.manytomany.model.Book;
import com.example.manytomany.model.Publisher;
import com.example.manytomany.repository.BookJpaRepository;
import com.example.manytomany.repository.PublisherJpaRepository;
import com.example.manytomany.repository.PublisherRepository;

@Service
public class PublisherH2service implements PublisherRepository {

    @Autowired
    private PublisherJpaRepository publisherJpaRepository;

    @Autowired
    private BookJpaRepository bookJpaRepository;

    @Override
    public ArrayList<Publisher> getPublishers() {List<Publisher> publisherList = publisherJpaRepository.findAll();
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

            // Create a default "no publisher" publisher (can be a new Publisher object)
            Publisher defaultPublisher = new Publisher();
            defaultPublisher.setPublisherName("No publisher assigned");

           

            // Set publisher to default for all books associated with this publisher
            for (Book book : books) {
                book.setPublisher(defaultPublisher);
            }

            // Save updated books
            bookJpaRepository.saveAll(books);

            // Delete publisher after updating books
            publisherJpaRepository.delete(publisher);

        } catch (ResponseStatusException e) {
            // Handle specific exception (Publisher not found)
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete publisher");
        }
    }
}
