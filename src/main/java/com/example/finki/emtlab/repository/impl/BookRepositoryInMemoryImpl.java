package com.example.finki.emtlab.repository.impl;

import com.example.finki.emtlab.model.Book;
import com.example.finki.emtlab.repository.BookRepository;
import org.springframework.stereotype.Repository;
import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class BookRepositoryInMemoryImpl implements BookRepository {

    private HashMap<Long, Book> books;
    private Long counter;


    public BookRepositoryInMemoryImpl() {
        this.books = new HashMap<>();
        this.counter = 0L;
    }

    @PostConstruct
    public void init() {
        Long id = this.generateKey();
        this.books.put(id, new Book(id, "book1", 10L, null));
        id = this.generateKey();
        this.books.put(id, new Book(id, "book2", 20L, null));
        id = this.generateKey();
        this.books.put(id, new Book(id, "book3", 30L, null));
    }

    private Long generateKey() {
        return ++counter;
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(this.books.values());
    }

    @Override
    public List<Book> findAllSortedByNumberOfSamples(boolean asc) {
        Comparator<Book> priceComparator = Comparator.comparing(Book::getNumberSamples);
        if (!asc) {
            priceComparator = priceComparator.reversed();
        }
        return this.books.values()
                .stream()
                .sorted(priceComparator)
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findAllByCategoryId(Long categoryId) {
        return this.books.values()
                .stream()
                .filter(item -> item.getCategory().getId().equals(categoryId))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(this.books.get(id));
    }

    @Override
    public Book save(Book book) {
        if (book.getId() == null) {
            book.setId(this.generateKey());
        }
        this.books.put(book.getId(), book);
        return book;
    }

    @Override
    public void deleteById(Long id) {
        this.books.remove(id);
    }
}
