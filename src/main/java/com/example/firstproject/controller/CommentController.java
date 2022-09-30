package com.example.firstproject.controller;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Transactional
public class CommentController {
    private final CommentService commentService;

    // 댓글 목록 조회
    @GetMapping("/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> showCommentList(@PathVariable Long articleId){
        // 서비스에게 위임
        List<CommentDto> commentDtoList = commentService.showCommentList(articleId);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(commentDtoList);
    }

    // 댓글 생성
    @PostMapping("/articles/{articleId}/comment/new")
    public ResponseEntity<CommentDto> createComment(@PathVariable Long articleId, @RequestBody CommentDto commentDto){

        CommentDto created = commentService.create(articleId, commentDto);

        return ResponseEntity.status(HttpStatus.OK).body(created);
    }

    // 댓글 수정
    @PatchMapping("/articles/comment/{id}/edit")
    public ResponseEntity<CommentDto> editComment(@PathVariable Long id, @RequestBody CommentDto commentDto){

        CommentDto edited = commentService.edit(id, commentDto);

        return ResponseEntity.status(HttpStatus.OK).body(edited);
    }

    // 댓글 삭제
    @DeleteMapping("/articles/comment/{id}/delete")
    public ResponseEntity<CommentDto> deleteComment(@PathVariable Long id){

        CommentDto deleted = commentService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
