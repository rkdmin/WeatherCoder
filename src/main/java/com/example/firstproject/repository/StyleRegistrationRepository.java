package com.example.firstproject.repository;

import com.example.firstproject.entity.StyleRegistration;
import com.example.firstproject.entity.Suggest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StyleRegistrationRepository extends JpaRepository<StyleRegistration, Long> {

}