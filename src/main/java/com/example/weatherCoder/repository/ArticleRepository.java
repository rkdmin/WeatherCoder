package com.example.weatherCoder.repository;

import com.example.weatherCoder.entity.Article;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Override
    ArrayList<Article> findAll();

    Page<Article> findByTitleContaining(String title, Pageable pageable);
}
