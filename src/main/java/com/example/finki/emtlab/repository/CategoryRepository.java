package com.example.finki.emtlab.repository;

import com.example.finki.emtlab.model.Category;
import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    //CRUD
    List<Category> findAll();
    Optional<Category> findById(Long id);
    List<Category> findByName(String name);
    Category save(Category category);
    void deleteById(Long id);
}
