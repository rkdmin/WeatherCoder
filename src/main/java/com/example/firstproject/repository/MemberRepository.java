package com.example.firstproject.repository;

import com.example.firstproject.entity.Comment;
import com.example.firstproject.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.Map;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // 특정 게시글의 모든 댓글 조회(1. 어노테이션 사용)
//    @Query(value = "SELECT * FROM member WHERE user_id = :userId", nativeQuery = true)
//    Member findByUserId(@Param("userId")String userId);

    // 아이디 중복 조회
    Member findByUserId(String userId);

}
