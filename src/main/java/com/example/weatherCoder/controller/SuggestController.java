package com.example.weatherCoder.controller;

import com.example.weatherCoder.dto.Suggest1;
import com.example.weatherCoder.dto.Suggest2;
import com.example.weatherCoder.exception.MemberException;
import com.example.weatherCoder.service.SuggestService;

import com.example.weatherCoder.type.ErrorCode;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SuggestController {
    private final SuggestService clothesService;

    @PostMapping("/suggest1-non-member")
    public Suggest1.Response suggest1NonMember(
            @Valid @RequestBody Suggest1.Request request,
            BindingResult bindingResult
            ){

        // @valid 발생
        if (bindingResult.hasErrors()) {
            List<FieldError> list = bindingResult.getFieldErrors();
            throw new MemberException(ErrorCode.INVALID_REQUEST, list.get(0).getDefaultMessage().toString());
        }

        List<Long> list;
        list = clothesService.suggest1(request.toClothesDtoList());

        // 비회원이면 추천을 저장하지 않음
        return Suggest1.Response.toResponse(list);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/suggest2")
    public Suggest2.Response suggest2(Principal principal, @Valid @RequestBody Suggest2.Request request,
            BindingResult bindingResult
    ){
        // @valid 발생
        if (bindingResult.hasErrors()) {
            List<FieldError> list = bindingResult.getFieldErrors();
            throw new MemberException(ErrorCode.INVALID_REQUEST, list.get(0).getDefaultMessage().toString());
        }

        List<Long> list = clothesService.suggest2(request.toClothesDtoList(principal.getName()));

        // 비회원이면 추천을 저장하지 않음
        return Suggest2.Response.toResponse(list);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/suggest3")
    public Suggest2.Response suggest3(Principal principal, @Valid @RequestBody Suggest2.Request request,
        BindingResult bindingResult
    ){
        // @valid 발생
        if (bindingResult.hasErrors()) {
            List<FieldError> list = bindingResult.getFieldErrors();
            throw new MemberException(ErrorCode.INVALID_REQUEST, list.get(0).getDefaultMessage().toString());
        }

        List<Long> list = clothesService.suggest3(request.toClothesDtoList(principal.getName()));

        return Suggest2.Response.toResponse(list);
    }
}