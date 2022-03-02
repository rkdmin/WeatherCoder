package com.example.firstproject.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController// Controller는 페이지(view)를 반환하지만 이건 JSON 즉, 데이터를 반환
public class FirstApiController {

    @GetMapping("/api/hello")
    public String hello(){
        return "hello world";
    }
}
