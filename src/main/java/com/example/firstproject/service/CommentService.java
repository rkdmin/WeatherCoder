package com.example.firstproject.service;

import com.example.firstproject.annotation.RunningTime;
import com.example.firstproject.dto.CommentVO;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommentService {

    @Autowired private CommentRepository commentRepository;
    @Autowired private ArticleRepository articleRepository;// article 데이터도 사용하게 됨

    // 댓글 목록조회
    public List<CommentVO>showCommentList(Long articleId){
        // 조회
        List<Comment> commentEntityList = commentRepository.findByArticleId(articleId);

        // entity -> dto
//        List<CommentVO> commentDtoList = new ArrayList<CommentVO>();
//        for(int i = 0; i < commentEntityList.size(); i++){
//            Comment commentEntity = commentEntityList.get(i);// 하나하나 쪼개고
//            CommentVO commentDto = commentEntity.toDto();// dto로 변환해서
//            commentDtoList.add(commentDto);
//        }

        // 반환
        return commentRepository.findByArticleId(articleId)
                .stream()// 하나하나 꺼내옴
                .map(commentEntity -> commentEntity.toDto())// 꺼내온 commentEntity를 -> commentDto로 변환
                .collect(Collectors.toList());// 반환형이 List이기에 형변환
    }

    // 댓글 생성
    @Transactional
    public CommentVO create(Long articleId, CommentVO commentDto) {

        // 게시글 조회 및 예외 처리
        Article articleEntity = articleRepository.findById(articleId)// 1. 아이디가 없다면
                .orElseThrow(()-> new IllegalArgumentException("댓글 생성 실패! 대상 게시글이 없습니다."));
        if(commentDto.getId() != null){// 2. 댓글엔 id가 필요없는데 있다면
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야 합니다.");
        }
        if(articleId != articleEntity.getId()){// 3. 요청된 id와 db에있는 article id가 다를 경우
            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못되었습니다.");
        }

        // 엔티티로 변경
        Comment commentEntity = commentDto.toEntity(articleEntity);// articleEntity가 있어야 객체 생성가능

        // 댓글 엔티티를 DB에 저장
        Comment created = commentRepository.save(commentEntity);

        // 결과
        return created.toDto();// dto로 반환
    }

    // 댓글 수정
    @Transactional
    public CommentVO edit(Long id, CommentVO commentDto) {
        // 예외
        Comment commentEntity = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("댓글 수정 실패! 댓글이 없습니다."));// 1.요청한 id로 찾아본 데이터가 db에 없는 경우
        if(id != commentDto.getId()){
            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 id가 입력되었습니다.");// 2. 요청한 id와 db속 id가 다른 경우
        }

        // 수정
        commentEntity.patch(commentDto);

        // 저장
        Comment edited = commentRepository.save(commentEntity);

        // 결과
        return edited.toDto();
    }

    // 댓글삭제
    @Transactional
    public CommentVO delete(Long id) {
        // 조회 및 예외처리
        Comment target = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("댓글 삭제 실패! 댓글이 없습니다."));// db에 요청한 데이터가 없음

        // db 에서 삭제
        commentRepository.delete(target);

        return null;
    }
}
