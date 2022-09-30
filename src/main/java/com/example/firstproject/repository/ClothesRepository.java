package com.example.firstproject.repository;

import com.example.firstproject.entity.Clothes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClothesRepository extends JpaRepository<Clothes, Long> {

    @Query(value = "SELECT c.id FROM Clothes c WHERE c.temp <= :temp + 3 AND " +
            "c.temp >= :temp - 3 AND  c.gender = :gender ORDER BY RAND() " +
            "LIMIT 1", nativeQuery = true)
    Long suggest1(int temp, String gender);

    @Query(value = "SELECT c.id FROM Clothes c WHERE c.temp <= :temp + 3 AND " +
            "c.temp >= :temp - 3 AND c.is_outer = :isOuter AND c.gender = " +
            ":gender" +
            " ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Long suggest1Outer(int temp, boolean isOuter, String gender);

}