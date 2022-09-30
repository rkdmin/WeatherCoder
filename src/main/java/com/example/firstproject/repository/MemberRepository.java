package com.example.firstproject.repository;

import com.example.firstproject.dto.MemberDto;
import com.example.firstproject.entity.Clothes;
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

//    Member findByUserId(@Param("userId")String userId);

    // 아이디 중복 조회
    Member findByUserId(String userId);
    // 이메일 중복 조회
    Member findByEmail(String email);
    // 닉네임 중복 조회
    Member findByNickname(String nickname);
    // 로그인 프로세스
    @Query(value = "SELECT m FROM Member m WHERE m.userId = :userId AND " +
            "m.password = :secPassword")
    Member loginProcess(String userId, String secPassword);
}