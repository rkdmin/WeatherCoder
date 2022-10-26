package com.example.weatherCoder.repository;

import com.example.weatherCoder.entity.MemberCategory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberCategoryRepository extends JpaRepository<MemberCategory, Long> {
    List<MemberCategory> findAllByMember_Email(String email);

    void deleteAllByMember_Email(String email);
}