package com.example.manytomany.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.manytomany.model.Book;
import com.example.manytomany.model.Publisher;

@Repository
public interface BookJpaRepository extends JpaRepository<Book,Integer>{
     ArrayList<Book> findByPublisher(Publisher publisher);
}