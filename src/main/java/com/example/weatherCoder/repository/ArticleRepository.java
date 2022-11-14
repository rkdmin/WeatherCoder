package com.example.weatherCoder.repository;

import com.example.weatherCoder.entity.Article;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Override
    ArrayList<Article> findAll();
}
