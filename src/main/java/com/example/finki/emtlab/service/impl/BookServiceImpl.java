package com.example.finki.emtlab.service.impl;

import com.example.finki.emtlab.model.Book;
import com.example.finki.emtlab.model.Category;
import com.example.finki.emtlab.model.Exception.BookNotFoundException;
import com.example.finki.emtlab.repository.BookRepository;
import com.example.finki.emtlab.service.BookService;
import com.example.finki.emtlab.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class    BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CategoryService categoryService;

    public BookServiceImpl(BookRepository bookRepository, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.categoryService = categoryService;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public List<Book> findAllByCategoryId(Long categoryId) {
        return this.bookRepository.findAllByCategoryId(categoryId);
    }

    @Override
    public List<Book> findAllSortedByNumberOfSamples(boolean asc) {
        return this.bookRepository.findAllSortedByNumberOfSamples(asc);
    }

    @Override
    public Book findById(Long id) {
        return this.bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    @Override
    public Book saveBook(Book book, MultipartFile image) throws IOException {
        Category category = this.categoryService.findById(book.getCategory().getId());
        book.setCategory(category);
        if(image != null && !image.getName().isEmpty()){
            byte[] bytes = image.getBytes();
            String base64Image =String.format("data:%s;base64,%s", image.getContentType(), Base64.getEncoder().encodeToString(bytes));
            book.setImageBase64(base64Image);
        }
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long id, Book book, MultipartFile image) throws IOException {
        Book b = this.findById(id);
        Category c = this.categoryService.findById(book.getCategory().getId());
        b.setCategory(c);
        b.setName(book.getName());
        b.setNumberSamples(book.getNumberSamples());
        if(image != null && !image.getName().isEmpty()){
            byte[] bytes = image.getBytes();
            String base64Image = String.format("data:%s;base64,%s", image.getContentType(), Base64.getEncoder().encodeToString(bytes));
            b.setImageBase64(base64Image);
        }
        return this.bookRepository.save(b);
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }
}
