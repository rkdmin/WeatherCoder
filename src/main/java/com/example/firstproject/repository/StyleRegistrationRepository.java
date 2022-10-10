package com.example.firstproject.repository;

import com.example.firstproject.entity.Member;
import com.example.firstproject.entity.StyleRegistration;
import com.example.firstproject.entity.Suggest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StyleRegistrationRepository extends JpaRepository<StyleRegistration, Long> {
    List<StyleRegistration> findAllByEmail(String email);

}