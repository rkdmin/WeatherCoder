package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleVO;
import com.example.firstproject.dto.CommentVO;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

class CommentServiceTest {
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    CommentRepository commentRepository;

    @Test
    void showCommentList(){
        
    }

    @Test
    @Transactional
    void create() {
    }

    @Test
    void edit() {
    }

    @Test
    void delete() {
    }
}