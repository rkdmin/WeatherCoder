package com.example.weatherCoder.controller;

import com.example.weatherCoder.dto.ArticleDto;
import com.example.weatherCoder.entity.Article;
import com.example.weatherCoder.exception.ArticleException;
import com.example.weatherCoder.exception.MemberException;
import com.example.weatherCoder.service.ArticleService;
import com.example.weatherCoder.type.ErrorCode;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
@Slf4j
@RestController
@RequiredArgsConstructor
@Transactional
public class ArticleController {

    private final ArticleService articleService;

    // GET
    // 전체조회
    @GetMapping("/articles")
    public List<ArticleDto> showList(){
        // 모든 Article 데이터를 조회
        return articleService.showList();
    }

    // id로 조회
    @GetMapping("/articles/{id}")
    public ArticleDto show(@PathVariable Long id){
        return articleService.show(id);
    }

    // POST
    @PostMapping("/articles/new")
    public String create(@RequestBody @Valid ArticleDto dto, BindingResult bindingResult){

        // @valid 발생
        validation(bindingResult);

        articleService.create(dto);

        return "게시글 작성이 완료되었습니다.";
    }

    // PATCH
    @PatchMapping("/articles/{id}/edit")
    public String edit(@RequestBody ArticleDto dto, BindingResult bindingResult,
                        @PathVariable Long id){

        articleService.edit(dto, id);

        return "게시글 수정이 완료되었습니다.";
    }

    // DELETE
    @DeleteMapping("/articles/{id}/delete")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        Article deleted = articleService.delete(id);

        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build():
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // 트랜잭션 -> 실패 -> 롤백!
    @PostMapping("/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleDto> dtos){
        List<Article> createdList = articleService.createArticles(dtos);
        return (createdList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(createdList):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    private static void validation(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> list = bindingResult.getFieldErrors();
            throw new ArticleException(ErrorCode.INVALID_REQUEST, list.get(0).getDefaultMessage().toString());
        }
    }

}