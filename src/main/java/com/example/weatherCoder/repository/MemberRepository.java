package com.example.weatherCoder.repository;


import com.example.weatherCoder.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // 특정 게시글의 모든 댓글 조회(1. 어노테이션 사용)

//    Member findByUserId(@Param("userId")String userId);

    // 이메일 중복 조회
    Optional<Member> findByEmail(String email);

    // 이메일 키 찾기
    Optional<Member> findByEmailKey(String emailKey);

    // 로그인 프로세스
    Optional<Member> findByEmailAndPassword(String userId, String secPassword);

    // 패스워드 키 찾기
    Optional<Member> findByPasswordKey(String passwordKey);
}