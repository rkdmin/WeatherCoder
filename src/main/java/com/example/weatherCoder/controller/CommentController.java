package com.example.weatherCoder.controller;

import com.example.weatherCoder.dto.CommentDto;
import com.example.weatherCoder.exception.ArticleException;
import com.example.weatherCoder.exception.CommentException;
import com.example.weatherCoder.service.CommentService;
import com.example.weatherCoder.type.ErrorCode;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
    public List<CommentDto> showCommentList(@PathVariable Long articleId){

        return commentService.showCommentList(articleId);
    }

    // 댓글 생성
    @PostMapping("/articles/{articleId}/comment/new")
    public String createComment(@PathVariable Long articleId,
        @RequestBody @Valid CommentDto commentDto, BindingResult bindingResult){

        // @valid 발생
        validation(bindingResult);

        commentService.create(articleId, commentDto);

        return "댓글 작성이 완료되었습니다.";
    }

    // 댓글 수정
    @PatchMapping("/articles/comment/{id}/edit")
    public String editComment(@PathVariable Long id, @RequestBody CommentDto commentDto){

        commentService.edit(id, commentDto);

        return "댓글 수정이 완료되었습니다.";
    }

    // 댓글 삭제
    @DeleteMapping("/articles/comment/{id}/delete")
    public String deleteComment(@PathVariable Long id){

        commentService.delete(id);

        return "댓글 삭제가 완료되었습니다.";
    }

    private static void validation(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> list = bindingResult.getFieldErrors();
            throw new CommentException(ErrorCode.INVALID_REQUEST, list.get(0).getDefaultMessage().toString());
        }
    }
}
