package com.example.weatherCoder.exception;


import com.example.weatherCoder.type.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentException extends RuntimeException{
    // 이런식으로해야 에러 클래스를 여러개 안만들어도댐
    private ErrorCode errorCode;
    private String errorMassage;

    public CommentException(ErrorCode errorCode){
        this.errorCode = errorCode;
        this.errorMassage = errorCode.getDescription();
    }
}
