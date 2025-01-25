package com.example.demo.repository;
import com.example.demo.model.Book;

import java.util.ArrayList;

public interface BookRepository {
   
    ArrayList<Book> getBooks();
    Book getBookById(int bookId);
    Book addBook(Book book);
    Book updateBook(int bookId,Book book);
    void deleteBook(int bookId);
}