package com.example.weatherCoder.repository;

import com.example.weatherCoder.entity.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // 특정 게시글의 모든 댓글 조회(1. 어노테이션 사용)
    @Query(value = "SELECT c FROM Comment c WHERE c.article.id = :articleId")
    List<Comment> findByArticleId(Long articleId);

    // 특정 닉네임의 모든 댓글 조회(2. 네이티브 쿼리 xml 사용)
    List<Comment> findByNickname(String nickname);

}
