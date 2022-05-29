package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController// RestAPI 용 컨트롤러! 데이터(JSON)를 반환
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    // GET
    // 전체조회
    @GetMapping("/articles")
    public List<Article> showList(){

        // 모든 Article 데이터를 조회
        return articleService.showList();
    }

    // id로 조회
    @GetMapping("/articles/{id}")
    public Article show(@PathVariable Long id){
        return articleService.show(id);
    }

    // POST
    @PostMapping("/articles/new")
    public ResponseEntity<Article> create(@RequestBody ArticleDto dto){
        Article created = articleService.create(dto);

        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // PATCH
    @PatchMapping("/articles/{id}/edit")
    public ResponseEntity<Article> edit(@RequestBody ArticleDto dto,
                        @PathVariable Long id){
        Article updated = articleService.edit(dto, id);

        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
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


}
