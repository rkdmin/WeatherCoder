package com.example.firstproject.entity;

import com.example.firstproject.dto.MemberDto;
import com.example.firstproject.type.MemberStatus;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    @Id
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;// S:활성 F:비활성 D:금지계정
    private String emailKey;

    private LocalDateTime regDate;
    private LocalDateTime dropDate;

    private String gender;// "남성", "여성"
    private int age;// 1, 2, 3, 4, 5
    private int height;// 1, 2, 3, 4, 5
    private int weight;// 1, 2, 3, 4, 5

    public static Member toEntity(MemberDto.Request request){
        return Member.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .status(MemberStatus.F)// 이메일인증안해서 아직
                .emailKey(UUID.randomUUID().toString())// 이메일 키
                .regDate(LocalDateTime.now())
                .gender(request.getGender())
                .age(request.getAge())
                .height(request.getHeight())
                .weight(request.getWeight())
                .build();
    }

}