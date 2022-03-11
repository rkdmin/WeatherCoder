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

    @Column
    private String userId;

    @Column
    private String email;

    @Column
    private String password;

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