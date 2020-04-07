package com.example.finki.emtlab.repository;

import com.example.finki.emtlab.model.Book;
import java.util.List;
import java.util.Optional;

public interface BookRepository {
    // CRUD operations
    //read operation
    List<Book> findAll();
    List<Book> findAllSortedByNumberOfSamples(boolean asc);
    List<Book> findAllByCategoryId(Long categoryId);
    Optional<Book> findById(Long id);
    // create & update
    Book save(Book book);
    // delete
    void deleteById(Long id);

}
