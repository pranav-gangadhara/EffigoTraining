package com.example.demo.controller;

import com.example.demo.service.BookH2service;
import com.example.demo.model.Book;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
public class Controller {
    @Autowired
    BookH2service b;
     @GetMapping("/books")
    public ArrayList<Book>  getBooks(){
      
        return b.getBooks();
    }
    @GetMapping("/books/{bookId}")
    public Book getMethodName(@PathVariable("bookId") int id) {
        return b.getBookById(id);
    }
    
    @PostMapping("/books")
    public Book postMethodName(@RequestBody Book book) {
       return b.addBook(book);
    }
    @PutMapping("/books/{bookId}")
    public Book putMethodName(@PathVariable("bookId") int id, @RequestBody Book book) {
        System.out.println("sonegthing");
        return b.updateBook(id,book);
    }
    @DeleteMapping("/books/{bookId}")
    public String deleteBookId(@PathVariable("bookId") int id) {
        b.deleteBook(id);
        return "book deleted successfully";
    }
}