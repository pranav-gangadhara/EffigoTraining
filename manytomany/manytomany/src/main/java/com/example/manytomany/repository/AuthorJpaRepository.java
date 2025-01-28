package com.example.manytomany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.manytomany.model.Author;

@Repository
public interface AuthorJpaRepository extends JpaRepository<Author,Integer>{
    
}