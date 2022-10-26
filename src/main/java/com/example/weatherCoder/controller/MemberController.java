package com.example.weatherCoder.controller;

import com.example.weatherCoder.dto.CategoryParam;
import com.example.weatherCoder.dto.MemberDto;
import com.example.weatherCoder.dto.StyleParam;
import com.example.weatherCoder.exception.MemberException;
import com.example.weatherCoder.service.MemberCategoryService;
import com.example.weatherCoder.service.MemberService;
import com.example.weatherCoder.service.MemberStyleService;
import com.example.weatherCoder.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final MemberCategoryService memberCategoryService;
    private final MemberStyleService memberStyleService;

    @PostMapping("/join")
    public String create(@RequestBody @Valid MemberDto.Request request,
                       BindingResult bindingResult) throws NoSuchAlgorithmException {

        // @valid 발생
        validation(bindingResult);

        memberService.create(request);

        return "회원가입이 완료되었습니다.";
    }

    @GetMapping("/email")
    public String emailCert(HttpServletRequest request){
        String emailKey = request.getParameter("id");

        memberService.emailCert(emailKey);

        return "이메일 인증이 완료되었습니다.";
    }

    @PostMapping("/my-clothes")
    public String setCategory(@RequestBody @Valid CategoryParam.Request request,
                              BindingResult bindingResult){
        // @valid 발생
        validation(bindingResult);

        memberCategoryService.registration(request);

        return "카테고리 저장이 완료되었습니다.";
    }

    @PostMapping("/my-style")
    public String setStyle(@RequestBody @Valid StyleParam.Request request,
        BindingResult bindingResult){
        // @valid 발생
        validation(bindingResult);

        memberStyleService.update(request);

        return "스타일 수정이 완료되었습니다.";
    }

    private static void validation(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> list = bindingResult.getFieldErrors();
            throw new MemberException(ErrorCode.INVALID_REQUEST, list.get(0).getDefaultMessage().toString());
        }
    }


//    // 로그인
//    @PostMapping("/login")
//    public ResponseEntity<MemberDto> login(@RequestBody MemberDto dto) throws NoSuchAlgorithmException{
//
//        String userId = dto.getUserId();// 아이디
//        String password = dto.getPassword();// 패스워드
//
//        // 서비스에게 위임
//        MemberDto memberDto = memberService.login(userId, password);
//
////        // 토큰생성
////        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
////        String token = jwtTokenProvider.makeJwtToken(dto.getUserId(), dto.getEmail());
//////        return (memberDto != null) ?
//////                ResponseEntity.status(HttpStatus.OK).header("token", token).body(memberDto):
//////                ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); 토큰사용=> 유지보수때 수정예정
//
//        return (memberDto != null) ?
//                ResponseEntity.status(HttpStatus.OK).body(memberDto):
//                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//    }
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