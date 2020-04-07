package com.example.finki.emtlab.web.rest_controller;

import com.example.finki.emtlab.model.Book;
import com.example.finki.emtlab.service.BookService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;


@RestController
@RequestMapping("/api/books")
public class BookRestController {

    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> findAll(){
        return this.bookService.findAll();
    }

    @GetMapping("/{id}")
    public Book findById(@PathVariable Long id){
        return this.bookService.findById(id);
    }

    @GetMapping("/by-category/{categoryId}")
    public List<Book> findAllByCategoryId(@PathVariable Long categoryId){
        return this.bookService.findAllByCategoryId(categoryId);
    }

    @GetMapping("/by-price")
    public List<Book> findAllSortedByNumberOfSamples(@RequestParam (required = false, defaultValue = "true") Boolean asc){
        return this.bookService.findAllSortedByNumberOfSamples(asc);
    }

    @PostMapping
    public Book saveBook(@Valid Book book,@RequestParam (required = false) MultipartFile image) throws IOException{
        return this.bookService.saveBook(book, image);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @Valid Book book, @RequestParam (required = false) MultipartFile image) throws IOException{
        return  this.bookService.updateBook(id, book, image);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        this.bookService.deleteById(id);
    }


}
