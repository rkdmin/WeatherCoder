package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller// 브라우저로부터 요청을 받음
public class MemberController {
    // 회원가입 페이지
    @GetMapping("/join")
    public String join() {
        return "main/join";
    }

    // 로그인 페이지
    @GetMapping("/login")
    public String login() {
        return "main/login";
    }

}
