package com.example.server_side_pagination.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.server_side_pagination.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAll(Pageable pageable);
    @Query("SELECT p FROM Product p WHERE p.id BETWEEN 11 AND 19 AND p.price BETWEEN :minPrice AND :maxPrice ORDER BY p.price")
    Page<Product> findByPriceRangeSortedForPage1(
        @Param("minPrice") Double minPrice, 
        @Param("maxPrice") Double maxPrice, 
        Pageable pageable
    );


}
