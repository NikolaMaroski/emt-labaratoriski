package com.example.finki.emtlab.model;

import lombok.Data;

@Data
public class Book {

    private Long id;
    private String name;
    private Long numberSamples;
    private Category category;
    private String imageBase64;

    public Book(Long id, String name, Long numberSamples, Category category) {
        this.id = id;
        this.name = name;
        this.numberSamples = numberSamples;
        this.category = category;
    }

    public Book() {
    }
}
