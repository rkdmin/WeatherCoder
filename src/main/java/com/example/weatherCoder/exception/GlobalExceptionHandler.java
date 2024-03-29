package com.example.weatherCoder.exception;


import com.example.weatherCoder.dto.ErrorResponse;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.example.weatherCoder.type.ErrorCode.*;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SuggestException.class)
    public ErrorResponse handelSuggestException(SuggestException e){
        log.error("{} is occurred", e.getErrorMassage());

        return new ErrorResponse(e.getErrorCode(), e.getErrorMassage());
    }

    @ExceptionHandler(ArticleException.class)
    public ErrorResponse handelArticleException(ArticleException e){
        log.error("{} is occurred", e.getErrorMassage());

        return new ErrorResponse(e.getErrorCode(), e.getErrorMassage());
    }

    @ExceptionHandler(CommentException.class)
    public ErrorResponse handelArticleException(CommentException e){
        log.error("{} is occurred", e.getErrorMassage());

        return new ErrorResponse(e.getErrorCode(), e.getErrorMassage());
    }


    @ExceptionHandler(MemberException.class)
    public ErrorResponse handelMemberException(MemberException e){
        log.error("{} is occurred", e.getErrorMassage());

        return new ErrorResponse(e.getErrorCode(), e.getErrorMassage());
    }

    @ExceptionHandler(CategoryException.class)
    public ErrorResponse handelCategoryException(CategoryException e){
        log.error("{} is occurred", e.getErrorMassage());

        return new ErrorResponse(e.getErrorCode(), e.getErrorMassage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErrorResponse handelDataIntegrityViolationException(SuggestException e){
        log.error("DataIntegrityViolation is occurred");

        return new ErrorResponse(INVALID_REQUEST, INVALID_REQUEST.getDescription());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ErrorResponse handelAccessDeniedException(AccessDeniedException e){
        log.error("{} is occurred", e.getMessage());

        return new ErrorResponse(INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ExceptionHandler(SignatureException.class)
    public ErrorResponse handelSignatureException(SignatureException e){
        log.error("{} is occurred", e.getMessage());

        return new ErrorResponse(INTERNAL_SERVER_ERROR, e.getMessage());
    }

//     다 거르고 마지막 예외
    @ExceptionHandler(Exception.class)
    public ErrorResponse handelException(Exception e){
        log.error("{} is occurred", e);

        return new ErrorResponse(INTERNAL_SERVER_ERROR,
                INTERNAL_SERVER_ERROR.getDescription());
    }
}
