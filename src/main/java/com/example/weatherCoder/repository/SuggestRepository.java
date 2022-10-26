package com.example.weatherCoder.repository;

import com.example.weatherCoder.entity.Suggest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuggestRepository extends JpaRepository<Suggest, Long> {

}