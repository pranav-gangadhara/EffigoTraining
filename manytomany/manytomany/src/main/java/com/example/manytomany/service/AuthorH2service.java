package com.example.manytomany.service;

import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.manytomany.model.Author;
import com.example.manytomany.model.Book;
import com.example.manytomany.repository.AuthorJpaRepository;
import com.example.manytomany.repository.AuthorRepository;
import com.example.manytomany.repository.BookJpaRepository;

@Service
public class AuthorH2service implements AuthorRepository{
    @Autowired
    private AuthorJpaRepository authorJpaRepository;
    
    @Autowired
    private BookJpaRepository bookJpaRepository;
    @Override
    public ArrayList<Author> getAuthors(){
        List<Author> authorList = authorJpaRepository.findAll();
        ArrayList<Author> authors = new ArrayList<>(authorList);
        return authors;
    }
    @Override
    public Author getAuthorById(int id){
        try{
            Author author=authorJpaRepository.findById(id).get();
        return author;
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @Override
    public Author addAuthor(Author author) {


              List<Integer> ids=new ArrayList<>();
              for(Book book:author.getBooks()){
                ids.add(book.getId());
              }
              List<Book> books=bookJpaRepository.findAllById(ids);
          
              author.setBooks(books);
              for(Book book:books){
                book.getAuthors().add(author);
              }
              bookJpaRepository.saveAll(books);
        
        return   authorJpaRepository.save(author);
    }
    @Override
    public Author updateAuthor(int id, Author author) {
        try{
        Author orginal=authorJpaRepository.findById(id).get();
       

        if(author.getAuthorName()!=null){
            orginal.setAuthorName(author.getAuthorName());
        }
        if(author.getBooks()!=null){
             
            List<Book> books1= orginal.getBooks();
                for (Book book : books1) {
                    book.getAuthors().remove(orginal);
                }
                bookJpaRepository.saveAll(books1);
            List<Integer> ids=new ArrayList<>();
              for(Book book:author.getBooks()){
                ids.add(book.getId());
              }
              
              List<Book> books=bookJpaRepository.findAllById(ids);
              orginal.setBooks(books);
              for(Book book:books){
                book.getAuthors().add(orginal);
              }
              bookJpaRepository.saveAll(books);

        }
        return  authorJpaRepository.save(orginal);
    }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @Override
    public void deleteAuthor(int id) {
        try{
        Author author=authorJpaRepository.findById(id).get();
        List<Book> books=author.getBooks();
        for(Book book :books){
            book.getAuthors().remove(author);
        }
         bookJpaRepository.saveAll(books);

        authorJpaRepository.deleteById(id);
    }
    catch(Exception e){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    }
    @Override
    public List<Book> getAuthorBooks(int id){
          try{
            Author author=authorJpaRepository.findById(id).get();
            return author.getBooks();
          }
          catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
          }
    }
}