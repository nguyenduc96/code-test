package com.sotatek.codingtest.helper;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHelper {

    @ExceptionHandler
    public ResultResp<?> badRequestException(HttpMessageNotReadableException exception) {
        return new ResultResp<>(HttpStatus.BAD_REQUEST);
    }

}
