package com.example.firstproject.controller;
import com.example.firstproject.dto.MemberDto;
import com.example.firstproject.jwt.JwtTokenProvider;
import com.example.firstproject.service.ArticleService;
import com.example.firstproject.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;

@RestController
public class MemberController {
    @Autowired
    MemberService memberService;
    @Autowired
    ArticleService articleService;

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
    public ResponseEntity<MemberDto> login(@RequestBody MemberDto dto, HttpServletRequest request) throws NoSuchAlgorithmException{

        String userId = dto.getUserId();// 아이디
        String password = dto.getPassword();// 패스워드

        // 서비스에게 위임
        MemberDto memberDto = memberService.login(userId, password, request);

        // 토큰생성
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
        String token = jwtTokenProvider.makeJwtToken(dto.getUserId(), dto.getEmail());
//        return (memberDto != null) ?
//                ResponseEntity.status(HttpStatus.OK).header("token", token).body(memberDto):
//                ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); 토큰사용=> 유지보수때 수정예정

        return (memberDto != null) ?
                ResponseEntity.status(HttpStatus.OK).body(memberDto):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // 아이디 찾기
    @PostMapping("/login/{email}")
    public ResponseEntity<MemberDto> findUserId(@PathVariable String email) throws NoSuchAlgorithmException {

        // 서비스에게 위임
        MemberDto memberDto = memberService.findUserId(email);

        return (memberDto != null) ?
                ResponseEntity.status(HttpStatus.OK).body(memberDto) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}