package com.example.weatherCoder.entity;

import com.example.weatherCoder.dto.MemberDto;
import com.example.weatherCoder.type.Authority;
import com.example.weatherCoder.type.MemberStatus;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member implements UserDetails {

    @Id
    private String username;

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

    // 비밀번호 변경
    private String passwordKey;

    // 권한 정보
    private String roles;

    public static Member toEntity(MemberDto.Request request){
        return Member.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .status(MemberStatus.F)// 이메일인증안해서 아직
                .emailKey(UUID.randomUUID().toString())// 이메일 키
                .regDate(LocalDateTime.now())
                .gender(request.getGender())
                .age(request.getAge())
                .height(request.getHeight())
                .weight(request.getWeight())
                .roles(Authority.ROLE_USER.toString())
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
        roles.add(new SimpleGrantedAuthority(getRoles()));

        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}