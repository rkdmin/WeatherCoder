package com.example.firstproject.dto;

import com.example.firstproject.entity.Member;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

public class MemberDto {
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Builder
    public static class Request{
        @NotBlank(message = "이메일을 입력하세요.")
        @Email(message = "이메일 형식에 맞지 않습니다.")
        private String email;// 이메일
        @NotBlank(message = "비밀번호를 입력하세요.")
        @Pattern(regexp="^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,16}$", message = "비밀번호는 영어, 숫자, 특스문자 포함해서 8~16자리 이내로 입력해주세요.")
        private String password;// 비밀번호

        private int age;// 1, 2, 3, 4, 5
        private int height;// 1, 2, 3, 4, 5
        private int weight;// 1, 2, 3, 4, 5
        private List<String> styleList;
    }
}