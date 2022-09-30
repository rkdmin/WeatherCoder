package com.example.firstproject.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR("내부 서버 오류가 발생했습니다."),
    CLOTHES_EMPTY("적합한 옷이 없습니다."),
    INVALID_REQUEST("잘못된 요청입니다.")
    ;

    private final String description;
}
