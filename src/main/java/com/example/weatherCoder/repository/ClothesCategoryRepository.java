package com.example.weatherCoder.repository;

import com.example.weatherCoder.entity.ClothesCategory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClothesCategoryRepository extends JpaRepository<ClothesCategory, Long> {

    @Query(value = "select distinct c.clothes_id from clothes_category c where c.category_id = :categoryId"
        , nativeQuery = true)
    List<Long> getClothesIdByCategoryId(long categoryId);
}