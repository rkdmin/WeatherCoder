package com.example.weatherCoder.dto;

import com.example.weatherCoder.entity.Member;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ChangePasswordRequest {
    @NotBlank(message = "이메일을 입력하세요.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;// 이메일

    @NotBlank(message = "비밀번호를 입력하세요.")
    @Pattern(regexp="^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,16}$", message = "비밀번호는 영어, 숫자, 특수문자 포함해서 8~16자리 이내로 입력해주세요.")
    private String password;// 비밀번호

    @Pattern(regexp="^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,16}$", message = "비밀번호는 영어, 숫자, 특수문자 포함해서 8~16자리 이내로 입력해주세요.")
    private String newPassword;// 새로운 비밀번호
}