package com.example.manytomany.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.manytomany.model.Author;
import com.example.manytomany.model.Book;
import com.example.manytomany.model.Publisher;
import com.example.manytomany.repository.AuthorJpaRepository;
import com.example.manytomany.repository.BookJpaRepository;
import com.example.manytomany.repository.BookRepository;
import com.example.manytomany.repository.PublisherJpaRepository;

@Service
public class BookH2service  implements BookRepository{
    @Autowired
    private BookJpaRepository bookJpaRepository;
    
    @Autowired
    private PublisherJpaRepository publisherJpaRepository;

    @Autowired
    private AuthorJpaRepository authorJpaRepository;
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
       
        Publisher publisher1 = book.getPublisher();
        int publisherId = publisher1.getId();
        try {
            
            Publisher publisher = publisherJpaRepository.findById(publisherId).get();
            book.setPublisher(publisher);
            List<Integer> authorIds= new ArrayList<>();
             for(Author author:book.getAuthors()){
               
                authorIds.add(author.getAuthorId());
             }
             List<Author> authors=authorJpaRepository.findAllById(authorIds);
             
             if(authors.size()!=authorIds.size())
             {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
             }
            
             book.setAuthors(authors);
            bookJpaRepository.save(book);
            return book;
        } catch(NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Wrong publisherId");
        }
    }
    @Override
    public Book updateBook(int bookId, Book book) {
        try{
        Book orginal=bookJpaRepository.findById(bookId).get();
       

        if(book.getName()!=null){
          orginal.setName(book.getName());
        } 
        if(book.getImageUrl()!=null){
            orginal.setImageUrl(book.getImageUrl());
        }
        if(book.getPublisher()!=null){
            Publisher publisher1 = book.getPublisher();
            int id=publisher1.getId();
      
            
                Publisher publisher = publisherJpaRepository.findById(id).get();
                book.setPublisher(publisher);
           
            
        }
        if(book.getAuthors()!=null){
            List<Integer> authorIds= new ArrayList<>();
             for(Author author:book.getAuthors()){
                authorIds.add(author.getAuthorId());
             }
             List<Author> authors=authorJpaRepository.findAllById(authorIds);
             if(authors.size()!=authorIds.size())
             {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
             }
             orginal.setAuthors(authors);
           
        }
    
      
        bookJpaRepository.save(orginal);
        return orginal;
    }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Wrong publisherId");
        }
   
    }
    @Override
    public void deleteBook(int bookId) {
         try {
            bookJpaRepository.deleteById(bookId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }
    @Override
    public Publisher getBookPublisher(int id){
        try {
            Book book = bookJpaRepository.findById(id).get();
           
            return book.getPublisher();
          } catch(Exception e){
              throw new ResponseStatusException(HttpStatus.NOT_FOUND);
          }
    }

    @Override
    public List<Author> getBookAuthors(int id){
        try{
        Book book=bookJpaRepository.findById(id).get();
        return book.getAuthors();
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        }
}