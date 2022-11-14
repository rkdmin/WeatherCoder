package com.example.weatherCoder.repository;

import com.example.weatherCoder.entity.Clothes;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClothesRepository extends JpaRepository<Clothes, Long> {

    @Query(value = "SELECT * FROM Clothes c WHERE c.temp <= :temp + 3 AND " +
            "c.temp >= :temp - 3 AND  c.gender = :gender ORDER BY RAND() " +
            "LIMIT 1", nativeQuery = true)
    Clothes suggest1(int temp, String gender);

    @Query(value = "SELECT * FROM Clothes c WHERE c.temp <= :temp + 3 AND " +
            "c.temp >= :temp - 3 AND c.is_outer = :isOuter AND c.gender = " +
            ":gender" +
            " ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Clothes suggest1Outer(int temp, boolean isOuter, String gender);

    @Query(value = "SELECT * FROM Clothes c WHERE c.temp <= :temp + 3 AND " +
            "c.temp >= :temp - 3 AND c.gender = :gender AND c.age = :age" +
            " AND c.height = :height AND c.weight = :weight AND c.style_name = :styleName" +
            "  ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Clothes suggest2(int temp, String gender, int age, int height,
                     int weight, String styleName);

    @Query(value = "SELECT * FROM Clothes c WHERE c.temp <= :temp + 3 AND " +
            "c.temp >= :temp - 3 AND c.gender = :gender AND c.is_outer = :isOuter" +
            " AND c.age = :age AND c.height = :height AND c.weight = :weight AND c.style_name = :styleName" +
            "  ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Clothes suggest2Outer(int temp, String gender, boolean isOuter, int age,
                          int height, int weight, String styleName);

    @Query(value = "SELECT * FROM Clothes c WHERE c.temp <= :temp + 3 AND " +
        "c.temp >= :temp - 3 AND c.gender = :gender AND c.id = :clothesId", nativeQuery = true)
    Optional<Clothes> suggest3(int temp, String gender, long clothesId);

    @Query(value = "SELECT * FROM Clothes c WHERE c.temp <= :temp + 3 AND " +
        "c.temp >= :temp - 3 AND c.gender = :gender AND c.is_outer = :isOuter AND c.id = :clothesId ", nativeQuery = true)
    Optional<Clothes> suggest3Outer(int temp, String gender, boolean isOuter, long clothesId);



}