package com.example.manytomany.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.manytomany.model.Publisher;

@Repository
public interface PublisherJpaRepository extends JpaRepository<Publisher, Integer> {

    @Query(value = "SELECT * FROM publisher WHERE publishername = :publisherName LIMIT 1", nativeQuery = true)
    Optional<Publisher> findByPublisherName(@Param("publisherName") String publisherName);
}
