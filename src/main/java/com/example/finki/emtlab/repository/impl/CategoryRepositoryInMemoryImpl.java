package com.example.finki.emtlab.repository.impl;

import com.example.finki.emtlab.model.Category;
import com.example.finki.emtlab.repository.CategoryRepository;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class CategoryRepositoryInMemoryImpl implements CategoryRepository {

    private HashMap<Long, Category> categories;
    private AtomicLong counter;

    public CategoryRepositoryInMemoryImpl() {
        this.categories = new HashMap<>();
        this.counter = new AtomicLong(0);
        Category c1 = new Category(1L, "Comic", "Comics and Graphic Novel...");
        Category c2 = new Category(2L, "Fairy Tale", "Fairy Tales comes true one upon a time...");
        this.categories.put(c1.getId(), c1);
        this.categories.put(c2.getId(), c2);
    }

    @Override
    public List<Category> findAll() {
        return new ArrayList<>(this.categories.values());
    }

    @Override
    public Optional<Category> findById(Long id) {
        return Optional.ofNullable(this.categories.get(id));
    }

    @Override
    public List<Category> findByName(String name) {
        return this.categories.values()
                .stream()
                .filter(item -> item.getName().contains(name))
                .collect(Collectors.toList());
    }

    @Override
    public Category save(Category category) {
        if(category.getId() == null){
            category.setId(this.counter.getAndIncrement());
        }
        this.categories.put(category.getId(), category);
        return category;
    }

    @Override
    public void deleteById(Long id) {
        this.categories.remove(id);
    }
}
