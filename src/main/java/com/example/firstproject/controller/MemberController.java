package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller// 브라우저로부터 요청을 받음
public class MemberController {
    @GetMapping("/join")// 접속할 url
    public String join() {
        return "main/login";
    }
    @GetMapping("/login")// 접속할 url
    public String login() {
        return "main/login";
    }
}
