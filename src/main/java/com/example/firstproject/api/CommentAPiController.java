package com.example.firstproject.api;

import com.example.firstproject.annotation.RunningTime;
import com.example.firstproject.dto.CommentVO;
import com.example.firstproject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentAPiController {
    @Autowired
    CommentService commentService;

    // 댓글 목록 조회
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentVO>> showCommentList(@PathVariable Long articleId){
        // 서비스에게 위임
        List<CommentVO> commentDtoList = commentService.showCommentList(articleId);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(commentDtoList);
    }

    // 댓글 생성
    @PostMapping("/api/articles/{articleId}/new-comment")
    public ResponseEntity<CommentVO> createComment(@PathVariable Long articleId, @RequestBody CommentVO commentDto){

        CommentVO created = commentService.create(articleId, commentDto);

        return ResponseEntity.status(HttpStatus.OK).body(created);
    }

    // 댓글 수정
    @PatchMapping("/api/articles/{id}/edit-comment")
    public ResponseEntity<CommentVO> editComment(@PathVariable Long id, @RequestBody CommentVO commentDto){

        CommentVO edited = commentService.edit(id, commentDto);

        return ResponseEntity.status(HttpStatus.OK).body(edited);
    }

    // 댓글 삭제
    @RunningTime
    @DeleteMapping("/api/articles/{id}/delete-comment")
    public ResponseEntity<CommentVO> deleteComment(@PathVariable Long id){

        CommentVO deleted = commentService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
