package com.example.weatherCoder.service;

import static com.example.weatherCoder.type.ErrorCode.*;

import com.example.weatherCoder.dto.CommentDto;
import com.example.weatherCoder.entity.Article;
import com.example.weatherCoder.entity.Comment;
import com.example.weatherCoder.exception.CommentException;
import com.example.weatherCoder.repository.ArticleRepository;
import com.example.weatherCoder.repository.CommentRepository;
import com.example.weatherCoder.type.ErrorCode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;// article 데이터도 사용하게 됨

    // 댓글 목록조회
    public List<CommentDto> showCommentList(Long articleId){
        // 조회
        List<Comment> commentList = commentRepository.findByArticleId(articleId);

        return CommentDto.toDtoList(commentList);
    }

    // 댓글 생성
    @Transactional
    public void create(Long articleId, CommentDto commentDto) {

        // 게시글 조회 및 예외 처리
        Optional<Article> optionalArticle = articleRepository.findById(articleId);
        createValidation(articleId, commentDto, optionalArticle);

        // 엔티티로 변경
        Comment commentEntity = Comment.toEntity(commentDto, optionalArticle.get());// articleEntity가 있어야 객체 생성가능

        // 댓글 엔티티를 DB에 저장
        commentRepository.save(commentEntity);
    }



    // 댓글 수정
    @Transactional
    public void edit(Long id, CommentDto commentDto) {
        commentDto.setId(id);// id 설정
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new CommentException(INVALID_COMMENT_ID));// 1.요청한 id로 찾아본 데이터가 db에 없는 경우
        if(id != commentDto.getId()){
            throw new CommentException(INVALID_COMMENT_ID);// 2. 요청한 id와 db속 id가 다른 경우
        }

        // 수정
        comment.patch(commentDto);

        commentRepository.save(comment);
    }

    // 댓글삭제
    @Transactional
    public void delete(Long id) {
        // 조회 및 예외처리
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new CommentException(COMMENT_EMPTY));// db에 요청한 데이터가 없음

        // db 에서 삭제
        commentRepository.delete(comment);
    }

    private static void createValidation(Long articleId, CommentDto commentDto,
        Optional<Article> optionalArticle) {
        if(!optionalArticle.isPresent()){
            throw new CommentException(INVALID_REQUEST);
        }
        if(commentDto.getId() != null){// 2. 댓글엔 id가 필요없는데 있다면
            throw new CommentException(EXIST_ID);
        }
        if(articleId != optionalArticle.get().getId()){// 3. 요청된 id와 db에있는 article id가 다를 경우
            throw new CommentException(INVALID_ARTICLE_ID);
        }
    }
}
