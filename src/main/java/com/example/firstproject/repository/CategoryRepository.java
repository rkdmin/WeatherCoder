package com.example.firstproject.repository;

import com.example.firstproject.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findBySeasonAndName(String season, String name);
}