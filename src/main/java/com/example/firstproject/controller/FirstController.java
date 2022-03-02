package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller// 브라우저로부터 요청을 받음
public class FirstController {

    @GetMapping("/hi")// 접속할 url
    public String Hello(Model model) {
        model.addAttribute("username", "22rkdmin");
        return "greetings";// templates/greetings.mustache -> 브라우저로 전송!
    }

    @GetMapping("/bye")
    public String Bye(Model model){
        model.addAttribute("nickname", "홍길동");
        return "goodbye";
    }

}
