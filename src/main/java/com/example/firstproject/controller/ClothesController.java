//package com.example.firstproject.controller;
//
//import com.example.firstproject.dto.ClothesDto;
//import com.example.firstproject.dto.MemberDto;
//import com.example.firstproject.entity.Clothes;
//import com.example.firstproject.jwt.JwtTokenProvider;
//import com.example.firstproject.service.ArticleService;
//import com.example.firstproject.service.ClothesService;
//import com.example.firstproject.service.MemberService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.security.NoSuchAlgorithmException;
//
//@RestController
//public class ClothesController {
//    @Autowired
//    MemberService memberService;
//    @Autowired
//    ClothesService clothesService;
//
//    @PostMapping("/articles/suggest/{number}")
//    public ResponseEntity<ClothesDto> suggest1(@RequestBody ClothesDto dto, @PathVariable Integer number) throws NoSuchAlgorithmException{
//        ClothesDto clothesDto = null;
//
//        switch(number){
//            case 1:
//                clothesDto = clothesService.suggest1(dto);
//            case 2:
//                clothesDto = clothesService.suggest2();
//            case 3:
//                clothesDto = clothesService.suggest3();
//        }
//
//
//        return null;
//    }
//
//    @PostMapping("/join")
//    public ResponseEntity<MemberDto> create(@RequestBody MemberDto dto) throws NoSuchAlgorithmException {
//        // 서비스에게 위임
//        MemberDto memberDto = memberService.create(dto);
//
//        return (memberDto != null) ?
//                ResponseEntity.status(HttpStatus.OK).body(memberDto):
//                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//    }
//
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
//        // 토큰생성
//        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
//        String token = jwtTokenProvider.makeJwtToken(dto.getUserId(), dto.getEmail());
////        return (memberDto != null) ?
////                ResponseEntity.status(HttpStatus.OK).header("token", token).body(memberDto):
////                ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); 토큰사용=> 유지보수때 수정예정
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
//}