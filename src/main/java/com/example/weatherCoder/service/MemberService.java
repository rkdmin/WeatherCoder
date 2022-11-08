package com.example.weatherCoder.service;

import com.example.weatherCoder.components.MailComponents;
import com.example.weatherCoder.dto.ChangePasswordRequest;
import com.example.weatherCoder.dto.MemberDto;
import com.example.weatherCoder.entity.Member;
import com.example.weatherCoder.entity.MemberStyle;
import com.example.weatherCoder.exception.MemberException;
import com.example.weatherCoder.repository.MemberRepository;
import com.example.weatherCoder.repository.MemberStyleRepository;
import com.example.weatherCoder.security.SHA256;
import com.example.weatherCoder.type.ErrorCode;
import com.example.weatherCoder.type.MemberStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import org.springframework.util.StringUtils;

import static com.example.weatherCoder.type.ErrorCode.*;
import static com.example.weatherCoder.type.ErrorCode.ALREADY_EXISTS_EMAIL;
import static com.example.weatherCoder.type.ErrorCode.INVALID_EMAIL_KEY;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class MemberService{
    private final MemberRepository memberRepository;
    private final MailComponents mailComponents;
    private final MemberStyleRepository memberStyleRepository;

    private final MemberStyleService memberStyleService;

    /**
     * 회원가입
     * @param request
     * @throws NoSuchAlgorithmException
     */
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
        sendEmailFromEmail(request, member.getEmailKey());
    }

    /**
     * 회원가입 이메일 인증
     * @param emailKey
     */
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

    /**
     * 이메일 전송(이메일 인증)
     * @param request
     * @param emailKey
     */
    private void sendEmailFromEmail(MemberDto.Request request, String emailKey) {
        String email = request.getEmail();
        String subject = "WeatherCoder 가입을 축하드립니다. ";
        String text = "<p>WeatherCoder 가입을 축하드립니다.</p>" +
                "<p>아래 링크를 클릭하셔서 가입을 완료 하세요.</p>" +
                "<div><a target='_blank' href='htt" +
                "p://localhost:8080/email?id=" +
                emailKey + "'> 가입 완료 </a></div>";
        mailComponents.sendMail(email, subject, text);
    }

    /**
     * 이메일 전송(패스워드 변경)
     * @param email
     * @param newSecPassword
     * @param passwordKey
     */
    private void sendEmailFromPassword(String email, String newSecPassword, String passwordKey) {
        String subject = "WeatherCoder 비밀번호 변경 안내입니다.";
        String text = "<p>WeatherCoder 비밀번호 변경 안내입니다.</p>" +
            "<p>아래 링크를 클릭하셔서 비밀번호 변경을 완료 하세요.</p>" +
            "<div><a target='_blank' href='http://localhost:8080/passwordCert?key=" +
            passwordKey + "&pa=" + newSecPassword +"'> 변경 완료 </a></div>";
        mailComponents.sendMail(email, subject, text);
    }

    /**
     * 비밀번호 암호화
     * @param member
     * @throws NoSuchAlgorithmException
     */
    private void secPassword(Member member) throws NoSuchAlgorithmException {
        SHA256 sha256 = new SHA256();
        String secPassword = sha256.encrypt(member.getPassword());// 기존 비밀번호 암호화
        member.setPassword(secPassword);// 암호화 된 비밀번호로 엔티티 저장
    }

    /**
     * 회원가입 에러처리
     * @param request
     */
    private void validCreate(MemberDto.Request request) {
        // 1. 중복된 이메일
        Optional<Member> checkEmail = memberRepository.findByEmail(request.getEmail());
        if(checkEmail.isPresent()){
            throw new MemberException(ALREADY_EXISTS_EMAIL);
        }
    }

    /**
     * 로그인
     * @param email
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     */
    public MemberDto.Response login(String email, String password) throws NoSuchAlgorithmException {
        //  로그인 및 예외 처리
        // 1. 입력된 아이디나, 패스워드가 존재하지 않는경우
        if(!StringUtils.hasText(email) || !StringUtils.hasText(password)){
            throw new MemberException(MEMBER_EMPTY, "이메일 또는 패스워드가 일치하지 않습니다.");
        }
        // 비밀번호 암호화
        SHA256 sha256 = new SHA256();
        String secPassword = sha256.encrypt(password);// 불러온 비밀번호 암호화
        // 2. 존재하지 회원정보
        Member member = memberRepository.findByEmailAndPassword(email, secPassword).orElseThrow(
            () -> new MemberException(MEMBER_EMPTY, "이메일 또는 패스워드가 일치하지 않습니다.")
        );

        // 회원의 스타일 가져오기
        List<MemberStyle> memberStyleList = memberStyleRepository.findAllByMember_Email(email);
        List<String> styleList = new ArrayList<>();
        for(MemberStyle memberStyle: memberStyleList){
            styleList.add(memberStyle.getStyle().getStyleName());
        }

        return MemberDto.Response.toDto(member, styleList);
    }

    /**
     * 비밀번호 변경
     * @param request
     * @throws NoSuchAlgorithmException
     */
    public void changePassword(ChangePasswordRequest request) throws NoSuchAlgorithmException {
        SHA256 sha256 = new SHA256();
        String secPassword = sha256.encrypt(request.getPassword());// 기존 비밀번호 암호화
        Member member =
            memberRepository.findByEmailAndPassword(request.getEmail(), secPassword).orElseThrow(
                () -> new MemberException(MEMBER_EMPTY, "패스워드가 일치하지 않습니다.")
            );

        member.setPassword(sha256.encrypt(request.getNewPassword()));// 새로운 비밀번호 암호화
        member.setPasswordKey(UUID.randomUUID().toString());// 패스워드 키 발급
        sendEmailFromPassword(request.getEmail(), member.getPassword(), member.getPasswordKey());// 메일전송
    }

    /**
     * 회원가입 이메일 인증
     */
    public void passwordCert(String passwordKey, String secNewPassword) {
        Member member =
            memberRepository.findByPasswordKey(passwordKey).orElseThrow(
                () -> new MemberException(INVALID_PASSWORD_KEY)
            );

        member.setPassword(secNewPassword);// 새로운 패스워드 업데이트
        member.setPasswordKey(null);// 패스워드 키를 비활성화
        memberRepository.save(member);
    }
}