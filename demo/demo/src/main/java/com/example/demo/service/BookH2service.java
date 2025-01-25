package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.repository.BookJpaRepository;
import com.example.demo.repository.BookRepository;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BookH2service  implements BookRepository{
    @Autowired
    private BookJpaRepository bookJpaRepository;
    
    @Override
    public ArrayList<Book> getBooks(){
        List<Book> bookList = bookJpaRepository.findAll();
        ArrayList<Book> books = new ArrayList<>(bookList);
        return books;
    }
    @Override
    public Book getBookById(int id){
        try{
        Book book=bookJpaRepository.findById(id).get();
        return book;
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @Override
    public Book addBook(Book book) {
      
        return   bookJpaRepository.save(book);
    }
    @Override
    public Book updateBook(int bookId, Book book) {
        Book orginal=bookJpaRepository.findById(bookId).get();
        if(orginal == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if(book.getName()!=null){
          orginal.setName(book.getName());
        } 
        if(book.getImageUrl()!=null){
            orginal.setImageUrl(book.getImageUrl());
        }
        
        return  bookJpaRepository.save(orginal);
    }
    @Override
    public void deleteBook(int bookId) {
        Book orginal=bookJpaRepository.findById(bookId).get();
        if(orginal == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        bookJpaRepository.deleteById(bookId);
    }
}