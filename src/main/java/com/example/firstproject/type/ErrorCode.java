package com.example.firstproject.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR("내부 서버 오류가 발생했습니다."),
    CLOTHES_EMPTY("적합한 옷이 없습니다."),
    INVALID_REQUEST("잘못된 요청입니다."),

    FAIL_SEND_MAIL("메일 전송에 실패했습니다."),
    ALREADY_EXISTS_EMAIL("이미 존재하는 이메일입니다."),
    INVALID_EMAIL_KEY("잘못된 이메일 키 입니다."),
    MEMBER_EMPTY("회원이 없습니다."),
    STYLE_EMPTY("스타일이 없습니다.")

    ;

    private final String description;
}
