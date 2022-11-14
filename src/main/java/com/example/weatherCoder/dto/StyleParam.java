package com.example.weatherCoder.dto;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


public class StyleParam {
    @Getter
    @Setter
    @AllArgsConstructor
    public static class Request{
        @NotBlank(message = "이메일이 없습니다.")
        private String email;
        @NotNull(message = "스타일이 없습니다.")
        private List<String> styleList;
    }
}
