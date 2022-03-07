package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.entity.Member;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class MemberDto {
    private Long id;// 일련번호
    private String userId;// 아이디
    private String email;// 이메일
    private String password;// 비밀번호
    private String nickname;// 닉네임
    private int level;// 레벨

    private String status;// 회원상태 Y:정상 N:탈퇴 D:접근거부

    private int cert;// 인증여부 1:인증 0:미인증

    private String regDate;// 가입일

    private String dropDate;// 탈퇴일

    public Member toEntity() {
        return new Member(id, userId, email, password, nickname, level, status, cert, regDate, dropDate);
    }
}
