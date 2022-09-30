package com.example.firstproject.repository;

import com.example.firstproject.entity.Clothes;
import com.example.firstproject.entity.Suggest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface SuggestRepository extends JpaRepository<Suggest, Long> {

}