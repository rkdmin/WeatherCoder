package com.example.firstproject.controller;
import com.example.firstproject.dto.MemberDto;
import com.example.firstproject.exception.MemberException;
import com.example.firstproject.service.ArticleService;
import com.example.firstproject.service.MemberService;
import com.example.firstproject.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/join")
    public String create(@RequestBody @Valid MemberDto.Request request,
                       BindingResult bindingResult) throws NoSuchAlgorithmException {

        // @valid 발생
        if (bindingResult.hasErrors()) {
            List<FieldError> list = bindingResult.getFieldErrors();
            throw new MemberException(ErrorCode.INVALID_REQUEST, list.get(0).getDefaultMessage().toString());
        }

        memberService.create(request);

        return "회원가입이 완료되었습니다.";
    }

    @GetMapping("/email")
    public String emailCert(HttpServletRequest request){
        String emailKey = request.getParameter("id");

        memberService.emailCert(emailKey);

        return "이메일 인증이 완료되었습니다.";
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