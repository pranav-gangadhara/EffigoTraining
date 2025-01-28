package com.example.manytomany.repository;

import java.util.ArrayList;
import java.util.List;

import com.example.manytomany.model.Author;
import com.example.manytomany.model.Book;
import com.example.manytomany.model.Publisher;

public interface BookRepository {
	   
    ArrayList<Book> getBooks();
    Book getBookById(int bookId);
    Book addBook(Book book);
    Book updateBook(int bookId,Book book);
    void deleteBook(int bookId);
    Publisher getBookPublisher(int bookId);
    List<Author> getBookAuthors(int id);
}