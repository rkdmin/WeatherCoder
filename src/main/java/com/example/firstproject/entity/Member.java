package com.example.firstproject.entity;
import com.example.firstproject.dto.MemberDto;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
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
    private String status;// S:활성 F:비활성 D:금지계정
    private String emailKey;

    private LocalDateTime regDate;
    private LocalDateTime dropDate;

    private int age;// 1, 2, 3, 4, 5
    private int height;// 1, 2, 3, 4, 5
    private int weight;// 1, 2, 3, 4, 5

    public static Member toEntity(MemberDto.Request request){
        return Member.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .status("F")// 이메일인증안해서 아직
                .emailKey(UUID.randomUUID().toString())// 이메일 키
                .regDate(LocalDateTime.now())
                .age(request.getAge())
                .height(request.getHeight())
                .weight(request.getWeight())
                .build();
    }

}