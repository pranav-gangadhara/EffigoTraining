package com.example.manytomany.repository;

import java.util.*;

import com.example.manytomany.model.Author;
import com.example.manytomany.model.Book;

public interface AuthorRepository {
   
    ArrayList<Author> getAuthors();
    Author getAuthorById(int authorId);
    Author addAuthor(Author author);
    Author updateAuthor(int authorId,Author author);
    void deleteAuthor(int authorId);
    List<Book> getAuthorBooks(int id);
}
