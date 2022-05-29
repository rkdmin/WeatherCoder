package com.example.firstproject.entity;
import com.example.firstproject.dto.MemberDto;
import lombok.*;

import javax.persistence.*;

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
    private int category;

    @Column
    private String status;

    @Column
    private String regDate;

    @Column
    private String dropDate;


    // entity -> dto
    public MemberDto toDto() {
        return new MemberDto(id, userId, email, password, nickname, status, category, regDate, dropDate);
    }
}