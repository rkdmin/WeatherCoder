package com.example.weatherCoder.service;

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
    public CommentDto edit(Long id, CommentDto commentDto) {
//        commentDto.setId(id);// id 설정
//        // 예외
//        Comment commentEntity = commentRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("댓글 수정 실패! 댓글이 없습니다."));// 1.요청한 id로 찾아본 데이터가 db에 없는 경우
//        if(id != commentDto.getId()){
//            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 id가 입력되었습니다.");// 2. 요청한 id와 db속 id가 다른 경우
//        }
//
//        // 수정
//        commentEntity.patch(commentDto);
//
//        // 저장
//        Comment edited = commentRepository.save(commentEntity);
//
//        // 결과
//        return edited.toDto();

        return null;
    }

    // 댓글삭제
    @Transactional
    public CommentDto delete(Long id) {
        // 조회 및 예외처리
        Comment target = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("댓글 삭제 실패! 댓글이 없습니다."));// db에 요청한 데이터가 없음

        // db 에서 삭제
        commentRepository.delete(target);

        return null;
    }

    private static void createValidation(Long articleId, CommentDto commentDto,
        Optional<Article> optionalArticle) {
        if(!optionalArticle.isPresent()){
            throw new CommentException(ErrorCode.INVALID_REQUEST);
        }
        if(commentDto.getId() != null){// 2. 댓글엔 id가 필요없는데 있다면
            throw new CommentException(ErrorCode.EXIST_ID);
        }
        if(articleId != optionalArticle.get().getId()){// 3. 요청된 id와 db에있는 article id가 다를 경우
            throw new CommentException(ErrorCode.INVALID_ARTICLE_ID);
        }
    }
}
