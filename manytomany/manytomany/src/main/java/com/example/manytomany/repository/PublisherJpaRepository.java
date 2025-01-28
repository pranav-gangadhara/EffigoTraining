package com.example.manytomany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.manytomany.model.Publisher;

@Repository
public interface PublisherJpaRepository extends JpaRepository<Publisher,Integer>{
    
}