package com.example.firstproject.ioc;

import org.springframework.stereotype.Component;


public abstract class Ingredient {
    private String name;

    public String getName() {
        return name;
    }

    public Ingredient(String name) {
        this.name = name;
    }
}