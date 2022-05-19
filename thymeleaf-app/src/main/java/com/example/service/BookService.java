package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Book;
import com.example.repository.BookRepository;

@Service
@Transactional
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public List<Book> findAll() {
        return bookRepository.findAllOrderByName();
    }

    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAllOrderByName(pageable);
    }

    public Book findOne(Integer id) {
        return bookRepository.findOne(id);
    }

    public Book create(Book book) {
        return bookRepository.save(book);
    }

    public Book update(Book book) {
        return bookRepository.save(book);
    }

    public void delete(Integer id) {
        bookRepository.delete(id);
    }
}