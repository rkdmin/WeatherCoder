package com.example.weatherCoder.repository;

import com.example.weatherCoder.entity.MemberStyle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberStyleRepository extends JpaRepository<MemberStyle, Long> {
    List<MemberStyle> findAllByMember_Email(String email);
}