package com.example.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.domain.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("SELECT x FROM Book x ORDER BY x.bookName, x.publisher, x.author")
    List<Book> findAllOrderByName();

    @Query("SELECT x FROM Book x ORDER BY x.bookName, x.publisher, x.author")
    Page<Book> findAllOrderByName(Pageable pageable);

}

