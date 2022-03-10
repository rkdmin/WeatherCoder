package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleVO;
import com.example.firstproject.dto.CommentVO;
import com.example.firstproject.dto.MemberDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Member;
import com.example.firstproject.service.ArticleService;
import com.example.firstproject.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

@RestController
public class MemberApiController {
    @Autowired
    MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<MemberDto> create(@RequestBody MemberDto dto) throws NoSuchAlgorithmException {
        // 서비스에게 위임
        MemberDto memberDto = memberService.create(dto);

        return (memberDto != null) ?
                ResponseEntity.status(HttpStatus.OK).body(memberDto):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<MemberDto> login(@RequestBody MemberDto dto) throws NoSuchAlgorithmException{
        String userId = dto.getUserId();// 아이디
        String password = dto.getPassword();// 패스워드

        // 서비스에게 위임
        MemberDto memberDto = memberService.login(userId, password);

        return (memberDto != null) ?
                ResponseEntity.status(HttpStatus.OK).body(memberDto):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

//    // GET
//    // 전체조회
//    @GetMapping("api/articles")
//    public List<Article> showList(){
//
//        // 모든 Article 데이터를 조회
//        return articleService.showList();
//    }
//
//    // id로 조회
//    @GetMapping("api/articles/{id}")
//    public Article show(@PathVariable Long id){
//        return articleService.show(id);
//    }
//
//    // POST
//    @PostMapping("api/articles/new")
//    public ResponseEntity<Article> create(@RequestBody ArticleVO dto){
//        Article created = articleService.create(dto);
//
//        return (created != null) ?
//                ResponseEntity.status(HttpStatus.OK).body(created):
//                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//    }
//
//    // PATCH
//    @PatchMapping("api/articles/{id}/edit")
//    public ResponseEntity<Article> edit(@RequestBody ArticleVO dto,
//                                        @PathVariable Long id){
//        Article updated = articleService.edit(dto, id);
//
//        return (updated != null) ?
//                ResponseEntity.status(HttpStatus.OK).body(updated):
//                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//    }
//
//    // DELETE
//    @DeleteMapping("api/articles/{id}/delete")
//    public ResponseEntity<Void> delete(@PathVariable Long id){
//        Article deleted = articleService.delete(id);
//
//        return (deleted != null) ?
//                ResponseEntity.status(HttpStatus.NO_CONTENT).build():
//                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//    }

}
