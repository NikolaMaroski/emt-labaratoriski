package com.example.finki.emtlab.web.controller;

import com.example.finki.emtlab.model.Book;
import com.example.finki.emtlab.model.Category;
import com.example.finki.emtlab.service.BookService;
import com.example.finki.emtlab.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/books")
public class BookController {

    private BookService bookService;
    private CategoryService categoryService;

    public BookController(BookService bookService, CategoryService categoryService) {
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getBookPage(Model model){
        List<Book> books = this.bookService.findAll();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/add-new")
    public String addNewBookPage(Model model){
        List<Category> categories = this.categoryService.findAll();
        model.addAttribute("categories",categories  );
        model.addAttribute("book", new Book());
        return "add-book";
    }

    @GetMapping("/{id}/edit")
    public String editBookPage(Model model, @PathVariable Long id){
        try{
            Book book = this.bookService.findById(id);
            List<Category> categories = this.categoryService.findAll();
            model.addAttribute("book", book);
            model.addAttribute("categories", categories);
            return "add-book";
        }catch (RuntimeException ex){
            return "redirect:/books?error=" + ex.getMessage();
        }
    }

    @PostMapping
    public String SaveBook(@Valid Book book, BindingResult bindingResult, @RequestParam MultipartFile image, Model model){
        if(bindingResult.hasErrors()){
            List<Category> categories = this.categoryService.findAll();
            model.addAttribute("categories", categories);
            return "add-book";
        }
        try {
           this.bookService.saveBook(book, image);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/books";
    }

    @PostMapping("/{id}/delete")
    public String deleteBookWithPost(@PathVariable Long id){
        this.bookService.deleteById(id);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteBookWithDelete(@PathVariable Long id){
        this.bookService.deleteById(id);
        return "redirect:/books";
    }

}
