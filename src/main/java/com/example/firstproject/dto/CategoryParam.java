package com.example.firstproject.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


public class CategoryParam {
    @Getter
    @Setter
    @AllArgsConstructor
    public static class Request{
        @NotBlank(message = "이메일이 없습니다.")
        private String email;
        @NotNull(message = "계절 리스트가 없습니다.")
        private List<SeasonInfo> seasonList;
    }
}
