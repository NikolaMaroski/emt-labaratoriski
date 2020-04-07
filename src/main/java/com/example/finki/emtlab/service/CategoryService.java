package com.example.finki.emtlab.service;

import com.example.finki.emtlab.model.Category;
import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();
    List<Category> findAllByName(String name);
    Category findById(Long id);
    Category save(Category category);
    Category update(Long id, Category category);
    Category updateName(Long id, String name);
    void deleteById(Long id);
}
