package com.example.weatherCoder.dto;

import com.example.weatherCoder.entity.Member;
import com.example.weatherCoder.entity.MemberStyle;
import java.time.LocalDateTime;
import lombok.*;

import javax.validation.constraints.*;
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
        @Pattern(regexp="^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,16}$", message = "비밀번호는 영어, 숫자, 특수문자 포함해서 8~16자리 이내로 입력해주세요.")
        private String password;// 비밀번호

        @NotNull(message = "성별을 선택하세요.")
        private String gender;// "남성" 혹은 "여성"

        @NotNull(message = "나이를 선택하세요.")
        private Integer age;// 1, 2, 3, 4, 5

        @NotNull(message = "키를 선택하세요.")
        private Integer height;// 1, 2, 3, 4, 5

        @NotNull(message = "뭄무게를 선택하세요.")
        private Integer weight;// 1, 2, 3, 4, 5

        @NotNull(message = "스타일을 선택하세요.")
        private List<String> styleList;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Builder
    public static class Response{
        private String email;// 이메일
        private String gender;// "남성" 혹은 "여성"
        private Integer age;// 1, 2, 3, 4, 5
        private Integer height;// 1, 2, 3, 4, 5
        private Integer weight;// 1, 2, 3, 4, 5
        private List<String> styleList;
        private LocalDateTime regDt;

        public static Response toDto(Member member, List<String> styleList) {
            return Response.builder()
                .email(member.getEmail())
                .gender(member.getGender())
                .age(member.getAge())
                .height(member.getHeight())
                .weight(member.getWeight())
                .styleList(styleList)
                .regDt(member.getRegDate())
                .build();
        }
    }
}