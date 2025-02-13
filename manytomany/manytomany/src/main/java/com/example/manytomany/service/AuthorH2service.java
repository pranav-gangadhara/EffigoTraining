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

import jakarta.transaction.Transactional;

@Service
public class AuthorH2service implements AuthorRepository {

    @Autowired
    private AuthorJpaRepository authorJpaRepository;
    
    @Autowired
    private BookJpaRepository bookJpaRepository;

    @Override
    public ArrayList<Author> getAuthors() {
        return new ArrayList<>(authorJpaRepository.findAll());
    }

    @Override
    public Author getAuthorById(int id) {
        return authorJpaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found"));
    }

    @Transactional
    @Override
    public Author addAuthor(Author author) {
        if (authorJpaRepository.findByAuthorNameIgnoreCase(author.getAuthorName()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Author with the same name already exists");
        }

        if (author.getBooks() == null) {
            author.setBooks(new ArrayList<>());
        }

        List<Book> books = bookJpaRepository.findAllById(
            author.getBooks().stream().map(Book::getId).toList()
        );
        author.setBooks(books);

        for (Book book : books) {
            if (book.getAuthors() == null) {
                book.setAuthors(new ArrayList<>());
            }
            if (book.getAuthors().stream().noneMatch(existing -> existing.equals(author))) {
                book.getAuthors().add(author);
            }
        }

        bookJpaRepository.saveAll(books);
        return authorJpaRepository.save(author);
    }

    @Transactional
    @Override
    public Author updateAuthor(int id, Author author) {
        Author original = authorJpaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found"));

        if (author.getAuthorName() != null && authorJpaRepository
                .findByAuthorNameIgnoreCase(author.getAuthorName())
                .filter(a -> a.getId() != id).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Author with the same name already exists");
        }
        original.setAuthorName(author.getAuthorName());

        if (author.getBooks() != null) {
            for (Book book : original.getBooks()) {
                book.getAuthors().remove(original);
            }
            bookJpaRepository.saveAll(original.getBooks());

            List<Book> books = bookJpaRepository.findAllById(
                author.getBooks().stream().map(Book::getId).toList()
            );
            original.setBooks(books);
            for (Book book : books) {
                book.getAuthors().add(original);
            }
            bookJpaRepository.saveAll(books);
        }

        return authorJpaRepository.save(original);
    }

    @Transactional
    @Override
    public void deleteAuthor(int id) {
        Author author = authorJpaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found"));

        for (Book book : author.getBooks()) {
            book.getAuthors().remove(author);
        }
        bookJpaRepository.saveAll(author.getBooks());

        authorJpaRepository.deleteById(id);
    }

    @Override
    public List<Book> getAuthorBooks(int id) {
        return authorJpaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found"))
                .getBooks();
    }
}
