package com.example.firstproject.service;

import com.example.firstproject.aop.MailComponents;
import com.example.firstproject.dto.MemberDto;
import com.example.firstproject.entity.Member;
import com.example.firstproject.exception.MemberException;
import com.example.firstproject.repository.MemberRepository;
import com.example.firstproject.security.SHA256;
import com.example.firstproject.type.MemberStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import static com.example.firstproject.type.ErrorCode.ALREADY_EXISTS_EMAIL;
import static com.example.firstproject.type.ErrorCode.INVALID_EMAIL_KEY;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class MemberService{
    private final MemberRepository memberRepository;
    private final MailComponents mailComponents;

    private final MemberStyleService memberStyleService;


    // 회원가입
    public void create(MemberDto.Request request) throws NoSuchAlgorithmException {
        // 유효성 검사
        validCreate(request);

        // 엔티티로 변경
        Member member = Member.toEntity(request);

        // 비밀번호 암호화
        secPassword(member);

        // 회원 정보 저장
        memberRepository.save(member);

        // 스타일 등록
        memberStyleService.registration(member, request.getStyleList());

        // 메일 전송
        sendEmail(request, member.getEmailKey());
    }

    // 이메일 처리
    public void emailCert(String emailKey) {
        Optional<Member> optionalMember = memberRepository.findByEmailKey(emailKey);

        if(!optionalMember.isPresent()){
            throw new MemberException(INVALID_EMAIL_KEY);
        }

        Member member = optionalMember.get();
        member.setStatus(MemberStatus.S);// 회원 활성 상태
        member.setEmailKey("null");// 이메일 키를 비활성화
        memberRepository.save(member);// 업데이트
    }

    private void sendEmail(MemberDto.Request request, String emailKey) {
        String email = request.getEmail();
        String subject = "WeatherCoder 가입을 축하드립니다. ";
        String text = "<p>WeatherCoder 가입을 축하드립니다.</p>" +
                "<p>아래 링크를 클릭하셔서 가입을 완료 하세요.</p>" +
                "<div><a target='_blank' href='htt" +
                "p://localhost:8080/email?id=" +
                emailKey + "'> 가입 완료 </a></div>";
        mailComponents.sendMail(email, subject, text);
    }

    private void secPassword(Member member) throws NoSuchAlgorithmException {
        SHA256 sha256 = new SHA256();
        String secPassword = sha256.encrypt(member.getPassword());// 기존 비밀번호 암호화
        member.setPassword(secPassword);// 암호화 된 비밀번호로 엔티티 저장
    }

    private void validCreate(MemberDto.Request request) {
        // 회원가입 및 예외 처리
        // 1. 중복된 이메일
        Optional<Member> checkEmail = memberRepository.findByEmail(request.getEmail());
        if(checkEmail.isPresent()){
            throw new MemberException(ALREADY_EXISTS_EMAIL);
        }
    }

//    // 로그인
//    public MemberDto login(String userId, String password) throws NoSuchAlgorithmException{
//        // 비밀번호 암호화
//        SHA256 sha256 = new SHA256();
//        String secPassword = sha256.encrypt(password);// 불러온 비밀번호 암호화
//
//        //  로그인 및 예외 처리
//        // 1. 존재하지 회원정보
//        Member member = memberRepository.loginProcess(userId, secPassword);
//        if(member == null){
//            throw new IllegalArgumentException("로그인 실패! : 존재하지 않는 회원정보입니다.");
//        }
//        // 2. 입력된 아이디나, 패스워드가 존재하지 않는경우
//        if(userId == null || password == null || userId.equals("") || password.equals("")){
//            throw new IllegalArgumentException("로그인 실패! : 아이디나 비밀번호를 입력해주세요.");
//        }
//
//        // dto -> entity
//        MemberDto memberDto = member.toDto();
//
//        return memberDto;
//    }
//
//    // 아이디 찾기
//    public MemberDto findUserId(String email) throws NoSuchAlgorithmException{
//        //  로그인 및 예외 처리
//        // 1. 존재하지 않는 이메일
//        Member member = memberRepository.findByEmail(email);
//        if(member == null){
//            throw new IllegalArgumentException("아이디 찾기 실패! : 존재하지 않는 이메일입니다.");
//        }
//
//        // dto -> entity
//        MemberDto memberDto = member.toDto();
//
//        return memberDto;
//    }
}