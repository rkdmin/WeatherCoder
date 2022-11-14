package com.example.weatherCoder.repository;

import com.example.weatherCoder.entity.Style;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StyleRepository extends JpaRepository<Style, String> {
    Optional<Style> findByStyleName(String styleName);
}