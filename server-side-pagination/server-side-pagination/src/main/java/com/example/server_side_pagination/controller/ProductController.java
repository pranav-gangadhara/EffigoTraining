package com.example.server_side_pagination.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.server_side_pagination.entity.Product;
import com.example.server_side_pagination.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    // Get paginated list of products
    @GetMapping
    public Page<Product> getProducts(Pageable pageable) {
        return productService.getProducts(pageable);
    }

    // Add a single product
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product savedProduct = productService.addProduct(product);
        return ResponseEntity.ok(savedProduct);
    }

    // Add multiple products in bulk
    @PostMapping("/bulk")
    public ResponseEntity<List<Product>> addProducts(@RequestBody List<Product> products) {
        List<Product> savedProducts = productService.addProducts(products);
        return ResponseEntity.ok(savedProducts);
    }

    // Update a product
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        Product updatedProduct = productService.updateProduct(id, productDetails);
        return ResponseEntity.ok(updatedProduct);
    }

    // Delete a product
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully.");
    }
}
