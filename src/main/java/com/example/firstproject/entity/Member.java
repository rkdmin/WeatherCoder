package com.example.firstproject.entity;
import com.example.firstproject.dto.CommentVO;
import com.example.firstproject.dto.MemberDto;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Member {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "아이디를 입력하세요.")
    @Column
    private String userId;

    @NotBlank(message = "이메일을 입력하세요.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    @Column
    private String email;

    @NotBlank(message = "비밀번호를 입력하세요.")
    @Pattern(regexp="[a-zA-Z1-9]{6,12}", message = "비밀번호는 영어와 숫자로 포함해서 6~12자리 이내로 입력해주세요.")
    @Column
    private String password;

    @NotBlank(message = "닉네임을 입력해주세요.")
    @Size(min = 2, max = 8, message = "닉네임을 2~8자 사이로 입력해주세요.")
    @Column
    private String nickname;

    @Column
    private int level;

    @Column
    private String status;

    @Column
    private int cert;

    @Column
    private String regDate;

    @Column
    private String dropDate;


    // entity -> dto
    public MemberDto toDto() {
        return new MemberDto(id, userId, email, password, nickname, level, status, cert, regDate, dropDate);
    }
}
