package com.example.weatherCoder.exception;


import com.example.weatherCoder.type.ErrorCode;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryException extends RuntimeException{
    // 이런식으로해야 에러 클래스를 여러개 안만들어도댐
    private ErrorCode errorCode;
    private String errorMassage;

    public CategoryException(ErrorCode errorCode){
        this.errorCode = errorCode;
        this.errorMassage = errorCode.getDescription();
    }
}
