package com.example.weatherCoder.controller;

import com.example.weatherCoder.dto.CategoryParam;
import com.example.weatherCoder.dto.ChangePasswordRequest;
import com.example.weatherCoder.dto.MemberDto;
import com.example.weatherCoder.dto.StyleParam;
import com.example.weatherCoder.exception.MemberException;
import com.example.weatherCoder.security.TokenProvider;
import com.example.weatherCoder.service.MemberCategoryService;
import com.example.weatherCoder.service.MemberService;
import com.example.weatherCoder.service.MemberStyleService;
import com.example.weatherCoder.type.ErrorCode;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final MemberCategoryService memberCategoryService;
    private final MemberStyleService memberStyleService;
    private final TokenProvider tokenProvider;

    @PostMapping("/join")
    public String create(@RequestBody @Valid MemberDto.Request request,
                       BindingResult bindingResult) throws NoSuchAlgorithmException {

        // @valid 발생
        validation(bindingResult);

        memberService.create(request);

        return "이메일 전송이 완료되었습니다.";
    }

    @GetMapping("/email")
    public String emailCert(HttpServletRequest request){
        String emailKey = request.getParameter("id");

        memberService.emailCert(emailKey);

        return "이메일 인증이 완료되었습니다.";
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/my-clothes")
    public String setCategory(Principal principal, @RequestBody @Valid CategoryParam.Request request,
                              BindingResult bindingResult){
        // @valid 발생
        validation(bindingResult);

        memberCategoryService.registration(principal.getName(), request);

        return "카테고리 저장이 완료되었습니다.";
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/my-style")
    public String setStyle(Principal principal, @RequestBody @Valid StyleParam.Request request,
        BindingResult bindingResult){
        // @valid 발생
        validation(bindingResult);

        memberStyleService.update(principal.getName(), request);

        return "스타일 수정이 완료되었습니다.";
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestBody @Valid ChangePasswordRequest request,
        BindingResult bindingResult) throws NoSuchAlgorithmException {

        // @valid 발생
        validation(bindingResult);

        memberService.changePassword(request);

        return "메일 전송이 완료되었습니다.";
    }

    @GetMapping("/passwordCert")
    public String passwordCert(HttpServletRequest request){
        String passwordKey = request.getParameter("key");
        String newSecPassword = request.getParameter("pa");

        memberService.passwordCert(passwordKey, newSecPassword);

        return "비밀번호 변경이 완료되었습니다.";
    }

    private static void validation(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> list = bindingResult.getFieldErrors();
            throw new MemberException(ErrorCode.INVALID_REQUEST, list.get(0).getDefaultMessage().toString());
        }
    }


    // 로그인
    @PostMapping("/login")
    public MemberDto.Response login(@RequestBody MemberDto.Request request)
        throws NoSuchAlgorithmException {

        MemberDto.Response response = memberService.login(request.getUsername(), request.getPassword());
        String token = tokenProvider.generateToken(response.getUsername(), response.getRoles());
        response.setToken(token);
        return response;
    }
//
//    // 아이디 찾기
//    @PostMapping("/login/{email}")
//    public ResponseEntity<MemberDto> findUserId(@PathVariable String email) throws NoSuchAlgorithmException {
//
//        // 서비스에게 위임
//        MemberDto memberDto = memberService.findUserId(email);
//
//        return (memberDto != null) ?
//                ResponseEntity.status(HttpStatus.OK).body(memberDto) :
//                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//    }
}