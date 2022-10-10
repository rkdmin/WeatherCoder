package com.example.firstproject.repository;

import com.example.firstproject.entity.Style;
import com.example.firstproject.entity.StyleRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StyleRepository extends JpaRepository<Style, String> {
    Optional<Style> findByStyleName(String styleName);
}