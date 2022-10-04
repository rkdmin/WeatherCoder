package com.example.firstproject.controller;

import com.example.firstproject.dto.Suggest1;
import com.example.firstproject.service.SuggestService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SuggestController {
    private final SuggestService clothesService;

    @GetMapping("/suggest1")
    public Suggest1.Response suggest1(
            @Valid @RequestBody Suggest1.Request request
            ){
     22   List<Long> list;
        list = clothesService.suggest1(request.toClothesDtoList());

        // 비회원이면 추천을 저장하지 않음
        return Suggest1.Response.toResponse(list);
    }
}