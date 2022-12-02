package com.example.weatherCoder.controller;

import com.example.weatherCoder.dto.ArticleDto;
import com.example.weatherCoder.exception.ArticleException;
import com.example.weatherCoder.service.ArticleService;
import com.example.weatherCoder.type.ErrorCode;
import java.util.List;
import javax.transaction.Transactional;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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
    public String edit(@RequestBody ArticleDto dto, @PathVariable Long id){

        articleService.edit(dto, id);

        return "게시글 수정이 완료되었습니다.";
    }

    // DELETE
    @DeleteMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id){
        articleService.delete(id);

        return "게시글 삭제가 완료되었습니다.";
    }

    @GetMapping("/articles/title/{title}")
    public Page<ArticleDto> showListByTitle(@PathVariable String title, final Pageable pageable){
        return articleService.showListByTitle(title, pageable);
    }

    private static void validation(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> list = bindingResult.getFieldErrors();
            throw new ArticleException(ErrorCode.INVALID_REQUEST, list.get(0).getDefaultMessage().toString());
        }
    }

}
