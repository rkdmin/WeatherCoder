package com.example.firstproject.service;
import com.example.firstproject.dto.MemberDto;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.MemberRepository;
import com.example.firstproject.security.SHA256;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;

@Service
@Slf4j
public class MemberService{

    @Autowired
    private MemberRepository memberRepository;

    // 회원가입
    @Transactional
    public MemberDto create(MemberDto memberDto) throws NoSuchAlgorithmException {
        // 회원가입 및 예외 처리
        // 1. 중복된 아이디
        Member checkId = memberRepository.findByUserId(memberDto.getUserId());// id 검색
        if(checkId != null){
            throw new IllegalArgumentException("회원가입 실패! : 중복된 아이디입니다.");
        }
        // 2. 중복된 이메일
        Member checkEmail = memberRepository.findByEmail(memberDto.getEmail());// 이메일 검색
        if(checkEmail != null){
            throw new IllegalArgumentException("회원가입 실패! : 중복된 이메일입니다.");
        }
        // 3. 중복된 닉네임
        Member checkNickname = memberRepository.findByNickname(memberDto.getNickname());// 닉네임 검색
        if(checkNickname != null){
            throw new IllegalArgumentException("회원가입 실패! : 중복된 닉네임입니다.");
        }
        // 4. 필요없는데 일련번호가 존재
        if(memberDto.getId() != null){
            throw new IllegalArgumentException("회원가입 실패! : 일련번호가 기재되어있습니다.");
        }

        // 엔티티로 변경
        Member memberEntity = memberDto.toEntity();

        // 비밀번호 암호화
        SHA256 sha256 = new SHA256();
        String secPassword = sha256.encrypt(memberEntity.getPassword());// 기존 비밀번호 암호화
        memberEntity.setPassword(secPassword);// 암호화 된 비밀번호로 엔티티 저장

        // 댓글 엔티티를 DB에 저장
        Member created = memberRepository.save(memberEntity);


        // 결과
        return created.toDto();// dto로 반환
    }

    // 로그인
    public MemberDto login(String userId, String password, HttpServletRequest request) throws NoSuchAlgorithmException{
        // 비밀번호 암호화
        SHA256 sha256 = new SHA256();
        String secPassword = sha256.encrypt(password);// 불러온 비밀번호 암호화

        //  로그인 및 예외 처리
        // 1. 존재하지 회원정보
        Member member = memberRepository.loginProcess(userId, secPassword);
        if(member == null){
            throw new IllegalArgumentException("로그인 실패! : 존재하지 않는 회원정보입니다.");
        }
        // 2. 입력된 아이디나, 패스워드가 존재하지 않는경우
        if(userId == null || password == null || userId.equals("") || password.equals("")){
            throw new IllegalArgumentException("로그인 실패! : 아이디나 비밀번호를 입력해주세요.");
        }

        // dto -> entity
        MemberDto memberDto = member.toDto();

        // 로그인 세션 처리
        HttpSession session = request.getSession();
        session.setAttribute("memberDto", memberDto);
        session.setAttribute("userId", memberDto.getUserId());
        session.setMaxInactiveInterval(60);

        return memberDto;
    }

    // 아이디 찾기
    public MemberDto findUserId(String email) throws NoSuchAlgorithmException{
        //  로그인 및 예외 처리
        // 1. 존재하지 않는 이메일
        Member member = memberRepository.findByEmail(email);
        if(member == null){
            throw new IllegalArgumentException("아이디 찾기 실패! : 존재하지 않는 이메일입니다.");
        }

        // dto -> entity
        MemberDto memberDto = member.toDto();

        return memberDto;
    }
}