package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@AllArgsConstructor
@ToString
@Getter
@Setter
public class MemberDto {
    private Long id;// 일련번호
    private String userId;// 아이디
    private String email;// 이메일
    private String password;// 비밀번호
    private String name;// 이름
    private int level;// 레벨
    private String status;// 회원상태 Y:정상 N:탈퇴 D:접근거부
    private int cert;// 인증여부 1:인증 0:미인증
    private LocalDateTime regDate;// 가입일
    private LocalDateTime dropDate;// 탈퇴일
}
